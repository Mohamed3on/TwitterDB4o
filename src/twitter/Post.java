/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter;

import com.db4o.ObjectSet;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import events.NewPostEvent;
import handlers.NewPostEventHandler;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static twitter.Twitter.db;

/**
 * @author Group
 */
public class Post {
    private String postID;
    private User poster;
    private String body;
    private HashSet<User> reposts;
    private HashSet<User> likes;
    private Date createdAt;
    private String longtiude;
    private String latitude;
    private HashSet<Reply> replies;
    private HashSet<String> hashtags;
    private HashSet<String> mentions;

    public Post(User poster, String body, String longtiude, String latitude) {
        this.poster = poster;
        this.body = body;
        this.createdAt = new Date();
        this.longtiude = longtiude;
        this.latitude = latitude;
        replies = new HashSet<>();
        hashtags = new HashSet<>();
        mentions = new HashSet<>();
        reposts = new HashSet<>();
        likes = new HashSet<>();
    }

    public Post(User poster, String body) {
        this.poster = poster;
        this.body = body;
        this.createdAt = new Date();
        replies = new HashSet<>();
        hashtags = new HashSet<>();
        mentions = new HashSet<>();
        reposts = new HashSet<>();
        likes = new HashSet<>();
        this.identifyHashtags();
        this.identifyMentions();
    }

    public Post() {
    }

    /**
     * identifies the hashtags in the post using regExp then add the new hashtags to the database linked to the posts they was in
     */
    private void identifyHashtags()
    {
        String regex="(?:(?<=\\s)|^)#(\\w*[A-Za-z_]+\\w*)";
        Pattern r = Pattern.compile(regex);
        // Now create matcher object.
        Matcher m = r.matcher(body);
        while (m.find()) 
        {
            this.hashtags.add(m.group(1));
        }
        for (String hashtag : hashtags)
        {        
            //Checks if the hashtag exists in the db
            boolean exists = false;
            ObjectSet hashtags = db.queryByExample(new Hashtag());
            while (hashtags.hasNext()) 
            {
                Hashtag currHashtag = (Hashtag) hashtags.next();
                //If the hashtag exists, only add the post to its postslist
                if (currHashtag.getHashtag().equals(hashtag)) 
                {
                    exists = true;
                    currHashtag.addPostToPostsList(this);
                    Hashtag.addHashTagToDB(currHashtag);
                }
            }
            //if no hashtag is in the database with the same name
            //create a new hashtag and add this post to its postsList
            if(!exists)
            {
                Hashtag newHashtag = new Hashtag(hashtag);
                newHashtag.addPostToPostsList(this);
                Hashtag.addHashTagToDB(newHashtag);                             
            }
        }
        addOrUpdatePostToDB(this);
    }
    
    /**
     * Identifies mentions in the post using regExp and creates a new notification to the user to be notified
     */
    private void identifyMentions(){
        String regex="(?:(?<=\\s)|^)@(\\w*[A-Za-z_]+\\w*)";
        Pattern r = Pattern.compile(regex);
        // Now create matcher object.
        Matcher m = r.matcher(body);
        while (m.find()) 
        {
            this.addMention(m.group(1));
        }
        for (String mention : this.mentions)
        {
            User userToBeNotified = User.getUserWithThisHandle(mention);
            Notification notification = new Notification(this, poster, Notification.type.MENTION, userToBeNotified);
            if(userToBeNotified != null)
            {
                userToBeNotified.addNotification(notification);
                User.addOrUpdateUserToDB(userToBeNotified);
                Notification.fireNotificationEvent(notification);
            }
        }
    }
    
    public void setPostid(String id) {
        this.postID = id;
        addOrUpdatePostToDB(this);
    }

    public String getPostID() {
        return postID;
    }

    public User getPoster() {
        return poster;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
        addOrUpdatePostToDB(this);
    }

    public HashSet<User> getReposts() {
        return reposts;
    }

    public void addRepost(User user) {
        this.reposts.add(user);
        addOrUpdatePostToDB(this);
    }
    
    public void removeRepost(User user) {
        this.reposts.remove(user);
        addOrUpdatePostToDB(this);
    }

    public HashSet<User> getLikes() {
        return likes;
    }

    /**
     * Adds a like to this post and creates a new notification the the post owner
     * @param user - user that liked this post
     */
    public void addLike(User user) 
    {
        this.likes.add(user);
        Notification newNotification = new Notification(this, user, Notification.type.LIKE, this.poster);
        this.poster.addNotification(newNotification);
        User.addOrUpdateUserToDB(this.poster);
        addOrUpdatePostToDB(this);
        Notification.fireNotificationEvent(newNotification);
    }
    
    public void removeLike(User user) {
        this.likes.remove(user);
        addOrUpdatePostToDB(this);
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        addOrUpdatePostToDB(this);
    }

    public String getLongtiude() {
        return longtiude;
    }

    public void setLongtiude(String longtiude) {
        this.longtiude = longtiude;
        addOrUpdatePostToDB(this);
    }

    public String getLatitude() {
        return latitude;
    }
    
    public void setLatitude(String latitude) {
        this.latitude = latitude;
        addOrUpdatePostToDB(this);
    }

    public HashSet<Reply> getReplies() {
        return replies;
    }

    public void addReply(Reply reply) {
        this.replies.add(reply);
        addOrUpdatePostToDB(this);
    }

    public HashSet<String> getHashtags() 
    {
        return hashtags;
    }

    public void setHashtags(HashSet<String> hashtags) {
        this.hashtags = hashtags;
        addOrUpdatePostToDB(this);
    }

    public HashSet<String> getMentions() {
        return mentions;
    }

    public void addMention(String handle) {
        //TODO get user from db using handle and add it to the hashset
        this.mentions.add(handle);
    }


    public static void addOrUpdatePostToDB(Post newPost) 
    {   
        if(newPost.getPostID() == null)
        {
            db.store(newPost);
            db.commit();
            //Sets the post id to the OID generated by the DB
            newPost.setPostid(String.valueOf(db.ext().getID(newPost)));
            db.ext().store(newPost, Integer.MAX_VALUE);
            db.commit();
//            db.ext().store(newPost.poster, Integer.MAX_VALUE);
//            db.commit();
            Post.fireNewPostEvent(newPost);
        }
        else
        {
            db.ext().store(newPost, Integer.MAX_VALUE);
            db.commit();
//            db.ext().store(newPost.poster, Integer.MAX_VALUE);
//            db.commit();
        }
    }
    
    public static void DeletePostFromDB(Post selectedPost) 
    {   
        ObjectSet result = db.queryByExample(new Post());
        while (result.hasNext())
        {
            Post currPost = (Post) result.next();
            if(currPost.getPostID().equals(selectedPost.getPostID()))
            {
                //If the post is a reply, delete it from the original post replies
                if(currPost instanceof Reply)
                {
                    Reply rep = (Reply) currPost;
                    if(rep.getOriginalPost() != null)
                        rep.getOriginalPost().getReplies().remove(rep);
                }
                else
                {
                    for (Reply r : currPost.getReplies())
                    {
                        DeletePostFromDB(r.getOriginalPost());
                        r.setOriginalPost(null);
                    }
                }
                db.delete(currPost);            
                db.commit(); 
            }
        }
    }
    public static void fireNewPostEvent(Post newPost)
    {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();
        EPStatement statement = engine.getEPAdministrator().createEPL(NewPostEvent.getStatement());
        statement.setSubscriber(new NewPostEventHandler(newPost));
        engine.getEPRuntime().sendEvent(new NewPostEvent(newPost));
    }
}

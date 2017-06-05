/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.ArrayList;
import static twitter.Twitter.db;

/**
 *
 * @author Group
 */
public class SearchResult {
    private ArrayList<Post> posts;
    private ArrayList<Hashtag> hashtags;
    private ArrayList<User> users;

    public SearchResult(String searchQuery)
    {
        setHashtags(searchQuery);
        setPosts(searchQuery);
        setUsers(searchQuery);
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * Sets the list of posts that contains the searchQuery
     * @param searchQuery
     */
    public void setPosts(String searchQuery) {
        ArrayList<Post> returnedPosts = new ArrayList<>();
        ObjectSet postsSet = db.queryByExample(new Post());
        while (postsSet.hasNext())
        {
            Post currPost = (Post) postsSet.next();
            if(currPost.getBody().contains(searchQuery))
                returnedPosts.add(currPost);
        }
        this.posts = returnedPosts;
    }

    public ArrayList<Hashtag> getHashtags() {
        return hashtags;
    }

    /**
     * Sets a list of posts that contains the passed searchQuery as a hashtag
     * @param searchQuery
     */
    public void setHashtags(String searchQuery) 
    {
        ArrayList<Hashtag> returnedHashtags = new ArrayList<>();
        ObjectSet hashTagsSet = db.queryByExample(new Hashtag());
        while (hashTagsSet.hasNext())
        {
            Hashtag currHashTag = (Hashtag) hashTagsSet.next();
            if(currHashTag.getHashtag().equalsIgnoreCase(searchQuery))
            {
                returnedHashtags.add(currHashTag);
            }
        }
        this.hashtags = returnedHashtags;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Sets a list of Users where there handle contains the searchQuery
     * @param searchQuery
     */
    public void setUsers(String searchQuery) {
        ArrayList<User> returnedUsers = new ArrayList<>();
        ObjectSet usersSet = db.queryByExample(new User());
        while (usersSet.hasNext())
        {
            User currUser = (User) usersSet.next();
            if(currUser.getHandle().contains(searchQuery))
                returnedUsers.add(currUser);
        }
        this.users = returnedUsers; 
    }
}

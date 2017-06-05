/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter;

import java.util.HashSet;
import static twitter.Twitter.db;

/**
 *
 * @author Group
 */
public class Hashtag 
{
    private String hashtag;
    private HashSet<Post> postsList;

    /**
     * Creates a new hashtag 
     * @param hashtag - hashtag value
     */
    public Hashtag(String hashtag) {
        this.hashtag = hashtag;
        this.postsList = new HashSet<>();
    }

    public Hashtag() {
    }

    /**
     * get hashtag value
     * @return hashtag value(the hashtag itself)
     */
    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    /**
     * Get posts which this hashtag is in 
     * @return posts which this hashtag is in
     */
    public HashSet<Post> getPostsList() {
        return postsList;
    }

    /**
     * Add a post to the list of post where this hashtag exists
     * @param post - post where this hashtag exists
     */
    public void addPostToPostsList(Post post) 
    {
        if(postsList == null)
            postsList = new HashSet<>();
        postsList.add(post);
    }
    
    /**
     * Add a new hashtag to DB
     * @param newHashtag - hashtag to add to db
     */
    public static void addHashTagToDB(Hashtag newHashtag)
    {
        db.ext().store(newHashtag, Integer.MAX_VALUE);
        db.commit();
    }
        
}

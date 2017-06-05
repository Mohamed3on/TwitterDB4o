/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter;

/**
 *
 * @author Group
 */
public class Repost {
    Post originalPost;
    User reposter;


    public Repost(Post originalPost, User reposter) {
        this.originalPost = originalPost;
        originalPost.addRepost(reposter);
        this.reposter = reposter;
    }

    public User getReposter() {
        return reposter;
    }

    public Post getOriginalPost() {
        return originalPost;
    }
}

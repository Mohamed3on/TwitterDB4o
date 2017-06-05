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
public class Reply extends Post {
    Post originalPost;

    public Reply(String body,Post originalPost,User poster) 
    {
        super(poster,body);
        this.originalPost = originalPost;
    }

    public Post getOriginalPost() 
    {
        return originalPost;
    }

    public void setOriginalPost(Post originalPost)
    {
        this.originalPost = originalPost;
    }
}

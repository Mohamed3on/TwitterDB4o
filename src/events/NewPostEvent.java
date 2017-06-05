/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import twitter.Post;

/**
 *
 * @author Group
 */
public class NewPostEvent 
{
    private final Post post;

    public NewPostEvent(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
    
    public static String getStatement()
    {
        String statement = "select post from NewPostEvent";
        return statement;
    }
}

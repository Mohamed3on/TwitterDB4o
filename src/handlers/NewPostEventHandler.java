/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import GUI.Home;
import events.*;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import twitter.Post;
import twitter.Twitter;
import twitter.User;

/**
 *
 * @author Group
 */
public class NewPostEventHandler 
{
    private Post post;
    
    public NewPostEventHandler(Post post) 
    {
        this.post = post;
    }
    
    public void update(Post event)
    {
        HashSet<User> followers = new HashSet<>(post.getPoster().getFollowers());
        for (User follower : followers)
        {
            for (Home frame : Twitter.openedHomeFrames)
            {
                User frameLoggedUser = frame.getLoggedUser(); 
                if(frameLoggedUser.getHandle().equals(follower.getHandle()))
                {
//                    frame.setPostList();
                    frame.refresh();
                }
            }
        }        
    }
}

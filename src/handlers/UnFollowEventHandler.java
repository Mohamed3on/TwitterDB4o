/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import GUI.Home;
import events.*;
import twitter.Twitter;
import twitter.User;

/**
 *
 * @author Group
 */
public class UnFollowEventHandler 
{
    private User user;

    public UnFollowEventHandler(User user) {
        this.user = user;
    }

    public void update(User event)
    {
        for (Home frame : Twitter.openedHomeFrames)
        {
            User frameLoggedUser = frame.getLoggedUser(); 
            if(frameLoggedUser.getHandle().equals(user.getHandle()))
            {
                frame.refresh();
            }
        }
    }
}

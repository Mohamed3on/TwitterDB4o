/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import GUI.Home;
import events.NotificationEvent;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import twitter.Notification;
import twitter.User;
import GUI.Home;
import javax.swing.JDialog;
import javax.swing.JFrame;
import twitter.Twitter;

/**
 *
 * @author Group
 */
public class NotificationEventHandler 
{
    private Notification notification;
    
    public NotificationEventHandler(Notification notification) 
    {
        this.notification = notification;
    }
    
    public void update(Notification event)
    {
        User notifiedUser = notification.getNotifiedUser();
        for (Home frame : Twitter.openedHomeFrames)
        {
            User frameLoggedUser = frame.getLoggedUser(); 
            if(frameLoggedUser.getHandle().equals(notifiedUser.getHandle()))
            {
//              frame.setNumbers();
                frame.refresh();
            }
        }
    }
}

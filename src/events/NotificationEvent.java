/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import twitter.Notification;

/**
 *
 * @author Group
 */
public class NotificationEvent 
{
    private final Notification notification;

    public NotificationEvent(Notification notification) {
        this.notification = notification;
    }

    public Notification getNotification() {
        return notification;
    }
    
    public static String getStatement()
    {
        String statement = "select notification from NotificationEvent";
        return statement;
    }
}

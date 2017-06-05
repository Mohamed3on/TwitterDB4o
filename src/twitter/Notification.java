/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import events.NotificationEvent;
import handlers.NotificationEventHandler;

/**
 *
 * @author Group
 */
public class Notification {
    Post notifyingPost;
    User notifyingUser;
    User notifiedUser;
    public enum type{LIKE,MENTION,REPOST,FOLLOW};
    type notificationType;
    EPServiceProvider engine;

    public Notification(Post notifyingPost, User notifyingUser, type type, User notifiedUser) 
    {
        this.notifyingPost = notifyingPost;
        this.notifyingUser = notifyingUser;
        this.notifiedUser=notifiedUser;
        this.notificationType=type;
    }
    
    public Notification(User notifyingUser, type type, User notifiedUser) 
    {
        //this.notifyingPost = null;
        this.notifyingUser = notifyingUser;
        this.notifiedUser=notifiedUser;
        this.notificationType=type;
    }
    
    public Post getNotifyingPost() {
        return notifyingPost;
    }

    public User getNotifyingUser() {
        return notifyingUser;
    }

    public User getNotifiedUser() {
        return notifiedUser;
    }
    
    public type getNotificationType() {
        return notificationType;
    }
    
    public static void fireNotificationEvent(Notification newNotification)
    {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();
        EPStatement statement = engine.getEPAdministrator().createEPL(NotificationEvent.getStatement());
        statement.setSubscriber(new NotificationEventHandler(newNotification));
        engine.getEPRuntime().sendEvent(new NotificationEvent(newNotification));
    }
}

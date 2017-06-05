/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import twitter.User;

/**
 *
 * @author Group
 */
public class FollowEvent 
{
    private User user;

    public FollowEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    
    public static String getStatement()
    {
        String statement = "select user from FollowEvent";
        return statement;
    }
}

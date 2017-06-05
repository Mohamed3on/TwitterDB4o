/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter;

import GUI.Home;
import java.awt.Frame;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Group
 */
@Deprecated
public class AccountThread extends Thread
{
    private User loggedUser;
    private Home loggedHome;
    public User toFollow;
    public AccountThread(User loggedUser, Home loggedHome) throws NoSuchAlgorithmException, IOException
    {
        this.loggedUser = loggedUser;
        this.loggedHome = loggedHome;
    }
    @Override
    public void run()
    {
        Post p = new Post(loggedUser, "Test");
        Post.addOrUpdatePostToDB(p);
        loggedUser.addFollowing(toFollow);
        loggedHome.refresh();
    }   
}

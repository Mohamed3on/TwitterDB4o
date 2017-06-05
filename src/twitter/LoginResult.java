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
public class LoginResult {
    private User user;
    private boolean exist;

    public LoginResult()
    {
        
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean doesExist() {
        return exist;
    }

    public void setExist(boolean result) {
        this.exist = result;
    }
    
}

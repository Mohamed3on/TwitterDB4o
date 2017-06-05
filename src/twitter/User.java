package twitter;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import events.FollowEvent;
import events.UnFollowEvent;
import handlers.FollowEventHandler;
import handlers.UnFollowEventHandler;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import static twitter.Twitter.db;


/**
 * @author Group
 */
public class User {
    private String handle;
    private String name;
    private String avatarPath;
    private Date createdAt;
    private String email;
    private String bio;
    private HashSet<User> followers;
    private HashSet<User> followings;
    private boolean active;
    private String userID;
    private String password;
    private Stack<Notification> notifications;
    public User(String handle, String name, String email, String password) throws NoSuchAlgorithmException, IOException//Set Bio default value 
    {
        this.password = hashPassword(password);
        this.handle = handle;
        this.name = name;
        this.email = email;
        bio = "";
        followers = new HashSet<>();
        followings = new HashSet<>();
        createdAt = new Date();
        active = true;
        notifications = new Stack<>();
        avatarPath = "images/default.jpg";
        userID = null;
    }

    public User() {
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        //Check if it was unique first
        this.handle = handle;
        addOrUpdateUserToDB(this);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        addOrUpdateUserToDB(this);
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(File avatarPath) throws FileNotFoundException, IOException {
        //Delete Old Image from App filesystem
        File oldImage = new File(this.avatarPath);
        if (!this.avatarPath.equals("images/default.jpg"))
            oldImage.delete();
        //Add Image to App filesystem
        File file = new File("images/" + userID + avatarPath.getName());
        Files.copy(avatarPath.toPath(), file.toPath());
        this.avatarPath = file.getAbsolutePath();
        addOrUpdateUserToDB(this);
    }
    
    public int getNumOfPosts()
    {
        int numOfPosts = 0;
        ObjectSet postsSet = db.queryByExample(new Post());
        while (postsSet.hasNext())
        {
            Post currPost = (Post) postsSet.next();
            if(currPost.getPoster().getUserID().equals(this.userID))
                numOfPosts++;
        }
        return numOfPosts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        //Check uniqueness
        this.email = email;
        addOrUpdateUserToDB(this);
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
        addOrUpdateUserToDB(this);
    }

    public HashSet<User> getFollowers() {
        return followers;
    }

    public void addFollower(User follower) 
    {
        if (this.followers.add(follower))
        {
            Notification newNotification = new Notification(follower, Notification.type.FOLLOW, this);
            this.addNotification(newNotification);
            addOrUpdateUserToDB(this);
            fireFollowEvent(follower);
        }
        else
            JOptionPane.showMessageDialog(null, "You already follow this user", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void removeFollower(User follower) {
        if (this.followers.remove(follower))
        {
            addOrUpdateUserToDB(this);
            fireUnFollowEvent(follower);
        }
        else
            JOptionPane.showMessageDialog(null, "Something Wrong Happened!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public HashSet<User> getFollowings() {
        return followings;
    }

    public void addFollowing(User following) {
        if (this.followings.add(following))
        {
            addOrUpdateUserToDB(this);
            fireFollowEvent(following);
        }
        else
            JOptionPane.showMessageDialog(null, "You already follow this user", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void removeFollowing(User following) {
        if (this.followings.remove(following))
        {
            addOrUpdateUserToDB(this);
            fireUnFollowEvent(following);
        }
        else
            JOptionPane.showMessageDialog(null, "Something Wrong Happened!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        addOrUpdateUserToDB(this);
    }

    public Stack<Notification> getNotifications() {
        return notifications;
    }

//    public void addLike(Post likedPost, User likingUser) {
//        Notification n = new Notification(likedPost, likingUser, Notification.type.LIKE, this);
//        this.addNotification(n);
//    }
//    
//    public void addRepost(Post repostedPost, User repostingUser) {
//        Notification n = new Notification(repostedPost, repostingUser, Notification.type.REPOST, this);
//        this.addNotification(n);
//    }
//    
//    public void addMention(Post MentioningPost, User MentioningUser) {
//        Notification n = new Notification(MentioningPost, MentioningUser, Notification.type.MENTION, this);
//        this.addNotification(n);
//    }

    public void addNotification(Notification notification) {
        this.notifications.push(notification);
        addOrUpdateUserToDB(this);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = hashPassword(password);
        addOrUpdateUserToDB(this);
    }
    
    public static User getUserWithThisHandle(String handle) 
    {
        ObjectSet userSet = db.queryByExample(new User());
        while (userSet.hasNext())
        {
            User currUser = (User) userSet.next();
            if(currUser.getHandle().equals(handle))
                return currUser;
        }
        return null;
    }
    
    public static void addOrUpdateUserToDB(User user) {
        if (user.getUserID() == null) 
        {
            db.store(user);
            db.commit();
            user.setUserID(String.valueOf(db.ext().getID(user)));
            db.ext().store(user, Integer.MAX_VALUE);
            db.commit();
        }
        else 
        {
            db.ext().store(user, Integer.MAX_VALUE);
            db.commit();
        }
    }

    public static LoginResult login(String handle, String password) throws NoSuchAlgorithmException 
    {
        LoginResult loginResult = new LoginResult();
        String hashedPassword = hashPassword(password);
        ObjectSet Users = db.queryByExample(new User());
        while (Users.hasNext()) 
        {
            User currUser = (User) Users.next();
            if (currUser.handle.equals(handle) && currUser.password.equals(hashedPassword)) 
            {
                loginResult.setExist(true);
                loginResult.setUser(currUser);
                break;
            }
            else 
            {
                loginResult.setExist(false);
                loginResult.setUser(null);
            }
        }
        return loginResult;
    }

    public static final String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest); //hashed password
    }

    /**
     * Gets the posts of the user himself/herself
     * @return the posts of the user himself/herself
     */
    public ArrayList<Post> getUserPosts() {
        User THIS = this;
        ArrayList<Post> posts = new ArrayList<>();
        List<Post> allPosts = db.query(new Predicate<Post>() {
            @Override
            public boolean match(Post candidate) {
                return candidate.getPoster().getUserID().equals(THIS.userID);
            }
        });
        for (Post post : allPosts) {
            posts.add(post);
        }
        return posts;
    }

    /**
     * Returns the posts of the user and those who he/she follows
     * @return the posts of the user and those who he/she follows
     */
    public ArrayList<Post> getUserTimeLine() {
        User THIS = this;
        ArrayList<Post> posts = new ArrayList<>();
        List<Post> allPosts = db.query(new Predicate<Post>() {
            @Override
            public boolean match(Post candidate) {
                return candidate.getPoster().getUserID().equals(THIS.userID) || THIS.followings.contains(candidate.getPoster());
            }
        });
        for (Post post : allPosts) {
            posts.add(post);
        }
        return posts;
    }

    public boolean isFollowing(User user) {
        return this.followers.contains(user);
    }

    public boolean isFollowerOf(User user) {
        return this.followings.contains(user);
    }

    public boolean equals(User usr) {
        return this.userID.equals(usr.userID);
    }
    
    public static boolean checkIfThisHandleIsInDB(String handle) 
    {
        ObjectSet userSet = db.queryByExample(new User());
        while (userSet.hasNext())
        {
            User currUser = (User) userSet.next();
            if(currUser.getHandle().equals(handle))
                return true;
        }
        return false;
    }
    
    public static boolean checkIfThisEmailIsInDB(String email) 
    {
        ObjectSet userSet = db.queryByExample(new User());
        while (userSet.hasNext())
        {
            User currUser = (User) userSet.next();
            if(currUser.getEmail().equals(email))
                return true;
        }
        return false;
    }
    
    public static void fireFollowEvent(User followedUser)
    {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();
        EPStatement statement = engine.getEPAdministrator().createEPL(FollowEvent.getStatement());
        statement.setSubscriber(new FollowEventHandler(followedUser));
        engine.getEPRuntime().sendEvent(new FollowEvent(followedUser));
    }
    
    public static void fireUnFollowEvent(User unfollowedUser)
    {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();
        EPStatement statement = engine.getEPAdministrator().createEPL(UnFollowEvent.getStatement());
        statement.setSubscriber(new UnFollowEventHandler(unfollowedUser));
        engine.getEPRuntime().sendEvent(new UnFollowEvent(unfollowedUser));
    }

}
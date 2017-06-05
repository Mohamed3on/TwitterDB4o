package twitter;
import GUI.Home;
import com.db4o.*;
import esper.config.Config;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import org.apache.log4j.BasicConfigurator;
/**
 *
 * @author Group
 */
public class Twitter {
    public static ObjectContainer db;
    public static ArrayList<Home> openedHomeFrames;
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InterruptedException 
    {
        BasicConfigurator.configure();
        openedHomeFrames = new ArrayList<>();
        try
        {   
            new Config().init();
            db = Db4oEmbedded.openFile("database.yap");
//            Login l = new Login();
//            l.setVisible(true);
            User userOne = new User("a", "a", "a", "a");
            User.addOrUpdateUserToDB(userOne);
            LoginResult result = User.login("a", "a");
            userOne = result.getUser();
            
            
            User userTwo = new User("b", "b", "b", "b");
            User.addOrUpdateUserToDB(userTwo);
            result = User.login("b", "b");
            userTwo = result.getUser();
            
            
            Home home = new Home(userOne);
            home.setVisible(true);
            home.setLocation(0, 120);
            openedHomeFrames.add(home);
            Home home2 = new Home(userTwo);
            home2.setVisible(true);
            home2.setLocation(700, 120);
            openedHomeFrames.add(home2);            
//            AccountThread t1 = new AccountThread(userOne, home);
//            t1.toFollow = userTwo;
//            t1.start();
//            Thread.sleep(1000);
//            AccountThread t2 = new AccountThread(userTwo, home2);
//            t2.toFollow = userOne;
//            t2.start();
        }
       finally
        {

        }
    }
    
}

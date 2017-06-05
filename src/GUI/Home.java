/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.Profile.Profilepostowner;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import twitter.Post;
import twitter.SearchResult;
import twitter.Twitter;
import twitter.User;

/**
 *
 * @author Group
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    private User loggedUser;
    private int initialNumOfNotifications;
    public Home() {        
        initComponents();
        this.setLocationRelativeTo(null);
    }
    public Home(User loggedUser) {        
        this.loggedUser = loggedUser;
        Profilepostowner = loggedUser.getName(); // for the delete button to be visible or invisible 
        initComponents();
        this.setLocationRelativeTo(null);
        // methods call
        initialNumOfNotifications = loggedUser.getNotifications().size();
        setPictures();
        setBio();
        setNumbers();
        createLabelsActions();
        this.flagPic.setVisible(false);
    }
    
    public User getLoggedUser()
    {
        return loggedUser;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profilePicLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ArrayList <Post> posts = loggedUser.getUserTimeLine();
        Collections.reverse(posts);
        final String [] postsBodies = new String [posts.size()];
        for(int i=0;i<posts.size();i++)
        {
            postsBodies[i]="@"+posts.get(i).getPoster().getHandle()+" | "+posts.get(i).getBody();
        }
        postsList = new javax.swing.JList<>(postsBodies);
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        postArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        bioArea = new javax.swing.JTextArea();
        handleLabel = new javax.swing.JLabel();
        numOfFollowers = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        numOfFollowings = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        numOfPosts = new javax.swing.JLabel();
        editBio = new javax.swing.JButton();
        Profile = new javax.swing.JButton();
        editProfilePicBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        searchFld = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        notifiLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        flagPic = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Timeline");

        profilePicLabel.setText("ProfilePic");

        postsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = postsBodies;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        postsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                postsListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(postsList);

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel1.setText("Posts");

        postArea.setColumns(20);
        postArea.setRows(5);
        postArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                postAreaKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(postArea);

        jButton1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jButton1.setText("Post");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bioArea.setEditable(false);
        bioArea.setColumns(20);
        bioArea.setRows(5);
        jScrollPane3.setViewportView(bioArea);

        handleLabel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        handleLabel.setText("Handle");

        numOfFollowers.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        numOfFollowers.setText("0");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setText("Followers");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel4.setText("Followings");

        numOfFollowings.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        numOfFollowings.setText("0");

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel6.setText("Posts");

        numOfPosts.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        numOfPosts.setText("0");

        editBio.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        editBio.setText("Edit");
        editBio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBioActionPerformed(evt);
            }
        });

        Profile.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        Profile.setText("Profile");
        Profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfileActionPerformed(evt);
            }
        });

        editProfilePicBtn.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        editProfilePicBtn.setText("Edit");
        editProfilePicBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProfilePicBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("142");

        searchFld.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        searchBtn.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Refresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        notifiLabel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        notifiLabel.setText("0");
        notifiLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notifiLabelMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel7.setText("Notifications");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchFld)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(profilePicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(handleLabel)
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(numOfFollowers))
                                        .addGap(14, 14, 14)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(numOfFollowings))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel6)
                                            .addComponent(numOfPosts))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(notifiLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(flagPic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(editBio))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(editProfilePicBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Profile, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel2)
                            .addComponent(jButton4))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(searchBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchFld, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Profile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(profilePicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(handleLabel)
                                            .addComponent(numOfFollowers))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(editBio)
                                        .addGap(20, 20, 20))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(numOfPosts)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel6))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(numOfFollowings)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel4)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(notifiLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(flagPic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)))))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(editProfilePicBtn))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton4)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Update current Bio 
    private void editBioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBioActionPerformed
        // TODO add your handling code here:
        String newBio = JOptionPane.showInputDialog(this, "Enter New Bio:");
        loggedUser.setBio(newBio); // update the bio of the current logged in user 
        this.dispose();
        Home home = new Home(loggedUser);
        home.setVisible(true);
        Twitter.openedHomeFrames.add(home);

    }//GEN-LAST:event_editBioActionPerformed

    // Reload Profile JFrame of the logged in user 
    private void ProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfileActionPerformed
        this.dispose();
        Profile profile = new Profile(loggedUser,loggedUser);
        profile.setVisible(true);
    }//GEN-LAST:event_ProfileActionPerformed

    private void editProfilePicBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProfilePicBtnActionPerformed
        
        // Open File Browser For the user 
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) 
        {
            File selectedFile = jfc.getSelectedFile();
            try // try to set the selected image as an avatar for the user 
            {
                loggedUser.setAvatarPath(selectedFile);
            } 
            catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Failure", JOptionPane.ERROR_MESSAGE);
            }
            // Refresh Home page in order to see the new Picture 
            this.dispose();
            Home home = new Home(loggedUser);
            home.setVisible(true);
            Twitter.openedHomeFrames.add(home);

        }
        else // If the user chose a wrong file 
            JOptionPane.showMessageDialog(null, "Something went wrong while choosing new avatar!", "Failure", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_editProfilePicBtnActionPerformed

    // method to calculate The number of characters typed in a post with maximum limit of 142 character 
    private void postAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_postAreaKeyTyped
       
        int numTyped = postArea.getText().length();
        int numOfChar = 142;
        jLabel2.setText(String.valueOf(numOfChar-numTyped));
    }//GEN-LAST:event_postAreaKeyTyped

   
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String post = postArea.getText();
        if(postArea.getText().length() > 142) // in case the limit is exceeded 
            JOptionPane.showMessageDialog(null, "Post can not be greater than 142 characters", "Limit Exceeded!",JOptionPane.ERROR_MESSAGE);
        else
        {
            // else add the post 
            Post newPost = new Post(loggedUser, post);
            Post.addOrUpdatePostToDB(newPost);
            refresh(); // Refresh to see the new Timeline 
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // if the user double clicked on the post in the PostsList, show the selected post 
    private void postsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_postsListMouseClicked
        
        if (evt.getClickCount() == 2) 
        {
            ArrayList <Post> postList = loggedUser.getUserTimeLine();
            Collections.reverse(postList);
            Post selectedPost = postList.get(postsList.getSelectedIndex());
            ShowPost showPost = new ShowPost(selectedPost,loggedUser);
            showPost.checkIfOwnPost() ; // check if the selected post can be deleted by this user 
            showPost.setVisible(true);            
        }
        
    }//GEN-LAST:event_postsListMouseClicked

    // Search in the database by the entered string in Posts, users and hashtags 
    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        
        SearchResult searchResult = new SearchResult(searchFld.getText());
        Results results = new Results(searchResult, loggedUser);
        results.setVisible(true);
    }//GEN-LAST:event_searchBtnActionPerformed

    // Logout Method to reload Login Jframe 
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        this.dispose();
        Login l = new Login() ; 
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Refresh Home to see the new Stuff 
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         refresh();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void notifiLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notifiLabelMouseClicked
        // TODO add your handling code here:
        ShowNotifications sn = new ShowNotifications(loggedUser);
        sn.setVisible(true);
        //notifiLabel.setText(notifiLabel.);
        initialNumOfNotifications++;
        checkIfThereNewNotification();
        
    }//GEN-LAST:event_notifiLabelMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }
    
    private void setPictures()
    {
        // Set the Picture Label Area with the image of the Avatar Path Saved Before 
        ImageIcon profilePic = new ImageIcon(loggedUser.getAvatarPath());
        Image image = profilePic.getImage();
        Image scaledProfilePic = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale the image  
        profilePic = new ImageIcon(scaledProfilePic); 
        profilePicLabel.setIcon(profilePic);
        
        ImageIcon flagPic = new ImageIcon("images/reddot.png");
        Image flagimage = flagPic.getImage();
        Image scaledflagPic = flagimage.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH); // scale the image  
        flagPic = new ImageIcon(scaledflagPic); 
        this.flagPic.setIcon(flagPic);
    }
    
    private void setBio() // Change user bio 
    {
        bioArea.setText(loggedUser.getBio());
    }
    
    private void setNumbers() // set  number of post , number of followers ,number of followings and handle string 
    {
        notifiLabel.setText(String.valueOf(loggedUser.getNotifications().size()));
        numOfPosts.setText(String.valueOf(loggedUser.getNumOfPosts()));
        numOfFollowers.setText(String.valueOf(loggedUser.getFollowers().size()));
        numOfFollowings.setText(String.valueOf(loggedUser.getFollowings().size()));
        handleLabel.setText(loggedUser.getHandle());
    }

    public void setPostList()
    {
        ArrayList <Post> posts = loggedUser.getUserTimeLine();
        Collections.reverse(posts);
        final String [] postsBodies = new String [posts.size()];
        for(int i=0;i<posts.size();i++)
        {
            postsBodies[i]="@"+posts.get(i).getPoster().getHandle()+" | "+posts.get(i).getBody();
        }
        postsList = new javax.swing.JList<>(postsBodies);

        postsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = postsBodies;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });

        postsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                postsListMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(postsList);
    }
    
    private void createLabelsActions()
    {
        Home THIS = this;
        // if the user pressed on any of the labels it opens its Jframe 
        profilePicLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Profile profile = new Profile(loggedUser,loggedUser);
                profile.setVisible(true);
                THIS.dispose();
            }
        });
        numOfPosts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Profile profile = new Profile(loggedUser,loggedUser);
                profile.setVisible(true);
                THIS.dispose();
            }
        });
        numOfFollowers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ShowFollowers showFollowers = new ShowFollowers(loggedUser,loggedUser);
                showFollowers.setVisible(true);
                refresh();
                //THIS.dispose();
            }
        });
        numOfFollowings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ShowFollowings showFollowings = new ShowFollowings(loggedUser,loggedUser);
                showFollowings.setVisible(true);
                refresh();
                //THIS.dispose();
            }
        });
    }
    
    public void refresh() // Reload Home Page 
    {
        
        setPictures();
        setBio();
        setNumbers();
        createLabelsActions();
        setPostList();
        checkIfThereNewNotification();
//        Point location = this.getLocation();
//        Home home = new Home(loggedUser);
//        home.setVisible(true);
//        home.setLocation(location);
//        Twitter.openedHomeFrames.add(home);
//        this.dispose();
    }
    
    public void checkIfThereNewNotification()
    {
//        System.out.println("I'm here");
//        for (StackTraceElement e : Thread.currentThread().getStackTrace())
//            System.out.println(e.getMethodName());
//        System.out.println("Init: " + initialNumOfNotifications);
//        System.out.println("Label: "+ Integer.parseInt(notifiLabel.getText()));
        
        if (initialNumOfNotifications < Integer.parseInt(notifiLabel.getText()))
        {
            flagPic.setVisible(true);
            //initialNumOfNotifications = Integer.parseInt(notifiLabel.getText());
        }
        else
        {
            flagPic.setVisible(false);
            //initialNumOfNotifications = Integer.parseInt(notifiLabel.getText());
        }
        
        //initialNumOfNotifications = loggedUser.getNotifications().size();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Profile;
    private javax.swing.JTextArea bioArea;
    private javax.swing.JButton editBio;
    private javax.swing.JButton editProfilePicBtn;
    private javax.swing.JLabel flagPic;
    private javax.swing.JLabel handleLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private static javax.swing.JLabel jLabel3;
    private static javax.swing.JLabel jLabel4;
    private static javax.swing.JLabel jLabel6;
    private static javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JLabel notifiLabel;
    private javax.swing.JLabel numOfFollowers;
    private javax.swing.JLabel numOfFollowings;
    private javax.swing.JLabel numOfPosts;
    private javax.swing.JTextArea postArea;
    private javax.swing.JList<String> postsList;
    private javax.swing.JLabel profilePicLabel;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchFld;
    // End of variables declaration//GEN-END:variables
}

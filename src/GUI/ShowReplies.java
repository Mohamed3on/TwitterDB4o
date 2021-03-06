/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import static GUI.ShowPost.selectedPost;
import com.db4o.ObjectSet;
import java.awt.Frame;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import twitter.Post;
import twitter.Reply;
import twitter.Twitter;
//import static twitter.Twitter.db;
import twitter.User;

/**
 *
 * @author group7
 */
public class ShowReplies extends javax.swing.JFrame {

    /**
     * Creates new form ShowReplies
     */
    private Post postToShowRepliesTo;
    private User loggedUser;
    public ShowReplies() {
        initComponents();
    }
    
    public ShowReplies(Post postToShowRepliesTo, User loggedUser) {
        this.loggedUser = loggedUser;
        this.postToShowRepliesTo = postToShowRepliesTo;
        initComponents();
        this.originalPostArea.setText(postToShowRepliesTo.getBody());        
        handleLabel.setText(postToShowRepliesTo.getPoster().getHandle());
        setProfilePic();
        setRepliesList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ListOfReplies = new javax.swing.JList<>();
        ReplyBody = new javax.swing.JTextField();
        ReplyButton = new javax.swing.JButton();
        posterAvatarLabel = new javax.swing.JLabel();
        handleLabel = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        originalPostArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Replies");

        ListOfReplies.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(ListOfReplies);

        ReplyBody.setToolTipText("");

        ReplyButton.setText("Reply");
        ReplyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReplyButtonActionPerformed(evt);
            }
        });

        posterAvatarLabel.setText("PosterAvatar");
        posterAvatarLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                posterAvatarLabelMouseClicked(evt);
            }
        });

        handleLabel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        handleLabel.setText("Handle");
        handleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleLabelMouseClicked(evt);
            }
        });

        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        originalPostArea.setEditable(false);
        originalPostArea.setColumns(20);
        originalPostArea.setRows(5);
        jScrollPane2.setViewportView(originalPostArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(handleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(posterAvatarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ReplyBody)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ReplyButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BackButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posterAvatarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(handleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(ReplyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BackButton))
                    .addComponent(ReplyBody))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void posterAvatarLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_posterAvatarLabelMouseClicked
        
       
    }//GEN-LAST:event_posterAvatarLabelMouseClicked

    private void handleLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_handleLabelMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_handleLabelMouseClicked

    
    private void ReplyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReplyButtonActionPerformed
        
        String Rep = "@"+postToShowRepliesTo.getPoster().getHandle()+" "+ReplyBody.getText();
        Reply R = new Reply(Rep , postToShowRepliesTo ,postToShowRepliesTo.getPoster());
        postToShowRepliesTo.addReply(R);
//        Login.loggedUser.incrementNumOfPosts();
        this.dispose();
        ShowReplies sr = new ShowReplies(postToShowRepliesTo,loggedUser);
        sr.setVisible(true);
        sr.setLocationRelativeTo(null);
    }//GEN-LAST:event_ReplyButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        // TODO add your handling code here:
        Frame[] Al = JFrame.getFrames();
        for (int i = 0; i < Al.length; i++) {
            Al[i].dispose();
        }
        Home h = new Home(loggedUser) ; 
        h.setVisible(true);
        h.setLocationRelativeTo(null);
        Twitter.openedHomeFrames.add(h);

    }//GEN-LAST:event_BackButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    private void setProfilePic()
    {
        ImageIcon profilePic = new ImageIcon(postToShowRepliesTo.getPoster().getAvatarPath());
        Image image = profilePic.getImage();
        Image scaledProfilePic = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale the image  
        profilePic = new ImageIcon(scaledProfilePic); 
        posterAvatarLabel.setIcon(profilePic);
    }
    
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
            java.util.logging.Logger.getLogger(ShowReplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowReplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowReplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowReplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowReplies().setVisible(true);
            }
        });
    }

    private void setRepliesList()
    {
        List<Post> RepliesList = new ArrayList<>(postToShowRepliesTo.getReplies());
        String [] Replies = new String [RepliesList.size()];       
        for(int i=0;i<RepliesList.size();i++)
        {
            Replies[i]=RepliesList.get(i).getBody();
        }
        ListOfReplies = new javax.swing.JList<>(Replies);

        ListOfReplies.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = Replies;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
            
        });

        jScrollPane1.setViewportView(ListOfReplies);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JList<String> ListOfReplies;
    private javax.swing.JTextField ReplyBody;
    private javax.swing.JButton ReplyButton;
    public javax.swing.JLabel handleLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea originalPostArea;
    private javax.swing.JLabel posterAvatarLabel;
    // End of variables declaration//GEN-END:variables
}

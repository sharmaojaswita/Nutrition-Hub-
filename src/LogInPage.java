
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LogInPage extends javax.swing.JFrame {

    JLabel lb;
    public LogInPage() {
        lb=new JLabel();
        initComponents();
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        int h=(int)d.getHeight();
        int w=(int)d.getWidth();
        lb.setBounds(0, 0, w, h);
        ImageIcon ic =new ImageIcon("src/myuploads/nh15.jpeg");
        Image img=ic.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon im=new ImageIcon(img);
        lb.setIcon(im);
        add(lb);
        setSize(800, 500);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Phosphate", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("                LogIn Page ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(240, 30, 380, 50);

        jTextField1.setFont(new java.awt.Font("Microsoft YaHei Light", 3, 14)); // NOI18N
        getContentPane().add(jTextField1);
        jTextField1.setBounds(440, 129, 170, 30);

        jLabel2.setFont(new java.awt.Font("Papyrus", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Email");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(300, 133, 60, 30);

        jLabel3.setFont(new java.awt.Font("Papyrus", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(300, 180, 110, 38);
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(440, 180, 170, 30);

        jButton1.setFont(new java.awt.Font("Adelle Sans Devanagari", 2, 18)); // NOI18N
        jButton1.setText("LogIn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(380, 250, 110, 41);

        jButton2.setFont(new java.awt.Font("Helvetica Neue", 2, 18)); // NOI18N
        jButton2.setText("Forgot Password");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(340, 310, 190, 29);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
String Email=jTextField1.getText();
String Password=jPasswordField1.getText();
try
{
    ResultSet rs= DBloader.executeSql("select * from user where Email='"+Email+"'and Password='"+Password+"'");
            
          if(rs.next())
          {
            JOptionPane.showMessageDialog(this,"LogIn Successful");
            global.email=Email;
            User_Home us=new User_Home();
            dispose();
            
          }
          else
          {
              JOptionPane.showMessageDialog(this,"LogIn Failed");
          }
}
catch(Exception e)
{
    e.printStackTrace();
}

        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        
               // TODO add your handling code here:
               ForgotPassword obj = new ForgotPassword();
               
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(LogInPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogInPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogInPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogInPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogInPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    }


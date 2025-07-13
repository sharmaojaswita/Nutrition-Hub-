
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;
import javax.swing.JLabel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author vanshikajoshi
 */
public class Favourites extends javax.swing.JFrame {
 JLabel lb;
    /**
     * Creates new form 
     */
    public Favourites() {
         lb=new JLabel();
        initComponents();
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        int h=(int)d.getHeight();
        int w=(int)d.getWidth();
        lb.setBounds(0, 0, w, h);
        ImageIcon ic =new ImageIcon("src/myuploads/nh12.jpeg");
        Image img=ic.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon im=new ImageIcon(img);
        lb.setIcon(im);
        add(lb);
        setSize(w, h);
        setVisible(true);
        getFavourites();
    }
void getFavourites()
    {
    int x=10,y=10;
    jPanel1.removeAll();
    try
        {
            System.out.println(global.email);
        ResultSet rs=DBloader.executeSql("select * from favorites where email='"+global.email+"'");
        while(rs.next())
        {
        String name1=rs.getString("recipe");
        int Id=rs.getInt("id");
        name1=name1.replace(" ", "%20");
         HttpResponse<String> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/complexSearch?query=" + name1)
                        .header("X-RapidAPI-Key", "652fb35be6msh43c979dd6e258dap1ede35jsn29e136726656")
                        .header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                        .asString();
         if(response.getStatus()==200)
         {
         String ans=response.getBody();
             System.out.println(ans);
             JSONObject mainobj=new JSONObject(ans);
             if(mainobj.getInt("totalResults")==0)
             {
             continue;
             }
             JSONArray arr=mainobj.getJSONArray("results");
             JSONObject obj=(JSONObject)arr.get(0);
             SearchRecipe rec=new SearchRecipe();
                   
             int id=(int)obj.get("id");
                     
                    
            String name=obj.get("title").toString();
                     String title=name.replace(" ", "%20");
                     rec.lb1.setText(name);
                     
                     if(obj.has("image"))
                     {
                     String photo=obj.get("image").toString();
                         System.out.println(photo);
                            try
                            {
                             BufferedImage bi = ImageIO.read(new URL(photo));

                            bi = scale(bi, rec.lb2.getWidth(), rec.lb2.getHeight());
                            rec.lb2.setIcon(new ImageIcon(bi));
                            
                            }
                            catch(Exception ex)
                            {
                            ex.printStackTrace();
                            }
                     }
                    rec.bt1.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                            ingredients ig=new ingredients(id);
                             dispose();
                         }
                    });
                    rec.bt2.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             Details de=new Details(id);
                             dispose();
                         }
                    });
                    rec.bt3.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             nutrition nut=new nutrition(id);
                             dispose();
                         }
                    });
                    
                    rec.bt5.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             
                             cost_of_recipe cs=new cost_of_recipe(id);
                             dispose();
                         }
                    });
                    
                    rec.bt6.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             Taste t1=new Taste(id);
                             dispose();
                             
                         }
                    });
                    
                    rec.bt7.setText("Remove From Favorite");
                    
                    rec.bt7.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try    
                 {
                    ResultSet rs=DBloader.executeSql("select * from favorites where recipe_id="+id);
                    if(rs.next())
                    {
                    rs.deleteRow();
                    JOptionPane.showMessageDialog(rootPane, "Recipe Removed From Favorites Successfully");
                    getFavourites();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(rootPane, "Recipe not Found");
                    }
                 
                 }
                 catch(Exception ex)
                 {
                 ex.printStackTrace();
                 }
                 
             }
                    });
                    rec.bt4.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             try
                             {
                               HttpResponse<String> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/food/videos/search?query=" + title)
                                            .header("X-RapidAPI-Key", "652fb35be6msh43c979dd6e258dap1ede35jsn29e136726656")
                                            .header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                                            .asString();
                               if(response.getStatus()==200)
                               {
                               String res=response.getBody();
                                   System.out.println(res);
                                   JSONObject mainobj=new JSONObject(res);
                                   JSONArray arr=mainobj.getJSONArray("videos");
                                   if(!arr.isNull(0))
                                   {
                                   JSONObject obj=(JSONObject)arr.get(0);
                                   String id=obj.get("youTubeId").toString();
                                    URI u=new URI("https://www.youtube.com/results?search_query=" + id);
                                       Desktop d=Desktop.getDesktop();
                                       d.browse(u);
                                   }
                                   else
                                   {
                                   URI u=new URI("https://www.youtube.com/watch?v=aDm5WZ3QiIE");
                                   Desktop d=Desktop.getDesktop();
                                   d.browse(u);
                                   }
                                  
                               }
                               
                             }
                             catch(Exception ex)
                             {
                             ex.printStackTrace();;
                             }
                             
                         }
                    });
                    
                
                    
                    rec.bt8.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             Summary sum=new Summary(id);
                             dispose();
                         }
                    });
                    
                  
                    
                    
                    
                    
                     rec.setBounds(10, y, 1001, 595);
                     
                     rec.setVisible(true);
                         jPanel1.add(rec);
                         jPanel1.repaint();
                         jPanel1.revalidate();
                         y=y+605;
         }
        jPanel1.setPreferredSize(new Dimension(1260,y+50));
        
        }
        
        }
    catch(Exception ex)
    {
    ex.printStackTrace();
    }
    
    
    }
    
     public static BufferedImage scale(BufferedImage src, int w, int h) {
        BufferedImage img
                = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int x, y;
        int ww = src.getWidth();
        int hh = src.getHeight();
        int[] ys = new int[h];
        for (y = 0; y < h; y++) {
            ys[y] = y * hh / h;
        }
        for (x = 0; x < w; x++) {
            int newX = x * ww / w;
            for (y = 0; y < h; y++) {
                int col = src.getRGB(newX, ys[y]);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Phosphate", 3, 36)); // NOI18N
        jLabel1.setText("                Favourites");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(455, 19, 420, 34);

        jPanel1.setLayout(null);
        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(210, 100, 970, 490);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(390, 100, 790, 490);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Favourites.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Favourites.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Favourites.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Favourites.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Favourites().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

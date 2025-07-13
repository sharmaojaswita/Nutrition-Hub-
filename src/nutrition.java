import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import org.json.JSONArray;
import org.json.JSONObject;


public class nutrition extends JFrame
{
    
   JTabbedPane tb;
   JScrollPane jsp1,jsp2;
   JTable jt1,jt2;
   ArrayList<good> al1;
    ArrayList<bad> al2;
    mytablemodel1 t1;
    mytablemodel2 t2;
    JLabel lb;
    public nutrition(int id) throws HeadlessException {
        
        lb=new JLabel();
        
        setLayout(null);
        al1=new ArrayList<>();
        al2=new ArrayList<>();
        
        tb=new JTabbedPane();
        jt1=new JTable();
        jt2=new JTable();
        t1=new mytablemodel1();
        t2=new mytablemodel2();
        jt1.setModel(t1);
        jt2.setModel(t2);
        
        setTitle("Nutritional Information");
        
        jsp1=new JScrollPane(jt1);
        jsp2=new JScrollPane(jt2);
        
         jsp1.setBounds(433, 134, 500, 500);
          jsp2.setBounds(433, 134, 500, 500);
          
          tb.add("good",jsp1);
          tb.add("bad",jsp2);
          tb.setBackground(Color.white);
          tb.setBounds(433, 134, 500, 500);
          add(tb);
          Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
          int h=(int)d.getHeight();
          int w=(int)d.getWidth();
          lb.setBounds(0, 0, w, h);
        add(lb);
          ImageIcon ic=new ImageIcon("src/myuploads/nh1.jpeg");
        Image img=ic.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon im=new ImageIcon(img);
        lb.setIcon(im);
          setSize(w, h);
          setVisible(true);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          getNutrition(id);
        
    }
    void getNutrition(int id)
    {
    try
        {
        HttpResponse<String> res = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/"+id+"/nutritionWidget.json")
                    .header("X-RapidAPI-Key", "652fb35be6msh43c979dd6e258dap1ede35jsn29e136726656")
                    .header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                    .asString();
        
        if(res.getStatus()==200)
            {
            String ans=res.getBody().toString();
                System.out.println(ans );
                JSONObject mainobj=new JSONObject(ans);
                JSONArray arr1=mainobj.getJSONArray("good");
                JSONArray arr2=mainobj.getJSONArray("bad");
                for(int i=0;i<arr1.length();i++)
                {
                   JSONObject obj=(JSONObject)arr1.get(i);
                   String title=obj.get("title").toString();
                   String amount=obj.getString("amount");
                    double perc=(Double)obj.get("percentOfDailyNeeds");
                    int percentage=(int) perc;
                    al1.add((new good(title, amount, percentage)));
                  t1.fireTableDataChanged();
                }
                for(int i=0;i<arr2.length();i++)
                {
                JSONObject obj=(JSONObject)arr2.get(i);
                     String title=obj.get("title").toString();
                   String amount=obj.get("amount").toString();
                    double perc=(Double)obj.get("percentOfDailyNeeds");
                    int percentage=(int) perc;
                    al2.add(new bad(title, amount, percentage));
                    t2.fireTableDataChanged();
                }
                
            }
        }
    catch(Exception ex)
         {
             ex.printStackTrace();
        }
    }

 
    class mytablemodel1 extends AbstractTableModel
    {

        @Override
        public int getRowCount() {
            return al1.size();
        }
        

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int i, int j) {
            good g1=al1.get(i);
            if(j==0)
            {
            return g1.title;
            }
            else if(j==1)
            {
            return g1.amount;
            }
            else
            {
            return g1.percentage;
            }
        }

        @Override
        public String getColumnName(int column) {
            String col[]={"title","Amount","Percentage"};
            return col[column];
        }
        
    
    }
    class mytablemodel2 extends AbstractTableModel
    {

        @Override
        public int getRowCount() {
            return al2.size();
        }
        

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int i, int j) {
            bad g1=al2.get(i);
            if(j==0)
            {
            return g1.title;
            }
            else if(j==1)
            {
            return g1.amount;
            }
            else
            {
            return g1.percentage;
            }
        }

        @Override
        public String getColumnName(int column) {
            String col[]={"title","Amount","Percentage"};
            return col[column];
        }
        
    
    }
//    public static void main(String[] args) {
//        nutrition n1=new nutrition(0);
//    }
}

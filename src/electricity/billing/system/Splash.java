
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable{
    Thread t;
    Splash () {
   
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icon/img1.jpg"));
        Image i2=i1.getImage().getScaledInstance(1000,664,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image);
        setVisible(true);
        setSize(1000,664);
        setLocation(300,100);

//        for transition while opening
//        int x=1;
//        for(int i=2;i<664;i+=4,x+=1){
//            setSize(i+x,664 );
//            setLocation(800-((i+x)/2),350-(i/2));
//        }        
//        try{
//            Thread.sleep(5);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
    
        t=new Thread(this);
        t.start();
        setVisible(true);
    }
    
    public void run(){
        try {
            Thread.sleep(3000);
            setVisible(false);
            
                       
            //login frame
            new Login();
            //signup Frame
//            new Signup();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new Splash();
    }
}

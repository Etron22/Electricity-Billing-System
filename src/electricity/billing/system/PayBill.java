
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class PayBill extends JFrame implements ActionListener{
    Choice cmonth;
    JButton pay,back;
    String meter;
    PayBill(String meter){
        this.meter=meter;
        setLayout(null);
        getContentPane().setBackground(new Color(96,189,236));
        setBounds(300,150,900,600);
        JLabel heading =new JLabel("Electricity Bill");
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Tohama",Font.BOLD,24));
        heading.setBounds(120,5,400,30);
        add(heading);
        
        JLabel lblmeternumber= new JLabel("Meter Number");
        lblmeternumber.setBounds(35,80,200,20);
        lblmeternumber.setForeground(Color.WHITE);
        add(lblmeternumber);
        
        JLabel meternumber= new JLabel("");
        meternumber.setBounds(300,80,200,20);
        meternumber.setForeground(Color.WHITE);
        add(meternumber);
    
        JLabel lblname= new JLabel("Name");
        lblname.setBounds(35,140,200,20);
        lblname.setForeground(Color.WHITE);
        add(lblname);
        
        JLabel labelname= new JLabel("");
        labelname.setBounds(300,140,200,20);
        labelname.setForeground(Color.white);
        add(labelname);
        
        JLabel lblmonth= new JLabel("Month");
        lblmonth.setBounds(35,200,200,20);
        lblmonth.setForeground(Color.white);
        add(lblmonth);
        
        cmonth=new Choice();
        cmonth.setBounds(300,200,200,20);
        cmonth.add("January");
        cmonth.add("Febrary");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        cmonth.setForeground(Color.black);
        add(cmonth);
        
        JLabel lblunits= new JLabel("Units");
        lblunits.setBounds(35,260,200,20);
        lblunits.setForeground(Color.white);
        add(lblunits);
        
        JLabel labelunits= new JLabel("");
        labelunits.setBounds(300,260,200,20);
        labelunits.setForeground(Color.white);
        add(labelunits);
        
        JLabel lbltotalbill= new JLabel("Total Bill");
        lbltotalbill.setBounds(35,320,200,20);
        lbltotalbill.setForeground(Color.white);
        add(lbltotalbill);
        
        JLabel labeltotalbill= new JLabel("");
        labeltotalbill.setBounds(300,320,200,20);
        labeltotalbill.setForeground(Color.white);
        add(labeltotalbill);
        
        JLabel lblstatus= new JLabel("Status");
        lblstatus.setBounds(35,380,200,20);
        lblstatus.setForeground(Color.white);
        add(lblstatus);
        
        JLabel labelstatus= new JLabel("");
        labelstatus.setBounds(300,380,200,20);
        labelstatus.setForeground(Color.RED);
        add(labelstatus);
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from customer where meter_no='"+meter+"'");
            while(rs.next()){
                meternumber.setText(meter);
                labelname.setText(rs.getString("name"));
            }
            
            rs=c.s.executeQuery("select * from bill where meter_no='"+meter+"' AND month='"+cmonth.getSelectedItem()+"'");
            while(rs.next()){
                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));
                labelstatus.setText(rs.getString("status"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        cmonth.addItemListener(new ItemListener(){
            
            public void itemStateChanged(ItemEvent ae){
                try{
                    Conn c=new Conn();
                    ResultSet rs=c.s.executeQuery("select * from bill where meter_no='"+meter+"' AND month='"+cmonth.getSelectedItem()+"'");
                    while(rs.next()){
                        labelunits.setText(rs.getString("units"));
                        labeltotalbill.setText(rs.getString("totalbill"));
                        labelstatus.setText(rs.getString("status"));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    });
        
        pay=new JButton("Pay");
        pay.setBackground(Color.white);
        pay.setForeground(Color.black);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);
        
        back=new JButton("Back");
        back.setBackground(Color.white);
        back.setForeground(Color.black);
        back.setBounds(230,460,100,25);
        back.addActionListener(this);
        add(back);
        
         ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/bill1.png"));
        Image i2=i1.getImage().getScaledInstance(512,513,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(300,50,512,513);
        add(image);
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==pay){
            try{
                Conn c=new Conn();
                c.s.executeUpdate("update bill set status = 'Paid' where meter_no='"+meter+"'");
            } catch(Exception e){
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);
        }else{
            setVisible(false);
        }
    }
    public static void main(String[] args){
        new PayBill("");
    }
}

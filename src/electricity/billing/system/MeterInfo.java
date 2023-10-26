
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class MeterInfo extends JFrame implements ActionListener{
    JTextField tfname, tfaddress,tfcity, tfstate,tfemail,tfphone;
    JButton submit;
    JLabel lblmeter;
    String meternumber;
    Choice meterlocation,metertype,phasecode,billtype;
    MeterInfo(String meternumber){
        this.meternumber=meternumber;
        setSize(700,500);
        setLocation(400,200);
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(96,189,236));
        add(p);
        
        JLabel heading=new JLabel("Meter Information");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);
        
        JLabel lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(100,80,100,20);
        p.add(lblmeter);
        
        JLabel lblmeternumber=new JLabel(meternumber);
        lblmeternumber.setBounds(240,80,100,20);
        p.add(lblmeternumber ); 
        
        JLabel lblmeterno=new JLabel("Meter Location");
        lblmeterno.setBounds(100,120,100,20);
        p.add(lblmeterno);
        
        meterlocation=new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240,120,200,20);
        p.add(meterlocation);
        
        
                       
        JLabel lbladdress=new JLabel("Meter Type");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);
        
       
        metertype=new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240,160,200,20);
        p.add(metertype);
        
               
        JLabel lblphasecode=new JLabel("Phase Code");
        lblphasecode.setBounds(100,200,100,20);
        p.add(lblphasecode);
        
        phasecode=new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(240,200,200,20);
        p.add(phasecode);
        
                
        JLabel lblbilltype=new JLabel("Bill Type");
        lblbilltype.setBounds(100,240,100,20);
        p.add(lblbilltype);
        
        billtype=new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(240,240,200,20);
        p.add(billtype);
        
        JLabel lblemail=new JLabel("Days");
        lblemail.setBounds(100,280,100,20);
        p.add(lblemail);
        
                
        JLabel lbld=new JLabel("30 Days");
        lbld.setBounds(240,280,100,20);
        p.add(lbld);
        
        JLabel lblnote=new JLabel("Note");
        lblnote.setBounds(100,320,100,20);
        p.add(lblnote);
        
        JLabel lbli=new JLabel("By Default Bill is calculated for 30 days only");
        lbli.setBounds(240,320,500,20);
        p.add(lbli);
        
        
        
        submit=new JButton("Submit");
        submit.setBounds(220,390,100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        p.add(submit);
        
               
        setLayout(new BorderLayout());
        add(p,"Center");
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2=i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image,"West");
        
        getContentPane().setBackground(Color.white);
        
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
            if(ae.getSource()==submit){
                String meter=meternumber;
                String location=meterlocation.getSelectedItem();
                String type=metertype.getSelectedItem();
                String code=phasecode.getSelectedItem();
                String typebill=billtype.getSelectedItem();
                String days="30";
                
                
                String query="insert into meter_info values('"+meter+"','"+location+"','"+type+"','"+code+"','"+typebill+"','"+days+"')";
                
//                String query1 = "insert into customer values('"+name+"', '"+meter+"', '"+address+"', '"+city+"', '"+state+"', '"+email+"', '"+phone+"')";
//                String query2 = "insert into login values('"+meter+"', '', '"+name+"', '', '')";
                
                try{
                    Conn c =new Conn();
                    c.s.executeUpdate(query);
                    
                    
                    JOptionPane.showMessageDialog(null, "Meter Information added Successfully.");
                    setVisible(false);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                setVisible(false);
            }
        
    }

    public static void main(String[] args){
        new MeterInfo("");
    }
}


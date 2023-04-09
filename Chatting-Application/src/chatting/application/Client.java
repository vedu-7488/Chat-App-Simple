
package chatting.application;


import static chatting.application.Server.f;
import static chatting.application.Server.formatLabel;
import static chatting.application.Server.vertical;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Client implements ActionListener {
    JTextField text;
    static JPanel a1;
    static JFrame f=new JFrame();
    static Box vertical=Box.createVerticalBox();
    static DataOutputStream dout;
    Client(){
        
        f.setLayout(null);
        JPanel p1=new JPanel();//creating panel--2
        p1.setBackground(new Color(7,94,84));//custon color shades
        p1.setBounds(0, 0, 450, 70);//location of panel
        p1.setLayout(null);//so that can be customised latter in set bounds
        
        f.add(p1);//adding panel//--4
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));//Fetching image
        Image i2=i1.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);//scaling image
        ImageIcon i3=new ImageIcon(i2);//converting i2  for use
        JLabel back=new JLabel(i3);////passing image--3
        back.setBounds(5,20,25,25);//dimention
        p1.add(back);//adding image over panel
        
        back.addMouseListener(new MouseAdapter(){//adding mouse listener to back
        public void mouseClicked(MouseEvent ae){//function for exit on clicked
            System.exit(0);
            
        }
    });//event complete
        //profile pic
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));//Fetching image
        Image i5=i4.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);//scaling image
        ImageIcon i6=new ImageIcon(i5);//converting i2  for use
        
        JLabel profile=new JLabel(i6);////passing image--3
        profile.setBounds(40,10,50,50);//dimention
        p1.add(profile);//adding image over panel
        
        //video
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));//Fetching image
        Image i8=i7.getImage().getScaledInstance(20, 30,Image.SCALE_DEFAULT);//scaling image
        ImageIcon i9=new ImageIcon(i8);//converting i2  for use
        
        JLabel video=new JLabel(i9);////passing image
        video.setBounds(300,20,20,30);//dimention
        p1.add(video);//adding image over panel
        
        //phone
        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));//Fetching image
        Image i11=i10.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);//scaling image
        ImageIcon i12=new ImageIcon(i11);//converting i2  for use
        
        JLabel phone=new JLabel(i12);////passing image
        phone.setBounds(360,20,20,30);//dimention
        p1.add(phone);//adding image over panel
        
        
             //more dots
        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));//Fetching image
        Image i14=i13.getImage().getScaledInstance(10, 25,Image.SCALE_DEFAULT);//scaling image
        ImageIcon i15=new ImageIcon(i14);//converting i2  for use
        
        JLabel morevert=new JLabel(i15);////passing image
        morevert.setBounds(420,20,10,25);//dimention
        p1.add(morevert);//adding image over panel
        
        //name
        JLabel name=new JLabel("Gautam");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,22));
        p1.add(name);
        
        //activity
        
        JLabel status=new JLabel("ONLINE");
        status.setBounds(110,40,100,18);
        status.setForeground(Color.white);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,12));
        p1.add(status);
        
    //***********************************************************
//______________header Complete_______________________________________________

//texts______________________________________________
        a1=new JPanel();
        a1.setBounds(5,75,440,570);
        f.add(a1);
        
        
        //typing 
        text=new JTextField();
        
        text.setBounds(12,655,340,40);//side ,upar,len bre
        text.setFont(new Font("SAN_SERIF",Font.ITALIC,14));
        f.add(text);
        
        //Send Button
        JButton send=new JButton("Send");
        send.setBounds(350,655,100,40);
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.white);
        send.addActionListener(this);
       f.add(send);
        
        
        
         f.setSize(450,700);//main frame size--1
         f.setLocation(800,50);//opening location
         f.setUndecorated(true);
         f.getContentPane().setBackground(Color.white);//background color
         
         
         
        
        f.setVisible(true);//visiblity is false bydefault
    }
    public void actionPerformed(ActionEvent ae){
        try{
            String out=text.getText();//string type need to be converted 
            JLabel output=new JLabel(out);//string  to label
            
            
            JPanel p2=formatLabel(out);//passing label to function for formatting
          
                     
             a1.setLayout(new BorderLayout());//final addition to panel
            
            
            JPanel right=new JPanel(new BorderLayout());
            right.add(p2,BorderLayout.LINE_END); //allign to the right
            vertical.add(right);//adding to the right
            vertical.add(Box.createVerticalStrut(15));//line between subsequent massage(gap)
            a1.add(vertical,BorderLayout.PAGE_START);//adding to the pannel to the roght
            
            //sending massage
            dout.writeUTF(out);
            //to make textbox empty before sending
            
            text.setText("");
            
            f.repaint();//for making it visible
            f.invalidate();//for making it visible
            f.validate();//for making it visible
        }catch(Exception e){
            e.printStackTrace();
        }
            
        
    }
    public static JPanel formatLabel(String out ){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));//setting Layout
        
        JLabel output=new JLabel("<html><p style=\"width:150px\">"+ out + "</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,16));//changing font
        output.setBackground(new Color(37,211,102));//background color
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(12,12,12,50));
                
               
        panel.add(output);
        
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        
        return panel;
    }
    
    
    public static void main(String args[]){
        new Client();
        
        try{
            Socket s=new Socket("127.0.0.1",6001); 
            DataInputStream din=new DataInputStream(s.getInputStream());
               dout=new DataOutputStream(s.getOutputStream());
            
                 while(true){
                     a1.setLayout(new BorderLayout());
                    String msg=din.readUTF();
                    JPanel panel=formatLabel(msg);
                    
                    JPanel left=new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    vertical.add(left);
                    
                    vertical.add(Box.createVerticalStrut(15));
                    a1.add(vertical,BorderLayout.PAGE_START);
                    f.repaint();
                    f.validate();
                    f.invalidate();
                     
                }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}

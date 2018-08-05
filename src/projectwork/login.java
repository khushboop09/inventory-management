
package projectwork;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class login extends JFrame implements ActionListener, KeyListener
{
    JButton submit;
    JButton clear;
    JTextField username;
    JPasswordField password;
    JLabel jl=new JLabel("");
    Connection con = database.getConnection();;
    JPanel jpn;
    ResultSet rs;
    String usernm,passw;
    
    
    
     public login()
     {
        
        JLabel name=new JLabel("USERNAME");
        name.setForeground(Color.white);
        JLabel pass=new JLabel("PASSWORD");
        pass.setForeground(Color.WHITE);
        
        username=new JTextField(20);
        username.setCaretColor(Color.WHITE);
        username.setOpaque(false);
        username.setForeground(Color.white);
        
        password=new JPasswordField();
        password.setCaretColor(Color.WHITE);
        password.setOpaque(false);
        password.setForeground(Color.WHITE);
        password.addKeyListener(this);
       
        
        submit=new JButton("SUBMIT");
        submit.addActionListener(this);
        submit.setOpaque(false);
        submit.setContentAreaFilled(false);
        submit.setForeground(Color.white);
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit.setBorderPainted(false);
        
        clear=new JButton("CLEAR");
        clear.addActionListener(this);
        clear.setOpaque(false);
        clear.setContentAreaFilled(false);
        clear.setForeground(Color.white);
        clear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clear.setIcon(new ImageIcon(getClass().getResource("/img/button_cancel.gif")));
        clear.setBorderPainted(false);
        
        
        jpn=new JPanel();
        jpn.setLayout(new GridLayout(3, 2, 10, 15));
        
        jpn.add(name);
        jpn.add(username);
        jpn.add(pass);
        jpn.add(password);
        jpn.setOpaque(false);
        jpn.add(clear);
        jpn.add(submit);
        jpn.setBounds(590, 215, 300, 100);
        
        jl.setBounds(590, 100, 200, 200);        
        
        
        ProjectWork.background.add(jl);
        ProjectWork.background.add(jpn);
        
      
     }


    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object src=e.getSource();
        char[] c=password.getPassword();
        String s=new String(c);
        if(src==submit)
        {
            try 
            {
               PreparedStatement pst = con.prepareStatement("SELECT * FROM `login`;");
                rs = pst.executeQuery();
                while(rs.next())
                {
                usernm = rs.getString(1);
                passw = rs.getString(2);
                }
                if(username.getText().equals(usernm) && s.equals(passw))
                {
                    jl.setVisible(false);
                    jpn.setVisible(false);
                    new JProgressBarThread();

                }
                else
                {
                    username.setText("");
                    password.setText("");

                    jl.setText("Incorrect Username or Password!");
                    jl.setForeground(Color.RED);
                }
            }
            catch (SQLException ex) 
            {
               System.out.println(ex);
            }
            
       }
       else if(src==clear)
       {
           username.setText("");
           password.setText("");
       }
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        char[] c=password.getPassword();
        String s=new String(c);
        
        if(e.getKeyChar()==KeyEvent.VK_ENTER)
        {
         
               PreparedStatement pst;
            try 
            {
                pst = con.prepareStatement("SELECT * FROM `login`;");
                 rs = pst.executeQuery();
                while(rs.next())
                {
                usernm = rs.getString(1);
                passw = rs.getString(2);
                }
                if(username.getText().equals(usernm) && s.equals(passw))
                {
                    jl.setVisible(false);
                    jpn.setVisible(false);
                    new JProgressBarThread();

                }
                else
                {
                    username.setText("");
                    password.setText("");

                    jl.setText("Incorrect Username or Password!");
                    jl.setForeground(Color.RED);
                }
            
            }
            catch (SQLException ex) 
            {
                System.out.println(ex);
            }
               
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
    }
    
    
}

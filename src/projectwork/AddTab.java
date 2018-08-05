
package projectwork;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class AddTab implements ActionListener,KeyListener 
{
    JPanel jpnl=new JPanel();
    JPanel panel;
    JButton btnadd,clear;
    JTextField tfname,tfid,tfquantity,tfcost;
    Connection con;
    
    public AddTab() 
    {
        JLabel name=new JLabel("Product Name");
        name.setForeground(Color.WHITE);
        
        JLabel id=new JLabel("Product Id");
        id.setForeground(Color.WHITE);
        
        JLabel cost=new JLabel("Cost");
        cost.setForeground(Color.WHITE);
        
        JLabel quantity=new JLabel("Quantity");
        quantity.setForeground(Color.WHITE);
        
        btnadd=new JButton("ADD");
        btnadd.setOpaque(false);
        btnadd.setContentAreaFilled(false);
        btnadd.setForeground(Color.white);
        btnadd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnadd.addActionListener(this);
        btnadd.setIcon(new ImageIcon(getClass().getResource("/img/list_add.png")));
        btnadd.setBorderPainted(false);
        
        clear=new JButton("CLEAR ");
        clear.setOpaque(false);
        clear.setContentAreaFilled(false);
        clear.setForeground(Color.white);
        clear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clear.addActionListener(this);
        clear.setIcon(new ImageIcon(getClass().getResource("/img/button_cancel.gif")));
        clear.setBorderPainted(false);
        
        tfname=new JTextField();
        tfname.setForeground(Color.black);
        tfname.setCaretColor(Color.black);
        
        tfid=new JTextField();
        tfid.setForeground(Color.black);
        tfid.setCaretColor(Color.black);
        
        tfcost=new JTextField();
        tfcost.setOpaque(false);
        tfcost.setForeground(Color.black);
        tfcost.setCaretColor(Color.black);
        tfcost.addKeyListener(this);
        
        tfquantity=new JTextField();
        tfquantity.setOpaque(false);
        tfquantity.setForeground(Color.black);
        tfquantity.setCaretColor(Color.black);
        
        
        jpnl.setLayout(new GridLayout(4, 2, 0, 20));
        jpnl.add(id);
        jpnl.add(tfid);
        jpnl.add(name);
        jpnl.add(tfname);
        jpnl.add(quantity);
        jpnl.add(tfquantity);
        jpnl.add(cost);
        jpnl.add(tfcost);
        jpnl.setOpaque(false);
        jpnl.setBounds(100,80, 350, 190);
        
        panel=new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(clear);
        panel.add(btnadd);
        panel.setBounds(200,280, 190, 190);
        panel.setOpaque(false);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==btnadd)
        {
             String pid=tfid.getText();
             String pname=tfname.getText();
             String pquantity=tfquantity.getText();
             String pcost=tfcost.getText();
             
             con = database.getConnection();
             
             PreparedStatement pst;
            try 
            {
               pst = con.prepareStatement("INSERT INTO `products`(`Product Id`,`Product Name`,`Quantity`,`Price`) VALUES(?,?,?,?)");
               pst.setString(1, pid);
               pst.setString(2, pname);
               pst.setString(3, pquantity);
               pst.setString(4, pcost);
               pst.executeUpdate();
               JOptionPane.showMessageDialog(null," PRODUCT ADDED! ");
               tfid.setText("");
               tfname.setText("");
               tfquantity.setText("");
               tfcost.setText("");
                
            
            }
            catch (SQLException ex) 
            {   
                System.out.println(ex);
            }
        }
        else if(e.getSource()==clear)
        {
            tfid.setText("");
            tfname.setText("");
            tfquantity.setText("");
            tfcost.setText("");
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
       
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
     if(e.getKeyChar()==KeyEvent.VK_ENTER)
        {
             String pid=tfid.getText();
             String pname=tfname.getText();
             String pquantity=tfquantity.getText();
             String pcost=tfcost.getText();
             
             con = database.getConnection();
             
             PreparedStatement pst;
            try 
            {
               pst = con.prepareStatement("INSERT INTO `products`(`Product Id`,`Product Name`,`Quantity`,`Price`) VALUES(?,?,?,?)");
               pst.setString(1, pid);
               pst.setString(2, pname);
               pst.setString(3, pquantity);
               pst.setString(4, pcost);
               pst.executeUpdate();
               JOptionPane.showMessageDialog(null," PRODUCT ADDED! ");
               tfid.setText("");
               tfname.setText("");
               tfquantity.setText("");
               tfcost.setText("");
                
            
            }
            catch (SQLException ex) 
            {   
                System.out.println(ex);
            }
        }
        else if(e.getSource()==clear)
        {
            tfid.setText("");
            tfname.setText("");
            tfquantity.setText("");
            tfcost.setText("");
        
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
    
    }
  
    
}

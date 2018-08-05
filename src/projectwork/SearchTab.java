
package projectwork;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class SearchTab implements ActionListener, FocusListener
{
    JPanel jp1, jp2, jp3, jp4;
    JButton all,search,clear;
    Connection con;
    JScrollPane scroll = new JScrollPane();
    public static JTable table;
    public static String Content[][];
    public static int rowNum = 0;
    static String sSQL ="SELECT * FROM `products`";
    static Statement stmt;
    static ResultSet rs;
    public static int total = 0;
    
    
    //TextFields
    JTextField tfname=new JTextField(15);
    JTextField tfid=new JTextField(15);
    
    public SearchTab() 
    {
       JLabel pname=new JLabel(" Product Name ");
       pname.setForeground(Color.WHITE);
       
       JLabel or=new JLabel(" OR ");
       or.setSize(40, 40);
       or.setForeground(Color.WHITE);
       
       JLabel pid=new JLabel(" Product Id ");
       pid.setForeground(Color.WHITE);
       
       tfname.addFocusListener(this);
       tfname.setForeground(Color.black);
       tfname.setCaretColor(Color.black);
 
       tfid.setForeground(Color.black);
       tfid.setCaretColor(Color.black);
       tfid.setOpaque(false);
       
       search=new JButton("SEARCH");
       search.setOpaque(false);
       search.setContentAreaFilled(false);
       search.setForeground(Color.white);
       search.setCursor(new Cursor(Cursor.HAND_CURSOR));
       search.addActionListener(this);
       search.setIcon(new ImageIcon(getClass().getResource("/img/sear.png")));
       search.setBorderPainted(false);
       
       all=new JButton(" SHOW ALL ");
       all.setOpaque(false);
       all.setContentAreaFilled(false);
       all.setForeground(Color.white);
       all.setCursor(new Cursor(Cursor.HAND_CURSOR));
       all.addActionListener(this);
       all.setIcon(new ImageIcon(getClass().getResource("/img/document_edit.png")));
       all.setBorderPainted(false);
       
       clear=new JButton("  CLEAR ");
       clear.setOpaque(false);
       clear.setContentAreaFilled(false);
       clear.setForeground(Color.white);
       clear.setCursor(new Cursor(Cursor.HAND_CURSOR));
       clear.addActionListener(this);
       clear.setIcon(new ImageIcon(getClass().getResource("/img/button_cancel.gif")));
       clear.setBorderPainted(false);
       
       jp1=new JPanel();
       jp1.setLayout(new GridLayout(1, 1, 0, 20));
       jp1.add(pname);
       tfname.setOpaque(false);
       jp1.add(tfname);
       jp1.setBounds(100,80, 350, 25);
       jp1.setOpaque(false);
       
       jp2=new JPanel();
       jp2.add(or);
       jp2.setBounds(100, 125, 350, 20);
       jp2.setOpaque(false);
       
       jp3=new JPanel();
       jp3.setLayout(new GridLayout(1, 1, 0, 20));
       jp3.add(pid);
       jp3.add(tfid);
       jp3.setBounds(100, 170, 350, 25);
       jp3.setOpaque(false);
       
       jp4=new JPanel();
       jp4.add(clear);
       jp4.add(search);
       jp4.add(all);
       jp4.setBounds(100, 210, 350, 40);
       jp4.setOpaque(false);
              
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==all)
        {
              table=createTable();
              table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
              table.setEnabled(false);
              table.getTableHeader().setReorderingAllowed(false);
              scroll.getViewport().add(table);
              scroll.setBounds(150, 350, 500, 100);
                
              tabs.bkimg1.add(scroll);
            
        }
        
        else if(e.getSource()==search)
        {
            String name = tfname.getText();
            String id=tfid.getText();
            if(!name.equals(""))
            {
            String ColumnHeaderName[] = { "Product Id","Product Name","Quantity","Price" };
            try
            {
            con = database.getConnection();
            
            PreparedStatement pst = con.prepareStatement("SELECT * FROM `products` WHERE `Product Name` = ?");
            
            pst.setString(1, name);
            rs = pst.executeQuery();
            Content=new String[1][4];
            
            while(rs.next())
                {
                
                    Content[rowNum][0] = "" + rs.getString(1);
                    Content[rowNum][1] = "" + rs.getString(2);
                    Content[rowNum][2] = "" + rs.getString(3);
                    Content[rowNum][3] = "" + rs.getString(4);
                    rowNum++;

                }
          
            }
            catch(Exception ex)
            {   }
              table=new JTable(Content, ColumnHeaderName);
              scroll.getViewport().add(table);
              scroll.setBounds(150, 350, 500, 100);
                
              tabs.bkimg1.add(scroll);
            }
            else if(!id.equals(""))
            {
                String ColumnHeaderName[] = { "Product Id","Product Name","Quantity","Price" };
            try
            {
            con = database.getConnection();
            
            PreparedStatement pst = con.prepareStatement("SELECT * FROM `products` WHERE `Product Id` = ?");
            
            pst.setString(1, id);
            rs = pst.executeQuery();
            Content=new String[1][4];
           
            while(rs.next())
                {
                
                    Content[rowNum][0] = "" + rs.getString(1);
                    Content[rowNum][1] = "" + rs.getString(2);
                    Content[rowNum][2] = "" + rs.getString(3);
                    Content[rowNum][3] = "" + rs.getString(4);
                    rowNum++;

                }
          
            }
            catch(Exception ex)
            {   }
              table=new JTable(Content, ColumnHeaderName);
              scroll.getViewport().add(table);
              scroll.setBounds(150, 350, 500, 100);
                
              tabs.bkimg1.add(scroll);
            }
        }
        else if(e.getSource()==clear)
        {
            tfname.setText("");
            tfid.setText("");
            scroll.setVisible(false);
            table.setVisible(false);
            
        }
    }
    
    public JTable createTable()
    {
        con = database.getConnection();
        String ColumnHeaderName[] = { "Product Id","Product Name","Quantity","Price" };
           
            try 
            {
                  
                stmt = con.createStatement();
                rs=stmt.executeQuery(sSQL);
                
                total = 0;
			//Move to the last record
			rs.afterLast(); 
			//Get the current record position
			if(rs.previous())
                        {
                            total = rs.getRow();
                        }
			//Move back to the first record; 
			rs.beforeFirst(); 
                        
                        if(total != 0)
			{
				Content = new String[total][4];				
				while(rs.next())
				{
					Content[rowNum][0] = "" + rs.getString("Product Id");
					Content[rowNum][1] = "" + rs.getString("Product Name");
					Content[rowNum][2] = "" + rs.getString("Quantity");
					Content[rowNum][3] = "" + rs.getString("Price");
					
					rowNum++;
				}
			}
			else
			{
				Content = new String[0][4];
				Content[0][0] = " ";
				Content[0][1] = " ";
				Content[0][2] = " ";
				Content[0][3] = " ";
				
			}
	}
                
        catch(Exception eE)
        { }
                
               JTable Ttable = new JTable(Content,ColumnHeaderName)
                {
                @Override
                    public boolean isCellEditable(int row, int column) 
                    { 
                        return false;
                    }
                };
                
		//Start resize the table column
                
		Ttable.getColumnModel().getColumn(0).setMinWidth(0);
		Ttable.getColumnModel().getColumn(0).setPreferredWidth(100);
		Ttable.getColumnModel().getColumn(1).setPreferredWidth(200);
		Ttable.getColumnModel().getColumn(2).setPreferredWidth(100);
                Ttable.getColumnModel().getColumn(3).setPreferredWidth(100);
		//End resize the table column
		
		//Disposed variables
		ColumnHeaderName=null;
		Content=null;
		
		rowNum = 0;
                
                return Ttable;
    }

    @Override
    public void focusGained(FocusEvent e) 
    {
       
    }

    @Override
    public void focusLost(FocusEvent e)
    {
         
    }

   
   
}


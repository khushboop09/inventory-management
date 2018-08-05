
package projectwork;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class RemoveTab implements ActionListener
{
    JPanel jp4;
    JButton show,delete;
    
    static Connection con;
    static JScrollPane scroll = new JScrollPane();
    public static JTable table;
    public static String Content[][];
    public static int rowNum = 0;
    static String sSQL ="SELECT * FROM `products`";
    static Statement stmt;
    static ResultSet rs;
    public static int total = 0;
    
    public static JPanel jpnlMain = new JPanel();
    
    public RemoveTab() 
    {
       
       delete=new JButton(" DELETE ");
       delete.setOpaque(false);
       delete.setContentAreaFilled(false);
       delete.setForeground(Color.white);
       delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
       delete.addActionListener(this);
       delete.setIcon(new ImageIcon(getClass().getResource("/img/4697.png")));
       delete.setBorderPainted(false);
       
       show=new JButton("   SHOW ALL  ");
       show.setOpaque(false);
       show.setContentAreaFilled(false);
       show.setForeground(Color.white);
       show.setCursor(new Cursor(Cursor.HAND_CURSOR));
       show.addActionListener(this);
       show.setIcon(new ImageIcon(getClass().getResource("/img/search.png")));
       show.setBorderPainted(false);
       
       jp4=new JPanel();
       jp4.add(show);
       jp4.add(delete);
       jp4.setBounds(100, 50, 350, 40);
       jp4.setOpaque(false);
       
    }
    
    public static void reloadRecord(String srcSQL)
	{
		sSQL = srcSQL;
		scroll.getViewport().remove(table);
		table=createTable();
		scroll.getViewport().add(table);
		
	}

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        if(e.getSource()==show)
        {
              table=createTable();
             table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
              //table.setEnabled(false);
              table.getTableHeader().setReorderingAllowed(false);
              scroll.getViewport().add(table);
              scroll.setBounds(150, 100, 510, 130);
                
              tabs.bkimg.add(scroll);
        }   
       else if(e.getSource()==delete)
        {
                try
                    {
                        stmt = con.createStatement();
                        
                        if(table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()) != null)
                        {
                            String ObjButtons[] = {"Yes","No"};
                            int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to removed " + table.getValueAt(table.getSelectedRow(),1) + " in the record?","Delete Record",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,ObjButtons,ObjButtons[1]);
                            if(PromptResult==0)
                            {
                                stmt.execute("DELETE FROM `products` WHERE `Product Id` = '"+table.getValueAt(table.getSelectedRow(),0)+"'");
                                reloadRecord(sSQL);
                                JOptionPane.showMessageDialog(null,"Record has been successfully removed.","Comfirm Delete",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                catch(SQLException | HeadlessException sqlE)
                {
                    System.out.println(sqlE);
                if(sqlE.getMessage()!=null)
                    {
                        JOptionPane.showMessageDialog(null,"You cannot delete this product because it is being used by another user.\nIn order to delete this product, delete its data from another table.","Comfirm Delete",JOptionPane.WARNING_MESSAGE);
                    }
                else
                    {
                        JOptionPane.showMessageDialog(null,"Please select a record in the list to deleted.","No Record Selected",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            
        }
            }
    public static JTable createTable()
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
                
		
		
               // Ttable.setBackground(Color.WHITE);
		
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

        }
     
    


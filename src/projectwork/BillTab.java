
package projectwork;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.*;

public class BillTab extends JFrame implements ActionListener 
{
    JPanel jpn4;
    JButton print;
    JScrollPane scroll=new JScrollPane();
    static JTable table;
    Connection con;
    ResultSet rs;
    public static String Content[][];
    public static int rowNum = 0;
    public static int total=0;
    static Statement stmt;
    static String sSQL ="SELECT * FROM `products`";
    static JFrame JFParentFrame;
    
    public BillTab() 
    {
       
       print=new JButton("  PRINT ");
       print.setOpaque(false);
       print.setContentAreaFilled(false);
       print.setForeground(Color.white);
       print.setCursor(new Cursor(Cursor.HAND_CURSOR));
       print.addActionListener(this);
       print.setIcon(new ImageIcon(getClass().getResource("/img/print.png")));
       print.setBorderPainted(false);
       
        table=createTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        scroll.getViewport().add(table);
        scroll.setBounds(150, 150, 520, 120);
                
        tabs.bkimg3.add(scroll);
       
       jpn4=new JPanel();
       jpn4.setLayout(new FlowLayout(FlowLayout.CENTER));
       jpn4.add(print);
       jpn4.setBounds(120, 290, 350, 40);
       jpn4.setOpaque(false);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
      if(e.getSource()==print)
      {
          rptPreview();
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

    public static void JFrameObjectRef(JFrame frame)
    { 
        BillTab.JFParentFrame = frame;
        
    }
    
    
    public void rptPreview()
	{
            
            
		try
		{
			if(table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()) != null)
			{
                           
				mdlMethods PrintingClass = new mdlMethods();
				ResultSet rsPrint = stmt.executeQuery("SELECT * FROM `products` WHERE `Product Id`='" + table.getValueAt(table.getSelectedRow(),0) + "'");
				if(rsPrint.next()==true)
				{
					String RecordToPrint = "";
					java.util.Date CurrentDate = new java.util.Date();
					SimpleDateFormat SDFDateFormatter = new SimpleDateFormat("MMM. dd, yyyy HH:mm:ss",Locale.getDefault());
								
					RecordToPrint += "                      S T O R E   R E C O R D S\n";
					RecordToPrint += "                              " + SDFDateFormatter.format(CurrentDate).toString() + "\n\n\n";
					
                                        RecordToPrint += "-----------------------------------------------------------------------------------\n\n";
					
                                        RecordToPrint += " Product ID: " + rsPrint.getString("Product Id") + "\n\n";
								
					RecordToPrint += " Product Name: " + rsPrint.getString("Product Name") + "\n";
					//RecordToPrint += " Quantity: " + rsPrint.getString("Quantity") + "\n";
					RecordToPrint += " Price: " + rsPrint.getString("Price") + "\n";
							
					RecordToPrint += "-----------------------------------------------------------------------------------\n\n\n";
																	
					PrintingClass.printRecord(RecordToPrint,JFParentFrame);
                                        				
					CurrentDate=null;
					SDFDateFormatter=null;
					RecordToPrint=null;
															
				}
				else
				{   JOptionPane.showMessageDialog(null,"The selected record has been change since last modified. Please click the 'Reload' button and try again!","No record to print",JOptionPane.WARNING_MESSAGE);
				}
				//Dispose the variable
				rsPrint=null;				
			}
                        else
                        {
                            System.out.println("Error !!");
                        }   
		}
    		catch(Exception sqlE)
                {
                    System.out.println(sqlE);
                    JOptionPane.showMessageDialog(null,"Please select a record in the list to print.","No Record Selected",JOptionPane.INFORMATION_MESSAGE);
        	}
	}
    
    
    
}
    
   
    


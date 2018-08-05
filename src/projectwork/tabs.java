
package projectwork;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class tabs extends JFrame 
{
    JTabbedPane t;
    JPanel p1,p2,p3,p4,pn;
    static JLabel bkimg,bkimg1,bkimg2,bkimg3;
    JLabel logout; 
    	
    public tabs()
    {
  
       
       bkimg=new JLabel(new ImageIcon(getClass().getResource("/img/tabs_background.jpg")));
       bkimg.setLayout(null);
       bkimg1=new JLabel(new ImageIcon(getClass().getResource("/img/tabs_background.jpg")));
       bkimg1.setLayout(null);
       bkimg2=new JLabel(new ImageIcon(getClass().getResource("/img/tabs_background.jpg")));
       bkimg2.setLayout(null);
       bkimg3=new JLabel(new ImageIcon(getClass().getResource("/img/tabs_background.jpg")));
       bkimg3.setLayout(null);
   
       t=new JTabbedPane();
       p1=new JPanel();
       p2=new JPanel();
       p3=new JPanel();
       p4=new JPanel();
       
  
       logout = new JLabel(new ImageIcon(getClass().getResource("/img/exit.png")));
       logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
       logout.setOpaque(false);
       logout.setToolTipText("Logout");
       logout.addMouseListener(new MouseAdapter() 
       {
            @Override
           public void mouseClicked(MouseEvent e)
           {
                String ObjButtons[] = {"Yes","No"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Close ? ","Close",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,ObjButtons,ObjButtons[1]);
		if(PromptResult==0)
                {
                   ProjectWork pw=new ProjectWork();
                   pw.setVisible(false);
                   pw.dispose();
                   System.exit(0);
                }
           }
           
       });
       
       
       //--------- REMOVE tab -------------------------  
       
       RemoveTab rt=new RemoveTab();
       p1.setLayout(new BorderLayout());
       p1.add(bkimg, BorderLayout.CENTER);
       bkimg.add(rt.jp4);
       
       //----------END REMOVE TAB----------------------
       
       
       //--------------SEARCH tab------------------------
       
       SearchTab st = new SearchTab();
       p2.setLayout(new BorderLayout());
       p2.add(bkimg1, BorderLayout.CENTER);
       bkimg1.add(st.jp1);
       bkimg1.add(st.jp2);
       bkimg1.add(st.jp3);
       bkimg1.add(st.jp4);
       
       //------------------END SEARCH TAB---------------------
       
       
       //------------------ADD TAB----------------------------
       
       AddTab at=new AddTab();
       p3.setLayout(new BorderLayout());
       p3.add(bkimg2, BorderLayout.CENTER);
       bkimg2.add(at.jpnl);
       bkimg2.add(at.panel);
       
       //------------------END ADD TAB------------------------
       
       //------------------BILL TAB----------------------------
       
       BillTab bt=new BillTab();
       p4.setLayout(new BorderLayout());
       p4.add(bkimg3, BorderLayout.CENTER);
       bkimg3.add(bt.jpn4);
       
       //--------------------END BILL TAB---------------------
       
       t.addTab("REMOVE", p1);
       t.addTab("SEARCH", p2);
       t.addTab("ADD", p3);
       t.addTab("BILL", p4);
      
       t.setBackground(Color.BLACK);
       t.setForeground(Color.WHITE);
        
       t.setMnemonicAt(0,'R');
       t.setMnemonicAt(1,'S');
       t.setMnemonicAt(2,'A');
       t.setMnemonicAt(3,'B');
      
       t.setSelectedIndex(1);
       
       t.setTabPlacement(JTabbedPane.LEFT);
        
        pn = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,0));
        pn.add(logout);
       
       ProjectWork.jp.add(pn,BorderLayout.SOUTH);
       ProjectWork.jp.add(t, BorderLayout.CENTER);
       
       
    }        
       
}

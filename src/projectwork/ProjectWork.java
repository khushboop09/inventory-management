
package projectwork;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class ProjectWork extends JFrame implements ActionListener,KeyListener
{
    static JButton jb;
    static JLabel background;
    static JScrollPane sc;
    static JPanel jp=new JPanel(new BorderLayout());
     
    public ProjectWork() 
    {
        
        setTitle("INVENTORY");
	setSize(1000,590);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
        setResizable(false);

	background=new JLabel(new ImageIcon(getClass().getResource("/img/ssx-background.png")));
	background.setLayout(null);
        
        
        jb=new JButton(" LOGIN ");    
        jb.setBounds(570, 215, 80, 30);
        jb.setOpaque(false);
        jb.setContentAreaFilled(false);
        jb.setForeground(Color.white);
        jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb.addKeyListener(this);
        jb.addActionListener(this);
        
        
        background.add(jb);
        jp.add(background, BorderLayout.CENTER);
       
     
        add(jp,BorderLayout.CENTER);
        
        BillTab.JFrameObjectRef(this);
       
    }
    public static void main(String[] args) 
    {
         EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ProjectWork();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
         
        if(e.getSource()==jb)
        {
            jb.setVisible(false);
            new login();
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
            jb.setVisible(false);
            new login();   
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        
    }

}

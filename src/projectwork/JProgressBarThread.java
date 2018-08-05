
package projectwork;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;


class JProgressBarThread extends JFrame
{
    JProgressBar jp;
    Thread t;
    int i;
    JPanel pn;
    public JProgressBarThread()
    {
            try
            {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            }
            catch(Exception e)
            {
                System.out.println(e);
            }

            pn=new JPanel();
            pn.setBounds(570, 220, 300, 60);
            pn.setOpaque(false);

            jp=new JProgressBar();
            jp.setPreferredSize(new Dimension(350,30));
            jp.setStringPainted(true);

            t=new Thread(new Runnable(){
                        @Override
                        public void run()
                        {
                            for(i=0;i<=jp.getMaximum();i++)
                            {

                                // Update value
                                jp.setValue(i);
                                try
                                {
                                    // Get the effect
                                    Thread.sleep(50);
                                    if(i==jp.getMaximum())
                                    {   
                                        
                                         EventQueue.invokeLater(new Runnable()
                                         {

                                                @Override
                                                public void run()
                                                {
                                                    jp.setVisible(false);
                                                    ProjectWork.background.setVisible(false);
                                                    new tabs();
                                                }
                                          });
                                     }
                                 } 
                                catch(Exception e)
                                {}
                             }
                               
                        }
                        
                        });

            // Start thread
            t.start();

            pn.add(jp);
            ProjectWork.background.add(pn);
    }
   
}
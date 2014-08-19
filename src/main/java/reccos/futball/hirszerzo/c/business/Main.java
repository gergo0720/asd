package reccos.futball.hirszerzo.c.business;
import javax.swing.JDesktopPane;
import javax.swing.SwingUtilities;



public class Main extends JDesktopPane{
 
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
                            
                            try {
                                Thread.sleep(3000);
                            } catch(Exception e) {
                                
                            }
                            
                            ReccosB f = new ReccosB();
                            f.setVisible(true);
                            f.connect();
                            f.requestFocusInWindow();
			}
		});
	}
}

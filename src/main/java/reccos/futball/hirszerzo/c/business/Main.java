package reccos.futball.hirszerzo.c.business;
import javax.swing.JDesktopPane;
import javax.swing.SwingUtilities;



public class Main extends JDesktopPane{
 
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
                            
                            try {
                                Thread.sleep(3200);
                            } catch(Exception e) {
                                
                            }
                            
                            
                            ReccosC f = new ReccosC();
                            f.setVisible(true);
                            f.connect();
                            f.requestFocusInWindow();
			}
		});
	}
}

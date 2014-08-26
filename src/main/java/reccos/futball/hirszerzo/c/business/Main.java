package reccos.futball.hirszerzo.c.business;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.version.LibVlcVersion;



public class Main extends JDesktopPane{
 
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
                            setupLibVLC();
                            load();
                            try {
                                Thread.sleep(3200);
                            } catch(Exception e) {
                                
                            }
                            ReccosC frame = new ReccosC();
                            frame.setVisible(true);
                            frame.connect();
                            frame.requestFocusInWindow();
			}

                    private void load() {
                        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
                    }
                    
                    private void setupLibVLC() {

                    new NativeDiscovery().discover();

                        // discovery()'s method return value is WRONG on Linux
                        try {
                            LibVlcVersion.getVersion();
                        } catch (UnsatisfiedLinkError e) {
                            JOptionPane.showMessageDialog(null, "Ön a 64 bites Futball-Hírszerzőt használja, azonban nem található ehhez megfelelő 64 bites VLC");
                            System.exit(0);
                        }
                    }
		});

	}
}

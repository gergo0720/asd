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
                            load(System.getProperty("os.name"), System.getProperty("os.arch"));
                            try {
                                Thread.sleep(3200);
                            } catch(Exception e) {
                                
                            }
                            ReccosC frame = new ReccosC();
                            frame.setVisible(true);
                            frame.connect();
                            frame.requestFocusInWindow();
			}

                    private void load(String os, String osArch) {
                        String libsPath = null;
                        if(os.toLowerCase().contains("windows"))
                           if(osArch.toLowerCase().contains("64")) { 
                               libsPath = "C:\\Program Files\\VideoLAN\\VLC";
                           } else {
                               libsPath = "C:\\Program Files (x86)\\VideoLAN\\VLC";
                           }
                        else {
                            libsPath = "/usr/lib";
                        }
                        
                        
                        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), libsPath);
                        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
                    }
                    
                    private void setupLibVLC() {

                    new NativeDiscovery().discover();

                        // discovery()'s method return value is WRONG on Linux
                        try {
                            LibVlcVersion.getVersion();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Nem található a VLC");
                        }
                    }
		});

	}
}

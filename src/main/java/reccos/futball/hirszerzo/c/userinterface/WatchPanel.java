package reccos.futball.hirszerzo.c.userinterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import reccos.futball.hirszerzo.c.business.JabberSmackApi;


public class WatchPanel extends JPanel {
	
	PlayerSelectorPanel playerPanel;
	AnnotationPanel annotationPanel = new AnnotationPanel();
	Dimension size = new Dimension(250,600);
	
	public WatchPanel() {
                setPreferredSize(new Dimension(250,600));
		setBackground(Color.LIGHT_GRAY);
		setFocusable(true);
		playerPanel = new PlayerSelectorPanel(size);
		playerPanel.setPreferredSize(size);
                add(annotationPanel);
		
        }
}

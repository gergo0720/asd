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
		setBackground(new Color(243,244,247));
		setFocusable(true);
		playerPanel = new PlayerSelectorPanel(size);
		playerPanel.setPreferredSize(size);
		
		add(playerPanel);
		playerPanel.addPropertyChangeListener("player",new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent evt) {
					String name = evt.getNewValue().toString();
					annotationPanel.setName(name);
					remove(playerPanel);
					add(annotationPanel);
					
					if(name.compareTo("Jatekvezeto") == 0)
						annotationPanel.setLayoutType(AnnotationPanel.REFEREE_LAYOUT);
					else
						annotationPanel.setLayoutType(AnnotationPanel.PLAYER_LAYOUT);
					
					annotationPanel.recalculateButtons();
					validate();
					repaint();
			}
		});
		
		annotationPanel.addPropertyChangeListener("back",new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(!JabberSmackApi.getInstance().isConnected()){
					firePropertyChange("back", true, false);
					return;
				}
				remove(annotationPanel);
				add(playerPanel);
				validate();
				repaint();
			}
		});
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				annotationPanel.setPreferredSize(getSize());
				annotationPanel.recalculateButtons();
				playerPanel.setPreferredSize(getSize());
			}
		});
	}
	
	public AnnotationPanel getAnnotationPanel(){
		return annotationPanel;
	}
}

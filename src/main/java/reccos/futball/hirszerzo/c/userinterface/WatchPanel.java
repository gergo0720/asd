package reccos.futball.hirszerzo.c.userinterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.Y_AXIS;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jivesoftware.smack.packet.Message;
import reccos.futball.hirszerzo.c.business.JabberSmackApi;


public class WatchPanel extends JPanel {
	JPanel followedPersonPanel;
	PlayerSelectorPanel playerPanel;
	AnnotationLayouts annotationLayout;
	Dimension size = new Dimension(250,600);
	
	public WatchPanel() {
            followedPersonPanel = new JPanel();
            setPreferredSize(new Dimension(250,600));
            setBackground(new Color(128,128,128));
            setFocusable(true);
            playerPanel = new PlayerSelectorPanel(size);
            annotationLayout = new AnnotationLayouts();
            playerPanel.setPreferredSize(size);
            add(playerPanel);
            addListeners();
        }
        
        private void setupFollowedPersonPanel() {
            followedPersonPanel.setPreferredSize(new Dimension(240,60));
            followedPersonPanel.setBackground(new Color(64,64,64));
            followedPersonPanel.setLayout(new GridBagLayout());
            add(followedPersonPanel);
            //JLabel followed = new JLabel("Hey There");
            JLabel person = new JLabel("Őt követi: " + playerPanel.getSelectedName());
            person.setFont(new Font("Monospace", Font.BOLD, 14));
            person.setForeground(Color.WHITE);
            followedPersonPanel.add(person);
        }
        
        private void addListeners() {
            playerPanel.selectButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                        System.out.println(playerPanel.getSelectedName());
                        remove(playerPanel);
                        annotationLayout.setAnnotationLayout(playerPanel.getSelectedName());
                        setupFollowedPersonPanel();
                        setBackground(Color.red);
                        add(annotationLayout);
                        
                        repaint();
                        
                            
                }
            });
        }
        
        public void enableButtons() {
            annotationLayout.enableButtons();
        }
        
        public void disableButtons() {
            annotationLayout.disableButtons();
        }
        
       
        
        
        
        
        
}

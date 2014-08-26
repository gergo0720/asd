package reccos.futball.hirszerzo.c.userinterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.Y_AXIS;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jivesoftware.smack.packet.Message;
import reccos.futball.hirszerzo.c.business.Annotator;
import reccos.futball.hirszerzo.c.business.JabberSmackApi;
import static reccos.futball.hirszerzo.c.userinterface.PlayerSelectorPanel.isPlayerSelected;


public class WatchPanel extends JPanel {
	JPanel followedPersonPanel;
	PlayerSelectorPanel playerPanel;
	AnnotationLayouts annotationLayout;
	Dimension size = new Dimension(250,600);
        JButton back = new JButton("<<");
	
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
            back.setForeground(Color.WHITE);
            back.setFont(new Font("Monospace", Font.BOLD, 12));
            back.setFocusPainted(false);
            back.setPreferredSize(new Dimension(50,30));
            back.setBackground(new Color(64,64,64));
            followedPersonPanel.setPreferredSize(new Dimension(240,60));
            followedPersonPanel.setBackground(new Color(64,64,64));
            followedPersonPanel.setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            
            gc.gridx = 0;
            gc.gridy = 0;
            add(followedPersonPanel,gc);
            gc.gridx = 0;
            gc.gridy = 1;
            followedPersonPanel.add(back,gc);
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
                        setBackground(new Color(96,96,96));
                        Annotator.setPlayer(playerPanel.getSelectedName());
                        Annotator.setMatch("matchID");
                        isPlayerSelected = true;
                        System.out.println(playerPanel.getSelectedName());
                        annotationLayout.setAnnotationLayout(playerPanel.getSelectedName());
                        setupFollowedPersonPanel();
                        add(annotationLayout);
                        remove(playerPanel);
                        validate();
                        repaint();                
                }
            });
            
            back.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    removeAll();
                    followedPersonPanel.removeAll();
                    add(playerPanel);
                    validate();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reccos.futball.hirszerzo.c.userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.jivesoftware.smack.XMPPException;
import reccos.futball.hirszerzo.c.business.Annotator;
import reccos.futball.hirszerzo.c.business.JabberSmackApi;

/**
 *
 * @author koverg
 */
public class AnnotationLayouts extends JPanel implements ActionListener {
    String action = "no action";
    String qualifier = "good";
    String time = "no time";
    JPanel referee;
    JPanel players;
    String[] playerTitles = {"Passz", "Passz", "Csel", "Csel", "Szerel", "Szerel", "Lő", "Lő", "Helyezkedés", "Helyezkedés",
                        "Szabálytalan", "Szabálytalan"};
    String[] refereeTitles = {"Rossz döntés", "Jó döntés"};
    JButton[] buttons = new JButton[12];
    
    public AnnotationLayouts() {
        setPreferredSize(new Dimension(240, 500));
        setBackground(new Color(96,96,96));
        referee = new JPanel();
        players = new JPanel();
    }
    
    public void setAnnotationLayout(String layout) {
        removeAll();
        if(layout.equals("Jatekvezeto")) {
            System.out.println("ref");
            referee = new JPanel();
            refereeLayout();
        } else {
            System.out.println("player");
            players = new JPanel();
            playerLayout();
        }
    }
    
    private void playerLayout() {
        resetButtons(12);
        remove(players);
        remove(referee);
        validate();
        repaint();
        Image newImg;
        players.setPreferredSize(new Dimension(240, 500));
        //players.setBackground(Color.red);
        players.setLayout(new GridLayout(6,2));
        players.setBackground(new Color(96,96,96));
        for(Integer i = 0; i < 12; i++) {
            JButton button = new JButton(playerTitles[i]);
            buttons[i] = button;
            button.addActionListener(this);
            button.setVerticalTextPosition(SwingConstants.TOP);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Monospace", Font.BOLD, 12));
            button.setFocusPainted(false);
            button.setSize(new Dimension(83,50));
            button.setBackground(new Color(64,64,64));
            players.add(button);
            try {
            
                Image img = null;
                if(i%2==0) {
                    img = ImageIO.read(getClass().getResource("/notok.png"));
                } else {
                    img = ImageIO.read(getClass().getResource("/ok.png"));
                }
                newImg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(newImg));
            } catch (IOException ex) {
                
            }
        }
        if(!VideoFrame.isNowPlaying)
            disableButtons();
        add(players);
    }
    
    private void refereeLayout() {
        resetButtons(2);
        remove(players);
        remove(referee);
        validate();
        repaint();
        Image newImg;
        referee.setPreferredSize(new Dimension(240, 84));
        //players.setBackground(Color.red);
        referee.setLayout(new GridLayout(1,2));
        referee.setBackground(new Color(96,96,96));
        for(Integer i = 0; i < 2; i++) {
            JButton button = new JButton(refereeTitles[i]);
            buttons[i] = button;
            button.addActionListener(this);
            button.setVerticalTextPosition(SwingConstants.TOP);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Monospace", Font.BOLD, 12));
            button.setFocusPainted(false);
            button.setSize(new Dimension(83,50));
            button.setBackground(new Color(64,64,64));
            referee.add(button);
            try {
            
                Image img = null;
                if(i%2==0) {
                    img = ImageIO.read(getClass().getResource("/notok.png"));
                } else {
                    img = ImageIO.read(getClass().getResource("/ok.png"));
                }
                newImg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(newImg));
            } catch (IOException ex) {
                
            }
        }
        if(!VideoFrame.isNowPlaying)
            disableButtons();
        add(referee);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < buttons.length; i++) {
            if(e.getSource().equals(buttons[i])) {
                if(i % 2 == 0) {
                    qualifier = "bad";
                } else {
                    qualifier = "good";
                }
                time = SeekPanel.getMinutesMessage()+":"+SeekPanel.getSecondsMessage();
                setAction(buttons[i].getText());
                click(getAction(), qualifier, time);
            }
        }
    }
    
    public void enableButtons() {
        for(JButton b: buttons) {
            b.setEnabled(true);
        }
    }
    
    public void disableButtons() {
        for(JButton b: buttons) {
            b.setEnabled(false);
        }
    }

    private void resetButtons(int size) {
        buttons = new JButton[size];
    }
    
    public void click(String action,String qualifier, String time){
        try {
                if(action.compareTo("Játékvezetés") == 0)
                        Annotator.sendRefereeEvent(qualifier, time);
                else
                        Annotator.sendPlayerEvent(action, qualifier, time);
        } catch (XMPPException e) {
                JOptionPane.showMessageDialog(null, "Failed to send message: " + e.getMessage());
                JabberSmackApi.getInstance().login();
        } catch (IllegalStateException e) {
                JOptionPane.showMessageDialog(null, "Failed to send message: " + e.getMessage());
                try{
                        JabberSmackApi.getInstance().login();
                }
                catch(Exception ex){
                }
        }
    }
    
    public void setAction(String action){
		if(action.compareTo("Passz") == 0){
			this.action = "pass";
		}else if(action.compareTo("Csel") == 0){
			this.action = "dribble";
		}else if(action.compareTo("Szerel") == 0){
			this.action = "tackle";
		}else if(action.compareTo("Lő") == 0){
			this.action = "shoot";
		}else if(action.compareTo("Helyezkedés") == 0){
			this.action = "position";
                }else if(action.compareTo("Szabálytalan") == 0){
			this.action = "foul";
		}else if(action.compareTo("Jó döntés") == 0){
			this.action = "Játékvezetés";
                }else if(action.compareTo("Rossz döntés") == 0){
			this.action = "Játékvezetés";
		}else{
			this.action = "unknown";
		}
	}
	
    public String getAction(){
            return action;
    }
    
}

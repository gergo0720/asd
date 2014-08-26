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
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author koverg
 */
public class AnnotationLayouts extends JPanel {
    JPanel referee;
    JPanel players;
    
    public AnnotationLayouts() {
        setPreferredSize(new Dimension(240, 500));
        setBackground(new Color(128,128,128));
        referee = new JPanel();
        players = new JPanel();
    }
    
    public void setAnnotationLayout(String layout) {
        if(layout.equals("Jatekvezeto")) {
            System.out.println("ref");
            refereeLayout();
        } else {
            System.out.println("player");
            playerLayout();
        }
    }
    
    private void playerLayout() {
        Image newImg;
        players.setPreferredSize(new Dimension(240, 500));
        //players.setBackground(Color.red);
        players.setLayout(new GridLayout(6,2));
        for(Integer i = 0; i < 12; i++) {
            JButton button = new JButton("Felirat");
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
        add(players);
    }
    
    private void refereeLayout() {
        referee.setPreferredSize(new Dimension(240, 500));
        referee.setBackground(Color.pink);
        add(referee);
    }
}

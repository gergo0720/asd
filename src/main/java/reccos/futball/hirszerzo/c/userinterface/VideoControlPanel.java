/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reccos.futball.hirszerzo.c.userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author gergo
 */
public class VideoControlPanel extends JPanel {
    JButton play;
    JButton stop;
    JButton pause;
    JButton browse;
    
    public VideoControlPanel() {
        setSize(800, 100);
        setLayout(new GridLayout(1, 4, 5, 0));
        
        play = new JButton("Start");
        stop = new JButton("Stop");
        browse = new JButton("Tallózás");
        
        play.setForeground(Color.WHITE);
        play.setFont(new Font("Monospace", Font.BOLD, 12));
        play.setFocusPainted(false);
        play.setBackground(new Color(64,64,64));
        
        stop.setForeground(Color.WHITE);
        stop.setFont(new Font("Monospace", Font.BOLD, 12));
        stop.setFocusPainted(false);
        stop.setBackground(new Color(64,64,64));
        
        browse.setForeground(Color.WHITE);
        browse.setFont(new Font("Monospace", Font.BOLD, 12));
        browse.setFocusPainted(false);
        browse.setBackground(new Color(64,64,64));
        
        add(play);
        add(stop);
        add(browse);
        
    }
    
    public void disableButtons() {
        stop.setEnabled(false);
        play.setEnabled(false);
        browse.setEnabled(false);
    }
    
    public void enableButtons() {
        stop.setEnabled(true);
        play.setEnabled(true);
        browse.setEnabled(true);
    }
    
    
}

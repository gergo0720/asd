/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reccos.futball.hirszerzo.c.userinterface;

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
        
        add(play);
        add(stop);
        add(browse);
        
    }
    
    
}

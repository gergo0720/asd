/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reccos.futball.hirszerzo.c.userinterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 *
 * @author koverg
 */
public class seekPanel extends JPanel {
    JSlider seekSlider;
    Timer timer;
    JLabel showTimer;
    int hours = 0;
    int seconds = 0;
    int minutes = 0;
    int maxHours = 0;
    int maxSeconds = 0;
    int maxMinutes = 0;
    boolean timerChanged = false;
    public seekPanel() {
        setPreferredSize(new Dimension(400,50));
        showTimer = new JLabel();
        seekSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        seekSlider.setPreferredSize(new Dimension(300,50));
        add(seekSlider);
        add(showTimer);
    }
    
    public void configureSlider(int max) {
        seekSlider.setMinimum(0);
        seekSlider.setMaximum(max);
    }
    
    public void configureTimer(final EmbeddedMediaPlayer player) {
        
        
        timer = new Timer(0, new ActionListener() {
			
            public void actionPerformed(ActionEvent e) {
                
                    seconds = (int) ((player.getTime() / 1000) - ((player.getTime() / 1000) / 60) *60);
                    minutes = (int) (player.getTime() / 60000);
                    
                    if(player.getTime() == player.getLength()) {
                        player.stop();
                        //timer.restart();
                    }
                    
                    timerChanged = true;
                    seekSlider.setValue((int)player.getTime());
                    
                    showTime();
                    repaint();
            }
        });
        timer.setRepeats(true);
        timer.setInitialDelay(0);
        
        maxSeconds = (int) ((player.getLength() / 1000) - ((player.getLength() / 1000) / 60) *60);
        maxMinutes = (int) ((player.getLength() / 60000) - ((player.getLength() / 60000) / 60) *60);
        maxHours = (int) ((player.getLength() / 360000) - ((player.getLength() / 360000) / 60) *60);
    }
    
    public void timerStop() {
        timer.stop();
    }
    
    public void timerStart() {
        timer.start();
    }
    
    private void showTime() {
        showTimer.setText(hours + ":" + minutes + ":" + seconds + " / " + maxHours + ":" + maxMinutes + ":" + maxSeconds);
    }
    
}

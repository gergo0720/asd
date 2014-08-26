/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reccos.futball.hirszerzo.c.userinterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
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
public class SeekPanel extends JPanel {
    Component[] c;
    JSlider seekSlider;
    Timer timer;
    JLabel showTimer;
    Integer hours = 0;
    Integer seconds = 0;
    Integer minutes = 0;
    Integer maxHours = 0;
    Integer maxSeconds = 0;
    Integer maxMinutes = 0;
    String s = "00", m = "00", h = "00", ms, mm, mh;
    static String secondsMessage = "00";

    public static String getSecondsMessage() {
        return secondsMessage;
    }

    public static void setSecondsMessage(String secondsMessage) {
        SeekPanel.secondsMessage = secondsMessage;
    }

    public static String getMinutesMessage() {
        return minutesMessage;
    }

    public static void setMinutesMessage(String minutesMessage) {
        SeekPanel.minutesMessage = minutesMessage;
    }
    static String minutesMessage = "00";
    boolean timerChanged = false;
    public SeekPanel() {
        setPreferredSize(new Dimension(450,50));
        showTimer = new JLabel();
        seekSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        seekSlider.setPreferredSize(new Dimension(300,50));
        add(seekSlider);
        add(showTimer);
        showTime();
    }
    
    public void configureSlider(int min, int max) {
        seekSlider.setMinimum(min);
        seekSlider.setMaximum(max);
    }
    
    public void configureTimer(final EmbeddedMediaPlayer player) {
        maxSeconds = (int) ((player.getLength() / 1000) - ((player.getLength() / 1000) / 60) *60);
        maxMinutes = (int) ((player.getLength() / 60000) - ((player.getLength() / 60000) / 60) *60);
        maxHours = (int) ((player.getLength() / 3600000) - ((player.getLength() / 3600000) / 60) *60);
    }
    
    public void timerStop() {
        timer.stop();
    }
    
    public void timerStart() {
        timer.start();
    }
    
    public void showTime() {
        
        ms = maxSeconds > 9 ? maxSeconds.toString() : "0"+maxSeconds;
        mm = maxMinutes > 9 ? maxMinutes.toString() : "0"+maxMinutes;
        mh = maxHours > 9 ? maxHours.toString() : "0"+maxHours;
        
        showTimer.setText(h + ":" + m + ":" + s + " / " + mh + ":" + mm + ":" + ms);
        showTimer.setForeground(Color.WHITE);
        showTimer.setFont(new Font("Monospace", Font.BOLD, 12));
    }
    
    public void disableSlider() {
        c = getComponents();
        for(Component comp: c) {
            comp.setEnabled(false);
        }
    }
    
    public void enableSlider() {
        c = getComponents();
        for(Component comp: c) {
            comp.setEnabled(true);
        }
    }
}

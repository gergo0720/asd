/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reccos.futball.hirszerzo.c.userinterface;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import uk.co.caprica.vlcj.component.EmbeddedMediaListPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;

/**
 *
 * @author koverg
 */
public class VideoFrame{
    JPanel videoPanel;
    JPanel controllPanel;
    EmbeddedMediaPlayer player;
    Canvas canvas;
    WatchPanel watchPanel;
    VideoControlPanel videoControlPanel;
    JMenuBar menubar;
    JMenu menu;
    JMenuItem item;

    public VideoFrame(JFrame f) {
        canvas = new Canvas();
        videoPanel = new JPanel();
        controllPanel = new JPanel();
        videoControlPanel = new VideoControlPanel();

        
        
        final MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        player = mediaPlayerFactory.newEmbeddedMediaPlayer();
        CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
        player.setVideoSurface(videoSurface);
        
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.X_AXIS));
        int vidPanelWidth = 300;
        int vidPanelHeight = 400;
               
        JPanel vidpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        JPanel controlpanel = new JPanel();
        JPanel vidandcontrol = new JPanel();
        vidandcontrol.setLayout(new BoxLayout(vidandcontrol, BoxLayout.Y_AXIS));
        vidandcontrol.setPreferredSize(new Dimension(800,100));
        vidpanel.setPreferredSize(new Dimension(800, 500));
        buttonpanel.setPreferredSize(new Dimension(250,500));
        
        canvas.setSize(new Dimension(800,500));
        //videoPanel.setLocation(0, 0);
        vidpanel.add(canvas);
        //vidpanel.setBackground(Color.RED);
        buttonpanel.setBackground(Color.BLUE);
        watchPanel = new WatchPanel(new Dimension(250,600));
        buttonpanel.add(watchPanel);
        //controlpanel.setBackground(Color.YELLOW);
        controlpanel.add(videoControlPanel);
        vidandcontrol.setBackground(Color.MAGENTA);
       
        vidandcontrol.add(vidpanel);
        vidandcontrol.add(controlpanel);
                
        
       
        f.add(vidandcontrol);
        f.add(buttonpanel);
        
        
        addListeners();
    }
    public void playVideo() {
        player.playMedia("D:\\TBBT\\Noah.2014.BDRip.XviD.HUN-ZHR\\Sample\\zhr-noah.avi");
    }
    
    public void stopVideo() {
        player.stop();
    }
    
    public void pauseVideo() {
        player.pause();
    }
    
    private void videoBroweser() {
        
    }
    
    private void addListeners() {
        videoControlPanel.play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                playVideo();
            }
        });
        
        videoControlPanel.stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               stopVideo();
            }
        });
        
        videoControlPanel.pause.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               pauseVideo();
            }
        });
    }
    
}


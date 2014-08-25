/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reccos.futball.hirszerzo.c.userinterface;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    JPanel vidpanel;
    JPanel buttonpanel;
    JPanel vidandcontrol;
    JPanel controlpanel;
    static Integer startCounter = 1;
    JLabel blank;
    ImageIcon imageIcon;
    String video = null;

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
               
        vidpanel = new JPanel();
        buttonpanel = new JPanel();
        controlpanel = new JPanel();
        vidandcontrol = new JPanel();
        vidandcontrol.setLayout(new BoxLayout(vidandcontrol, BoxLayout.Y_AXIS));
        vidandcontrol.setPreferredSize(new Dimension(800,100));
        vidpanel.setPreferredSize(new Dimension(800, 500));
        buttonpanel.setLayout(new GridLayout(1,1));
        buttonpanel.setPreferredSize(new Dimension(250,500));
        setupBlankkScreen();
        canvas.setSize(new Dimension(800,500));
        //videoPanel.setLocation(0, 0);
        vidpanel.add(canvas);
        canvas.setVisible(false);
        //vidpanel.setBackground(Color.RED);
        //buttonpanel.setBackground(Color.BLUE);
        //JLabel l = new JLabel("hey there");
        //buttonpanel.add(l);
        watchPanel = new WatchPanel();
        buttonpanel.add(watchPanel);
        //controlpanel.setBackground(Color.YELLOW);
        controlpanel.add(videoControlPanel);
        //vidandcontrol.setBackground(Color.MAGENTA);
       
        vidandcontrol.add(vidpanel);
        vidandcontrol.add(controlpanel);
                
        
       
        f.add(vidandcontrol);
        f.add(watchPanel);
        
        
        addListeners(f);
    }
    public void playVideo() {
        player.playMedia(video);
    }
    
    public void stopVideo() {
        player.stop();
    }
    
    public void pauseVideo() {
        player.pause();
    }
    
    private void videoBroweser() {
        
    }
    
    private void addListeners(final JFrame f) {
        videoControlPanel.play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(PlayerSelectorPanel.isPlayerSelected) {    
                    if(video != null) {    
                        while(startCounter != 0) {  
                            if(!player.isPlaying()) {
                                blank.setVisible(false);
                                canvas.setVisible(true);
                                playVideo();
                                startCounter--;
                            }
                        }

                        if(player.isPlaying()) {
                            pauseVideo();
                            System.out.println(player.getLength());
                            videoControlPanel.play.setText("Start");
                        } else if(!player.isPlaying()) {
                            pauseVideo();
                            videoControlPanel.play.setText("Szünet");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Még nem választott videót, a tallózás gomb segítségével tölthet be videót.");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Még nem választott követendő személyt, a jobboldali listából választhat.");
                    return;
                }
            }
        });
        
        videoControlPanel.stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                stopVideo();
                startCounter = 1;
                videoControlPanel.play.setText("Start");            
                canvas.setVisible(false);
                blank.setVisible(true);
            }
        });
        
        videoControlPanel.browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int resultVal = fileChooser.showOpenDialog(f);
                if(resultVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    video = selectedFile.getAbsoluteFile().toString();
                }
            }
        });
    }
    
    private void setupBlankkScreen() {
        imageIcon = new ImageIcon(getClass().getResource("/logo300x300op.png"));
        //Image image = imageicon.getImage();
        blank = new JLabel(imageIcon);
        vidpanel.add(blank);
    }
 
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reccos.futball.hirszerzo.c.userinterface;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    static Integer startCounter = 1;
    static Boolean playing = true;
    JLabel blank;
    ImageIcon imageIcon;
    String video = null;
    SeekPanel seekPanel;
    GridBagConstraints gc;
    
    

    public VideoFrame(JFrame f) {
        canvas = new Canvas();
        videoPanel = new JPanel();
        controllPanel = new JPanel();
        videoControlPanel = new VideoControlPanel();
        seekPanel = new SeekPanel();
        
        final MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        player = mediaPlayerFactory.newEmbeddedMediaPlayer();
        CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
        player.setVideoSurface(videoSurface);
        
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.X_AXIS));
        int vidPanelWidth = 300;
        int vidPanelHeight = 400;
               
        vidpanel = new JPanel();
        buttonpanel = new JPanel();
        controllPanel = new JPanel();
        vidandcontrol = new JPanel();
        vidandcontrol.setBackground(new Color(128,128,128));
        buttonpanel.setBackground(new Color(128,128,128));
        vidpanel.setBackground(new Color(128,128,128));
        controllPanel.setBackground(new Color(96,96,96));
        videoControlPanel.setBackground(new Color(96,96,96));
        seekPanel.setBackground(new Color(96,96,96));
        seekPanel.seekSlider.setBackground(new Color(96,96,96));
        vidandcontrol.setLayout(new BoxLayout(vidandcontrol, BoxLayout.Y_AXIS));
        vidandcontrol.setPreferredSize(new Dimension(780,600));
        vidpanel.setPreferredSize(new Dimension(780, 500));
        vidpanel.setLayout(new GridBagLayout());
        buttonpanel.setLayout(new GridLayout(1,1));
        buttonpanel.setPreferredSize(new Dimension(250,500));
        controllPanel.setLayout(new FlowLayout());
        gc = new GridBagConstraints();
        
        setupBlankScreen();
        canvas.setPreferredSize(new Dimension(780,500));
        vidpanel.add(canvas);
        canvas.setVisible(false);
        
        watchPanel = new WatchPanel();
        buttonpanel.add(watchPanel);
        //controlpanel.setBackground(Color.YELLOW);
        controllPanel.add(videoControlPanel);
        controllPanel.add(seekPanel);
        //vidandcontrol.setBackground(Color.MAGENTA);
       
        vidandcontrol.add(vidpanel);
        vidandcontrol.add(controllPanel);
                
        f.add(vidandcontrol);
        f.add(watchPanel);
        
        
        addListeners(f);
    }
    public void playVideo() {
        player.playMedia(video);
        
    }
    
    public void stopVideo() {
        if(player.isPlaying()) {    
            player.stop();
            seekPanel.timerStop();
            seekPanel.timerChanged = false;
        }
    }
    
    public void pauseVideo() {
        player.pause();
    }
    
    private void videoBroweser(JFrame f) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int resultVal = fileChooser.showOpenDialog(f);
        if(resultVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            video = selectedFile.getAbsoluteFile().toString();
        }
    }
    
    private void addListeners(final JFrame f) {
        videoControlPanel.play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(PlayerSelectorPanel.isPlayerSelected) {    
                    if(video != null) {    
                        while(startCounter != 0) {  
                            if(!player.isPlaying()) {
                                setupInitialPlay();
                            }
                        }

                        if(player.isPlaying()) {
                            pauseVideo();
                            videoControlPanel.play.setText("Start");
                            seekPanel.timerStop();
                        } else if(!player.isPlaying()) {
                            pauseVideo();
                            videoControlPanel.play.setText("Szünet");
                            seekPanel.timerStart();
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
                seekPanel.configureSlider(0, (int) player.getLength());
                startCounter = 1;
                videoControlPanel.play.setText("Start");            
                canvas.setVisible(false);
                blank.setVisible(true);
            }
        });
        
        videoControlPanel.browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!player.isPlaying()) {
                    videoBroweser(f);
                } else {
                    JOptionPane.showMessageDialog(null, "Kérem először állítsa meg a videót.");
                }
            }
        });
        
        seekPanel.timer = new Timer(0, new ActionListener() {
			
            public void actionPerformed(ActionEvent e) {
                
                    seekPanel.seconds = (int) ((player.getTime() / 1000) - ((player.getTime() / 1000) / 60) *60);
                    seekPanel.s = seekPanel.seconds > 9 ? seekPanel.seconds.toString() : "0"+seekPanel.seconds;
                    seekPanel.minutes = (int) ((player.getTime() / 60000) - ((player.getTime() / 60000) / 60) *60);
                    seekPanel.m = seekPanel.minutes > 9 ? seekPanel.minutes.toString() : "0"+seekPanel.minutes;
                    seekPanel.hours = (int) ((player.getTime() / 3600000) - ((player.getTime() / 3600000) / 60) *60);
                    seekPanel.h = seekPanel.hours > 9 ? seekPanel.hours.toString() : "0"+seekPanel.hours;
                    
                    if((seekPanel.hours.equals(seekPanel.maxHours)) &&
                            (seekPanel.minutes.equals(seekPanel.maxMinutes)) &&
                            (seekPanel.seconds.equals(seekPanel.maxSeconds))) {
                        stopVideo();
                        seekPanel.configureSlider(0, (int) player.getLength());
                        startCounter = 1;
                        videoControlPanel.play.setText("Start");            
                        canvas.setVisible(false);
                        blank.setVisible(true);
                    }
                    
                    seekPanel.timerChanged = true;
                    if(player.isPlaying() && VideoFrame.playing) {
                        seekPanel.seekSlider.setValue((int)player.getTime());
                    }
                    
                    seekPanel.showTime();
                    seekPanel.repaint();
            }
        });
        seekPanel.timer.setRepeats(true);
        seekPanel.timer.setInitialDelay(0);
    }
    
    private void setupBlankScreen() {
        imageIcon = new ImageIcon(getClass().getResource("/logo300x300op.png"));
        //Image image = imageicon.getImage();
        blank = new JLabel(imageIcon);
        vidpanel.add(blank);
    }
    
    private void setupSeekPanel() {
        try {
            Thread.sleep(100);
            seekPanel.configureSlider(0,(int)player.getLength());
           
        } catch (InterruptedException ex) {
            Logger.getLogger(VideoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            seekPanel.seekSlider.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                playing=false;
                seekPanel.seekSlider.setValue((int)player.getLength() / seekPanel.seekSlider.getSize().width * e.getX());
                player.setTime(seekPanel.seekSlider.getValue());
                sliderListener();
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                playing=true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    public void sliderListener() {
        seekPanel.seekSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if(!VideoFrame.playing)
                    player.setTime(seekPanel.seekSlider.getValue());
            }
        });
    }
    
    private void setupInitialPlay() {
        blank.setVisible(false);
        canvas.setVisible(true);
        playVideo();
        startCounter--;
        setupSeekPanel();
        seekPanel.configureTimer(player);
    }
}


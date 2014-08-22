/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reccos.futball.hirszerzo.c.userinterface;

import javax.swing.JFrame;
import uk.co.caprica.vlcj.component.EmbeddedMediaListPlayerComponent;

/**
 *
 * @author koverg
 */
public class VideoFrame extends JFrame {
    
    private final EmbeddedMediaListPlayerComponent mediaPlayerComponent;
    
    public VideoFrame(JFrame f) {
        mediaPlayerComponent = new EmbeddedMediaListPlayerComponent();
        
        f.setContentPane(mediaPlayerComponent);
        
        f.setLocation(0, 0);
        f.setSize(800,600);
        
        mediaPlayerComponent.getMediaPlayer().playMedia(null);
    }
    
}

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
    JFrame frame = null;
    public VideoFrame(JFrame f) {
        setLocation(0,0);
        setSize(800,600);
        
        
        mediaPlayerComponent = new EmbeddedMediaListPlayerComponent();
        
        setContentPane(mediaPlayerComponent);
        setVisible(true);
        
        
        mediaPlayerComponent.getMediaPlayer().playMedia("C:\\Users\\koverg\\Downloads\\Helicopter_DivXHT_ASP.divx");
        setFrame(f);
    }
    
    private void setFrame(JFrame frame) {
        frame.add(this);
    }
    
}


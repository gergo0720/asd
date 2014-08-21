/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reccos.futball.hirszerzo.c.userinterface;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author koverg
 */
public class WelcomePanel extends JTabbedPane{
    JTabbedPane tab;
    
    public WelcomePanel(JPanel p1, JPanel p2, JFrame f1) {
        tab = new JTabbedPane();
        tab.addTab("Bejelentkezés", p1);
        tab.addTab("Regisztráció", p2);
        f1.add(tab);
    }
}

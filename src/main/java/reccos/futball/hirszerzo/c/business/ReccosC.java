package reccos.futball.hirszerzo.c.business;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import reccos.futball.hirszerzo.c.userinterface.LoginPanel;
import reccos.futball.hirszerzo.c.userinterface.RegisterPanel;
import reccos.futball.hirszerzo.c.userinterface.VideoFrame;
import reccos.futball.hirszerzo.c.userinterface.WatchPanel;
import reccos.futball.hirszerzo.c.userinterface.WelcomePanel;


public class ReccosC extends JFrame {
	
	LoginPanel loginPanel;
	WatchPanel watchPanel;
	RegisterPanel registerPanel;
        WelcomePanel welcome;
        JFrame frame;
	
	public ReccosC() {
		setupGUI();
		addListeners();
	}
	
	private void setupGUI(){
		frame = this;
		try{
			Image icon = ImageIO.read(getClass().getResourceAsStream("/logo100x100.png"));
			setIconImage(icon);	
		}catch(IOException e){
		}
		
		requestFocusInWindow();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(400, 540);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Futball hírszerző");
                
		watchPanel = new WatchPanel(getSize());
		loginPanel = new LoginPanel();
		registerPanel = new RegisterPanel(getSize());
                welcome = new WelcomePanel(loginPanel, registerPanel, this);
	}
	
	private void addListeners(){
		
		addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				JabberSmackApi.getInstance().disconnect();
				System.exit(0);
			}
			
		});
		
		addComponentListener(new ComponentAdapter() {
			
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				watchPanel.setPreferredSize(getSize());
			}
		});
		
		loginPanel.loginButton.addActionListener(new ActionListener() {
                        
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
//		loginPanel.registerButton.addActionListener(new ActionListener() {
//                        
//			public void actionPerformed(ActionEvent e) {
//				remove(loginPanel);
//				add(registerPanel);
//				validate();
//				repaint();
//			}
//		});
		
//		registerPanel.backButton.addActionListener(new ActionListener() {
//                        @Override
//			public void actionPerformed(ActionEvent e) {
//				remove(registerPanel);
//				add(loginPanel);
//				validate();
//				repaint();
//			}
//		});
		
		registerPanel.registerButton.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		
		watchPanel.addPropertyChangeListener("back", new PropertyChangeListener() {
			
                        @Override
			public void propertyChange(PropertyChangeEvent evt) {
				remove(watchPanel);
				add(loginPanel);
				validate();
				repaint();
			}
		});
	}
	
	public void connect(){
		JabberSmackApi.getInstance().connect();
	}
	
	private void login(){
		JabberSmackApi.getInstance().login(loginPanel.getEmail(),
				loginPanel.getPassword(),
				new PropertyChangeListener() {
			
                        
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().compareTo("login") == 0 && (Boolean) evt.getNewValue() == true){
					getContentPane().removeAll();
					setSize(1050,600);
                                        setLocationRelativeTo(null);
                                        VideoFrame vf = new VideoFrame(frame);
					validate();
					repaint();
				}
			}
		});
	}
	
	private void register(){
            
		if(!registerPanel.checkEmail())
			return;
		if(!registerPanel.checkPasswords())
			return;

		JabberSmackApi.getInstance().register(registerPanel.getEmail(),
                    registerPanel.getPassword(),
                    registerPanel.getSex(),
                    registerPanel.getYear(),
                    registerPanel.getMonth(),
                    registerPanel.getDay(),
                    new PropertyChangeListener() {
		
                        
			public void propertyChange(PropertyChangeEvent evt) {
                            System.out.println(evt.getNewValue().toString());
				if(evt.getNewValue().toString().compareTo("success")==0 ){
                                        System.out.println("ASD");
					getContentPane().removeAll();
					loginPanel.userNameField.setText(registerPanel.getEmail());
					loginPanel.passwordField.setText(registerPanel.getPassword());
					loginPanel.passwordField.setEchoChar('*');
					add(loginPanel);
				}else if(evt.getNewValue().toString().compareTo("used")==0 ){
					registerPanel.email.setBackground(Color.RED);
					JOptionPane.showMessageDialog(null, "Ez az e-mail cím már foglalt!");
				}
				validate();
				repaint();
			}
		});
	}
}

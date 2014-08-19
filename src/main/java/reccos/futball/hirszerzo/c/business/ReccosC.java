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

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import reccos.futball.hirszerzo.c.userinterface.LoginPanel;
import reccos.futball.hirszerzo.c.userinterface.RegisterPanel;
import reccos.futball.hirszerzo.c.userinterface.WatchPanel;


public class ReccosC extends JFrame {
	
	LoginPanel loginPanel;
	WatchPanel watchPanel;
	RegisterPanel registerPanel;
	
	public ReccosC() {
		setupGUI();
		addListeners();
	}
	
	private void setupGUI(){
		
		try{
			Image icon = ImageIO.read(getClass().getResourceAsStream("/logo100x100.png"));
			setIconImage(icon);	
		}catch(Exception e){
		}
		
		requestFocusInWindow();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(340, 440);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Futball hírszerző");
		watchPanel = new WatchPanel(getSize());
		loginPanel = new LoginPanel();
		registerPanel = new RegisterPanel(getSize());
		add(loginPanel);
	}
	
	private void addListeners(){
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				JabberSmackApi.getInstance().disconnect();
				System.exit(0);
			}
			
		});
		
		addComponentListener(new ComponentAdapter() {
			@Override
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
		
		loginPanel.registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(loginPanel);
				add(registerPanel);
				validate();
				repaint();
			}
		});
		
		registerPanel.backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(registerPanel);
				add(loginPanel);
				validate();
				repaint();
			}
		});
		
		registerPanel.registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		
		watchPanel.addPropertyChangeListener("back", new PropertyChangeListener() {
			
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
					remove(loginPanel);
					add(watchPanel);
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
				if(evt.getNewValue().toString().compareTo("success")==0 ){
					remove(registerPanel);
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

package reccos.futball.hirszerzo.c.userinterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class LoginPanel extends JPanel {
	
	public JTextField userNameField = new JTextField("E-mail cím");
	public JPasswordField passwordField = new JPasswordField("Jelszó");
	public JButton loginButton = new JButton("Bejelentkezés");
	public JButton registerButton = new JButton("Regisztráció");
	
	
	public LoginPanel() {
		setBackground(new Color(243,244,247));
		setupGUI();
		setupListeners();
	}
	
	private void setupGUI(){

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel imagePanel = new JPanel();
		JPanel otherPanel = new JPanel();
		JPanel textPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(getClass().getResourceAsStream("/logo300x300.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			imagePanel.add(picLabel);
		} catch (IOException e) {
			imagePanel.setPreferredSize(new Dimension(getSize().width, 300));
		}catch (IllegalArgumentException e) {
			imagePanel.setPreferredSize(new Dimension(getSize().width, 300));
		}
		textPanel.setBorder(new EmptyBorder(0,15,0,15));
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		textPanel.add(userNameField);
		textPanel.add(passwordField);		
		buttonPanel.add(loginButton);
		buttonPanel.add(registerButton);
		
		otherPanel.setLayout(new BoxLayout(otherPanel, BoxLayout.Y_AXIS));
		otherPanel.setBorder(new EmptyBorder(0, 20, 10, 20));
		otherPanel.add(textPanel);
		otherPanel.add(buttonPanel);
		
		add(imagePanel);
		add(otherPanel);
	}
	
	private void setupListeners(){
		passwordField.setEchoChar((char)0);

		userNameField.addFocusListener(new FocusListener() {
            public void focusLost(final FocusEvent e) {
            	userNameField.select(0, 0);

            }
            public void focusGained(final FocusEvent e) {
            	userNameField.selectAll();
            }
        });
		passwordField.addFocusListener(new FocusListener() {
			
			public void focusLost(FocusEvent e) {
				passwordField.select(0, 0);
				if(passwordField.getPassword().length == 0){ 
					passwordField.setEchoChar((char)0);
					passwordField.setText("Jelszó");
				}
			}
			
			public void focusGained(FocusEvent e) {
				String text = new String(passwordField.getPassword());
				passwordField.setBackground(Color.white);
				if(text.compareTo("Jelszó") != 0 || passwordField.getEchoChar() != (char)0)
					return;
				passwordField.setText("");
				passwordField.setEchoChar('*');
			}
		});
	}
	
	public String getEmail(){
		return userNameField.getText();
	}
	
	public String getPassword(){
		return new String(passwordField.getPassword());
	}
}

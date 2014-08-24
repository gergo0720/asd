package reccos.futball.hirszerzo.c.userinterface;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
	
        public JLabel robom = new JLabel("Futball-Hírszerző");
        public JLabel emailLabel = new JLabel("E-mail cím");
	public JTextField userNameField = new JTextField("E-mail cím");
        public JLabel passwordLabel = new JLabel("Jelszó");
	public JPasswordField passwordField = new JPasswordField("Jelszó");
	public JButton loginButton = new JButton("Bejelentkezés");
	public JButton registerButton = new JButton("Regisztráció");
	
	public LoginPanel() {
                super();
                
		//setBackground(new Color(243,244,247));
		setupGUI();
		setupListeners();
	}
        
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            ImageIcon imageicon = new ImageIcon(getClass().getResource("/logo300x300op.png"));
            Image image = imageicon.getImage();
            super.paintComponent(g2d);
            
            if(image != null) {
                g2d.drawImage(image, getWidth()/2-150, getHeight()/2-150, 300, 300, this);
            }
        }
        
        
        
	private void setupGUI() {
            setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            
            gc.gridx = 0;
            gc.gridy = 0;
            robom.setFont(new Font("Arial", Font.PLAIN, 42));
            add(robom, gc);
            
            gc.insets = new Insets(120, 0, 0, 260);
            gc.gridx = 0;
            gc.gridy = 1;
            add(emailLabel, gc);
            
            gc.insets = new Insets(5, 0, 0, 0);
            gc.gridx = 0;
            gc.gridy = 2;
            gc.weightx = 1;   
            userNameField.setPreferredSize(new Dimension(320,30));
            add(userNameField, gc);
            
            gc.insets = new Insets(20, 0, 0, 283);
            gc.gridx = 0;
            gc.gridy = 3;
            add(passwordLabel, gc);
            
            gc.insets = new Insets(5, 0, 50, 0);
            gc.gridx = 0;
            gc.gridy = 4;
            passwordField.setPreferredSize(new Dimension(320,30));
            add(passwordField, gc);
            
            gc.gridx = 0;
            gc.gridy = 5;
            loginButton.setPreferredSize(new Dimension(320,30));
            add(loginButton, gc);
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

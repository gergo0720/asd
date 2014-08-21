package reccos.futball.hirszerzo.c.userinterface;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
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
	
        public JLabel emailLabel = new JLabel("E-mail cím");
	public JTextField userNameField = new JTextField("E-mail cím");
        public JLabel passwordLabel = new JLabel("Jelszó");
	public JPasswordField passwordField = new JPasswordField("Jelszó");
	public JButton loginButton = new JButton("Bejelentkezés");
	public JButton registerButton = new JButton("Regisztráció");
	public static JButton b = new JButton("ASD");
        public static JButton b2 = new JButton("ASD2");
	
	public LoginPanel() {
                super();
                
		//setBackground(new Color(243,244,247));
		setupGUI();
		setupListeners();
	}
        
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
            g2d.setComposite(composite);
            ImageIcon imageicon = new ImageIcon(getClass().getResource("/logo300x300.png"));
            Image image = imageicon.getImage();
            super.paintComponent(g2d);
            
            if(image != null) {
                g2d.drawImage(image, getWidth()/2-150, getHeight()/2-150, 300, 300, this);
            }
            add(b);
            add(b2);
            b.requestFocusInWindow();
            b2.requestFocusInWindow();
        }
        
        
        
	private void setupGUI() {
            setLayout(new GridBagLayout());
            GridBagConstraints grid = new GridBagConstraints();
            
            
            
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
//	private void setupGUI(){
//          
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		JPanel imagePanel = new JPanel();
//		JPanel otherPanel = new JPanel();
//		JPanel textPanel = new JPanel();
//		JPanel buttonPanel = new JPanel();
//		
//		BufferedImage myPicture = null;
//		try {
//			myPicture = ImageIO.read(getClass().getResourceAsStream("/logo300x300.png"));
//			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//			imagePanel.add(picLabel);
//		} catch (IOException e) {
//			imagePanel.setPreferredSize(new Dimension(getSize().width, 300));
//		}catch (IllegalArgumentException e) {
//			imagePanel.setPreferredSize(new Dimension(getSize().width, 300));
//		}
//		
//                userNameField.setPreferredSize(new Dimension(80,20));
//                passwordField.setPreferredSize(new Dimension(80,20));
//                textPanel.setBorder(new EmptyBorder(0,15,0,15));
//		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
//                textPanel.add(emailLabel);
//		textPanel.add(userNameField);
//                textPanel.add(passwordLabel);
//		textPanel.add(passwordField);		
//		buttonPanel.add(loginButton);
//		//buttonPanel.add(registerButton);
//		
//		otherPanel.setLayout(new BoxLayout(otherPanel, BoxLayout.Y_AXIS));
//		otherPanel.setBorder(new EmptyBorder(0, 20, 10, 20));
//		otherPanel.add(textPanel);
//		otherPanel.add(buttonPanel);
//		
//		//add(imagePanel);
//		add(otherPanel);
//	}
//	
//	private void setupListeners(){
//		passwordField.setEchoChar((char)0);
//
//		userNameField.addFocusListener(new FocusListener() {
//            public void focusLost(final FocusEvent e) {
//            	userNameField.select(0, 0);
//
//            }
//            public void focusGained(final FocusEvent e) {
//            	userNameField.selectAll();
//            }
//        });
//		passwordField.addFocusListener(new FocusListener() {
//			
//			public void focusLost(FocusEvent e) {
//				passwordField.select(0, 0);
//				if(passwordField.getPassword().length == 0){ 
//					passwordField.setEchoChar((char)0);
//					passwordField.setText("Jelszó");
//				}
//			}
//			
//			public void focusGained(FocusEvent e) {
//				String text = new String(passwordField.getPassword());
//				passwordField.setBackground(Color.white);
//				if(text.compareTo("Jelszó") != 0 || passwordField.getEchoChar() != (char)0)
//					return;
//				passwordField.setText("");
//				passwordField.setEchoChar('*');
//			}
//		});
//	}
	
	public String getEmail(){
		return userNameField.getText();
	}
	
	public String getPassword(){
		return new String(passwordField.getPassword());
	}
}

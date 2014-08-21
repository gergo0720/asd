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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegisterPanel extends JPanel {
	
	JPanel textPanel = new JPanel();
	JPanel sexPanel  = new JPanel();
	JPanel datePanel = new JPanel();
	JPanel buttonPanel  = new JPanel();
	
        public JLabel robom = new JLabel("Futball-Hírszerző");
        public JLabel emailLabel = new JLabel("E-mail cím");
	public JTextField email = new JTextField("E-mail cím");
        public JLabel pwLabel = new JLabel("Jelszó");
	JPasswordField pwField = new JPasswordField("Jelszó");
        public JLabel pwAgainLabel = new JLabel("Jelszó");
	JPasswordField pwAgain = new JPasswordField("Jelszó mégegyszer");
	
	JLabel sexLabel = new JLabel("Nem");
	ButtonGroup sexGroup = new ButtonGroup();
	JRadioButton isMale = new JRadioButton("Férfi", true);
	JRadioButton isFemale = new JRadioButton("Nő", false);
	
	JLabel dateLabel = new JLabel("Születési dátum");
	JComboBox<String> yearBox = new JComboBox<String>();
	JComboBox<String> monthBox = new JComboBox<String>();
	JComboBox<String> dayBox = new JComboBox<String>();
	
	public JButton registerButton = new JButton("Regisztráció");
	public JButton backButton = new JButton("Vissza");
	
	public RegisterPanel(Dimension size) {
		setPreferredSize(size);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		setupGUI();
	}
        
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            ImageIcon imageicon = new ImageIcon(getClass().getResource("/logo300x300op.png"));
            Image image = imageicon.getImage();
            super.paintComponent(g2d);
            
            if(image != null) {
                g2d.drawImage(image, getWidth()/2-150, getHeight()/2-150, 300, 300, this);
            }
        } 
	
	private void setupGUI(){
                
                setupComboBoxes();
                setupTextFields();
                
                setLayout(new GridBagLayout());
                GridBagConstraints gc = new GridBagConstraints();
                
                gc.fill = GridBagConstraints.HORIZONTAL;
                
                gc.insets = new Insets(20, 35, 30, 0);
                gc.gridx = 0;
                gc.gridy = 0;
                gc.weightx = 0.2;
                gc.gridwidth = 2;
                robom.setFont(new Font("Arial", Font.PLAIN, 42));
                add(robom, gc);
                
                gc.insets = new Insets(20, 30, 0, 0);
                gc.gridx = 0;
                gc.gridy = 1;
                gc.gridwidth = 1;
                add(emailLabel, gc);
                
                gc.insets = new Insets(20, 0, 0, 30);
                gc.gridx = 1;
                gc.gridy = 1;
                gc.gridwidth = 1;
                email.setPreferredSize(new Dimension(150,50));
                add(email, gc);
                
                gc.insets = new Insets(20, 30, 0, 0);
                gc.gridx = 0;
                gc.gridy = 2;
                gc.gridwidth = 1;
                add(pwLabel, gc);
                
                gc.insets = new Insets(20, 0, 0, 30);
                gc.gridx = 1;
                gc.gridy = 2;
                gc.gridwidth = 1;
                pwField.setPreferredSize(new Dimension(150,30));
                add(pwField, gc);
                
                gc.insets = new Insets(20, 30, 0, 0);
                gc.gridx = 0;
                gc.gridy = 3;
                gc.gridwidth = 1;
                add(pwAgainLabel, gc);
                
                gc.insets = new Insets(20, 0, 0, 30);
                gc.gridx = 1;
                gc.gridy = 3;
                gc.gridwidth = 1;
                pwAgain.setPreferredSize(new Dimension(150,30));
                add(pwAgain, gc);
                
                gc.insets = new Insets(20, 30, 0, 0);
                gc.weightx = 0.5;
                gc.gridx = 0;
                gc.gridy = 4;
                gc.gridwidth = 1;
                add(sexLabel, gc);
                
                gc.insets = new Insets(20, 0, 0, 150);
                gc.weightx = 0.2;
                gc.gridx = 1;
                gc.gridy = 4;
                gc.gridwidth = 1;
                //isFemale.setPreferredSize(new Dimension(250,30));
                add(isFemale, gc);
                
                gc.insets = new Insets(10, 0, 0, 150);
                gc.weightx = 0.2;
                gc.gridx = 1;
                gc.gridy = 5;
                gc.gridwidth = 1;
                //isFemale.setPreferredSize(new Dimension(250,30));
                add(isMale, gc);
                
                gc.insets = new Insets(20, 30, 0, 0);
                gc.weightx = 0.5;
                gc.gridx = 0;
                gc.gridy = 6;
                gc.gridwidth = 1;
                add(dateLabel, gc);
                
                gc.insets = new Insets(20, 0, 0, 150);
                gc.weightx = 0.2;
                gc.gridx = 1;
                gc.gridy = 6;
                gc.gridwidth = 1;
                //isFemale.setPreferredSize(new Dimension(250,30));
                add(yearBox, gc);
                
                gc.insets = new Insets(10, 0, 0, 150);
                gc.weightx = 0.2;
                gc.gridx = 1;
                gc.gridy = 7;
                gc.gridwidth = 1;
                //isFemale.setPreferredSize(new Dimension(250,30));
                add(monthBox, gc);
                
                gc.insets = new Insets(10, 0, 0, 150);
                gc.weightx = 0.2;
                gc.gridx = 1;
                gc.gridy = 8;
                gc.gridwidth = 1;
                //isFemale.setPreferredSize(new Dimension(250,30));
                add(dayBox, gc);
                
                
                gc.insets = new Insets(20, 30, 0, 30);
                gc.gridx = 0;
                gc.gridy = 9;
                gc.gridwidth = 2;
                registerButton.setPreferredSize(new Dimension(320,30));
                add(registerButton, gc);
                
//		textPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//		textPanel.setLayout(new GridBagLayout());
//		sexPanel.setBorder(new EmptyBorder(10, 20, 0, 20));
//		sexPanel.setLayout(new GridBagLayout());
//		datePanel.setBorder(new EmptyBorder(0, 20, 20, 20));
//		datePanel.setLayout(new GridBagLayout());
//
//		
//		GridBagConstraints c = new GridBagConstraints();
//        c.weightx = 1;   
//        c.weighty = 1;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipady = 10;
//
//        textPanel.add(email,c);
//
//        c.gridy = 1;
//        textPanel.add(pwField,c);
//
//        c.gridy = 2;
//        textPanel.add(pwAgain,c);
//
//
//        c = new GridBagConstraints();
//        c.gridheight = 2;
//        c.gridwidth = 2;
//
//        c.weightx = 1; 
//        c.weighty = 1;   
//
//		c.fill = GridBagConstraints.HORIZONTAL;
//        c.anchor = GridBagConstraints.PAGE_START;
//		sexPanel.add(sexLabel, c);
//		c.weightx = 0.0; 
//		c.gridy = 2;
//
//		sexPanel.add(isMale,   c);
//		c.weightx = 1; 
//
//
//		sexPanel.add(isFemale, c);
//		
//		c = new GridBagConstraints();
//		c.fill = GridBagConstraints.HORIZONTAL;
//        c.anchor = GridBagConstraints.PAGE_START;
//        c.ipady = 20;
//		c.gridheight = 2;
//		c.gridwidth = 3;
//		datePanel.add(dateLabel, c);
//        c.ipady = 0;
//
//		c.gridy = 2;
//	    c.weightx = 0.0;   
//	    c.weighty = 1;   
//		datePanel.add(yearBox,   c);
//		
//		c.weightx = 0.5;   
//		datePanel.add(monthBox,  c);
//    
//	    c.weightx = 0.0;   
//		datePanel.add(dayBox,    c);
//		
//		//buttonPanel.add(backButton);
//		buttonPanel.add(registerButton);
//		
//		
//		sexGroup.add(isMale);
//		sexGroup.add(isFemale);
//		
//		add(textPanel);
//		add(sexPanel);
//		add(datePanel);
//		add(buttonPanel);
//                
//                
	}
        
	private void setupTextFields(){
		email.requestFocusInWindow();
		email.selectAll();
		email.addFocusListener(new FocusListener() {
            public void focusLost(final FocusEvent pE) {
            	email.select(0, 0);
            }
            public void focusGained(final FocusEvent pE) {
            	email.selectAll();
                email.setBackground(Color.WHITE);
            }
        });
		
		pwField.setEchoChar((char)0);
		pwAgain.setEchoChar((char)0);
		addPasswordFieldListener(pwField, "Jelszó");
		addPasswordFieldListener(pwAgain, "Jelszó mégegyszer");
	}
	
	private void addPasswordFieldListener(final JPasswordField field, final String baseText){
		field.addFocusListener(new FocusListener() {
			
			public void focusLost(FocusEvent e) {
				field.select(0, 0);
				if(field.getPassword().length == 0){ 
					field.setEchoChar((char)0);
					field.setText(baseText);
				}
			}
			public void focusGained(FocusEvent e) {
				String text = new String(field.getPassword());
				field.setBackground(Color.white);
				if(text.compareTo(baseText) != 0)
					return;
				field.setText("");
				field.setEchoChar('*');
			}
		});
	}
	
	private void setupComboBoxes(){
		
		for(Integer i=2014; i>1900; i--)
			yearBox.addItem(i.toString());
		
		monthBox.addItem("Január");
		monthBox.addItem("Február");
		monthBox.addItem("Március");
		monthBox.addItem("Április");
		monthBox.addItem("Május");
		monthBox.addItem("Június");
		monthBox.addItem("Július");
		monthBox.addItem("Augusztus");
		monthBox.addItem("Szeptember");
		monthBox.addItem("Október");
		monthBox.addItem("November");
		monthBox.addItem("December");
		
		yearBox.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				generateDayBox();
			}
		});
		monthBox.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				generateDayBox();
			}
		});
		generateDayBox();
	}
	
	private void generateDayBox(){

		dayBox.removeAllItems();
		for(Integer i=1; i<29; i++)
			dayBox.addItem(i.toString());
		
		int index = monthBox.getSelectedIndex();
		if(monthBox.getItemAt(index).compareTo("Február") == 0){
			int year = Integer.parseInt(yearBox.getItemAt(yearBox.getSelectedIndex()));
			if(year % 4 == 0)
				dayBox.addItem("29");
			return;
		}
		dayBox.addItem("29");
		dayBox.addItem("30");
		
		if(monthBox.getItemAt(index).compareTo("Január") == 0)
			dayBox.addItem("31");
		if(monthBox.getItemAt(index).compareTo("Március") == 0)
			dayBox.addItem("31");
		if(monthBox.getItemAt(index).compareTo("Május") == 0)
			dayBox.addItem("31");
		if(monthBox.getItemAt(index).compareTo("Július") == 0)
			dayBox.addItem("31");
		if(monthBox.getItemAt(index).compareTo("Augusztus") == 0)
			dayBox.addItem("31");
		if(monthBox.getItemAt(index).compareTo("Október") == 0)
			dayBox.addItem("31");
		if(monthBox.getItemAt(index).compareTo("December") == 0)
			dayBox.addItem("31");
	}
	
	public boolean checkEmail(){
	      String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	      Pattern p = java.util.regex.Pattern.compile(ePattern);
	      Matcher m = p.matcher(email.getText());
	      
	      if(!m.matches()){
	    	  email.setBackground(Color.red);
	    	  JOptionPane.showMessageDialog(null, "Kérem adjon meg egy érvényes e-mail címet!");
	    	  return false;
	      }
	      return m.matches();
	}
	
	public boolean checkPasswords(){
		String first = new String(pwField.getPassword());
		String second = new String(pwAgain.getPassword());
		
		if(pwField.getEchoChar() == (char)0 || pwAgain.getEchoChar() == (char)0){
			pwField.setBackground(Color.red);
			JOptionPane.showMessageDialog(null, "Kérem adjon meg egy érvényes jelszót!");
			return false;
		}
		if(first.compareTo(second) != 0){
			pwAgain.setBackground(Color.red);
			JOptionPane.showMessageDialog(null, "A jelszók nem egyeznek!");
			return false;
		}
		if(first.length() < 4){
			pwField.setBackground(Color.red);
			JOptionPane.showMessageDialog(null, "A jelszónak legalább 4 karakterből kell állnia!");
			return false;
		}
		pwAgain.setBackground(Color.white);
		return true;
	}
        
        
	
	public String getEmail(){
		return email.getText();
	}
	public String getPassword(){
		return new String(pwField.getPassword());
	}
	public String getSex(){
		return isMale.isSelected() ? "male" : "female";
	}
	public String getYear(){
		return  yearBox.getSelectedItem().toString();
	}
	public String getMonth(){
		return  monthBox.getSelectedItem().toString();
	}
	public String getDay(){
		return  dayBox.getSelectedItem().toString();
	}
}




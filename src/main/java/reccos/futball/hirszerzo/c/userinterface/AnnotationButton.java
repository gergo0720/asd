package reccos.futball.hirszerzo.c.userinterface;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

import org.jivesoftware.smack.XMPPException;

import reccos.futball.hirszerzo.c.business.Annotator;
import reccos.futball.hirszerzo.c.business.JabberSmackApi;



public class AnnotationButton extends CustomButton {

	String action = "no action";
	String qualifier = "good";

	public AnnotationButton(Rectangle r, String a, boolean isPositive) {
		this(r,isPositive,false,a);
	}
	
	public AnnotationButton(Rectangle r, boolean isPositive, boolean enabled, String action) {
		super(r);
		isEnabled = enabled;
		setAction(action);
		if(isPositive){
			baseColor =  new Color(30,248,71);
			hoveredColor =  new Color(10,255,10);
			pressedColor =  new Color(20,150,20);
			qualifier = "good";
		}else{
			baseColor = new Color(240,35,35);
			hoveredColor = new Color(255,10,10);
			pressedColor = new Color(150,20,20);
			qualifier = "bad";
		}
		if(isEnabled)
			color = baseColor;
		else
			color = Color.GRAY;
	}
	
	public void click(){
		if(!isEnabled)
			return;
		
		try {
			if(action.compareTo("Játékvezetés") == 0)
				Annotator.sendRefereeEvent(qualifier);
			else
				Annotator.sendPlayerEvent(action, qualifier);
		} catch (XMPPException e) {
			JOptionPane.showMessageDialog(null, "Failed to send message: " + e.getMessage());
			JabberSmackApi.getInstance().login();
		} catch (IllegalStateException e) {
			JOptionPane.showMessageDialog(null, "Failed to send message: " + e.getMessage());
			try{
				JabberSmackApi.getInstance().login();
			}
			catch(Exception ex){
			}
		}
	}
	
	public void setAction(String action){
		if(action.compareTo("Passz") == 0){
			this.action = "pass";
		}else if(action.compareTo("Csel") == 0){
			this.action = "dribble";
		}else if(action.compareTo("Szerelés") == 0){
			this.action = "tackle";
		}else if(action.compareTo("Lövés") == 0){
			this.action = "shoot";
		}else if(action.compareTo("Helyezkedés") == 0){
			this.action = "position";
		}else if(action.compareTo("Játékvezetés") == 0){
			this.action = "Játékvezetés";
		}else{
			this.action = "unknown";
		}
	}
	
	public String getAction(){
		return action;
	}
}

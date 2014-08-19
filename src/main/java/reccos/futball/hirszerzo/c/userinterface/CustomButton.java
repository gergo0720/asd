package reccos.futball.hirszerzo.c.userinterface;
import java.awt.Color;
import java.awt.Rectangle;


public abstract class CustomButton extends Rectangle{
	
	boolean isHovered = false;
	boolean isPressed = false;
	boolean isEnabled = false;

	Color color;
	Color baseColor = Color.BLACK;
	Color hoveredColor = Color.GRAY;
	Color pressedColor = Color.DARK_GRAY;
	
	public CustomButton(Rectangle r) {
		super(r);
	}
	
	public CustomButton(Rectangle r, boolean enabled) {
		super(r);
		isEnabled = enabled;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		setColor();
	}
	
	public boolean isHovered() {
		return isHovered;
	}
	
	public void setHovered(boolean isHovered) {
		this.isHovered = isHovered;
		setColor();
	}
	
	public boolean isPressed() {
		return isPressed;
	}
	
	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
		setColor();
	}
	
	private void setColor(){
		if(!isEnabled){
			return;
		}
		
		if(isPressed)
			color = pressedColor;
		else if(isHovered)
			color = hoveredColor;
		else
			color = baseColor;
	}

	public Color getColor() {
		return color;
	}
}

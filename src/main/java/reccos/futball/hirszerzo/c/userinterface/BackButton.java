package reccos.futball.hirszerzo.c.userinterface;
import java.awt.Rectangle;



public class BackButton extends CustomButton{

	public BackButton(Rectangle r) {
		super(r);
	}
	
	public BackButton(Rectangle r,boolean enabled) {
		super(r);
		isEnabled = enabled;
	}


}

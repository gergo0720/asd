package reccos.futball.hirszerzo.c.userinterface;

import java.awt.Rectangle;


public class PlayButton extends CustomButton{

	boolean isPlaying = false;

	
	public PlayButton(Rectangle r) {
		super(r);
		isEnabled = true;
	}
	
	public void click(){
		if(isPlaying)
			isPlaying = false;
		else
			isPlaying = true;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
}

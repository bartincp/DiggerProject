import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.KeyStroke;


public class Player implements ActionListener{
	
	private Icon playericon;
	private boolean playerstate;
	private int xposition, yposition;
	private static final int xrespawn, yrespawn, gridwidth, gridheight;
	
	public Player(){
		
	}
	
	public void death(){
		playerstate = false;
	}
	
	public void respawn(){
		xposition = xrespawn;
		yposition = yrespawn;
	}

	public void moveRight(){
		if(xposition>=0 && xposition<gridwidth)
			xposition++;
	}
	
	public void moveLeft(){
		if(xposition>=0 && xposition<gridwidth)
			xposition--;
	}
	
	public void moveUp(){
		if(yposition>=0 && yposition<gridheight)
			yposition++;
	}
	
	public void moveDown(){
		if(yposition>=0 && yposition<gridheight)
			yposition--;
	}

	public void actionPerformed(ActionEvent event) {
		//add actionlistener for keystroke matching the event to keystroke
	}
}

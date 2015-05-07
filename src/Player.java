import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.KeyStroke;


public class Player extends Component{
	
	private Icon playericon;
	private boolean playerstate;
	private int xposition, yposition;
	private int xrespawn, yrespawn, gridwidth, gridheight;
	
	public Player(int xaxis, int yaxis, int widthofgrid, int heightofgrid){
		xrespawn = xaxis;
		yrespawn = yaxis;
		gridwidth = widthofgrid;
		gridheight = heightofgrid;
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

	public void actionPerformed(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if(keyCode==KeyEvent.VK_UP){
			moveUp();
		}
		if(keyCode==KeyEvent.VK_DOWN){
			moveDown();
		}
		if(keyCode==KeyEvent.VK_LEFT){
			moveLeft();
		}
		if(keyCode==KeyEvent.VK_RIGHT){
			moveRight();
		}
	}
}

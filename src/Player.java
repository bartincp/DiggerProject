import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;


public class Player extends ImageIcon implements Interactable{
	
	private Icon playericon;
	private boolean playerstate;
	private int xposition, yposition;
	private int xrespawn, yrespawn, gridwidth, gridheight;
	private ArrayList<Interactable> list;
	private int score = 0;
	
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
	
	public void linkGrid(ArrayList<Interactable> inputlist){
		list = inputlist;
	}
	
	public void moveUp(){
		if(yposition>=0 && yposition<gridheight){
			list.set(xposition*yposition, new Dirt());
			list.get(xposition*yposition).transform();
			yposition--;
			Interactable icon = list.get(xposition*yposition);
			list.set(xposition*yposition, this);
			score += list.get(xposition*yposition).transform();
		}
	}
	
	public void moveDown(){
		if(yposition>=0 && yposition<gridheight){
			list.set(xposition*yposition, new Dirt());
			list.get(xposition*yposition).transform();
			yposition++;
			Interactable icon = list.get(xposition*yposition);
			list.set(xposition*yposition, this);
			score += list.get(xposition*yposition).transform();
		}
	}
	
	public void moveLeft(){
		if(xposition>=0 && xposition<gridwidth){
			list.set(xposition*yposition, new Dirt());
			list.get(xposition*yposition).transform();
			xposition--;
			Interactable icon = list.get(xposition*yposition);
			list.set(xposition*yposition, this);
			score += list.get(xposition*yposition).transform();
		}
	}
	
	public void moveRight(){
		if(xposition>=0 && xposition<gridwidth){
			list.set(xposition*yposition, new Dirt());
			list.get(xposition*yposition).transform();
			xposition++;
			Interactable icon = list.get(xposition*yposition);
			list.set(xposition*yposition, this);
			score += list.get(xposition*yposition).transform();
		}
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

	public int transform() {
		return 0;
	}
}

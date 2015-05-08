import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.KeyStroke;


public class Player extends JComponent implements Interactable{
	
	private Icon playericon;
	private boolean playerstate;
	private int xposition, yposition;
	private int xrespawn, yrespawn, gridwidth, gridheight;
	private ArrayList<Interactable> list;
	private int score = 0;
	
	public Player(int yaxis, int xaxis, int widthofgrid, int heightofgrid){
		xrespawn = xaxis;
		yrespawn = yaxis;
		gridwidth = widthofgrid;
		gridheight = heightofgrid;
		playerstate = true;
		playericon = new ImageIcon("Digdug.png");
		xposition = xaxis;
		yposition = yaxis;
	}
	
	public Icon getIcon(){
		return playericon;
	}
	
	public int getXPosition(){
		return xposition;
	}
	
	public int getYPosition(){
		return yposition;
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
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			yposition--;
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
			score += list.get(gridwidth*yposition+xposition).transform();
		}
	}
	
	public void moveDown(){
		if(yposition>=0 && yposition<gridheight){
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			yposition++;
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
			score += list.get(gridwidth*yposition+xposition).transform();
		}
	}
	
	public void moveLeft(){
		if(xposition>=0 && xposition<gridwidth){
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			xposition--;
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
			score += list.get(gridwidth*yposition+xposition).transform();
		}
	}
	
	public void moveRight(){
		if(xposition>=0 && xposition<gridwidth){
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			xposition++;
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
			score += list.get(gridwidth*yposition+xposition).transform();
		}
	}

	public int transform() {
		return 0;
	}

	public void keyPressed(KeyEvent event) {
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

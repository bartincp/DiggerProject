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
import javax.swing.JLabel;
import javax.swing.KeyStroke;


public class Player extends JComponent implements Interactable{
	
	private Icon playericon;
//	private boolean playerstate;
	private int xposition, yposition;
	private int xrespawn, yrespawn, gridwidth, gridheight;
	private ArrayList<Interactable> list;
	private int score = 0;
	private JLabel label;
	private boolean enemy;
	
	public Player(int xaxis, int yaxis, int widthofgrid, int heightofgrid){
		xrespawn = xaxis;
		yrespawn = yaxis;
		gridwidth = widthofgrid;
		gridheight = heightofgrid;
//		playerstate = true;
		playericon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Digdug.png");
		xposition = xaxis;
		yposition = yaxis;
		label = new JLabel();
		label.setIcon(playericon);
		enemy = true;
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
	
//	public void death(){
//		playerstate = false;
//	}
	
	public void respawn(){
		xposition = xrespawn;
		yposition = yrespawn;
	}
	
	public void linkGrid(ArrayList<Interactable> inputlist){
		list = inputlist;
	}
	
	public void moveUp(){
		if(yposition-1>=0 && yposition-1<gridheight){
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			yposition--;
			Interactable temp = list.get(gridwidth*yposition+xposition);
			if(temp.returnEnemy()){
				this.respawn();
				list.set(gridwidth*yposition+xposition,this);
				return;
			}
			score += temp.transform();
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
		}
	}
	
	public void moveDown(){
		if(yposition+1>=0 && yposition+1<gridheight){
//			System.out.println(gridwidth);
//			System.out.println(yposition);
//			System.out.println(xposition);
//			System.out.println(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			yposition++;
			Interactable temp = list.get(gridwidth*yposition+xposition);
			if(temp.returnEnemy()){
				this.respawn();
				list.set(gridwidth*yposition+xposition,this);
				return;
			}
			score += temp.transform();
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
		}
	}
	
	public void moveLeft(){
		if(xposition-1>=0 && xposition-1<gridwidth){
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			xposition--;
			Interactable temp = list.get(gridwidth*yposition+xposition);
			if(temp.returnEnemy()){
				this.respawn();
				list.set(gridwidth*yposition+xposition,this);
				return;
			}
			score += temp.transform();
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
		}
	}
	
	public void moveRight(){
		if(xposition+1>=0 && xposition+1<gridwidth){
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			xposition++;
			Interactable temp = list.get(gridwidth*yposition+xposition);
			if(temp.returnEnemy()){
				this.respawn();
				list.set(gridwidth*yposition+xposition,this);
				return;
			}
			score += temp.transform();
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
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

	public Icon returnIcon() {
		return playericon;
	}
	
	public JLabel returnLabel(){
		return label;
	}
	
	public int returnScore(){
		return score;
	}
	
	public boolean returnEnemy(){
		return enemy;
	}
}

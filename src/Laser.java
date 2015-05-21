import java.util.ArrayList;

import javax.swing.*;


/**
 * TODO Put here a description of what this class does.
 *
 * @author carducjd.
 *         Created May 8, 2015.
 */
public class Laser extends JComponent implements Interactable{
	
	private Icon lasericon;
	private boolean laserstate;
	private int xposition, yposition, gridwidth, gridheight;
	private int xdirection, ydirection;
	private ArrayList<Interactable> list;
	private int score;
	private JLabel label;
	private boolean enemy;
	private Player user;
	
	public Laser(){
		this.lasericon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Laser.gif");
		this.laserstate = false;
		gridwidth = 5;
		gridheight = 5;
		this.xposition = 0;
		this.yposition = 0;
		label = new JLabel();
		label.setIcon(lasericon);
		this.enemy = true;
		user = null;
		System.out.println("laser created!");
	}
	
	public Laser(int xaxis, int yaxis, int widthofgrid, int heightofgrid, Player player){
		this.lasericon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Laser.gif");
		this.laserstate = true;
		this.gridwidth = widthofgrid;
		this.gridheight = heightofgrid;
		this.xposition = xaxis;
		this.yposition = yaxis;
		label = new JLabel();
		label.setIcon(lasericon);
		this.enemy = true;
		user = player;
	}

	public int[] transform() {
		// Another suitable method name would be die();
		laserstate = false;
		lasericon = null;
		xposition = -1;
		yposition = -1;
		label.setIcon(null);
		label = null;
		int[] tempArray = {0,0};
		return tempArray;
	}

	public Icon returnIcon() {
		return lasericon;
	}

	public JLabel returnLabel() {
		return label;
	}
	
	// Make separate move methods?
	// dirAxis (0 is N/S, 1 is E/W) dirAmt (-1 is N, W; 1 is S, E)
	public void move(int dirAxis, int dirAmt){
		int[] tempArray = {0,0};
		// Assuming the laser has not reached the border yet:
		if(dirAxis == 0){
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			if(yposition-1>=0&&yposition+1<gridheight){
				yposition+=dirAmt;
			}
			else{
				list.set(gridwidth*yposition+xposition, new Dirt());
				list.get(gridwidth*yposition+xposition).transform();
				this.transform();
				return;
			}
			if(list.get(gridwidth*yposition+xposition).returnEnemy()==false){
				if(list.get(gridwidth*yposition+xposition).returnState()==false){
					list.set(gridwidth*yposition+xposition, this);
				}
				else{
					this.transform();
				}
			}
		}
		if(dirAxis == 1){
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			if(xposition-1>=0&&xposition+1<gridwidth){
				xposition+=dirAmt;
			}
			else{
				list.set(gridwidth*yposition+xposition, new Dirt());
				list.get(gridwidth*yposition+xposition).transform();
				this.transform();
				return;
			}
			if(list.get(gridwidth*yposition+xposition).returnEnemy()==false){
				if(list.get(gridwidth*yposition+xposition).returnState()==false){
					list.set(gridwidth*yposition+xposition, this);
				}
				else{
					this.transform();
				}
			}
		}
		
//		if ((yposition-1>=0 && yposition+1<gridheight) || (xposition-1>=0 && xposition+1<gridwidth)){
//			list.set(gridwidth*yposition+xposition, new Dirt());
//			list.get(gridwidth*yposition+xposition).transform();
//			if (dirAxis == 0) {
//				yposition+=dirAmt;
//			}
//			if (dirAxis == 1) {
//				xposition+=dirAmt;
//			}
//			System.out.println(yposition+1);
//			System.out.println(gridheight);
//			System.out.println(gridwidth*yposition+xposition);
//			Interactable temp = list.get(gridwidth*yposition+xposition);
//			if(temp.returnEnemy()){
//				this.transform();
//				return;
//			}
//			// This kills the enemy??
//			score += temp.transform();
////			Interactable icon = list.get(gridwidth*yposition+xposition);
//			list.set(gridwidth*yposition+xposition, this);
//		}
//		// Once laser reaches border, it disappears.
//		else {
////			Interactable temp = list.get(gridwidth*yposition+xposition);
////			score += this.transform();
////			score += temp.transform();
//			list.set(gridwidth*yposition+xposition, new Dirt());
//			list.get(gridwidth*yposition+xposition).transform();
//			this.transform();
//			return;
//		}
	}
	
	public boolean returnEnemy(){
		return enemy;
	}
	//movement method?
	
	public boolean returnState(){
		return laserstate;
	}
	
	public void linkGrid(ArrayList<Interactable> inputlist){
		list = inputlist;
	}
}

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
	private boolean enemy, state;
	
	public Laser(){
		this.lasericon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/laser.png");
		this.laserstate = true;
		gridwidth = 700;
		gridheight = 700;
		this.xposition = 0;
		this.yposition = 0;
		label = new JLabel();
		label.setIcon(lasericon);
		this.enemy = true;
		state = true;
	}
	
	public Laser(int xaxis, int yaxis, int widthofgrid, int heightofgrid){
		this.lasericon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/laser.png");
		this.laserstate = true;
		this.gridwidth = widthofgrid;
		this.gridheight = heightofgrid;
		this.xposition = xaxis;
		this.yposition = yaxis;
		label = new JLabel();
		label.setIcon(lasericon);
		this.enemy = true;
		state = true;
	}

	public int transform() {
		// Another suitable method name would be die();
		laserstate = false;
		lasericon = null;
		xposition = -1;
		yposition = -1;
		label = null;
		state = false;
		return 0;
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
		// Assuming the laser has not reached the border yet:
		if (yposition-1>=0 && yposition+1<gridheight && xposition-1>=0 && xposition+1<gridwidth){
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			if (dirAxis == 0) {
				yposition=+dirAmt;
			}
			if (dirAxis == 1) {
				xposition=+dirAmt;
			}
			Interactable temp = list.get(gridwidth*yposition+xposition);
			if(temp.returnEnemy()){
				this.transform();
				return;
			}
			// This kills the enemy??
			score += temp.transform();
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
		}
		// Once laser reaches border, it disappears.
		else {
			Interactable temp = list.get(gridwidth*yposition+xposition);
			this.transform();
			score += temp.transform();
			return;
		}
	}
	
	public boolean returnEnemy(){
		return enemy;
	}
	//movement method?
	
	public boolean returnState(){
		return state;
	}
	
	public void linkGrid(ArrayList<Interactable> inputlist){
		list = inputlist;
	}
}

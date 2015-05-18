import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;


public class Player extends JComponent implements Interactable{
	
	private Icon playericon;
	private int lives;
	private int emeraldChange;
	private boolean playerstate;
	private int xposition, yposition;
	private int xrespawn, yrespawn, gridwidth, gridheight;
	private ArrayList<Interactable> list;
	private int score = 0;
	private JLabel label;
	private boolean enemy;
	private int northsouth, eastwest;
	private int[] statArray;
	
	public Player(int xaxis, int yaxis, int widthofgrid, int heightofgrid){
		lives = 3;
		xrespawn = xaxis;
		yrespawn = yaxis;
		gridwidth = widthofgrid;
		gridheight = heightofgrid;
		playerstate = true;
		playericon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Digdug.png");
		xposition = xaxis;
		yposition = yaxis;
		label = new JLabel();
		label.setIcon(playericon);
		enemy = false;
		statArray=new int[2]; statArray[0]=0; statArray[1]=0;
		northsouth = 0;
		eastwest = 1;
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
	
	// dirAxis (0 is N/S, 1 is E/W) dirAmt (-1 is N, W; 1 is S, E)
//	public void move(int dirAxis, int dirAmt){
//		// Assuming the laser has not reached the border yet:
//		if (yposition-1>=0 && yposition+1<gridheight && xposition-1>=0 && xposition+1<gridwidth){
//			list.set(gridwidth*yposition+xposition, new Dirt());
//			list.get(gridwidth*yposition+xposition).transform();
//			if (dirAxis == 0) {
//				yposition=+dirAmt;
//			}
//			if (dirAxis == 1) {
//				xposition=+dirAmt;
//			}
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
//	}
	
	public void moveUp(){
		if(yposition-1>=0 && yposition-1<gridheight){
			// Turns current player location into air
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			// Shifts position
			yposition--;
			// Reads contents of new space
			Interactable temp = list.get(gridwidth*yposition+xposition);
			if(temp.returnEnemy()){
				// return lives
				this.respawn();
				lives--;
//				list.set(gridwidth*yposition+xposition,this);
			}
			// Change type to int[], return score, emeraldCount
			// score += temp.transform;
			statArray = temp.transform();
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
			eastwest = 0;
			northsouth = -1;

		}
		return;
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
//			score += temp.transform();
			statArray = temp.transform();
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
			eastwest = 0;
			northsouth = 1;
		}
		return;
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
//			score += temp.transform();
			statArray = temp.transform();
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
			northsouth = 0;
			eastwest = -1;
		}
		return;
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
//			score += temp.transform();
			statArray = temp.transform();
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
			northsouth = 0;
			eastwest = 1;
		}
		return;
	}

	public int[] transform() {
		respawn();
		int[] tempArray = {0,0};
		return tempArray;
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
	
//	public int returnNorthSouth(){
//		return northsouth;
//	}
//	
//	public int returnEastWest(){
//		return eastwest;
//	}
	
	public int getDirectionAxis(){
		int directionaxis = -1;
		if(northsouth==0)
			directionaxis = 1;
		if(eastwest==0)
			directionaxis = 0;
		return directionaxis;
	}
	
	public int getDirectionAmount(){
		int directionamount = 0;
		if(northsouth==0)
			directionamount = eastwest;
		if(eastwest==0)
			directionamount = northsouth;
		return directionamount;
	}
	
	public boolean returnState(){
		return playerstate;
	}
	
	public int[] returnStats(){
		return statArray;
	}
			
	public void addScore(int n){
		score += n;
	}
	
	public boolean goldcheck(){
		if(yposition-1>=0 && yposition-1<gridheight){
			if(list.get(gridwidth*(yposition-1)+xposition).getClass()==Gold.class){
				return true;
			}
		}
		return false;
	}
	
	public int goldabovenumber(){
		return ((Gold)list.get(gridwidth*(yposition-1)+xposition)).getGoldNumber();
	}
}

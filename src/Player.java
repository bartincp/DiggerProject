import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class Player extends JComponent implements Interactable{
	
	private Icon playericon;
	private static int lives = 3;
	private int emeraldChange;
	private boolean playerstate;
	private int xposition, yposition;
	int xrespawn, yrespawn, gridwidth, gridheight;
	private ArrayList<Interactable> list;
	private Level level;
	private int score = 0;
	private JLabel label;
	private boolean enemy;
	private int northsouth, eastwest;
	private int[] statArray;
	private boolean goldpushed;
	private int goldpushednumber;
	
	public Player(int xaxis, int yaxis, int widthofgrid, int heightofgrid){
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
		goldpushed = false;
		goldpushednumber = -1;
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
		list.set(gridwidth*yposition+xposition, this);
		lives--;
		repaint();
		validate();
	}
	
	public void linkGrid(ArrayList<Interactable> inputlist){
		list = inputlist;
	}
	
	public void linkLvl(Level lvl){
		level = lvl;
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
			Interactable temp = list.get(gridwidth*(yposition-1)+xposition);
			if(temp.getClass()==Gold.class&&temp.returnState()==true){
				return;
			}
			// Turns current player location into air
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			// Shifts position
			yposition--;
			// Reads contents of new space
//			Interactable temp = list.get(gridwidth*yposition+xposition);
			if(temp.returnEnemy()){
				// return lives
				this.respawn();
//				list.set(gridwidth*yposition+xposition,this);
				return;
			}
			// Change type to int[], return score, emeraldCount
			// score += temp.transform;
			statArray = temp.transform();
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
			eastwest = 0;
			northsouth = -1;

		}
	}
	
	public void moveDown(){
		if(yposition+1>=0 && yposition+1<gridheight){
			Interactable temp = list.get(gridwidth*(yposition+1)+xposition);
			if(temp.getClass()==Gold.class&&temp.returnState()==true){
				return;
			}
//			System.out.println(gridwidth);
//			System.out.println(yposition);
//			System.out.println(xposition);
//			System.out.println(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			yposition++;
//			Interactable temp = list.get(gridwidth*yposition+xposition);
			if(temp.returnEnemy()){
				this.respawn();
//				list.set(gridwidth*yposition+xposition,this);
				return;
			}
//			score += temp.transform();
			statArray = temp.transform();
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
			eastwest = 0;
			northsouth = 1;
		}
	}
	
	public void moveLeft(){
		if(xposition-1>=0 && xposition-1<gridwidth){
			Interactable temp = list.get(gridwidth*yposition+(xposition-1));
			if(temp.getClass()==Gold.class&&temp.returnState()==true){
				if(xposition-2>=0){
					int tempnum = gridwidth*yposition+(xposition-2);
					if(list.get(tempnum).getClass()==Dirt.class&&((Gold)temp).getSpacesDropped()==0){
						list.set(tempnum+1,new Dirt());
						list.get(tempnum+1).transform();
						list.set(tempnum, temp);
						goldpushednumber = ((Gold)temp).getGoldNumber();
						level.setGoldXPositions(goldpushednumber,xposition-2);
						level.setGoldYPositions(goldpushednumber, yposition);
						((Gold)temp).setXPos(xposition-2);
						((Gold)temp).setYPos(yposition);
						goldpushed=true;
					}
					else
						return;
				}
				else{
					if(temp.returnState())
						return;
				}
			}
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			xposition--;
			temp = list.get(gridwidth*yposition+xposition);
//			Interactable temp = list.get(gridwidth*yposition+xposition);
			if(temp.returnEnemy()){
				this.respawn();
//				list.set(gridwidth*yposition+xposition,this);
				return;
			}
//			score += temp.transform();
			statArray = temp.transform();
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
			northsouth = 0;
			eastwest = -1;
		}
	}
	
	public void moveRight(){
		if(xposition+1>=0 && xposition+1<gridwidth){
			Interactable temp = list.get(gridwidth*yposition+(xposition+1));
			if(temp.getClass()==Gold.class&&temp.returnState()==true){
				if(xposition+2<gridwidth){
					int tempnum = gridwidth*yposition+(xposition+2);
					if(list.get(tempnum).getClass()==Dirt.class&&((Gold)temp).getSpacesDropped()==0){
						list.set(tempnum-1,new Dirt());
						list.get(tempnum-1).transform();
						list.set(tempnum, temp);
						goldpushednumber = ((Gold)temp).getGoldNumber();
						level.setGoldXPositions(goldpushednumber,xposition+2);
						level.setGoldYPositions(goldpushednumber, yposition);
						((Gold)temp).setXPos(xposition+2);
						((Gold)temp).setYPos(yposition);
						goldpushed=true;
					}
					else
						return;
				}
				else{
					if(temp.returnState())
						return;
				}
			}
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			xposition++;
			temp = list.get(gridwidth*yposition+xposition);
//			Interactable temp = list.get(gridwidth*yposition+xposition);
			if(temp.returnEnemy()){
				this.respawn();
//				list.set(gridwidth*yposition+xposition,this);
				return;
			}
//			score += temp.transform();
			statArray = temp.transform();
//			Interactable icon = list.get(gridwidth*yposition+xposition);
			list.set(gridwidth*yposition+xposition, this);
			northsouth = 0;
			eastwest = 1;
		}
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
		int[] moreStatArray = new int[3];
		moreStatArray[0]=statArray[0]; // Points
		moreStatArray[1]=statArray[1]; // Emerald change
		moreStatArray[2]=lives; // Lives
		statArray[0] = 0;
		statArray[1] = 0;
		return moreStatArray;
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
	
	public void setLives(int lives){
		this.lives=lives;
	}
	
	public int goldPushCheck(){
		if(goldpushed==true){
			goldpushed=false;
			int tempnumber=goldpushednumber;
			goldpushednumber=-1;
			return tempnumber;
		}
		return goldpushednumber;
	}
}

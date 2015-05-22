import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * 
 * TODO This class governs the creation and procedures of the player character in the Digger game.
 *
 * @author Jake Carducci, Taylor Jenkins, Carl Bartine
 *         Modified May 21, 2015.
 */


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
	
	/**
	 * 
	 * TODO This Player constructor generates a player sprite with associated position, icon, and stat data
	 *
	 * @param xaxis The horizontal position of the player.
	 * @param yaxis The vertical postion of the player.
	 * @param widthofgrid The total count of horizontal positions.
	 * @param heightofgrid The total count of vertical positions.
	 */
	
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
	
	/**
	 * 
	 * TODO The getIcon method returns the icon of the player sprite
	 *
	 * @return The icon of the sprite
	 */
	
	public Icon getIcon(){
		return playericon;
	}
	
	/**
	 * 
	 * TODO The getXPosition method returns the horizontal position of the player on the playing space
	 *
	 * @return Player's horizontal position
	 */
	
	public int getXPosition(){
		return xposition;
	}
	
	/**
	 * 
	 * TODO The getYPosition method returns the vertical position of the player on the playing space
	 *
	 * @return Player's vertical position
	 */
	
	public int getYPosition(){
		return yposition;
	}
	
//	public void death(){
//		playerstate = false;
//	}
	
	/**
	 * 
	 * TODO This method resets the player at his spawn point and decreases the life count
	 *
	 */
	
	public void respawn(){
		xposition = xrespawn;
		yposition = yrespawn;
		list.set(gridwidth*yposition+xposition, this);
		lives--;
		repaint();
		validate();
	}
	
	/**
	 * 
	 * TODO The linkGrid method allows the player class to import and use methods of the grid array containing game objects.
	 *
	 * @param inputlist The object list of the grid array.
	 */
	
	public void linkGrid(ArrayList<Interactable> inputlist){
		list = inputlist;
	}
	
	/**
	 * 
	 * TODO The linkGrid method allows the player class to import and use methods of the current level object.
	 *
	 * @param lvl The level object being imported.
	 */
	
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
	
	/**
	 * 
	 * TODO The moveUp method allows the player sprite to move his vertical position upwards.
	 *
	 */
	
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
	
	/**
	 * 
	 * TODO The moveDown method allows the player sprite to move his vertical position downwards.
	 *
	 */
	
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
	
	/**
	 * 
	 * TODO The moveLeft method allows the player sprite to move his horizontal position left. Also does checking for gold bag pushing.
	 *
	 */
	
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
	
	/**
	 * 
	 * TODO The moveRight method allows the player sprite to move his horizontal position right. Also does checking for gold bag pushing.
	 *
	 */
	
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
	
	/**
	 * 
	 * TODO The transform method is invoked upon player contact with an enemy, resulting in death and respawn.
	 *
	 * @return Array containing point and emerald change values
	 */
	
	public int[] transform() {
		respawn();
		int[] tempArray = {0,0};
		return tempArray;
	}
	
	/**
	 * 
	 * TODO This method returns the icon of the player sprite
	 *
	 * @return Icon of the sprite
	 */
	
	public Icon returnIcon() {
		return playericon;
	}
	
	/**
	 * 
	 * TODO This method returns the JLabel of the player sprite
	 *
	 * @return JLabel of the player
	 */
	
	public JLabel returnLabel(){
		return label;
	}
	
	/**
	 * 
	 * TODO This method returns the current score of the player
	 *
	 * @return Score amount
	 */
	
	public int returnScore(){
		return score;
	}
	
	/**
	 * 
	 * TODO This method returns the enemy status of the player, which should always be false
	 *
	 * @return Status as an enemy
	 */
	
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
	
	/**
	 * 
	 * TODO This method indicates whether the player is facing the north-south axis or the left-right axis
	 *
	 * @return 1 if facing northsouth, 0 if facing eastwest
	 */
	
	public int getDirectionAxis(){
		int directionaxis = -1;
		if(northsouth==0)
			directionaxis = 1;
		if(eastwest==0)
			directionaxis = 0;
		return directionaxis;
	}
	
	/**
	 * 
	 * TODO This method indicates whether the player is facing the north-south axis or the left-right axis
	 *
	 * @return The value stored in eastwest if facing northsouth, the value stored in northsouth if facing eastwest
	 */
	
	public int getDirectionAmount(){
		int directionamount = 0;
		if(northsouth==0)
			directionamount = eastwest;
		if(eastwest==0)
			directionamount = northsouth;
		return directionamount;
	}
	
	/**
	 * 
	 * TODO This method checks to see if the player still exists.
	 *
	 * @return True if existing, false if not existing
	 */
	
	public boolean returnState(){
		return playerstate;
	}
	
	/**
	 * 
	 * TODO Put here a description of what this method does.
	 *
	 * @return moreStatArray The array containing the exported player statistics
	 */
	
	public int[] returnStats(){
		int[] moreStatArray = new int[3];
		moreStatArray[0]=statArray[0]; // Points
		moreStatArray[1]=statArray[1]; // Emerald change
		moreStatArray[2]=lives; // Lives
		statArray[0] = 0;
		statArray[1] = 0;
		return moreStatArray;
	}
	
	/**
	 * 
	 * TODO This method allows for the manual addition of points to the player score
	 *
	 * @param n Score change
	 */
	
	public void addScore(int n){
		score += n;
	}
	
	/**
	 * 
	 * TODO This method allows for the detection of money bags within horizontal touching distance of the player
	 *
	 * @return True if there is an adjacent money bag, false if not
	 */
	
	public boolean goldcheck(){
		if(yposition-1>=0 && yposition-1<gridheight){
			if(list.get(gridwidth*(yposition-1)+xposition).getClass()==Gold.class){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * TODO This method checks the value of the goldcounter stored within any potential money bags above the player
	 *
	 * @return The gold counter value of the gold money bag above player (the bag indicator)
	 */
	
	public int goldabovenumber(){
		return ((Gold)list.get(gridwidth*(yposition-1)+xposition)).getGoldNumber();
	}
	
	/**
	 * 
	 * TODO This method allows for manual resetting of the player life count
	 *
	 * @param lives The number of lives the player is being set to
	 */
	
	public void setLives(int lives){
		this.lives=lives;
	}
	
	/**
	 * 
	 * TODO This method checks to see if any gold was pushed by the player
	 * @return The number identifier for the particular bag of gold being pushed
	 */
	
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

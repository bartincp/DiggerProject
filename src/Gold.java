import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * TODO This class governs the creation and procedures of the gold bags generated on the playing field.
 *
 * @author Jake Carducci, Taylor Jenkins, Carl Bartine
 *         Modified May 21, 2015.
 */

public class Gold implements Interactable{
	
	private Icon goldicon;
	private boolean state;
	private static final int points = 420;
	private JLabel label;
	private boolean enemy;
	private static final int emeraldChange = 0;
	private ArrayList<Interactable> list;
	private int spacesdropped;
	private int xpos, ypos;
	private int gridwidth, gridheight;
	private int number;
	
	/**
	 * 
	 * TODO The constructor generates the laser sprites with associated icon, position, and state data.
	 *
	 */
	
	public Gold(int xposition, int yposition, int width, int height, int goldnumber){
		goldicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Gold.png");
		label = new JLabel();
		label.setIcon(goldicon);
		enemy = false;
		state = true;
		spacesdropped = 0;
		xpos = xposition;
		ypos = yposition;
		gridwidth = width;
		gridheight = height;
		number = goldnumber;
	}
	
	/**
	 * 
	 * TODO This method is invoked whenever the gold is collected by the player
	 *
	 * @return The point and emerald count changes in an array
	 */
	
	public int[] transform() {
		goldicon = null;
		label.setIcon(null);
		state = false;
		int[] tempArray = {points, emeraldChange};
		return tempArray;
	}
	
	/**
	 * 
	 * TODO This method causes the bag to changed into a gold pile after the bag falls an excessive amount
	 *
	 */
	
	public void gimmeabreak() {
		if(spacesdropped>2){
			goldicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/BrokenGold.png");
			label.setIcon(goldicon);
			state = false;
		}
		spacesdropped = 0;
	}

	/**
	 * 
	 * TODO This method allows this class to access the methods of the imported grid array
	 *
	 * @param grid The grid object array being imported
	 */
	
	public void linkGrid(ArrayList<Interactable> grid){
		list = grid;
	}
	
	/**
	 * 
	 * TODO This method allows the bag to fall one space at a time when invoked.
	 *
	 */
	
	public void move(){
		boolean canmove = false;
		int predictedtileindex = gridwidth*(ypos+1)+xpos;
		if(predictedtileindex<list.size()&&predictedtileindex>=0){
			Interactable temp = list.get(predictedtileindex);
			if((temp.getClass()==Dirt.class&&temp.returnState()==false)||(temp.getClass()!=Dirt.class&&temp.getClass()!=Emerald.class&&temp.getClass()!=Gold.class)){
				if(temp.getClass()==Nobbin.class||temp.getClass()==Hobbin.class||temp.getClass()==Player.class)
					list.get(predictedtileindex).transform();
				canmove = true;
				list.set(gridwidth*ypos+xpos,new Dirt());
				list.get(gridwidth*ypos+xpos).transform();
				list.set(predictedtileindex, this);
				ypos++;
				predictedtileindex = gridwidth*(ypos+1)+xpos;
				spacesdropped++;
			}
		}
		if(canmove==false)
			gimmeabreak();
	}
	
	/**
	 * 
	 * TODO The returnIcon method returns the icon of the gold
	 *
	 * @return The icon of the sprite
	 */	 
	
	public Icon returnIcon() {
		return goldicon;
	}

	/**
	 * 
	 * TODO The returnLabel method returns the JLabel of the gold
	 *
	 * @return The JLabel of the sprite
	 */
	
	public JLabel returnLabel() {
		return label;
	}

	/**
	 * 
	 * TODO This method returns the status of the sprite as an enemy, which should be false
	 *
	 * @return True if an enemy, false if not
	 */
	
	public boolean returnEnemy() {
		return enemy;
	}

	/**
	 * 
	 * TODO The returnState method returns the existence of the gold
	 *
	 * @return True if the laser exists, false if it does not
	 */
	
	public boolean returnState() {
		return state;
	}
	
	/**
	 * 
	 * TODO This method returns the current horizontal position of the gold
	 *
	 * @return Gold horizontal position
	 */
	
	public int getXPosition(){
		return this.xpos;
	}
	
	/**
	 * 
	 * TODO This method allows for the horizontal position of the gold to be manually set.
	 *
	 * @param newXPos Horizontal position being set
	 * @return True if position is set and valid, false if otherwise
	 */
	
	public boolean setXPos(int newXPos){
		if (newXPos >= 0 && newXPos < getGridwidth()){
			this.xpos = newXPos;
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * TODO This method returns the current vertical position of the gold
	 *
	 * @return Gold vertical position
	 */
	
	public int getYPosition(){
		return this.ypos;
	}
	
	/**
	 * 
	 * TODO This method allows for the vertical position of the gold to be manually set.
	 *
	 * @param newXPos Vertical position being set
	 * @return True if position is set and valid, false if otherwise
	 */
	
	public boolean setYPos(int newYPos){
		if (newYPos >= 0 && newYPos < getGridheight()){
			this.ypos = newYPos;
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * TODO This method allows class to export grid width
	 *
	 * @return Number of horizontal spaces in grid
	 */
	
	public int getGridwidth(){
		return this.gridwidth;
	}
	
	/**
	 * 
	 * TODO This method allows class to export grid height
	 *
	 * @return Number of vertical spaces in grid
	 */
	
	public int getGridheight(){
		return this.gridheight;
	}
	
	/**
	 * 
	 * TODO This method returns the unique identifier of the gold object
	 *
	 * @return number Gold identifier number
	 */
	
	public int getGoldNumber(){
		return number;
	}
	
	/**
	 * 
	 * TODO This method returns the number of spaces the gold has dropped
	 *
	 * @return spacesdropped Number of successive vertical spaces bag has dropped 
	 */
	
	public int getSpacesDropped(){
		return spacesdropped;
	}
	
	/**
	 * 
	 * TODO This method checks to see if air exists below the gold for falling.
	 *
	 * @return True if air below exists, false if otherwise
	 */
	
	public boolean airBelow(){
		if(ypos+1<gridheight){
			//list is null
			Interactable tempspacebelow = list.get(gridwidth*(ypos+1)+xpos);
			if(tempspacebelow.getClass()==Dirt.class&&tempspacebelow.returnState()==false){
				return true;
			}
		}
		return false;
	}
}

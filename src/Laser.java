import java.util.ArrayList;

import javax.swing.*;


/**
 * 
 * TODO This class governs the creation and procedures of the lasers created by the player.
 *
 * @author Jake Carducci, Taylor Jenkins, Carl Bartine
 *         Modified May 21, 2015.
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
	
	/**
	 * 
	 * TODO The constructor generates the laser sprites with associated icon, position, and state data.
	 *
	 */
	
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
	}
	
	/**
	 * 
	 * TODO The constructor generates the laser sprites with the option to specify position and origin information.
	 *
	 */
	
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
	
	/**
	 * 
	 * TODO This method is invoked whenever the beam strikes the panel edge, a block, or an enemy
	 *
	 * @return The point and emerald count changes in an array
	 */
	
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

	/**
	 * 
	 * TODO The returnIcon method returns the icon of the laser sprite
	 *
	 * @return The icon of the sprite
	 */
	
	public Icon returnIcon() {
		return lasericon;
	}

	/**
	 * 
	 * TODO The returnLabel method returns the JLabel of the laser sprite
	 *
	 * @return The JLabel of the sprite
	 */
	
	public JLabel returnLabel() {
		return label;
	}
	
	/**
	 * 
	 * TODO This method helps the laser move from one space to another upon the player firing it
	 * 
	 * @param dirAxis 0 is N/S, 1 is E/W
	 * @param dirAmt -1 is N, W; 1 is S, E
	 */

	public void move(int dirAxis, int dirAmt){
		int[] tempArray = {0,0};
		// Assuming the laser has not reached the border yet:
		//Handles moving up and down
		if(dirAxis == 0){
			//Changes current space to air
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			//Checks if the laser can move in the correct direction and if not deletes it
			if(yposition-1>=0&&yposition+1<gridheight){
				yposition+=dirAmt;
			}
			else{
				this.transform();
				return;
			}
			//Checks if the next space the laser can move into is and enemy and handles situation accordingly
			if(list.get(gridwidth*yposition+xposition).returnEnemy()==false){
				if(list.get(gridwidth*yposition+xposition).returnState()==false){
					list.set(gridwidth*yposition+xposition, this);
				}
				else{
					this.transform();
				}
			}
			else{
				Interactable temp = list.get(gridwidth*yposition+xposition);
				list.set(gridwidth*yposition+xposition, new Dirt());
				list.get(gridwidth*yposition+xposition).transform();
				((Nobbin)temp).respawn();
				this.transform();
			}
		}
		//Handles moving left and right
		if(dirAxis == 1){
			list.set(gridwidth*yposition+xposition, new Dirt());
			list.get(gridwidth*yposition+xposition).transform();
			if(xposition-1>=0&&xposition+1<gridwidth){
				xposition+=dirAmt;
			}
			else{
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
			else{
				System.out.println(yposition);
				System.out.println(xposition);
				Interactable temp = list.get(gridwidth*yposition+xposition);
				list.set(gridwidth*yposition+xposition, new Dirt());
				list.get(gridwidth*yposition+xposition).transform();
				((Nobbin)temp).respawn();
				this.transform();
			}
		}
	}
	
	/**
	 * 
	 * TODO This method returns the status of the sprite as an enemy, which should be false
	 *
	 * @return True if an enemy, false if not
	 */
	
	public boolean returnEnemy(){
		return enemy;
	}
	
	/**
	 * 
	 * TODO The returnState method returns the existence of the laser sprite
	 *
	 * @return True if the laser exists, false if it does not
	 */
	
	public boolean returnState(){
		return laserstate;
	}
	
	/**
	 * 
	 * TODO This method allows for the import and method use of the grid object array in this class.
	 *
	 * @param inputlist The object list of the grid being linked
	 */
	
	public void linkGrid(ArrayList<Interactable> inputlist){
		list = inputlist;
	}
}

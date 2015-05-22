//import java.io.File;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;


/**
 * TODO Put here a description of what this class does.
 *
 * @author carducjd.
 *         Created May 5, 2015.
 */
public class Dirt extends JComponent implements Interactable{
	// Maybe use xpos and ypos instead for consistency?
//	private int[][] position;
	private Icon dirticon;
//	private File iconFile;
	private boolean state;
	private static final int points = 0;
	private JLabel label;
	private boolean enemy;
	private static final int emeraldChange = 0;
	
	/**
	 * 
	 * TODO The constructor generates the dirt sprite with associated icon, position, and state data.
	 *
	 */
	
	public Dirt(){
//		this.position = new int[][] {{0},{0}};
		this.dirticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Dirt.png");
//		this.airicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Air.jpg");
//		this.iconFile = new File("dirtSpriteOn.gif");
		this.state = true;
		label = new JLabel();
		label.setIcon(dirticon);
		enemy = false;
	}
	
	 /**
	 * 
	 * TODO The constructor generates the dirt sprite with the option to specify position and state information.
	 *
	 */
	
	public Dirt(int xPos, int yPos, boolean state){
//		this.position = new int[][] {{xPos},{yPos}};
		this.dirticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Dirt.png");
//		this.airicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Air.jpg");
//		this.iconFile = new File("dirtSpriteOn.gif");
		this.state = true;
		label = new JLabel();
		label.setIcon(dirticon);
		enemy = false;
	}
	
	 /**
	 * 
	 * TODO The constructor generates the dirt sprite with the option to specify position and state information.
	 *
	 */
	
	public Dirt(int[][] position, boolean state){
//		this.position = position;
		if (state){
			this.dirticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Dirt.png");
//			this.iconFile = new File("dirtSpriteOn.gif");
//			this.state = true;
		}
		if (!state){
//			this.dirticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Air.jpg");
			this.dirticon = null;
//			this.iconFile = new File("dirtSpriteOff.gif");
//			this.state = false;
		}
		enemy = false;
	}
	
	/**
	 * 
	 * TODO This method is invoked whenever the dirt is dug by a player or hobbin
	 *
	 * @return The point and emerald count changes in an array
	 */
	
	public int[] transform(){
		label.setIcon(null);
		state = false;
//		this.state=!this.state;
//		if (this.state){
//			this.dirticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Dirt.jpg");
////			this.iconFile = new File("dirtSpriteOn.gif");
//		}
//		if (!this.state){
//			this.dirticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Air.jpg");
////			this.iconFile = new File("dirtSpriteOff.gif");
//		}
		int[] tempArray = {points, emeraldChange};
		return tempArray;
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
	 * TODO The returnIcon method returns the icon of the dirt sprite
	 *
	 * @return The icon of the sprite
	 */	 
	
	public Icon returnIcon() {
		return dirticon;
	}
	
	/**
	 * 
	 * TODO The returnLabel method returns the JLabel of the dirt sprite
	 *
	 * @return The JLabel of the sprite
	 */
	
	public JLabel returnLabel(){
		return label;
	}
	
	/**
	 * 
	 * TODO The returnState method returns the existence of the dirt sprite
	 *
	 * @return True if the dirt exists, false if it does not
	 */
	
	public boolean returnState(){
		return state;
	}
}

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * 
 * TODO This class governs the creation and procedures of the emeralds generated on the playing space.
 *
 * @author Jake Carducci, Taylor Jenkins, Carl Bartine
 *         Modified May 21, 2015.
 */

public class Emerald extends JComponent implements Interactable{
	
	private Icon emeraldicon;
	private boolean emeraldstate;
//	private int xposition, yposition;
	private static final int points = 10;
	private JLabel label;
	private boolean enemy;
	private static final int emeraldChange = -1;
	
	/**
	 * 
	 * TODO The constructor generates the laser sprites with associated icon, position, and state data.
	 *
	 */
	
	// Added zero-parameter block since Level had trouble constructing Emerald without it (JDC)
	public Emerald(){
//		this.airicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Air.jpeg");
		this.emeraldicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Emerald.png");
		this.emeraldstate = true;
//		this.xposition = 0;
//		this.yposition = 0;
		label = new JLabel();
		label.setIcon(emeraldicon);
		enemy = false;
	}
	
	 /**
	 * 
	 * TODO The constructor generates the laser sprites with the option to specify position information.
	 *
	 */
	
	public Emerald(int xaxis, int yaxis){
//		this.airicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Air.jpg");
		this.emeraldicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Emerald.png");
		this.emeraldstate = true;
//		this.xposition = xaxis;
//		this.yposition = yaxis;
		label = new JLabel();
		label.setIcon(emeraldicon);
		enemy = false;
	}

	/**
	 * 
	 * TODO This method is invoked whenever the emerald is taken by a hobbin or the player
	 *
	 * @return The point and emerald count changes in an array
	 */
	
	public int[] transform(){
		emeraldstate = false;
		emeraldicon = null;
//		xposition = -1;
//		yposition = -1;
//		label.setIcon(airicon);
		label.setIcon(null);
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
	 * TODO The returnIcon method returns the icon of the emerald sprite
	 *
	 * @return The icon of the sprite
	 */	 
	
	public Icon returnIcon() {
		return emeraldicon;
	}
	
	/**
	 * 
	 * TODO The returnLabel method returns the JLabel of the emerald sprite
	 *
	 * @return The JLabel of the sprite
	 */
	
	public JLabel returnLabel(){
		return label;
	}
	
	/**
	 * 
	 * TODO The returnState method returns the existence of the emerald sprite
	 *
	 * @return True if the emerald exists, false if it does not
	 */
	
	public boolean returnState(){
		return emeraldstate;
	}
}

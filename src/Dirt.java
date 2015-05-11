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
	private int[][] position;
	private Icon dirticon, airicon;
//	private File iconFile;
	private boolean state;
	private static final int points = 0;
	private JLabel label;
	
	public Dirt(){
		this.position = new int[][] {{0},{0}};
		this.dirticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Dirt.png");
//		this.airicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Air.jpg");
//		this.iconFile = new File("dirtSpriteOn.gif");
		this.state = true;
		label = new JLabel();
		label.setIcon(dirticon);
	}
	
	public Dirt(int xPos, int yPos, boolean state){
		this.position = new int[][] {{xPos},{yPos}};
		this.dirticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Dirt.png");
//		this.airicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Air.jpg");
//		this.iconFile = new File("dirtSpriteOn.gif");
		this.state = true;
		label = new JLabel();
		label.setIcon(dirticon);
	}
	
	public Dirt(int[][] position, boolean state){
		this.position = position;
		if (state){
			this.dirticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Dirt.png");
//			this.iconFile = new File("dirtSpriteOn.gif");
			this.state = true;
		}
		if (!state){
//			this.dirticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Air.jpg");
			this.dirticon = null;
//			this.iconFile = new File("dirtSpriteOff.gif");
			this.state = false;
		}
	}
	
	public int transform(){
		label.setIcon(null);
//		this.state=!this.state;
//		if (this.state){
//			this.dirticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Dirt.jpg");
////			this.iconFile = new File("dirtSpriteOn.gif");
//		}
//		if (!this.state){
//			this.dirticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Air.jpg");
////			this.iconFile = new File("dirtSpriteOff.gif");
//		}
		return points;
	}

	public Icon returnIcon() {
		return dirticon;
	}
	
	public JLabel returnLabel(){
		return label;
	}
}

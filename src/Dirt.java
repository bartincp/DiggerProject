//import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;


/**
 * TODO Put here a description of what this class does.
 *
 * @author carducjd.
 *         Created May 5, 2015.
 */
public class Dirt extends ImageIcon{
	// Maybe use xpos and ypos instead for consistency?
	private int[][] position;
	private Icon dirticon;
//	private File iconFile;
	private boolean state;
	
	public Dirt(){
		this.position = new int[][] {{0},{0}};
		this.dirticon = new ImageIcon("desktop/Dirt");
//		this.iconFile = new File("dirtSpriteOn.gif");
		this.state = true;
	}
	
	public Dirt(int xPos, int yPos, boolean state){
		this.position = new int[][] {{xPos},{yPos}};
		this.dirticon = new ImageIcon("desktop/Dirt");
//		this.iconFile = new File("dirtSpriteOn.gif");
		this.state = true;
	}
	
	public Dirt(int[][] position, boolean state){
		this.position = position;
		if (state){
			this.dirticon = new ImageIcon("desktop/Dirt");
//			this.iconFile = new File("dirtSpriteOn.gif");
			this.state = true;
		}
		if (!state){
			this.dirticon = new ImageIcon("desktop/Air");
//			this.iconFile = new File("dirtSpriteOff.gif");
			this.state = false;
		}
	}
	
	public void transform(){
		this.state=!this.state;
		if (this.state){
			this.dirticon = new ImageIcon("desktop/Dirt");
//			this.iconFile = new File("dirtSpriteOn.gif");
		}
		if (!this.state){
			this.dirticon = new ImageIcon("desktop/Air");
//			this.iconFile = new File("dirtSpriteOff.gif");
		}
	}
}

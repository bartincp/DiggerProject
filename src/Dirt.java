import java.io.File;


/**
 * TODO Put here a description of what this class does.
 *
 * @author carducjd.
 *         Created May 5, 2015.
 */
public class Dirt {
	private int[][] position;
	private File iconFile;
	private boolean state;
	
	public Dirt(){
		this.position = new int[][] {{1},{1}};
		this.iconFile = new File("dirtSpriteOn.gif");
		this.state = true;
	}
	
	public Dirt(int[][] placement, boolean presence){
		this.position = placement;
		if (presence){
			this.iconFile = new File("dirtSpriteOn.gif");
			this.state = true;
		}
		if (!presence){
			this.iconFile = new File("dirtSpriteOff.gif");
			this.state = false;
		}
	}
	
	public void transform(){
		this.state=!this.state;
		if (this.state){
			this.iconFile = new File("dirtSpriteOn.gif");
		}
		if (!this.state){
			this.iconFile = new File("dirtSpriteOff.gif");
		}
	}
}

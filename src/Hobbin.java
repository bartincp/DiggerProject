import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * TODO Put here a description of what this class does.
 *
 * @author carducjd.
 *         Created May 14, 2015.
 */
public class Hobbin extends Nobbin {

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param xPos
	 * @param yPos
	 */
	public Hobbin(int xPos, int yPos, int widthofgrid, int heightofgrid) {
		super (xPos, yPos,widthofgrid,heightofgrid);
		this.icon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/hobbin.png");
		this.label.setIcon(this.icon);
	}
	
	public void moveRandom(){
		// May use unique behavior to account for dirt digging
		super.moveRandom();
	}
	
	protected int[] getOptimalMove(int playerX, int playerY){
		return super.getOptimalMove(playerX, playerY);
	}
	
	public int[] transform() {
		return super.transform();
	}
	
	public Icon returnIcon(){
		return super.returnIcon();
	}
	
	public JLabel returnLabel() {
		return super.returnLabel();
	}
	
	public boolean returnEnemy(){
		return super.returnEnemy();
	}
}

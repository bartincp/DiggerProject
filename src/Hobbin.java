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
	
	@Override
	public void moveRandom() {
		int dir = getRandomGenerator().nextInt(4);
		boolean notyetmoved = true;
		int newXPos, newYPos;
		if (notyetmoved) {
			if (dir == 0){
				newYPos = getYPosition() - 1;
				newXPos = getXPosition();
			} else if (dir == 1){
				newYPos = getYPosition() + 1;
				newXPos = getXPosition();
			} else if (dir == 2){
				newYPos = getYPosition();
				newXPos = getXPosition() - 1;
			} else {
				newYPos = getYPosition();
				newXPos = getXPosition() + 1;
			}
			if (newXPos >= 0 && newXPos < getGridwidth() && newYPos >= 0 && newYPos < getGridheight()) {
				Interactable temp = getGridList().get(getGridwidth()*newYPos+newXPos);
				// Prevent Hobbins from eating Nobbins??
					getGridList().set(5*getYPosition()+getXPosition(),new Dirt());
					getGridList().get(5*getYPosition()+getXPosition()).transform();
					setXPos(newXPos);
					setYPos(newYPos);
					getGridList().set(5*getYPosition()+getXPosition(),this);
					notyetmoved = false;
			}
		}
		
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

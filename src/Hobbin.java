import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * 
 * TODO This class governs the creation and procedures of the hobbin, a stronger enemy class in the game.
 *
 * @author Jake Carducci, Taylor Jenkins, Carl Bartine
 *         Modified May 21, 2015.
 */
public class Hobbin extends Nobbin {

	
	/**
	 * TODO The constructor generates the hobbin sprites with associated icon, position, and state data.
	 *
	 * @param xPos Horizontal respawn position
	 * @param yPos Vertical respawn position
	 * @param widthofgrid Total horizontal spaces
	 * @param heightofgrid Total vertical spaces
	 * @param num Unique identifier
	 */
	public Hobbin(int xPos, int yPos, int widthofgrid, int heightofgrid, int num) {
		super (xPos, yPos,widthofgrid,heightofgrid,num);
		this.icon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/hobbin.png");
		this.label.setIcon(this.icon);
		this.goldpushednumber = -1;
		this.goldpushed = false;
	}
	
	/**
	 * 
	 * TODO This method is invoked whenever the hobbin needs to move. The movement behavior is selected at random, but is designed to avoid edges and corners, to push bags, to eat dirt, and kill the player; it respawns if needed.
	 *
	 */
	
	@Override
	public void moveRandom() {
		int dir = getRandomGenerator().nextInt(4);
		boolean notyetmoved = true;
		int newXPos, newYPos;
		int newGoldXPos = -1;
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
				newGoldXPos = newXPos-1;
			} else {
				newYPos = getYPosition();
				newXPos = getXPosition() + 1;
				newGoldXPos = newXPos+1;
			}
			if (newXPos >= 0 && newXPos < getGridwidth() && newYPos >= 0 && newYPos < getGridheight()) {
				Interactable temp = getGridList().get(getGridwidth()*newYPos+newXPos);
				if (temp.getClass() != Nobbin.class && temp.getClass() != Emerald.class && temp.getClass() != Hobbin.class) {
					if(temp.getClass()==Gold.class){
						if(newGoldXPos>=0 && newGoldXPos<gridwidth){
							int tempnum = gridwidth*yPos+newGoldXPos;
							if(getGridList().get(tempnum).getClass()==Dirt.class&&((Gold)temp).getSpacesDropped()==0){
								getGridList().set(getGridwidth()*newYPos+newXPos,new Dirt());
								getGridList().get(getGridwidth()*newYPos+newXPos).transform();
								getGridList().set(tempnum, temp);
								goldpushednumber = ((Gold)temp).getGoldNumber();
								level.setGoldXPositions(goldpushednumber,newGoldXPos);
								level.setGoldYPositions(goldpushednumber, yPos);
								((Gold)temp).setXPos(newGoldXPos);
								((Gold)temp).setYPos(yPos);
								goldpushed=true;
							}
						}
						else
							if(temp.returnState()){
								return;
							}
					}
					getGridList().set(gridwidth*getYPosition()+getXPosition(),new Dirt());
					getGridList().get(gridwidth*getYPosition()+getXPosition()).transform();
					setXPos(newXPos);
					setYPos(newYPos);
					getGridList().set(gridwidth*getYPosition()+getXPosition(),this);
					if (temp.getClass() == Player.class){
						Player newTemp = (Player)temp;
						if (xPos == newTemp.xrespawn && yPos == newTemp.yrespawn)
							respawn();
						newTemp.respawn();
					}
					notyetmoved = false;
				}
			}
		}
		
	}
	
	/**
	 * 
	 * TODO This method inherits the transform method from the Nobbin class
	 *
	 * @return The point and emerald count changes in an array
	 */
	
	public int[] transform() {
		return super.transform();
	}
	
	/**
	 * 
	 * TODO The returnIcon method returns the icon of the hobbin sprite
	 *
	 * @return The icon of the sprite
	 */	 
	
	public Icon returnIcon(){
		return super.returnIcon();
	}
	
	/**
	 * 
	 * TODO The returnLabel method returns the JLabel of the hobbin sprite
	 *
	 * @return The JLabel of the sprite
	 */
	
	public JLabel returnLabel() {
		return super.returnLabel();
	}
	
	/**
	 * 
	 * TODO This method returns the status of the sprite as an enemy, which should be true
	 *
	 * @return True if an enemy, false if not
	 */
	
	public boolean returnEnemy(){
		return super.returnEnemy();
	}
}

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * TODO Put here a description of what this class does.
 *
 * @author bartincp
 *         Created May 14, 2015.
 */
public class Hobbin extends Nobbin {

	
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param xPos
	 * @param yPos
	 */
	public Hobbin(int xPos, int yPos, int widthofgrid, int heightofgrid, int num) {
		super (xPos, yPos,widthofgrid,heightofgrid,num);
		this.icon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/hobbin.png");
		this.label.setIcon(this.icon);
		this.goldpushednumber = -1;
		this.goldpushed = false;
	}
	
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

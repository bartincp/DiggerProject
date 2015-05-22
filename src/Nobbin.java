import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class Nobbin extends JComponent implements Interactable {

	protected Icon icon;
	private boolean alive;
	protected int xPos, yPos;
	private int xRespawn, yRespawn;
	protected JLabel label;
	private boolean enemy;
	protected int gridwidth;
	protected int gridheight;
	private ArrayList<Interactable> list;
	private Random randomgenerator;
	protected Level level;
	private int number;
	int goldpushednumber;
	boolean goldpushed;
	Level lvl;
	
	/**
	 * TODO The constructor generates the nobbin sprites with associated icon, position, and state data.
	 *
	 * @param xPos Horizontal respawn position
	 * @param yPos Vertical respawn position
	 * @param widthofgrid Total horizontal spaces
	 * @param heightofgrid Total vertical spaces
	 * @param num Unique identifier
	 */
	
	public Nobbin(int xPos, int yPos, int widthofgrid, int heightofgrid, int num){
		this.xRespawn = xPos;
		this.yRespawn = yPos;
		this.xPos = xPos;
		this.yPos = yPos;
		this.gridwidth = widthofgrid;
		this.gridheight = heightofgrid;
		this.icon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/nobbin.png");
		this.label = new JLabel();
		this.label.setIcon(this.icon);
		this.enemy = true;
		this.randomgenerator = new Random();
		this.number = num;
	}
	
	/**
	 * 
	 * TODO The linkGrid method allows this class to import and use methods of the grid array containing game objects.
	 *
	 * @param inputlist The object list of the grid array.
	 */
	
	public void linkGrid(ArrayList<Interactable> inputlist){
		this.list = inputlist;
	}
	
	/**
	 * 
	 * TODO The linkGrid method allows this class to import and use methods of the current level object.
	 *
	 * @param lvl The level object being imported.
	 */
	
	public void linkLvl(Level lvl){
		level=lvl;
	}
	
	/*
	 *  GETTERS & SETTERS
	 */
	
	/**
	 * 
	 * TODO This method returns the current horizontal position of the nobbin
	 *
	 * @return Nobbin horizontal position
	 */
	
	public int getXPosition(){
		return this.xPos;
	}
	
	/**
	 * 
	 * TODO This method allows for the horizontal position of the nobbin to be manually set.
	 *
	 * @param newXPos Horizontal position being set
	 * @return True if position is set and valid, false if otherwise
	 */
	
	public boolean setXPos(int newXPos){
		if (newXPos >= 0 && newXPos < getGridwidth()){
			this.xPos = newXPos;
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * TODO This method returns the current vertical position of the nobbin
	 *
	 * @return Nobbin vertical position
	 */
	
	public int getYPosition(){
		return this.yPos;
	}
	
	/**
	 * 
	 * TODO This method allows for the vertical position of the nobbin to be manually set.
	 *
	 * @param newXPos Vertical position being set
	 * @return True if position is set and valid, false if otherwise
	 */
	
	public boolean setYPos(int newYPos){
		if (newYPos >= 0 && newYPos < getGridheight()){
			this.yPos = newYPos;
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * TODO This method allows class to export grid width
	 *
	 * @return Number of horizontal spaces in grid
	 */
	
	public int getGridwidth(){
		return this.gridwidth;
	}
	
	/**
	 * 
	 * TODO This method allows class to export grid height
	 *
	 * @return Number of vertical spaces in grid
	 */
	
	public int getGridheight(){
		return this.gridheight;
	}
	
	/**
	 * 
	 * TODO This method creates and returns a random number generator
	 *
	 * @return Random generator
	 */
	
	public Random getRandomGenerator(){
		return this.randomgenerator;
	}
	
	/**
	 * 
	 * TODO This method returns the grid object array being used.
	 *
	 * @return Grid object array
	 */
	
	public ArrayList<Interactable> getGridList() {
		return this.list;
	}
	
	/*
	 * END GETTERS & SETTERS
	 */
	
	/**
	 * 
	 * TODO This method is invoked whenever the nobbin needs to move. The movement behavior is selected at random, but is designed to avoid edges, corners, dirt, and other collidable object, as well as to kill the player; it respawns if needed.
	 *
	 */
	
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
				if ((temp.getClass() == Dirt.class && !temp.returnState()) || temp.getClass() == Player.class){
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
	 * TODO This method is invoked when the enemy is destroyed by gold or laser.
	 *
	 */
	
	public void respawn(){
		xPos = xRespawn;
		yPos = yRespawn;
		list.set(gridwidth*yPos+xPos, this);
		level.setEnemyXPositions(number, xPos);
		level.setEnemyYPositions(number, yPos);
		repaint();
		validate();
	}
	
//	protected int[] getOptimalMove(int playerX, int playerY) {
//		int dx = playerX - xPos;
//		int dy = playerY - yPos;
//		int[] move = new int[2];
//		if (Math.abs(dx) <= Math.abs(dy)) {
//			move[0] = Math.abs(dx)/dx;
//		} else if (Math.abs(dx) > Math.abs(dy)) {
//			move[1] = Math.abs(dy)/dy;
//		}
//		return move;
//		
//		//implement breadth-first or iterative depth-first search
//	}
	
	/**
	 * 
	 * TODO This method invokes respawning when it is tranformed from being killed
	 *
	 * @return The point and emerald count changes in an array
	 */
	
	@Override
	public int[] transform() {
		respawn();
		int[] tempArray = {0,0};
		return tempArray;
	}

	/**
	 * 
	 * TODO The returnIcon method returns the icon of the nobbin sprite
	 *
	 * @return The icon of the sprite
	 */	 
	
	@Override
	public Icon returnIcon() {
		return this.icon;
	}

	/**
	 * 
	 * TODO The returnLabel method returns the JLabel of the nobbin sprite
	 *
	 * @return The JLabel of the sprite
	 */
	
	@Override
	public JLabel returnLabel() {
		return this.label;
	}

	/**
	 * 
	 * TODO This method returns the status of the sprite as an enemy, which should be true
	 *
	 * @return True if an enemy, false if not
	 */
	
	public boolean returnEnemy(){
		return enemy;
	}
	
	/**
	 * 
	 * TODO The returnState method returns the existence of the nobbin
	 *
	 * @return True if the nobbin exists, false if it does not
	 */
	
	public boolean returnState(){
		return true;
	}
	
	/**
	 * 
	 * TODO This method allows for the detection of money bags within horizontal touching distance of the nobbin
	 *
	 * @return True if there is an adjacent money bag, false if not
	 */
	
	public boolean goldcheck(){
		if(yPos-1>=0 && yPos-1<gridheight){
			if(list.get(gridwidth*(yPos-1)+xPos).getClass()==Gold.class){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * TODO This method checks the value of the goldcounter stored within any potential money bags above the enemy
	 *
	 * @return The gold counter value of the gold money bag above enemy (the bag indicator)
	 */
	
	public int goldabovenumber(){
		return ((Gold)list.get(gridwidth*(yPos-1)+xPos)).getGoldNumber();
	}

	/**
	 * 
	 * TODO This method checks to see if any gold was pushed by the nobbin
	 * @return The number identifier for the particular bag of gold being pushed
	 */
	
	public int goldPushCheck(){
		if(goldpushed==true){
			goldpushed=false;
			int tempnumber=goldpushednumber;
			goldpushednumber=-1;
			return tempnumber;
		}
		return goldpushednumber;
	}
}

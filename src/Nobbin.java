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
	private Level level;
	private int number;
	
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
	
	public void linkGrid(ArrayList<Interactable> inputlist){
		this.list = inputlist;
	}
	
	public void linkLvl(Level lvl){
		level=lvl;
	}
	
	/*
	 *  GETTERS & SETTERS
	 */
	
	public int getXPosition(){
		return this.xPos;
	}
	
	public boolean setXPos(int newXPos){
		if (newXPos >= 0 && newXPos < getGridwidth()){
			this.xPos = newXPos;
			return true;
		}
		return false;
	}
	
	public int getYPosition(){
		return this.yPos;
	}
	
	public boolean setYPos(int newYPos){
		if (newYPos >= 0 && newYPos < getGridheight()){
			this.yPos = newYPos;
			return true;
		}
		return false;
	}
	
	public int getGridwidth(){
		return this.gridwidth;
	}
	
	public int getGridheight(){
		return this.gridheight;
	}
	
	public Random getRandomGenerator(){
		return this.randomgenerator;
	}
	
	public ArrayList<Interactable> getGridList() {
		return this.list;
	}
	
	/*
	 * END GETTERS & SETTERS
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
	
	@Override
	public int[] transform() {
		respawn();
		int[] tempArray = {0,0};
		return tempArray;
	}

	@Override
	public Icon returnIcon() {
		return this.icon;
	}

	@Override
	public JLabel returnLabel() {
		return this.label;
	}

	public boolean returnEnemy(){
		return enemy;
	}
	
	public boolean returnState(){
		return true;
	}
	
	public boolean goldcheck(){
		if(yPos-1>=0 && yPos-1<gridheight){
			if(list.get(gridwidth*(yPos-1)+xPos).getClass()==Gold.class){
				return true;
			}
		}
		return false;
	}
	
	public int goldabovenumber(){
		return ((Gold)list.get(gridwidth*(yPos-1)+xPos)).getGoldNumber();
	}
}

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.util.Random;


public class Nobbin extends JComponent implements Interactable {

	private Icon icon;
	private boolean alive;
	private int xPos, yPos;
	private int xRespawn, yRespawn;
	private JLabel label;
	private boolean enemy;
	private int gridwidth;
	private int gridheight;
	private ArrayList<Interactable> list;
	private Random randomgenerator;
	
	public Nobbin(int xPos, int yPos, int widthofgrid, int heightofgrid){
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
	}
	
	public void linkGrid(ArrayList<Interactable> inputlist){
		list = inputlist;
	}
	
	public void moveRandom() {
		int dir = randomgenerator.nextInt(4);
		boolean notyetmoved = true;
		int newXPos, newYPos;
		if (notyetmoved) {
			if (dir == 0){
				newYPos = yPos - 1;
				newXPos = xPos;
			} else if (dir == 1){
				newYPos = yPos + 1;
				newXPos = xPos;
			} else if (dir == 2){
				newYPos = yPos;
				newXPos = xPos - 1;
			} else {
				newYPos = yPos;
				newXPos = xPos + 1;
			}
			if (newXPos >= 0 && newXPos < gridwidth && newYPos >= 0 && newYPos < gridheight) {
				Interactable temp = list.get(gridwidth*newYPos+newXPos);
				if (temp.getClass() == Dirt.class && !temp.returnState()){
					list.set(5*yPos+xPos,new Dirt());
					list.get(5*yPos+xPos).transform();
					xPos = newXPos;
					yPos = newYPos;
					list.set(gridwidth*yPos+xPos,this);
					notyetmoved = false;
				}
			}
		}
		
	}
	
	private int[] getOptimalMove(int playerX, int playerY) {
		int dx = playerX - xPos;
		int dy = playerY - yPos;
		int[] move = new int[2];
		if (Math.abs(dx) <= Math.abs(dy)) {
			move[0] = Math.abs(dx)/dx;
		} else if (Math.abs(dx) > Math.abs(dy)) {
			move[1] = Math.abs(dy)/dy;
		}
		return move;
		
		//implement breadth-first or iterative depth-first search
	}
	
	@Override
	public int[] transform() {
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
	
	public int getXPosition(){
		return xPos;
	}
	
	public int getYPosition(){
		return yPos;
	}
}

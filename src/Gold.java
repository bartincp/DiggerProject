import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Gold implements Interactable{
	
	private Icon goldicon;
	private boolean state;
	private static final int points = 420;
	private JLabel label;
	private boolean enemy;
	private static final int emeraldChange = 0;
	private ArrayList<Interactable> list;
	private int spacesdropped;
	private int xpos, ypos;
	private int gridwidth, gridheight;
	private int number;
	
	public Gold(int xposition, int yposition, int width, int height, int goldnumber){
		goldicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Gold.png");
		label = new JLabel();
		label.setIcon(goldicon);
		enemy = false;
		state = true;
		spacesdropped = 0;
		xpos = xposition;
		ypos = yposition;
		gridwidth = width;
		gridheight = height;
		number = goldnumber;
	}
	
	public int[] transform() {
		goldicon = null;
		label.setIcon(null);
		state = false;
		int[] tempArray = {points, emeraldChange};
		return tempArray;
	}
	public void gimmeabreak() {
		if(spacesdropped>2){
			goldicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/BrokenGold.png");
			label.setIcon(goldicon);
		}
		spacesdropped = 0;
	}

	public void linkGrid(ArrayList<Interactable> grid){
		list = grid;
	}
	public void move(){
		boolean canmove = false;
		int predictedtileindex = gridwidth*(ypos+1)+xpos;
		if(predictedtileindex<list.size()&&predictedtileindex>=0){
			Interactable temp = list.get(predictedtileindex);
			if((temp.getClass()==Dirt.class&&temp.returnState()==false)||temp.getClass()!=Dirt.class){
				canmove = true;
				list.set(gridwidth*ypos+xpos,new Dirt());
				list.get(gridwidth*ypos+xpos).transform();
				list.set(predictedtileindex, this);
				ypos++;
				predictedtileindex = gridwidth*(ypos+1)+xpos;
				spacesdropped++;
			}
		}
		if(canmove==false)
			gimmeabreak();
	}
	
	public Icon returnIcon() {
		return goldicon;
	}

	
	public JLabel returnLabel() {
		return label;
	}

	public boolean returnEnemy() {
		return enemy;
	}

	public boolean returnState() {
		return state;
	}
	
	public int getXPosition(){
		return this.xpos;
	}
	
	public boolean setXPos(int newXPos){
		if (newXPos >= 0 && newXPos < getGridwidth()){
			this.xpos = newXPos;
			return true;
		}
		return false;
	}
	
	public int getYPosition(){
		return this.ypos;
	}
	
	public boolean setYPos(int newYPos){
		if (newYPos >= 0 && newYPos < getGridheight()){
			this.ypos = newYPos;
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
	
	public int getGoldNumber(){
		return number;
	}
}

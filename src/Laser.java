import javax.swing.*;


/**
 * TODO Put here a description of what this class does.
 *
 * @author carducjd.
 *         Created May 8, 2015.
 */
public class Laser extends JComponent implements Interactable{
	
	private Icon lasericon;
	private boolean laserstate;
	private int xposition, yposition;
	private int xdirection, ydirection;
	private JLabel label;
	private boolean enemy;
	
	public Laser(){
		this.lasericon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/laser.png");
		this.laserstate = true;
		this.xposition = 0;
		this.yposition = 0;
		label = new JLabel();
		label.setIcon(lasericon);
		this.enemy = true;
	}
	
	public Laser(int xaxis, int yaxis){
		this.lasericon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/laser.png");
		this.laserstate = true;
		this.xposition = xaxis;
		this.yposition = yaxis;
		label = new JLabel();
		label.setIcon(lasericon);
		this.enemy = true;
	}

	public int transform() {
		// Another suitable method name would be die();
		laserstate = false;
		lasericon = null;
		xposition = -1;
		yposition = -1;
		label = null;
		return 0;
	}

	public Icon returnIcon() {
		return lasericon;
	}

	public JLabel returnLabel() {
		return label;
	}
	
	// Make separate move methods?
	public void move(){
		//Upwards
//		if (ydirection == 1 && yposition >= 0 && yposition < gridheight){
//			list.set(gridwidth*yposition+xposition, new Dirt());
//			list.get(gridwidth*yposition+xposition).transform();
//			yposition++;
//			Interactable icon = list.get(gridwidth*yposition+xposition);
//			list.set(gridwidth*yposition+xposition, this);
//			}
//		}
	}
	
	public boolean returnEnemy(){
		return enemy;
	}
	//movement method?
}

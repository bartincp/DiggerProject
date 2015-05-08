import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;


public class Emerald extends JComponent implements Interactable{
	
	private Icon emeraldicon;
	private boolean emeraldstate;
	private int xposition, yposition;
	private static final int points = 10;
	
	// Added zero-parameter block since Level had trouble constructing Emerald without it (JDC)
	public Emerald(){
		this.emeraldicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Emerald.png");
		this.emeraldstate = true;
		this.xposition = 0;
		this.yposition = 0;
	}
	
	public Emerald(int xaxis, int yaxis){
		this.emeraldicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Emerald.png");
		this.emeraldstate = true;
		this.xposition = xaxis;
		this.yposition = yaxis;
	}

	public int transform(){
		emeraldstate = false;
		emeraldicon = null;
		xposition = -1;
		yposition = -1;
		return points;
	}


	public Icon returnIcon() {
		return emeraldicon;
	}
}

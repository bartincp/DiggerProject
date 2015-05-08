import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class Emerald extends JComponent implements Interactable{
	
	private Icon emeraldicon, airicon;
	private boolean emeraldstate;
	private int xposition, yposition;
	private static final int points = 10;
	private JLabel label;
	
	// Added zero-parameter block since Level had trouble constructing Emerald without it (JDC)
	public Emerald(){
		this.airicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Air.jpeg");
		this.emeraldicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Emerald.png");
		this.emeraldstate = true;
		this.xposition = 0;
		this.yposition = 0;
		label = new JLabel();
		label.setIcon(emeraldicon);
	}
	
	public Emerald(int xaxis, int yaxis){
		this.airicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Air.jpeg");
		this.emeraldicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/Emerald.png");
		this.emeraldstate = true;
		this.xposition = xaxis;
		this.yposition = yaxis;
		label = new JLabel();
		label.setIcon(emeraldicon);
	}

	public int transform(){
		emeraldstate = false;
		emeraldicon = null;
		xposition = -1;
		yposition = -1;
		label.setIcon(airicon);
		return points;
	}


	public Icon returnIcon() {
		return emeraldicon;
	}
	
	public JLabel returnLabel(){
		return label;
	}
}

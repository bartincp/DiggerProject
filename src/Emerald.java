import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Emerald extends ImageIcon{
	
	private Icon emeraldicon;
	private boolean emeraldstate;
	private int xposition, yposition;
	
	public Emerald(int xaxis, int yaxis){
		this.emeraldicon = new ImageIcon("desktop/Emerald");
		this.emeraldstate = true;
		this.xposition = xaxis;
		this.yposition = yaxis;
	}

	public void collect(){
		emeraldstate = false;
		emeraldicon = null;
		xposition = -1;
		yposition = -1;
	}
}

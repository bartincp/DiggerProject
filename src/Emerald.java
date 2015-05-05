import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Emerald extends ImageIcon{
	
	private Icon emeraldicon;
	private boolean emeraldstate;
	
	public Emerald(){
		emeraldicon = new ImageIcon(Image );
		emeraldstate = true;
	}

	public void collect()
	{
		emeraldstate = false;
		emeraldicon = null;
	}
}

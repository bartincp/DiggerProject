import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public interface Interactable{
	public int transform();
	public Icon returnIcon();
	public JLabel returnLabel();
	public boolean returnEnemy();
}

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * TODO This interface provides necessary methods for all classes that implement it regarding interactable game objects.
 *
 * @author Jake Carducci, Taylor Jenkins, Carl Bartine
 *         Modified May 21, 2015.
 */

public interface Interactable{
	public int[] transform();
	public Icon returnIcon();
	public JLabel returnLabel();
	public boolean returnEnemy();
	public boolean returnState();
}

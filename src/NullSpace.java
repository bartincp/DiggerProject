import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;


/**
 * 
 * TODO This class governs the creation and procedures of the null spaces in the playing space.
 *
 * @author Jake Carducci, Taylor Jenkins, Carl Bartine
 *         Modified May 21, 2015.
 */
public class NullSpace extends JComponent implements Interactable{
	private boolean nullstate;
	private Icon nullicon;
	private JLabel label;
	
	/**
	 * 
	 * TODO The constructor generates the null space with associated icon and state data.
	 *
	 */
	
	public NullSpace(){
		this.nullstate=true;
		this.nullicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/NullSpace.png");
		label = new JLabel();
		label.setIcon(nullicon);
	}

	/**
	 * 
	 * TODO This method would be invoked if the null space was being changed
	 *
	 * @return The point and emerald count changes in an array
	 */
	
	@Override
	public int[] transform() {
		nullstate = false;
		nullicon = null;
		int[] tempArray = {0,0};
		return tempArray;
	}

	/**
	 * 
	 * TODO The returnIcon method returns the icon of the null space
	 *
	 * @return The icon of the sprite
	 */	 
	
	@Override
	public Icon returnIcon() {
		return nullicon;
	}

	/**
	 * 
	 * TODO The returnLabel method returns the JLabel of the null space
	 *
	 * @return The JLabel of the sprite
	 */
	
	@Override
	public JLabel returnLabel() {
		// TODO Auto-generated method stub.
		return label;
	}

	/**
	 * 
	 * TODO This method returns the status of the sprite as an enemy, which should be false
	 *
	 * @return True if an enemy, false if not
	 */
	
	@Override
	public boolean returnEnemy() {
		// TODO Auto-generated method stub.
		return false;
	}

	/**
	 * 
	 * TODO The returnState method returns the 'existence' of the null space
	 *
	 * @return True if the laser exists, false if it does not
	 */
	
	@Override
	public boolean returnState() {
		// TODO Auto-generated method stub.
		return nullstate;
	}
}

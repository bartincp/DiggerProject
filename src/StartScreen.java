import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;


/**
 * 
 * TODO This class governs the creation and procedures of the start screen.
 *
 * @author Jake Carducci, Taylor Jenkins, Carl Bartine
 *         Modified May 21, 2015.
 */

public class StartScreen extends JComponent implements Interactable{
	private boolean startstate;
	private Icon starticon;
	private JLabel label;
	
	/**
	 * 
	 * TODO The constructor generates the start screen with associated icon data.
	 *
	 */
	
	public StartScreen(){
		this.startstate=true;
		this.starticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/IntroFinal.png");
		label = new JLabel();
		label.setIcon(starticon);
	}

	/**
	 * 
	 * TODO This method is invoked when the object needs to transform
	 *
	 * @return The point and emerald count changes in an array
	 */
	
	@Override
	public int[] transform() {
		startstate = false;
		starticon = null;
		int[] tempArray = {0,0};
		return tempArray;
	}

	/**
	 * 
	 * TODO The returnIcon method returns the icon of the start screen
	 *
	 * @return The icon of the sprite
	 */	 
	
	@Override
	public Icon returnIcon() {
		return starticon;
	}

	/**
	 * 
	 * TODO The returnLabel method returns the JLabel of the start screen
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
	 * TODO The returnState method returns the existence of the start screen
	 *
	 * @return True if the screen exists, false if it does not
	 */
	
	@Override
	public boolean returnState() {
		// TODO Auto-generated method stub.
		return startstate;
	}
}

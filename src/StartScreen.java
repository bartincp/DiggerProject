import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;


/**
 * TODO Put here a description of what this class does.
 *
 * @author carducjd.
 *         Created May 19, 2015.
 */
public class StartScreen extends JComponent implements Interactable{
	private boolean startstate;
	private Icon starticon;
	private JLabel label;
	
	public StartScreen(){
		this.startstate=true;
		this.starticon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/SmallIntro.png");
		label = new JLabel();
		label.setIcon(starticon);
	}

	@Override
	public int[] transform() {
		startstate = false;
		starticon = null;
		int[] tempArray = {0,0};
		return tempArray;
	}

	@Override
	public Icon returnIcon() {
		return starticon;
	}

	@Override
	public JLabel returnLabel() {
		// TODO Auto-generated method stub.
		return label;
	}

	@Override
	public boolean returnEnemy() {
		// TODO Auto-generated method stub.
		return false;
	}

	@Override
	public boolean returnState() {
		// TODO Auto-generated method stub.
		return startstate;
	}
}

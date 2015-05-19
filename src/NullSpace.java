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
public class NullSpace extends JComponent implements Interactable{
	private boolean nullstate;
	private Icon nullicon;
	private JLabel label;
	
	public NullSpace(){
		this.nullstate=true;
		this.nullicon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/NullSpace.png");
		label = new JLabel();
		label.setIcon(nullicon);
	}

	@Override
	public int[] transform() {
		nullstate = false;
		nullicon = null;
		int[] tempArray = {0,0};
		return tempArray;
	}

	@Override
	public Icon returnIcon() {
		return nullicon;
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
		return nullstate;
	}
}

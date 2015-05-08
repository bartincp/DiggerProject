import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class Nobbin extends JComponent implements Interactable {

	private Icon icon;
	private boolean alive;
	private int xPos, yPos;
	private int xRespawn, yRespawn;
	private JLabel label;
	
	public Nobbin(int xPos, int yPos){
		this.xRespawn = xPos;
		this.yRespawn = yPos;
		this.xPos = xPos;
		this.yPos = yPos;
		this.icon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/nobbin.png");
		this.label = new JLabel();
		this.label.setIcon(this.icon);
	}
	
	@Override
	public int transform() {
		return 0;
	}

	@Override
	public Icon returnIcon() {
		return this.icon;
	}

	@Override
	public JLabel returnLabel() {
		return this.label;
	}

}

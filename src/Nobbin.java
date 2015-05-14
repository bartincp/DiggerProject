import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class Nobbin extends JComponent implements Interactable {

	protected Icon icon;
	private boolean alive;
	private int xPos, yPos;
	private int xRespawn, yRespawn;
	protected JLabel label;
	private boolean enemy;
	
	public Nobbin(int xPos, int yPos){
		this.xRespawn = xPos;
		this.yRespawn = yPos;
		this.xPos = xPos;
		this.yPos = yPos;
		this.icon = new ImageIcon("C:/EclipseWorkspaces/csse220/DiggerProject/nobbin.png");
		this.label = new JLabel();
		this.label.setIcon(this.icon);
		this.enemy = true;
	}
	
	protected void moveMe(int playerX, int playerY) {
		//
	}
	
	protected int[] getOptimalMove(int playerX, int playerY) {
		int dx = playerX - xPos;
		int dy = playerY - yPos;
		int[] move = new int[2];
		if (Math.abs(dx) <= Math.abs(dy)) {
			move[0] = Math.abs(dx)/dx;
		} else if (Math.abs(dx) > Math.abs(dy)) {
			move[1] = Math.abs(dy)/dy;
		}
		return move;
		
		//implement breadth-first or iterative depth-first search
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

	public boolean returnEnemy(){
		return enemy;
	}
}

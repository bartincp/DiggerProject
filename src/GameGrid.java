import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class GameGrid extends JPanel{
	private ArrayList<Component> grid;
	private int gridwidth, gridheight;
	
	public GameGrid() {
		setBackground(Color.gray);
		Level lvl = new Level();
		lvl.advance();
		grid = lvl.getList();
		setLayout(new GridLayout(5, 5, 1, 1));
		for (int i = 0; i < grid.size(); i++) {
			add(grid.get(i));
		}
		int playerposition = 5*lvl.getPlayerYPosition()+lvl.getPlayerXPosition();
		Player userPlayer = (Player) grid.get(playerposition);
		userPlayer.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent event){
				userPlayer.keyPressed(event);
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyTyped(KeyEvent e) {
			}
		});
	}
}

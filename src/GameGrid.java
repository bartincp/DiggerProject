import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


public class GameGrid extends JPanel{
	private ArrayList<JComponent> grid;
	
	public GameGrid() {
		setBackground(Color.gray);
		Level lvl = new Level();
		lvl.advance();
		grid = lvl.getList();
		
		setLayout(new GridLayout(5, 5, 1, 1));
		for (int i = 0; i < grid.size(); i++) {
			Interactable gameObject = (Interactable) grid.get(i);
			Icon currIcon=gameObject.returnIcon();
			JLabel currLabel = new JLabel();
			currLabel.setIcon(currIcon);
//			add(grid.get(i));
			add(currLabel);
		}
		// Solution to missing imageicons: Use jlabels!!! Find a way to grab imageicons
		
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
		addKeyListener(new KeyListener(){

			
			public void keyPressed(KeyEvent event){
				int keyCode = event.getKeyCode();
				if(keyCode==KeyEvent.VK_U){
					lvl.advance();
				}
				if(keyCode==KeyEvent.VK_D){
					lvl.retreat();
				}
			}

			public void keyReleased(KeyEvent event){
			}

			public void keyTyped(KeyEvent event){
			}
		});
	}
}

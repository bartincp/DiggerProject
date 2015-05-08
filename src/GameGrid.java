import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class GameGrid extends JPanel{
	private ArrayList<Interactable> grid;
	private int playerposition;
	private Player userPlayer;
	private Level lvl;
	
	public GameGrid() {
		setFocusable(true);
		setBackground(Color.gray);
		lvl = new Level();
		lvl.advance();
		grid = lvl.getList();
		
		setLayout(new GridLayout(5, 5, 1, 1));
		for (int i = 0; i < grid.size(); i++) {
//			Interactable gameObject = (Interactable) grid.get(i);
//			Icon currIcon=gameObject.returnIcon();
			JLabel currLabel = (grid.get(i).returnLabel());
//			add(grid.get(i));
			add(currLabel);
		}
		// Solution to missing imageicons: Use jlabels!!! Find a way to grab imageicons
		
		playerposition = 5*lvl.getPlayerYPosition()+lvl.getPlayerXPosition();
		userPlayer = (Player) grid.get(playerposition);
		addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent event){
				removeAll();
				int keyCode = event.getKeyCode();
				if(keyCode==KeyEvent.VK_UP){
					userPlayer.moveUp();
//					System.out.println(userPlayer.returnScore());
				}
				if(keyCode==KeyEvent.VK_DOWN){
					userPlayer.moveDown();
//					System.out.println(userPlayer.returnScore());
				}
				if(keyCode==KeyEvent.VK_LEFT){
					userPlayer.moveLeft();
//					System.out.println(userPlayer.returnScore());
				}
				if(keyCode==KeyEvent.VK_RIGHT){
					userPlayer.moveRight();
//					System.out.println(userPlayer.returnScore());
				}
				if(keyCode==KeyEvent.VK_U){
					lvl = lvl.advance();
					grid = lvl.getList();
					playerposition = 5*lvl.getPlayerYPosition()+lvl.getPlayerXPosition();
					userPlayer = (Player) grid.get(playerposition);
				}
				if(keyCode==KeyEvent.VK_D){
					lvl = lvl.retreat();
					grid = lvl.getList();
					playerposition = 5*lvl.getPlayerYPosition()+lvl.getPlayerXPosition();
					userPlayer = (Player) grid.get(playerposition);
				}
				removeAll();
				setLayout(new GridLayout(5, 5, 1, 1));
				for (int i = 0; i < grid.size(); i++) {
					JLabel currLabel = (grid.get(i).returnLabel());
					add(currLabel);
				}
				repaint();
				validate();
			}

			public void keyReleased(KeyEvent event){
			}

			public void keyTyped(KeyEvent event){
			}
		});
	}
}

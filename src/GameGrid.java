import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;


public class GameGrid extends JPanel{
	private ArrayList<Component> grid;
	private int gridwidth, gridheight;
	
	/**
	 * Creates an initial grid of 25 light switches in a square grid of five width.
	 * Also checks to see what switches are adjacent to each other to create an action listener between the switches.
	 */
	public GameGrid(ArrayList<Component> list) {
		setBackground(Color.gray);
		setLayout(new GridLayout(gridwidth, gridheight, 1, 1));
		grid = list;
		for (int i = 0; i < grid.size(); i++) {
			add(grid.get(i));
		}
		grid.get(playerposition).addKeyListener();
	}
	
	/**
	 * Returns the array list used to create the grid layout of buttons.
	 * @return an array list.
	 */
	public ArrayList<MyButton> getList(){
		return button;
	}
	
	/**
	 * Returns the width of 5 for the grid layout so that it can be further manipulated.
	 * @return the grid width.
	 */
	public int returnwidth(){
		return gridwidth;
	}
}

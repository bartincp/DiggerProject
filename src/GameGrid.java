import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;


public class GameGrid extends JPanel{
	private ArrayList<Component> grid;
	private int gridwidth, gridheight;
	
	public GameGrid(ArrayList<Component> list) {
		setBackground(Color.gray);
		setLayout(new GridLayout(gridwidth, gridheight, 1, 1));
		grid = list;
		for (int i = 0; i < grid.size(); i++) {
			add(grid.get(i));
		}
		grid.get(playerposition).addKeyListener();
		grid.get(playerposition).linkGrid();
	}
}

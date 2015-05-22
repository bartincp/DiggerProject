import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * TODO This class governs the creation and procedures of the game instance and window for the Digger game.
 *
 * @author Jake Carducci, Taylor Jenkins, Carl Bartine
 *         Modified May 21, 2015.
 */


public class Runner extends JFrame{
	
	private GameGrid aPanel = new GameGrid();
	private StatDisplay bPanel = new StatDisplay();
	
	/**
	 * TODO This Runner constructor creates the main window to hold the stat and playing panels.
	 *
	 */
	public Runner() {
		Container pane = this.getContentPane();
		// May have to change to gridlayout in order to stack stats on top of playing space
		pane.setLayout(new FlowLayout());
		bPanel.importGameGrid(aPanel);
		aPanel.importStatDisplay(bPanel);
		pane.add(bPanel);
		pane.add(aPanel);
	}
	
	/**
	 * TODO This main line is run first when the executable is run, which creates a Runner object, which in turn initializes and runs the game.
	 *
	 * @param args Any console commands inputted, but none are necessary for this program
	 */
	public static void main(String args[]) {
		
		System.out.println("Dig Dug");
		
		Runner mainFrame = new Runner();
		mainFrame.setSize(700, 700);
		mainFrame.setTitle("Dig Dug");
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

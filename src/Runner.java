import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Runner extends JFrame{
	
	private GameGrid aPanel = new GameGrid();
	private StatDisplay bPanel = new StatDisplay();
	
	public Runner() {
		Container pane = this.getContentPane();
		// May have to change to gridlayout in order to stack stats on top of playing space
		pane.setLayout(new FlowLayout());
		bPanel.importGameGrid(aPanel);
		pane.add(bPanel);
		pane.add(aPanel);
	}
	
	public static void main(String args[]) {
		
		System.out.println("Dig Dug");
		
		Runner mainFrame = new Runner();
		mainFrame.setSize(700, 700);
		mainFrame.setTitle("Dig Dug");
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

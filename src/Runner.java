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
	
	public Runner() {
		Container pane = this.getContentPane();
		pane.setLayout(new FlowLayout());
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

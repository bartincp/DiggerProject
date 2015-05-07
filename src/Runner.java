import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Runner extends JFrame{
	
	public Runner() {
		Container pane = this.getContentPane();
//		pane.setLayout(new FlowLayout());
//		pane.add(aPanel);
	}
	
	public static void main(String args[]) {
		
		System.out.println("Starting ButtonsOnPanels...");
		
		Runner mainFrame = new Runner();
		
		mainFrame.setSize(700, 700);
		mainFrame.setTitle("DigDug");
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

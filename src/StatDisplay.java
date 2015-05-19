import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * TODO Put here a description of what this class does.
 *
 * @author carducjd.
 *         Created May 18, 2015.
 */
public class StatDisplay extends JPanel{
	private int lives;
	private int points;
	private int emeraldCount;
	private int currLevel;
	private GameGrid aPanel;
	private JLabel pointLabel;
	private JLabel livesLabel;
	private JLabel emeraldLabel;
	private JLabel levelLabel;
	
	public StatDisplay() { 
//		setFocusable(true);
		// Initiailize stat values
		points=0; lives=0; emeraldCount=0; currLevel=0;
		setBackground(Color.gray);
		setLayout(new FlowLayout());
		pointLabel = new JLabel("Points:" + points);
		livesLabel = new JLabel("Lives:" + lives);
		emeraldLabel = new JLabel("Emeralds:" + emeraldCount);
		levelLabel = new JLabel("Level:" + currLevel);
		add(pointLabel); add(livesLabel); add(emeraldLabel); add(levelLabel);
	}
	
	public void importGameGrid(GameGrid gamePanel){
		aPanel=gamePanel;
	}
	
	public void updateStatDisplay(int points, int emeraldCount, int lives, int currLevel){
		pointLabel.setText("Points:" + points);
		livesLabel.setText("Lives:" + lives);
		emeraldLabel.setText("Emeralds:" + emeraldCount);
		levelLabel.setText("Level:" + currLevel);
		updateUI();
	}
}

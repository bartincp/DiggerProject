import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * 
 * TODO This class governs the creation and procedures of the stat panel in the game window to store and display player stats.
 *
 * @author Jake Carducci, Taylor Jenkins, Carl Bartine
 *         Modified May 21, 2015.
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
	
	/**
	 * 
	 * TODO This constructor creates a panel above the playing space that will display the current playing statistics for the game, including emeralds, points, lives, and level number.
	 *
	 */
	
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
	
	/**
	 * 
	 * TODO This method links any imported GameGrid object to this StatPanel object.
	 *
	 * @param gamePanel The panel of the playing space being imported
	 */
	
	public void importGameGrid(GameGrid gamePanel){
		aPanel=gamePanel;
	}
	
	/**
	 * 
	 * TODO This method allows any class that sees this class to update the statistics of the panel manually.
	 *
	 * @param points The current score 
	 * @param emeraldCount The emeralds remaining
	 * @param lives Number of times player can hit hazards before game over
	 * @param currLevel Current level number
	 */
	
	public void updateStatDisplay(int points, int emeraldCount, int lives, int currLevel){
		pointLabel.setText("Points:" + points);
		livesLabel.setText("Lives:" + lives);
		emeraldLabel.setText("Emeralds:" + emeraldCount);
		levelLabel.setText("Level:" + currLevel);
		updateUI();
	}
}

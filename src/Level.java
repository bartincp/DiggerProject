
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;


/**
 * 
 * TODO This class governs the creation and procedures of the current level being invoked.
 *
 * @author Jake Carducci, Taylor Jenkins, Carl Bartine
 *         Modified May 21, 2015.
 */
public class Level {
	private int lvlNum;
	private ArrayList<Interactable> objectList;
//	private int[][] positionList;
//	private int[][] spawnList;
//	private int xIndex;
//	private int yIndex;
	private File lvlFile;
	private int playerxposition, playeryposition;
	private int xMax, yMax;
	private int emeraldCount;
	private int[] enemyxpositions, enemyypositions;
	private int enemycounter, goldcounter;
	private int[] goldxpositions, goldypositions;
	private boolean playerCreated;
	
	/**
	 * 
	 * TODO The constructor starts the game out at Level 0 along with initialization of position data arrays and other assorted information.
	 *
	 */
	
	public Level(){
		this.lvlFile = new File("C:/EclipseWorkspaces/csse220/DiggerProject/src/Level0.txt");
		this.lvlNum = -1;
		this.emeraldCount=0;
		this.objectList= new ArrayList<Interactable>();
//		this.positionList = new int[1][1];
//		this.spawnList = new int[1][1];
		enemyxpositions = new int[25];
		enemyypositions = new int[25];
		goldxpositions = new int[25];
		goldypositions = new int[25];
		enemycounter = 0;
		goldcounter = 0;
		playerCreated = false;
	}
	
	/**
	 * 
	 * TODO This method creates a scanner that reads the invoked text file row by row and starts at the top row. Outputs resulting object array to console.
	 *
	 */
	
	public void readFile(){
		enemycounter = 0;
		// Clears current level
		this.objectList.clear();
		this.emeraldCount=0;
		// Reads text file with level information
		this.lvlFile = new File("C:/EclipseWorkspaces/csse220/DiggerProject/src/Level" + this.lvlNum + ".txt");
		try {
			Scanner sc = new Scanner(this.lvlFile);
			System.out.println("Starting scanner...");
			// Temporary code to verify that file is being read
			readFileRowIter(sc,0);
		} catch (FileNotFoundException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		}
		System.out.println("The raw resulting array list is " + this.objectList);
		if (playerCreated){
			((Player) objectList.get(xMax*playeryposition+playerxposition)).linkGrid(objectList);	
		}
		
	}
	
	/**
	 * 
	 * TODO This method allows for iterative row-reading of the text file by the indicated scanner
	 *
	 * @param sc Current scanner
	 * @param yIndex Current row
	 */
	
	private void readFileRowIter(Scanner sc, int yIndex){
		if (!sc.hasNext()){
			System.out.println("Row " + yIndex + " doesn't exist! Ending scanner...");
			return;
		}
		String row = sc.next();
		readFileColIter(row,0,yIndex);
		readFileRowIter(sc,yIndex+1);
	}	
		
	/**
	 * 
	 * TODO This method allows for iterative character-reading of a given row in the text file by the scanner, and generates game objects based on character types.
	 *
	 * @param row String representing row and containing characters for objects
	 * @param xIndex Current character
	 * @param yIndex Current row
	 */
	
	private void readFileColIter(String row, int xIndex, int yIndex){
		xMax = row.length();
		if (xIndex == xMax){
			System.out.println("End of row, moving to row " + (yIndex+1));
			return;
		}
		char symbol = row.charAt(xIndex);
		if (symbol == '*'){
			System.out.println("Dirt created at row " + yIndex + ", column " + xIndex + ".");
			this.objectList.add(new Dirt(xIndex,yIndex,true));
		}
		if(symbol == '-'){
			System.out.println("Air created at row " + yIndex + ", column " + xIndex + ".");
			Interactable temp = new Dirt(xIndex,yIndex,true);
			temp.transform();
			this.objectList.add(temp);
		}
		if (symbol == 'v'){
			System.out.println("Emerald created at row " + yIndex + ", column " + xIndex + ".");
			this.objectList.add(new Emerald(xIndex, yIndex));
			emeraldCount++;
		}
		if (symbol == 'o'){
			System.out.println("Player created at row " + yIndex + ", column " + xIndex + ".");
			// Used xMax for grid dimensions for now, should definitely change
			playerCreated = true;
			this.objectList.add(new Player(xIndex,yIndex,xMax,xMax));
			playerxposition = xIndex;
			playeryposition = yIndex;
		}
		if (symbol == 'n'){
			System.out.println("Nobbin created at row " + yIndex + ", column " + xIndex + ".");
			this.objectList.add(new Nobbin(xIndex, yIndex, 5, 5, enemycounter));
			enemyxpositions[enemycounter] = xIndex;
			enemyypositions[enemycounter] = yIndex;
			enemycounter++;
		}
		if (symbol == 'h'){
			System.out.println("Hobbin created at row " + yIndex + ", column " + xIndex + ".");
			this.objectList.add(new Hobbin(xIndex, yIndex, 5, 5, enemycounter));
			enemyxpositions[enemycounter] = xIndex;
			enemyypositions[enemycounter] = yIndex;
			enemycounter++;
		}
		if (symbol == 'g'){
			System.out.println("Gold created at row " + yIndex + ", column " + xIndex + ".");
			this.objectList.add(new Gold(xIndex, yIndex, 5, 5, goldcounter));
			goldxpositions[goldcounter] = xIndex;
			goldypositions[goldcounter] = yIndex;
			goldcounter++;
		}
		if (symbol == 'I'){
			System.out.println("Intro created at row " + yIndex + ", column " + xIndex + ".");
			this.objectList.add(new StartScreen());
		}
		if (symbol == 'x'){
			System.out.println("Null space at row " + yIndex + ", column " + xIndex + ".");
			this.objectList.add(new NullSpace());
		}
		readFileColIter(row, xIndex+1, yIndex);
	}

	/**
	 * 
	 * TODO This method forces the game to move from the current level to the next
	 *
	 * @return Next level
	 */
	
	public Level advance(){
			this.lvlNum = this.lvlNum + 1;
			playerCreated = false;
			readFile();
			return this;
	}
	
	/**
	 * 
	 * TODO This method forces the game to move from the current level to the previous
	 *
	 * @return Previous level
	 */
	
	public Level retreat(){
		this.lvlNum = this.lvlNum - 1;
		playerCreated = false;
		readFile();
		return this;
	}
	
	/**
	 * 
	 * TODO This method forces the game to move from the current level to the 0th (intro)
	 *
	 * @return 0th or intro level
	 */
	
	public Level ReturnToIntro(){
		this.lvlNum = 0;
		playerCreated = false;
		readFile();
		return this;
	}
	
//	public void actionPerformed(KeyEvent event) {
//		int keyCode = event.getKeyCode();
//		if(keyCode==KeyEvent.VK_U){
//			retreat();
//		}
//		if(keyCode==KeyEvent.VK_P){
//			advance();
//		}
//	}
	
	/**
	 * 
	 * TODO Returns current horizontal position of the player
	 *
	 * @return Horizontal player position
	 */
	
	public int getPlayerXPosition(){
		return playerxposition;
	}
	
	/**
	 * 
	 * TODO Returns current vertical position of the player
	 *
	 * @return Vertical player position
	 */
	
	public int getPlayerYPosition(){
		return playeryposition;
	}
	
	/**
	 * 
	 * TODO Returns level number of current level
	 *
	 * @return Level number
	 */
	
	public int getLevelNumber(){
		return lvlNum;
	}
	
	/**
	 * 
	 * TODO Returns object list array generated by scanner
	 *
	 * @return Grid object list
	 */
	
	public ArrayList<Interactable> getList(){
		return objectList;
	}
	
	/**
	 * 
	 * TODO Returns the number of emeralds generated by scanner
	 *
	 * @return Emerald count for current level
	 */
	
	public int getEmeraldCount(){
		return emeraldCount;
	}
	
	/**
	 * 
	 * TODO Returns current horizontal positions of the generated enemies
	 *
	 * @return Array of enemy positions in x-direction
	 */
	
	public int[] getEnemyXPositions(){
		return enemyxpositions;
	}
	
	/**
	 * 
	 * TODO Returns current vertical positions of the generated enemies
	 *
	 * @return Array of enemy positions in y-direction
	 */
	
	public int[] getEnemyYPositions(){
		return enemyypositions;
	}
	
	/**
	 * 
	 * TODO Returns unique identifier for a given enemy
	 *
	 * @return Enemy identifier number/counter
	 */
	
	public int getEnemyNumber(){
		return enemycounter;
	}
	
	/**
	 * 
	 * TODO This method tells if the player was ever generated by the scanner during level creation
	 *
	 * @return True if player was created, false otherwise
	 */
	
	public boolean getPlayerCreated(){
		return playerCreated;
	}

	/**
	 * 
	 * TODO Allows for manual setting of horizontal positions for generated enemies
	 *
	 * @param n Current index in enemy horizontal position array
	 * @param xPosition Desired horizontal position of enemy
	 */
	
	public void setEnemyXPositions(int n, int xPosition) {
		enemyxpositions[n] = xPosition;
	}

	/**
	 * 
	 * TODO Allows for manual setting of vertical positions for generated enemies
	 *
	 * @param n Current index in enemy vertical position array
	 * @param yPosition Desired vertical position of enemy
	 */
	
	public void setEnemyYPositions(int n, int yPosition) {
		enemyypositions[n] = yPosition;
	}
	
	/**
	 * 
	 * TODO Returns current horizontal positions of the generated gold
	 *
	 * @return Array of gold bag positions in x-direction
	 */
	
	public int[] getGoldXPositions(){
		return goldxpositions;
	}
	
	/**
	 * 
	 * TODO Returns current vertical positions of the generated gold
	 *
	 * @return Array of gold bag positions in y-direction
	 */
	
	public int[] getGoldYPositions(){
		return goldypositions;
	}
	
	/**
	 * 
	 * TODO Returns unique identifier for a given gold bag
	 *
	 * @return Gold bag identifier number/counter
	 */
	
	public int getGoldNumber(){
		return goldcounter;
	}
	
	/**
	 * 
	 * TODO Allows for manual setting of horizontal positions for generated gold
	 *
	 * @param n Current index in gold horizontal position array
	 * @param xPosition Desired horizontal position of gold
	 */
	
	public void setGoldXPositions(int n, int xPosition){
		goldxpositions[n] = xPosition;
	}
	
	/**
	 * 
	 * TODO Allows for manual setting of vertical positions for generated gold
	 *
	 * @param n Current index in gold vertical position array
	 * @param yPosition Desired vertical position of gold
	 */
	
	public void setGoldYPositions(int n, int yPosition){
		goldypositions[n] = yPosition;
	}
}


import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;


/**
 * TODO Put here a description of what this class does.
 *
 * @author carducjd.
 *         Created May 5, 2015.
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
	private int enemycounter;
	
	public Level(){
		this.lvlFile = new File("C:/EclipseWorkspaces/csse220/DiggerProject/src/Level0.txt");
		this.lvlNum = 0;
		this.emeraldCount=0;
		this.objectList= new ArrayList<Interactable>();
//		this.positionList = new int[1][1];
//		this.spawnList = new int[1][1];
		enemyxpositions = new int[25];
		enemyypositions = new int[25];
		enemycounter = 0;
	}
	
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
		((Player) objectList.get(xMax*playeryposition+playerxposition)).linkGrid(objectList);
	}
	
	private void readFileRowIter(Scanner sc, int yIndex){
		if (!sc.hasNext()){
			System.out.println("Row " + yIndex + " doesn't exist! Ending scanner...");
			return;
		}
		String row = sc.next();
		readFileColIter(row,0,yIndex);
		readFileRowIter(sc,yIndex+1);
	}	
		
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
		if (symbol == 'v'){
			System.out.println("Emerald created at row " + yIndex + ", column " + xIndex + ".");
			this.objectList.add(new Emerald(xIndex, yIndex));
			emeraldCount++;
		}
		if (symbol == 'o'){
			System.out.println("Player created at row " + yIndex + ", column " + xIndex + ".");
			// Used xMax for grid dimensions for now, should definitely change
			this.objectList.add(new Player(xIndex,yIndex,xMax,xMax));
			playerxposition = xIndex;
			playeryposition = yIndex;
		}
		if (symbol == 'n'){
			System.out.println("Nobbin created at row " + yIndex + ", column " + xIndex + ".");
			this.objectList.add(new Nobbin(xIndex, yIndex, 5, 5));
			enemyxpositions[enemycounter] = xIndex;
			enemyypositions[enemycounter] = yIndex;
			enemycounter++;
		}
		readFileColIter(row, xIndex+1, yIndex);
	}

	public Level advance(){
			this.lvlNum = this.lvlNum + 1;
			readFile();
			return this;
	}
	
	public Level retreat(){
		this.lvlNum = this.lvlNum - 1;
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
	
	public int getPlayerXPosition(){
		return playerxposition;
	}
	
	public int getPlayerYPosition(){
		return playeryposition;
	}
	
	public int getLevelNumber(){
		return lvlNum;
	}
	
	public ArrayList<Interactable> getList(){
		return objectList;
	}
	
	public int getEmeraldCount(){
		return emeraldCount;
	}
	
	public int[] getEnemyXPositions(){
		return enemyxpositions;
	}
	
	public int[] getEnemyYPositions(){
		return enemyypositions;
	}
	
	public int getEnemyNumber(){
		return enemycounter;
	}

	public void setEnemyXPositions(int n, int xPosition) {
		enemyxpositions[n] = xPosition;
	}

	public void setEnemyYPositions(int n, int yPosition) {
		enemyypositions[n] = yPosition;
	}
}

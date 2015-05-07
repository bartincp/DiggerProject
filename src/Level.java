import java.io.*;
import java.util.*;
import java.awt.event.*;


/**
 * TODO Put here a description of what this class does.
 *
 * @author carducjd.
 *         Created May 5, 2015.
 */
public class Level {
	private int lvlNum;
	private ArrayList objectList;
//	private int[][] positionList;
//	private int[][] spawnList;
//	private int xIndex;
//	private int yIndex;
	private File lvlFile;
	
	public Level(){
		this.lvlFile = new File("C:/EclipseWorkspaces/csse220/DiggerProject/src/Level0.txt");
		this.lvlNum = 0;
		this.objectList= new ArrayList();
//		this.positionList = new int[1][1];
//		this.spawnList = new int[1][1];
	}
	
	public void readFile(){
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
		int xMax = row.length();
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
		}
		if (symbol == 'o'){
			System.out.println("Player created at row " + yIndex + ", column " + xIndex + ".");
			// Used xMax for grid dimensions for now, should definitely change
			this.objectList.add(new Player(xIndex,yIndex,xMax,xMax));
		}
		readFileColIter(row, xIndex+1, yIndex);
	}

	public void advance(){
		this.lvlNum = this.lvlNum + 1;
		readFile();
	}
	
	public void retreat(){
		this.lvlNum = this.lvlNum - 1;
		readFile();
	}
	
	public void actionPerformed(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if(keyCode==KeyEvent.VK_U){
			retreat();
		}
		if(keyCode==KeyEvent.VK_P){
			advance();
		}
	}
}

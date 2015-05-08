import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * TODO Put here a description of what this class does.
 *
 * @author carducjd.
 *         Created May 5, 2015.
 */
public class Level {
	private int lvlNum;
	private ArrayList<Component> objectList;
//	private int[][] positionList;
//	private int[][] spawnList;
//	private int xIndex;
//	private int yIndex;
	private File lvlFile;
	private int playerxposition, playeryposition;
	private int xMax, yMax;
	
	public Level(){
		this.lvlFile = new File("C:/EclipseWorkspaces/csse220/DiggerProject/src/Level0.txt");
		this.lvlNum = 0;
		this.objectList= new ArrayList<Component>();
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
		}
		if (symbol == 'o'){
			System.out.println("Player created at row " + yIndex + ", column " + xIndex + ".");
			// Used xMax for grid dimensions for now, should definitely change
			this.objectList.add(new Player(xIndex,yIndex,xMax,xMax));
			playerxposition = xIndex;
			playeryposition = yIndex;
		}
		readFileColIter(row, xIndex+1, yIndex);
	}
	
	// Will need use of JSwing
	public void create(){
		
	}
	
	// Will need use of JSwing
	public void destroy(){
		
	}
	
	public void initLvl(){
		this.lvlNum = this.lvlNum + 1;
		readFile();
//		create();
	}
	
	public void advance(){
//		destroy();
		initLvl();
	}
	
	public int getPlayerXPosition(){
		return playerxposition;
	}
	
	public int getPlayerYPosition(){
		return playeryposition;
	}
	
	public ArrayList<Component> getList(){
		return objectList;
	}
}

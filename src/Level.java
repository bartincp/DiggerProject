import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * TODO Put here a description of what this class does.
 *
 * @author carducjd.
 *         Created May 5, 2015.
 */
public class Level {
	private int lvlNum;
	private int[][] positionList;
	private int[][] spawnList;
	private File lvlFile;
	
	public Level(){
		this.lvlFile = new File("Level0.txt");
		this.lvlNum = 0;
		this.positionList = new int[1][1];
		this.spawnList = new int[1][1];
	}
	
	public void readFile(){
		this.lvlFile = new File("Level" + this.lvlNum + ".txt");
		try {
			Scanner sc = new Scanner(this.lvlFile);
			// Temporary code to verify that file is being read
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				System.out.println(line);
			}
			
		} catch (FileNotFoundException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		}
		
	}
	
	// Will need use of JSwing
	public void create(){
		
	}
	
	// Will need use of JSwing
	public void destroy(){
		
	}
	
	public void advance(){
		destroy();
		this.lvlNum = this.lvlNum + 1;
		readFile();
		create();
	}
}

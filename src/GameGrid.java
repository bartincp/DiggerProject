import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * 
 * TODO This class governs the creation and procedures of the playing space/panel in the Digger game.
 *
 * @author Jake Carducci, Taylor Jenkins, Carl Bartine
 *         Modified May 21, 2015.
 */

public class GameGrid extends JPanel{
	private int lives;
	private int points;
	private int emeraldCount;
	private ArrayList<Interactable> grid;
	private int playerposition;
	private Player userPlayer;
	private Level lvl;
	private Timer playertimer, lasertimer, enemytimer;
	private ActionListener taskPerformer, taskPerformerLaser, taskPerformerEnemies, taskPerformerGold;
	private boolean runningtimer = false;
	private Laser pewpew;
	private Timer[] goldtimers;
	private int[] statArray;
	private StatDisplay bPanel;
	
	/**
	 * TODO The GameGrid constructor creates a playing space where the player, collectables, and monsters will spawn, along with associated timers and action listeners.
	 *
	 */
	public GameGrid() {
		setFocusable(true);
		setBackground(Color.gray);
		// Creates two levels from constructor and advance method
		lvl = new Level();
		lvl.advance();
		grid = lvl.getList();
		// Only for intro screen
		setLayout(new GridLayout(1, 1, 1, 1));
		for (int i = 0; i < grid.size(); i++) {
			JLabel currLabel = (grid.get(i).returnLabel());
			add(currLabel);
		}
		addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent event){
				int keyCode = event.getKeyCode();
				if(keyCode==KeyEvent.VK_UP && lvl.getPlayerCreated()){
					taskPerformer = new ActionListener(){
						public void actionPerformed(ActionEvent event){
							userPlayer.moveUp();
							emeraldCheck();
							// Need a way to send points and emerald change to base method
							removeAll();
							setLayout(new GridLayout(5, 5, 1, 1));
							for (int i = 0; i < grid.size(); i++) {
								JLabel currLabel = (grid.get(i).returnLabel());
								add(currLabel);
							}
							repaint();
							validate();
						}
					};
				}
				if(keyCode==KeyEvent.VK_DOWN && lvl.getPlayerCreated()){
					taskPerformer = new ActionListener(){
						public void actionPerformed(ActionEvent event){
							userPlayer.moveDown();
							emeraldCheck();
							removeAll();
							setLayout(new GridLayout(5, 5, 1, 1));
							for (int i = 0; i < grid.size(); i++) {
								JLabel currLabel = (grid.get(i).returnLabel());
								add(currLabel);
							}
							repaint();
							validate();
						}
					};
				}
				if(keyCode==KeyEvent.VK_LEFT && lvl.getPlayerCreated()){
					taskPerformer = new ActionListener(){
						public void actionPerformed(ActionEvent event){
							userPlayer.moveLeft();
							//Checks if the player moved a bag of gold over a gap and initiates the gold timer if true
							int goldpushcheck = userPlayer.goldPushCheck();
							if(goldpushcheck!=-1){
								int x = lvl.getGoldXPositions()[goldpushcheck];
								int y = lvl.getGoldYPositions()[goldpushcheck];
								((Gold)grid.get(5*y+x)).linkGrid(grid);
								if(((Gold)grid.get(5*y+x)).airBelow()==true){
									goldtimers[goldpushcheck].start();
									//Checks if the gold that will begin to fall has another gold above and starts that timer accordingly
									if(grid.get(5*(y-1)+x).getClass()==Gold.class){
										Interactable anothertempgold = grid.get(5*(y-1)+x);
										goldtimers[((Gold)anothertempgold).getGoldNumber()].start();
									}
								}
							}
							emeraldCheck();
							removeAll();
							setLayout(new GridLayout(5, 5, 1, 1));
							for (int i = 0; i < grid.size(); i++) {
								JLabel currLabel = (grid.get(i).returnLabel());
								add(currLabel);
							}
							repaint();
							validate();
						}
					};
				}
				if(keyCode==KeyEvent.VK_RIGHT && lvl.getPlayerCreated()){
					taskPerformer = new ActionListener(){
						public void actionPerformed(ActionEvent event){
							userPlayer.moveRight();
							//Checks if player moved a bag of gold over a gap and initiates the timer if true
							int goldpushcheck = userPlayer.goldPushCheck();
							if(goldpushcheck!=-1){
								int x = lvl.getGoldXPositions()[goldpushcheck];
								int y = lvl.getGoldYPositions()[goldpushcheck];
								((Gold)grid.get(5*y+x)).linkGrid(grid);
								if(((Gold)grid.get(5*y+x)).airBelow()==true){
									goldtimers[goldpushcheck].start();
									//Checks if the falling bag of gold had a gold above it and initiates that timer if true
									if(grid.get(5*(y-1)+x).getClass()==Gold.class){
										Interactable anothertempgold = grid.get(5*(y-1)+x);
										goldtimers[((Gold)anothertempgold).getGoldNumber()].start();
									}
								}
							}
							emeraldCheck();
							removeAll();
							setLayout(new GridLayout(5, 5, 1, 1));
							for (int i = 0; i < grid.size(); i++) {
								JLabel currLabel = (grid.get(i).returnLabel());
								add(currLabel);
							}
							repaint();
							validate();
						}
					};
				}
				if(keyCode==KeyEvent.VK_SPACE && lvl.getPlayerCreated()){
					boolean gridstate;
					//Checks to see if the laser timer was created yet and stops the timer each time the spacebar is pressed
					//Ensures that the timer doesn't keep adding to itself if the spacebar is hit multiple times
					if(lasertimer!=null&&lasertimer.isRunning()==true)
						lasertimer.stop();
					int predictedposition;
					//Creates laser if the direction the player moved last and one in front of the player is in the grid and if the grid is open
					//Handles if the player shoots up or down
					if(userPlayer.getDirectionAxis()==0){
						predictedposition = 5*(userPlayer.getYPosition()+userPlayer.getDirectionAmount())+userPlayer.getXPosition();
						if(predictedposition >= 0 && predictedposition < grid.size()){
							Interactable temporary = grid.get(predictedposition);
							gridstate = temporary.returnState();
							if(gridstate==false){
								if(pewpew.returnState()==false){
									pewpew = new Laser(userPlayer.getXPosition(), (userPlayer.getYPosition()+userPlayer.getDirectionAmount()), 5, 5, userPlayer);
									grid.set(predictedposition, pewpew);
								}
							}
							//If the predicted space to be created in is occupied by an enemy the laser kills the enemy and respawns the enemy
							if(temporary.returnEnemy()==true){
								grid.set(predictedposition, new Dirt());
								grid.get(predictedposition).transform();
								((Nobbin)temporary).respawn();
							}
						}
					}
					//Creates the laser as above except if the player last moved left or right
					else{
						predictedposition = 5*userPlayer.getYPosition()+userPlayer.getXPosition()+userPlayer.getDirectionAmount();
						if(predictedposition >= 0 && predictedposition < grid.size()){
							Interactable temporary = grid.get(predictedposition);
							gridstate = grid.get(predictedposition).returnState();
							if(gridstate==false){
								if(pewpew.returnState()==false){
									pewpew = new Laser(userPlayer.getXPosition()+userPlayer.getDirectionAmount(), userPlayer.getYPosition(), 5, 5, userPlayer);
									grid.set(predictedposition, pewpew);
								}
							}
							if(temporary.returnEnemy()==true&&temporary.getClass()!=Laser.class&&pewpew.returnState()==false){
								grid.set(predictedposition, new Dirt());
								grid.get(predictedposition).transform();
								((Nobbin)temporary).respawn();
							}
						}
					}
					//Makes sure the laser doesn't respond to the player moving after it is created
					pewpew.linkGrid(grid);
					int tempdirectionaxis = userPlayer.getDirectionAxis();
					int tempdirectionamount = userPlayer.getDirectionAmount();
					taskPerformerLaser = new ActionListener(){
						public void actionPerformed(ActionEvent event){
							if(pewpew.returnState()==true){
								pewpew.move(tempdirectionaxis, tempdirectionamount);
								removeAll();
								setLayout(new GridLayout(5, 5, 1, 1));
								for (int i = 0; i < grid.size(); i++) {
									JLabel currLabel = (grid.get(i).returnLabel());
									add(currLabel);
								}
								repaint();
								validate();
							}
						}
					};
					lasertimer = new Timer(200,taskPerformerLaser);
					lasertimer.setInitialDelay(200);
					lasertimer.start();
				}
				//Advances level manually
				if(keyCode==KeyEvent.VK_U){
					if(lvl.getLevelNumber()<3){
						// No gold timers in Level 0
						if (lvl.getLevelNumber() > 0){
							enemytimer.stop();
							stopAllGoldTimers();
						}
						removeAll();
						lvl = lvl.advance();
						setLayout(new GridLayout(5, 5, 1, 1));
						grid = lvl.getList();
						for (int i = 0; i < grid.size(); i++) {
							JLabel currLabel = (grid.get(i).returnLabel());
							add(currLabel);
						}
						if (lvl.getPlayerCreated()){
							playerposition = 5*lvl.getPlayerYPosition()+lvl.getPlayerXPosition();
							userPlayer = (Player) grid.get(playerposition);
						}
						updateUI();
						repaint();
						validate();
						createGoldTimers();						
						createAndStartEnemies();
					}
				}
				//Moves backwards for the level manually
				if(keyCode==KeyEvent.VK_D){
					if(lvl.getLevelNumber()>1){
						stopAllGoldTimers();
						removeAll();
						lvl = lvl.retreat();
						setLayout(new GridLayout(5, 5, 1, 1));
						grid = lvl.getList();
						for (int i = 0; i < grid.size(); i++) {
							JLabel currLabel = (grid.get(i).returnLabel());
							add(currLabel);
						}
						if (lvl.getPlayerCreated()){
							playerposition = 5*lvl.getPlayerYPosition()+lvl.getPlayerXPosition();
							userPlayer = (Player) grid.get(playerposition);
						}
						repaint();
						validate();
						createGoldTimers();
						enemytimer.stop();
						createAndStartEnemies();
					}
				}
				//If the user holds down an arrow key then the timer will only engage if not running already to stop multiple instances and limit speed
				if((keyCode != KeyEvent.VK_D && keyCode != KeyEvent.VK_U && keyCode != KeyEvent.VK_SPACE) && lvl.getPlayerCreated()){
					if(runningtimer == false){
						playertimer = new Timer(600,taskPerformer);
						playertimer.setInitialDelay(0);
						playertimer.start();
						runningtimer = true;
					}
				}
				//Creates all the necessary timers when switching between levels manually
				if((keyCode == KeyEvent.VK_U || keyCode == KeyEvent.VK_D) && lvl.getPlayerCreated()){
					createGoldTimers();
					enemytimer.stop();
					createAndStartEnemies();
					emeraldCount = lvl.getEmeraldCount();
					userPlayer.linkLvl(lvl);
					createInitialConditions();
				}
				removeAll();
				setLayout(new GridLayout(5, 5, 1, 1));
				for (int i = 0; i < grid.size(); i++) {
					JLabel currLabel = (grid.get(i).returnLabel());
					add(currLabel);
				}
				repaint();
				validate();
				//Checks whenever the player moves if the player is below a bag of gold and starts the timer if true
				//Also checks if theres another bag of gold above that gold bag so both fall accordingly
				if (lvl.getPlayerCreated()){
					if(userPlayer.goldcheck()==true){
						int tempnumber = userPlayer.goldabovenumber();
						goldtimers[tempnumber].start();
						int doubleabove = (5*(userPlayer.getYPosition()-2)+userPlayer.getXPosition());
						if(doubleabove>=0){
							if(grid.get(doubleabove).getClass()==Gold.class){
								goldtimers[((Gold)grid.get(doubleabove)).getGoldNumber()].start();
							}
						}
					}
				}
			}

			public void keyReleased(KeyEvent event){
				int keyCode = event.getKeyCode();
				if((keyCode != KeyEvent.VK_D && keyCode != KeyEvent.VK_U && keyCode != KeyEvent.VK_SPACE) && lvl.getPlayerCreated()){
				playertimer.stop();
				runningtimer = false;
					if(userPlayer.goldcheck()==true){
						int tempnumber = userPlayer.goldabovenumber();
						goldtimers[tempnumber].start();
					}
				}
			}

			public void keyTyped(KeyEvent event){
			}
		});
	}
	
	/**
	 * 
	 * TODO The emeraldCheck method is run everytime an input is accepted from the user. It checks all stat changes and sees if all emeralds are collected or all lives are lost. If so, the level is changed accordingly.
	 *
	 */
	
	protected void emeraldCheck(){
		statArray = userPlayer.returnStats();
		int lvlNum = lvl.getLevelNumber();
		points  += statArray[0];
		emeraldCount  += statArray[1];
		lives = statArray[2];
		bPanel.updateStatDisplay(points,emeraldCount,lives,lvlNum);
		System.out.println("Lives: "+lives);
		System.out.println("Level: "+lvlNum);
		System.out.println("Emeralds left: "+emeraldCount);
		// If life count is negative, user is returned to intro (or game over)
		if (lives < 0){
			enemytimer.stop();
			stopAllGoldTimers();
			lvl.ReturnToIntro();
			removeAll();
			userPlayer.setLives(3);
			lives = 3;
			grid = lvl.getList();
			setLayout(new GridLayout(1, 1, 1, 1));
			for (int i = 0; i < grid.size(); i++) {
				JLabel currLabel = (grid.get(i).returnLabel());
				add(currLabel);
			}
			repaint();
			validate();
		}
		if (emeraldCount == 0 && lvl.getLevelNumber()<3){
			stopAllGoldTimers();
			lvl.advance();
			grid = lvl.getList();
			setLayout(new GridLayout(5, 5, 1, 1));
			for (int i = 0; i < grid.size(); i++) {
				JLabel currLabel = (grid.get(i).returnLabel());
				add(currLabel);
			}
			repaint();
			validate();
			playerposition = 5*lvl.getPlayerYPosition()+lvl.getPlayerXPosition();
			userPlayer = (Player) grid.get(playerposition);
			emeraldCount = lvl.getEmeraldCount();
			createGoldTimers();
			enemytimer.stop();
			createAndStartEnemies();
			userPlayer.linkLvl(lvl);
		}
	}
	
	/**
	 * 
	 * TODO The createGoldTimers method adds a timer for and marks positions of existing gold bags, which will help the automatic falling of gold bags through vertical channels.
	 *
	 */
	
	private void createGoldTimers(){
		goldtimers = new Timer[25];
		for(int n = 0; n < lvl.getGoldNumber(); n++){
			int num = n;
			goldtimers[n] = new Timer(600, new ActionListener(){
				public void actionPerformed(ActionEvent event){
					int goldxposition = lvl.getGoldXPositions()[num];
					int goldyposition = lvl.getGoldYPositions()[num];
					Interactable temp = grid.get(5*goldyposition+goldxposition);
					if(temp.getClass()==Gold.class){
						Gold tempgold = (Gold)grid.get(5*goldyposition+goldxposition);
						tempgold.linkGrid(grid);
						tempgold.move();
						lvl.setGoldXPositions(num, tempgold.getXPosition());
						lvl.setGoldYPositions(num, tempgold.getYPosition());
						removeAll();
						setLayout(new GridLayout(5, 5, 1, 1));
						for (int i = 0; i < grid.size(); i++) {
							JLabel currLabel = (grid.get(i).returnLabel());
							add(currLabel);
						}
						repaint();
						validate();
					}
				}
			});
			goldtimers[n].setInitialDelay(1200);
		}
	}
	
	/**
	 * 
	 * TODO The stopAllGoldTimers method disables any existing gold timers, which proves very useful in level changes to prevent conflicts
	 *
	 */
	
	private void stopAllGoldTimers(){
		for(int n = 0; n < lvl.getGoldNumber(); n++){
			goldtimers[n].stop();
		}
	}
	
	/**
	 * 
	 * TODO The createAndStartEnemies method adds timers for and marks positions of created enemies, which will help the creatures automatically move and perform actions over time.
	 *
	 */
	
	private void createAndStartEnemies(){
		taskPerformerEnemies = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				for(int n = 0; n < lvl.getEnemyNumber(); n++){
					int enemyxposition = lvl.getEnemyXPositions()[n];
					int enemyyposition = lvl.getEnemyYPositions()[n];
					Nobbin tempnobbin = (Nobbin)grid.get(5*enemyyposition+enemyxposition);
					tempnobbin.linkGrid(grid);
					tempnobbin.linkLvl(lvl);
					tempnobbin.moveRandom();
					lvl.setEnemyXPositions(n, tempnobbin.getXPosition());
					lvl.setEnemyYPositions(n, tempnobbin.getYPosition());
					if(tempnobbin.goldcheck()==true){
						int tempnumber = tempnobbin.goldabovenumber();
						goldtimers[tempnumber].start();
						int doubleabove = (5*(tempnobbin.getYPosition()-2)+tempnobbin.getXPosition());
						if(doubleabove>=0){
							if(grid.get(doubleabove).getClass()==Gold.class){
								goldtimers[((Gold)grid.get(doubleabove)).getGoldNumber()].start();
							}
						}
					}
				}
				removeAll();
				setLayout(new GridLayout(5, 5, 1, 1));
				for (int i = 0; i < grid.size(); i++) {
					JLabel currLabel = (grid.get(i).returnLabel());
					add(currLabel);
				}
				repaint();
				validate();
			}
		};
		enemytimer = new Timer(500, taskPerformerEnemies);
		enemytimer.setInitialDelay(0);
		enemytimer.start();
	}
	
	/**
	 * 
	 * TODO The getStats method returns an integer array containing the current life, point, and remaining emerald count.
	 *
	 * @return Returns the aformentioned integer array
	 */
	
	public int[] getStats(){
		int[] currStats = new int[3];
		currStats[0]=points; currStats[1]=emeraldCount; currStats[2]=lives;
		return currStats;
	}
	
	/**
	 * 
	 * TODO The importStatDisplay method allows the GameGrid object to import and perform methods from a given StatDisplay object
	 *
	 * @param statPanel The StatDisplay object
	 */
	
	public void importStatDisplay(StatDisplay statPanel){
		bPanel=statPanel;
	}
	
	/**
	 * 
	 * TODO The createInitialConditions method sets the initial stats of the game and initializes the player
	 *
	 */
	
	private void createInitialConditions(){
		if (lvl.getPlayerCreated()){
			playerposition = 5*lvl.getPlayerYPosition()+lvl.getPlayerXPosition();
			userPlayer = (Player) grid.get(playerposition);
			userPlayer.linkLvl(lvl);
			statArray = userPlayer.returnStats();
			pewpew = new Laser();
		}
		points=0;
		emeraldCount = lvl.getEmeraldCount();
	}
}

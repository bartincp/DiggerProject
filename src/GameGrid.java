import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.*;


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
	
	public GameGrid() {
		setFocusable(true);
		setBackground(Color.gray);
		// Creates two levels from constructor and advance method
		lvl = new Level();
		// Uncomment to go straight to level 1
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
//					userPlayer.moveUp();
//					System.out.println(userPlayer.returnScore());
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
//					userPlayer.moveDown();
//					System.out.println(userPlayer.returnScore());
				}
				if(keyCode==KeyEvent.VK_LEFT && lvl.getPlayerCreated()){
					taskPerformer = new ActionListener(){
						public void actionPerformed(ActionEvent event){
							userPlayer.moveLeft();
							int goldpushcheck = userPlayer.goldPushCheck();
							if(goldpushcheck!=-1){
								int x = lvl.getGoldXPositions()[goldpushcheck];
								int y = lvl.getGoldYPositions()[goldpushcheck];
								((Gold)grid.get(5*y+x)).linkGrid(grid);
								if(((Gold)grid.get(5*y+x)).airBelow()==true)
									goldtimers[goldpushcheck].start();
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
//					userPlayer.moveLeft();
//					System.out.println(userPlayer.returnScore());
				}
				if(keyCode==KeyEvent.VK_RIGHT && lvl.getPlayerCreated()){
					taskPerformer = new ActionListener(){
						public void actionPerformed(ActionEvent event){
							userPlayer.moveRight();
							int goldpushcheck = userPlayer.goldPushCheck();
							if(goldpushcheck!=-1){
								int x = lvl.getGoldXPositions()[goldpushcheck];
								int y = lvl.getGoldYPositions()[goldpushcheck];
								((Gold)grid.get(5*y+x)).linkGrid(grid);
								if(((Gold)grid.get(5*y+x)).airBelow()==true)
									goldtimers[goldpushcheck].start();
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
//					userPlayer.moveRight();
//					System.out.println(userPlayer.returnScore());
				}
				if(keyCode==KeyEvent.VK_SPACE && lvl.getPlayerCreated()){
					boolean gridstate;
					if(lasertimer!=null&&lasertimer.isRunning()==true)
						lasertimer.stop();
					int predictedposition;
					if(userPlayer.getDirectionAxis()==0){
						predictedposition = 5*(userPlayer.getYPosition()+userPlayer.getDirectionAmount())+userPlayer.getXPosition();
						if(predictedposition >= 0 && predictedposition < grid.size()){
							Interactable temporary = grid.get(predictedposition);
							gridstate = temporary.returnState();
							if(gridstate==false){//if(gridstate==false||temporary.returnEnemy()==true){
//								if(temporary.returnEnemy()){
//									temporary.transform();
//									return;
//								}
								if(pewpew.returnState()==false){
									pewpew = new Laser(userPlayer.getXPosition(), (userPlayer.getYPosition()+userPlayer.getDirectionAmount()), 5, 5, userPlayer);
									grid.set(predictedposition, pewpew);
								}
							}
						}
					}
					else{
						predictedposition = 5*userPlayer.getYPosition()+userPlayer.getXPosition()+userPlayer.getDirectionAmount();
						if(predictedposition >= 0 && predictedposition < grid.size()){
							gridstate = grid.get(predictedposition).returnState();
							if(gridstate==false){
								if(pewpew.returnState()==false){
									pewpew = new Laser(userPlayer.getXPosition()+userPlayer.getDirectionAmount(), userPlayer.getYPosition(), 5, 5, userPlayer);
									grid.set(predictedposition, pewpew);
								}
							}
						}
					}
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
				if((keyCode != KeyEvent.VK_D && keyCode != KeyEvent.VK_U && keyCode != KeyEvent.VK_SPACE) && lvl.getPlayerCreated()){
					if(runningtimer == false){
						playertimer = new Timer(600,taskPerformer);
						playertimer.setInitialDelay(0);
						playertimer.start();
						runningtimer = true;
					}
				}
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
				if (lvl.getPlayerCreated()){
					if(userPlayer.goldcheck()==true){
						int tempnumber = userPlayer.goldabovenumber();
						goldtimers[tempnumber].start();
					}
//					System.out.println("The player's score is: " + userPlayer.returnScore());
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
	
	protected void emeraldCheck(){
		statArray = userPlayer.returnStats();
		int lvlNum = lvl.getLevelNumber();
		points  += statArray[0];
		emeraldCount  += statArray[1];
		lives = statArray[2];
		bPanel.updateStatDisplay(points,emeraldCount,lives,lvlNum);
		// If life count is negative, user is returned to intro (or game over)
		if (lives == -1){
			enemytimer.stop();
			stopAllGoldTimers();
			lvl.ReturnToIntro();
			removeAll();
			bPanel.updateStatDisplay(0,0,3,0);
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
	
	private void stopAllGoldTimers(){
		for(int n = 0; n < lvl.getGoldNumber(); n++){
			goldtimers[n].stop();
		}
	}
	
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
	
	public int[] getStats(){
		int[] currStats = new int[3];
		currStats[0]=points; currStats[1]=emeraldCount; currStats[2]=lives;
		return currStats;
	}
	
	public void importStatDisplay(StatDisplay statPanel){
		bPanel=statPanel;
	}
	
	private void createInitialConditions(){
		if (lvl.getPlayerCreated()){
			playerposition = 5*lvl.getPlayerYPosition()+lvl.getPlayerXPosition();
			userPlayer = (Player) grid.get(playerposition);
			userPlayer.linkLvl(lvl);
			statArray = userPlayer.returnStats();
			pewpew = new Laser();
			System.out.println("player created!");
		}
		points=0;
		emeraldCount = lvl.getEmeraldCount();
	}
}

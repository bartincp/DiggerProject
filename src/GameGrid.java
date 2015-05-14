import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.*;


public class GameGrid extends JPanel{
	private ArrayList<Interactable> grid;
	private int playerposition;
	private Player userPlayer;
	private Level lvl;
	private Timer playertimer, lasertimer;
	private ActionListener taskPerformer, taskPerformerLaser;
	private boolean runningtimer = false;
	private Laser pewpew;
	
	public GameGrid() {
		setFocusable(true);
		setBackground(Color.gray);
		lvl = new Level();
		lvl.advance();
		grid = lvl.getList();
		
		setLayout(new GridLayout(5, 5, 1, 1));
		for (int i = 0; i < grid.size(); i++) {
//			Interactable gameObject = (Interactable) grid.get(i);
//			Icon currIcon=gameObject.returnIcon();
			JLabel currLabel = (grid.get(i).returnLabel());
//			add(grid.get(i));
			add(currLabel);
		}
		// Solution to missing imageicons: Use jlabels!!! Find a way to grab imageicons
		
		playerposition = 5*lvl.getPlayerYPosition()+lvl.getPlayerXPosition();
		userPlayer = (Player) grid.get(playerposition);
		pewpew = new Laser();
		addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent event){
				int keyCode = event.getKeyCode();
				if(keyCode==KeyEvent.VK_UP){
					taskPerformer = new ActionListener(){
						public void actionPerformed(ActionEvent event){
							userPlayer.moveUp();
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
				if(keyCode==KeyEvent.VK_DOWN){
					taskPerformer = new ActionListener(){
						public void actionPerformed(ActionEvent event){
							userPlayer.moveDown();
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
				if(keyCode==KeyEvent.VK_LEFT){
					taskPerformer = new ActionListener(){
						public void actionPerformed(ActionEvent event){
							userPlayer.moveLeft();
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
				if(keyCode==KeyEvent.VK_RIGHT){
					taskPerformer = new ActionListener(){
						public void actionPerformed(ActionEvent event){
							userPlayer.moveRight();
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
				if(keyCode==KeyEvent.VK_SPACE){
					boolean gridstate;
					if(lasertimer!=null&&lasertimer.isRunning()==true)
						lasertimer.stop();
					int predictedposition;
					if(userPlayer.getDirectionAxis()==0){
						predictedposition = 5*(userPlayer.getYPosition()+userPlayer.getDirectionAmount())+userPlayer.getXPosition();
						if(predictedposition >= 0 && predictedposition < grid.size()){
							gridstate = grid.get(predictedposition).returnState();
							if(gridstate==false){
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
					taskPerformerLaser = new ActionListener(){
						public void actionPerformed(ActionEvent event){
							if(pewpew.returnState()==true){
								pewpew.move(userPlayer.getDirectionAxis(), userPlayer.getDirectionAmount());
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
					lasertimer = new Timer(400,taskPerformerLaser);
					lasertimer.setInitialDelay(0);
					lasertimer.start();
				}
				if(keyCode==KeyEvent.VK_U){
					if(lvl.getLevelNumber()<3){
						lvl = lvl.advance();
						grid = lvl.getList();
						playerposition = 5*lvl.getPlayerYPosition()+lvl.getPlayerXPosition();
						userPlayer = (Player) grid.get(playerposition);
					}
				}
				if(keyCode==KeyEvent.VK_D){
					if(lvl.getLevelNumber()>1){
						lvl = lvl.retreat();
						grid = lvl.getList();
						playerposition = 5*lvl.getPlayerYPosition()+lvl.getPlayerXPosition();
						userPlayer = (Player) grid.get(playerposition);
					}
				}
				if(keyCode != KeyEvent.VK_D && keyCode != KeyEvent.VK_U && keyCode != KeyEvent.VK_SPACE){
					if(runningtimer == false){
						playertimer = new Timer(600,taskPerformer);
						playertimer.setInitialDelay(0);
						playertimer.start();
						runningtimer = true;
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

			public void keyReleased(KeyEvent event){
				int keyCode = event.getKeyCode();
				if(keyCode != KeyEvent.VK_D && keyCode != KeyEvent.VK_U && keyCode != KeyEvent.VK_SPACE){
				playertimer.stop();
				runningtimer = false;
				}
			}

			public void keyTyped(KeyEvent event){
			}
		});
	}
}

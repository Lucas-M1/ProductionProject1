package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI.KeyHandler;

import Characters.Objects;
import Characters.Player;
import environments.EnvironmentManager;
import items.WorldItems;

public class Frame extends JPanel implements Runnable{ //Class created to run the game thread so runnable is implemented 
	
	UserInput k = new UserInput(this);
	EnvironmentManager m = new EnvironmentManager(this);
	int framesPerSecond = 60;
	final int tileSize = 16;
	final int tileScale = 3;
	public final int realTileSize = tileSize * tileScale;  //panel size is established and scaled up to ensure objects on the screen are large enough and easily visible 
	public final int maxHorScreenSize = 12;
	//public final int maxVerScreenSize = 15;
	public final int maxVerScreenSize = 16;
	public final int height = realTileSize * maxHorScreenSize;
	public final int width = realTileSize * maxVerScreenSize;
	public final int worldVerSize = 50;
	public final int worldHorSize = 50;
	public final int mapWidth = realTileSize * worldVerSize;
	public final int mapHeight = realTileSize * worldHorSize;
	public HeadsUpDisplay hud = new HeadsUpDisplay(this);
	Thread game; //The game thread allows the application to continue running while other tasks are being executed simultaneously by creating a new thread of execution
	public CollisionDetection c = new CollisionDetection(this);
	public WorldItems itm[] = new WorldItems[20]; //Number of objects that can be displayed simultaneously
	public Objects npc[] = new Objects[10];
	public ItemPlacement item = new ItemPlacement(this);
	//int speed = 5;
	//int xpos = 150;
	//int ypos = 150;
	public Player p = new Player(this, k);
	
	public int state;
	public final int titleScreen = 0;
	public final int dialogue = 3;
	public final int play = 1;
	public final int paused = 2;
	

	
	public Frame() {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.black);
		this.addKeyListener(k);
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		
		
	}
	
	public void gameThread() {
		game = new Thread(this);
		game.start();
		
	}
	
	public void itemPlacement() {
		item.itemLocationSet();
		item.npc();
		state = titleScreen;
	}

	@Override
	public void run() { //run method called when game thread starts
		
		/*
		double interval = 1000000000/framesPerSecond;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while (game != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / interval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 2) {
				refresh();
				repaint();
				delta--;
				drawCount++;
				
			}
			
			if(timer >= 1000000000) {
				drawCount = 0;
				timer = 0;
			}
			
			
		}
		*/
		double interval = 1000000000/framesPerSecond; //The time for one loop, so in 1 second, the game loop runs 60 times
		double nextUpdate = System.nanoTime() + framesPerSecond;
		
		while (game != null) { //While the game is running:
			refresh();
			repaint(); //Calls painComponent method
			
			
			try {
				double sleepTime = nextUpdate - System.nanoTime();
				sleepTime = sleepTime/1000000;
				
				if (sleepTime < 0) {
					sleepTime = 0;
				}
				Thread.sleep((long) sleepTime);
				nextUpdate = nextUpdate + interval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}
	
	public void refresh() {
		
		if(state == play) {
			p.refresh();
			
			for (int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].refresh();
				}
			}
		}
		
		if(state == paused) {
			// NA
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D)g;                      //Graphic2D is used over Graphics as it provides more functionality 
		if(state == titleScreen) {
			hud.draw(graphics2d);
		}
		
		else {
			m.draw(graphics2d);
			for(int i = 0; i < itm.length; i++) { 
				if(itm[i] != null) {
					itm[i].draw(graphics2d, this);
				}
			}
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] !=null) {
					npc[i].draw(graphics2d);
				}
			}
			p.draw(graphics2d);
			hud.draw(graphics2d);
			graphics2d.dispose();

			
		}
		
	}
	
	
	
	
	
	
	

}



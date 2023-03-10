package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI.KeyHandler;

import Characters.Player;

public class Frame extends JPanel implements Runnable{ //Class created to run the game thread so runnable is implemented 
	
	UserInput k = new UserInput();
	int framesPerSecond = 60;
	final int tileSize = 16;
	final int tileScale = 3;
	public final int realTileSize = tileSize * tileScale;  //panel size is established and scaled up to ensure objects on the screen are large enough and easily visible 
	final int maxHorScreenSize = 20;
	final int maxVerScreenSize = 15;
	final int height = realTileSize * maxVerScreenSize;
	final int width = realTileSize * maxHorScreenSize;
	
	Thread game; //The game thread allows the application to continue running while other tasks are being executed simultaneously by creating a new thread of execution
	int speed = 5;
	int xpos = 150;
	int ypos = 150;
	Player p = new Player(this, k);

	
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
		p.refresh();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D)g; //Graphic2D is used over Graphics as it provides more functionality 
		p.draw(graphics2d);
		graphics2d.dispose();
	}
	
	
	
	
	
	
	

}

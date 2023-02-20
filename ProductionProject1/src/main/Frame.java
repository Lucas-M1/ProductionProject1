package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI.KeyHandler;

public class Frame extends JPanel implements Runnable{ //Class created to run the game thread so runnable is implemented 
	
	UserInput k = new UserInput();
	final int tileSize = 16;
	final int tileScale = 3;
	final int realTileSize = tileSize * tileScale;  //panel size is established and scaled up to ensure objects on the screen are large enough and easily visible 
	final int maxHorScreenSize = 20;
	final int maxVerScreenSize = 15;
	final int height = realTileSize * maxVerScreenSize;
	final int width = realTileSize * maxHorScreenSize;
	
	Thread game; //The game thread allows the application to continue running while other tasks are being executed simultaneously by creating a new thread of execution
	int speed = 5;
	int xpos = 150;
	int ypos = 150;

	
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
		
		double framesPerSecond = 1000/60;
		double nextUpdate = System.currentTimeMillis() + framesPerSecond;
		
		while (game != null) { //While the game is running:
			refresh();
			repaint(); //Calls painComponent method
			
			
			try {
				double sleepTime = nextUpdate - System.currentTimeMillis();
				sleepTime = sleepTime/1000;
				
				if (sleepTime < 0) {
					sleepTime = 0;
				}
				Thread.sleep((long) sleepTime);
				nextUpdate = nextUpdate + framesPerSecond;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}
	
	public void refresh() {
		if (k.up == true) {
			ypos = ypos - speed;
			
		}
		else if(k.down == true) {
			ypos = ypos + speed;
		}
		else if (k.left == true) {
			xpos = xpos - speed;
		}
		else if (k.right == true) {
			xpos = xpos + speed;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D)g; //Graphic2D is used over Graphics as it provides more functionality 
		graphics2d.setColor(Color.green);
		graphics2d.fillRect(xpos, ypos, realTileSize, realTileSize);
		graphics2d.dispose();
	}
	
	
	
	
	
	
	

}

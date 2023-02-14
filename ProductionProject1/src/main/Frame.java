package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Frame extends JPanel implements Runnable{ //Class created to run the game thread so runnable is implemented 
	
	final int tileSize = 16;
	final int tileScale = 3;
	final int realTileSize = tileSize * tileScale;  //panel size is established and scaled up to ensure objects on the screen are large enough and easily visible 
	final int maxHorScreenSize = 20;
	final int maxVerScreenSize = 15;
	final int height = realTileSize * maxVerScreenSize;
	final int width = realTileSize * maxHorScreenSize;
	
	Thread game; //The game thread allows the application to continue running while other tasks are being executed simultaneously by creating a new thread of execution
	
	public Frame() {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
		
	}
	
	public void gameThread() {
		game = new Thread(this);
		game.start();
		
	}

	@Override
	public void run() { //run method called when game thread starts
		while (game != null) { //While the game is running:
			
			
			
			
		}
		
	}
	
	
	
	
	
	

}

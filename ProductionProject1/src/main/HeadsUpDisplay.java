package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class HeadsUpDisplay {
	
	Frame f;
	Graphics2D g;
	Font arial_40, arial_80B;
	
	public boolean popUpMessage = false;
	public String message = "";
	public String currentDialogue = "";
	int counter = 0;
	public boolean gameFinished = false;
	
	
	
	public HeadsUpDisplay(Frame f) {
		this.f = f;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);

		
	}
	
	public void message(String text) {
		popUpMessage = true;
		message = text;
		
	}
	
	public void draw(Graphics2D g) {
		
		this.g = g;
		g.setFont(arial_40);
		g.setColor(Color.white);
		
		
		if(f.state == f.play) {
			
		}
		if(f.state == f.paused) {
			pauseScreen();
			
		}
		
		if (f.state == f.dialogue) {
			interactionScreen();
		}
	}

	public void pauseScreen() {
		String txt = "Game Paused";
		int x = setXCenter(txt);
		int y = f.mapHeight/2;
		
		g.drawString(txt, x, y);
		
	}
	
	public void interactionScreen() {
		
			g.setFont(g.getFont().deriveFont(Font.PLAIN, 30F));
			int width = f.width - (f.realTileSize*4);
			int height = f.realTileSize*4;
			int x = f.realTileSize*2; //2 tiles from the left edge of the screen
			int y = f.realTileSize/2;
			dialogueWindow(x, y, height, width);
			x = x + f.realTileSize;
			y = y + f.realTileSize;
			g.drawString(currentDialogue, x, y);
		}

	public void dialogueWindow(int x, int y, int height, int width) {
		Color colour = new Color(0, 0, 0, 200);
		g.setColor(colour);
		g.fillRoundRect(x, y, width, height, 20, 20);
		colour = new Color(255, 255, 255);
		g.setColor(colour);
		g.setStroke(new BasicStroke(5));
		g.drawRoundRect(x+5, y+5, width - 10, height - 10, 25, 25);
	}
	
	public int setXCenter (String txt) {
		int length = ((int)g.getFontMetrics().getStringBounds(txt, g).getWidth());
		int x = f.mapWidth/2 - length/2; //Center the 'Game Paused' text in the middle of the screen when the game is paused
		return x;


	}
	
	

}

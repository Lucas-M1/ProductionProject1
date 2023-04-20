package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import items.Health;
import items.WorldItems;

public class HeadsUpDisplay {
	
	Frame f;
	Graphics2D g;
	Font arial_40, arial_80B;
	public boolean popUpMessage = false;
	public String message = "";
	public String currentDialogue = "";
	int counter = 0;
	public boolean gameFinished = false;
	public int num = 0;
	public BufferedImage healthBar1, healthBar2, healthBar3;
	
	
	
	public HeadsUpDisplay(Frame f) {
		this.f = f;
		
		arial_40 = new Font("Fixedsys Regular", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		
		WorldItems health = new Health(f);
		healthBar1 = health.health1;
		healthBar2 = health.health2;
		healthBar3 = health.health3;
		


		
	}
	
	public void message(String text) {
		popUpMessage = true;
		message = text;
		
	}
	
	public void draw(Graphics2D g) {
		
		this.g = g;
		g.setFont(arial_40);
		g.setColor(Color.white);
		
		if(f.state == f.titleScreen) {
			titleScreen();
		}
		
		if(f.state == f.play) {
			playerHealth();
		}
		if(f.state == f.paused) {
			playerHealth();
			pauseScreen();
			
		}
		
		if (f.state == f.dialogue) {
			playerHealth();
			interactionScreen();
		}
	}
	
	

	public void pauseScreen() {
		String txt = "Game Paused";
		int x = setXCenter(txt);
		int y = f.mapHeight/2;
		
		g.drawString(txt, x, y);
		
	}
	
	public void playerHealth() {
		int i = 0;
		int x = f.realTileSize/2;
		int y = f.realTileSize/2;
		
		while(i < f.p.maxHealth/2) { //Max health drawn
			g.drawImage(healthBar3, x, y, null);
			i++;
			x = x + f.realTileSize;
		}
		
		i = 0;
		x = f.realTileSize/2;
		y = f.realTileSize/2;
		
		while(i < f.p.health) {
			g.drawImage(healthBar2, x, y, null);
			i++;
			if(i < f.p.health) {
				g.drawImage(healthBar1, x, y, null);
			}
			i++;
			x = x + f.realTileSize;
		}
		
	}
	
	public void titleScreen() {
		g.setFont(g.getFont().deriveFont(Font.BOLD, 55F));
		g.setColor(new Color(214, 160, 84));
		g.fillRect(0, 0, f.width, f.height);
		
		String txt = "dessert treasure hunt";
		int x = setXCenter(txt);
		int y = f.realTileSize*3;
		
		g.setColor(Color.black);
		g.drawString(txt, x+3, y+3);
		
		g.setColor(Color.white);
		g.drawString(txt, x, y);
		
		x = f.width/2 - (f.realTileSize*2)/2;
		y = y + f.realTileSize*2;
		g.drawImage(f.p.c1, x, y, f.realTileSize*2, f.realTileSize*2, null);
		
		g.setFont(g.getFont().deriveFont(Font.BOLD, 48F));
		
		txt = "Start Game";
		y = y + f.realTileSize*4;
		x = setXCenter(txt);
		g.drawString(txt, x, y);
		if(num == 0) {
			g.drawString("-", x - f.realTileSize, y);
		}
		
		txt = "Quit Game";
		y = y + f.realTileSize;
		x = setXCenter(txt);
		g.drawString(txt, x, y);
		if(num == 1) {
			g.drawString("-", x - f.realTileSize, y);
		}
		

	

		
	}
	
	public void interactionScreen() {
		
			g.setFont(g.getFont().deriveFont(Font.PLAIN, 26F));
			
			int width = f.width - (f.realTileSize*4);
			int height = f.realTileSize*4;
			int x = f.realTileSize*2; //2 tiles from the left edge of the screen
			int y = f.realTileSize/2;
			dialogueWindow(x, y, height, width);
			x = x + f.realTileSize;
			y = y + f.realTileSize;
			
			for(String txt : currentDialogue.split("\n")) { //breaks the line in the dialogue box so the text does not run out of the text box
				g.drawString(txt, x, y);
				y = y + 40;

			}
							
						
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
		int length = (int)g.getFontMetrics().getStringBounds(txt, g).getWidth();
		int x = f.width/2 - length/2; //Center the 'Game Paused' text in the middle of the screen when the game is paused
		return x;


	}
	
	

}

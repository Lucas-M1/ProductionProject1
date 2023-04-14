package main;

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
	int counter = 0;
	public boolean gameFinished = false;
	
	double playTime;
	DecimalFormat format = new DecimalFormat("#0.00");
	
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
	}
	
	public void pauseScreen() {
		String txt = "Game Paused";
		int x = setXCenter(txt);
		int y = f.mapHeight/2;
		
		g.drawString(txt, x, y);
		
	}
	
	public int setXCenter (String txt) {
		int length = ((int)g.getFontMetrics().getStringBounds(txt, g).getWidth());
		int x = f.mapWidth/2 - length/2; //Center the 'Game Paused' text in the middle of the screen when the game is paused
		return x;


	}
	
	

}

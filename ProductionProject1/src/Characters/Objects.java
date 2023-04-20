package Characters;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Frame;
import main.Optimization;

public class Objects {
	
	Frame f;
	public int envX;
	public int envY;
	public int playerSpeed;
	
	
	public BufferedImage l1, l2, l3, upStill, downStill, d1, d2, d3, d4, r1, r2, r3, u1, u2, u3, c1;
	public String direction;
	//public Rectangle hitBox;
	public int playerCounter = 0;
	public int playerNumber = 1;
	public Rectangle hitBox = new Rectangle(0, 0, 48, 48);
	public boolean collisionDetected = false;
	public int hitBoxX;
	public int hitBoxY;
	
	public int maxHealth;
	public int health;
	public int interval = 0;
	int dialogueIndex = 0;
	public String dialogueOptions[] = new String[10];

	
	public Objects(Frame f) {
		this.f = f;
		
		
	}
	
	public void behaviour() {
		
	}
	
	public void dealogueText() {
	}
	
	public void refresh() {
		behaviour();
		collisionDetected = false;
		f.c.collisionCheck(this);
		f.c.itemCollision(this, false);
		f.c.checkPlayer(this);
		
		if (collisionDetected == false) {
			
			switch(direction) {
			case "up":
				envY = envY - playerSpeed;
				break;
			case "down":
				envY = envY + playerSpeed;
				break;
			case "right":
				envX = envX + playerSpeed;
				break;
			case "left":
				envX = envX - playerSpeed;
				break;
			
			}
		}
		
		playerCounter++;
		if(playerCounter > 12) {
			if(playerNumber == 1) {
				playerNumber = 2;
			}
			else if(playerNumber == 2) {
				playerNumber = 1;
			}
			playerCounter = 0;
		}
	}
	
	public void draw(Graphics2D graphics2d) {
		
		BufferedImage img = null;

		
		int playerX = envX - f.p.envX + f.p.playerX;
		int playerY = envY - f.p.envY + f.p.playerY;
		
		if(envX + f.realTileSize > f.p.envX - f.p.playerX && envX - f.realTileSize < f.p.envX +f.p.playerX && envY + f.realTileSize > f.p.envY - f.p.playerY && envY - f.realTileSize < f.p.envY + f.p.playerY) { 
			switch (direction) {
			case "right":
				if(playerNumber == 1) {
					img = r1;
				}
				if(playerNumber == 2) {
					img = r2;
				}
				if(playerNumber == 3) {
					img = r3;
				}
				break;
			case "down":
				if(playerNumber == 1) {
					img = d1;
				}
				if(playerNumber == 2) {
					img = d2;
				}
				if(playerNumber == 3) {
					img = d3;
				}
				break;
			case "up":
				if(playerNumber == 1) {
					img = u1;
				}
				if(playerNumber == 2) {
					img = u2;
				}
				if(playerNumber == 3) {
					img = u3;
				}
				break;
			case "left":
				if(playerNumber == 1) {
					img = l1;
				}
				if(playerNumber == 2) {
					img = l2;
				}
				if(playerNumber == 3) {
					img = l3;
				}
				break;
			}
			
			graphics2d.drawImage(img, playerX, playerY, f.realTileSize, f.realTileSize, null);                                                                                                        //-playerY from the centre (where the player is) to the bottom. +playerY from the centre to the top of the screen
		}
		
	}
	
	public BufferedImage setup(String image) {
		Optimization op = new Optimization();
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream(image +".png"));
			img = op.scaleImage(img, f.realTileSize, f.realTileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	

}

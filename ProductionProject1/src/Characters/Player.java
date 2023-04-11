package Characters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Frame;
import main.UserInput;

public class Player extends Objects {
	
	
	Frame f;
	UserInput k;
	
	int keyCount = 0;
	public final int playerX;
	public final int playerY;
	
	public Player(Frame f, UserInput k) {
		
		this.f = f;
		this.k = k;
		
		playerX = f.width/2 - (f.realTileSize/2);
		playerY = f.height/2 - (f.realTileSize/2);
		hitBox = new Rectangle(); //Rectangle object acts as the character size when the player comes into contact with an object in the environment that they cannot pass through 
		hitBoxX = hitBox.x; //Default rectangle position as the current position can change
		hitBoxY = hitBox.y;
		hitBox.height = 32; //Only the first 30 pixels on each axis are solid when colliding with an object
		hitBox.width = 32;
		hitBox.x = 8;
		hitBox.y = 16;
		
		
		basePlayerStats();
		playerImage();
		
		
	}
	
	public void playerImage() { //Gets the png images from the resource package so they can be displayed on the screen
		
		try {
			l1 = ImageIO.read(getClass().getResourceAsStream("/character/sprite-1.png"));
			l2 = ImageIO.read(getClass().getResourceAsStream("/character/sprite-3.png"));
			l3 = ImageIO.read(getClass().getResourceAsStream("/character/sprite-3.png"));
			upStill = ImageIO.read(getClass().getResourceAsStream("/character/sprite-4.png"));
			downStill = ImageIO.read(getClass().getResourceAsStream("/character/sprite-6.png"));
			u1 = ImageIO.read(getClass().getResourceAsStream("/character/sprite-5.png"));
			u2 = ImageIO.read(getClass().getResourceAsStream("/character/sprite-8.png"));
			u3 = ImageIO.read(getClass().getResourceAsStream("/character/sprite-9.png"));
			d1 = ImageIO.read(getClass().getResourceAsStream("/character/sprite-7.png"));
			d2 = ImageIO.read(getClass().getResourceAsStream("/character/sprite-10.png"));
			d3 = ImageIO.read(getClass().getResourceAsStream("/character/sprite-11.png"));
			r1 = ImageIO.read(getClass().getResourceAsStream("/character/sprite-12.png"));
			r2 = ImageIO.read(getClass().getResourceAsStream("/character/sprite-14.png"));
			r3 = ImageIO.read(getClass().getResourceAsStream("/character/sprite-14.png"));
					
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void basePlayerStats() {
		
		envX = f.realTileSize * 23; 
		envY = f.realTileSize * 21;
		playerSpeed = 4;
		direction = "right";
		
	}
	
	public void refresh() {
		
		
		if (k.up == true || k.down == true || k.right == true || k.left) {
			
		
		
			if (k.up == true) {
				direction = "up";
				
				
			}
			else if(k.down == true) {
				direction = "down";
				
			}
			else if (k.left == true) {
				direction = "left";
				
			}
			else if (k.right == true) {
				direction = "right";
				
			}
			
			//Check for collision 
			collisionDetected = false;
			f.c.collisionCheck(this);
			
			int itemCol = f.c.itemCollision(this, true); //Checks for collision with an item such as a door
			collectItem(itemCol);
			
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
	}
	
	public void collectItem (int i) {
		if (i != 999) {
			String item = f.itm[i].itemName;
			
			if (item == "key") {
				keyCount++;
				f.itm[i] = null;
				System.out.println(keyCount);
			}
			if (item == "door") {
				if (keyCount > 0) {
					f.itm[i] = null;
					keyCount --;

				}
				System.out.println(keyCount);

			}
		}
	}
	
	public void draw(Graphics2D graphics2d) {
		
		//graphics2d.setColor(Color.green);
		//graphics2d.fillRect(posX, posY, f.realTileSize, f.realTileSize);
		BufferedImage img = null;
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
		graphics2d.drawImage(img, playerX, playerY, f.realTileSize, f.realTileSize, null);
	}

}

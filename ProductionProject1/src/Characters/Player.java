package Characters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Frame;
import main.UserInput;

public class Player extends Object {
	
	Frame f;
	UserInput k;
	
	public Player(Frame f, UserInput k) {
		basePlayerStats();
		playerImage();
		this.f = f;
		this.k = k;
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
		
		posX = 150;
		posY = 150;
		playerSpeed = 4;
		direction = "right";
		
	}
	
	public void refresh() {
		
		
		if (k.up == true) {
			direction = "up";
			posY = posY - playerSpeed;
			
		}
		else if(k.down == true) {
			direction = "down";
			posY = posY + playerSpeed;
		}
		else if (k.left == true) {
			direction = "left";
			posX = posX - playerSpeed;
		}
		else if (k.right == true) {
			direction = "right";
			posX = posX + playerSpeed;
		}
		playerCounter++;
		if(playerCounter > 10) {
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
		graphics2d.drawImage(img, posX, posY, f.realTileSize, f.realTileSize, null);
	}

}

package Characters;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Frame;
import main.UserInput;

public class Player extends Object {
	
	Frame f;
	UserInput k;
	
	public Player(Frame f, UserInput k) {
		basePlayerStats();
		this.f = f;
		this.k = k;
	}
	
	public void basePlayerStats() {
		
		posX = 150;
		posY = 150;
		playerSpeed = 4;
		
	}
	
	public void refresh() {
		if (k.up == true) {
			posY = posY - playerSpeed;
			
		}
		else if(k.down == true) {
			posY = posY + playerSpeed;
		}
		else if (k.left == true) {
			posX = posX - playerSpeed;
		}
		else if (k.right == true) {
			posX = posX + playerSpeed;
		}
		
	}
	
	public void draw(Graphics2D graphics2d) {
		
		graphics2d.setColor(Color.green);
		graphics2d.fillRect(posX, posY, f.realTileSize, f.realTileSize);
		
	}

}

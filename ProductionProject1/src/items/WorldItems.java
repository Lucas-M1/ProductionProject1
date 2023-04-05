package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Frame;

public class WorldItems {
	
	public int envX;
	public int envY;
	public BufferedImage img;
	public boolean isCollision = false;
	public String name;
	
	public void draw(Graphics2D graphics2d, Frame f) {
		
		int playerX = envX - f.p.envX + f.p.playerX;
		int playerY = envY - f.p.envY + f.p.playerY;
		
		if(envX + f.realTileSize > f.p.envX - f.p.playerX && envX - f.realTileSize < f.p.envX +f.p.playerX && envY + f.realTileSize > f.p.envY - f.p.playerY && envY - f.realTileSize < f.p.envY + f.p.playerY) { //Creates a boundary from the centre of the screen preventing the camera from leaving the map when at the edges
			                                                                                                                                                                                                      //-playerX from the centre (where the player is) to the left. +playerX from the centre to the right
			graphics2d.drawImage(img, playerX, playerY, f.realTileSize, f.realTileSize, null);                                                                                                        //-playerY from the centre (where the player is) to the bottom. +playerY from the centre to the top of the screen
		}
		
	}

}

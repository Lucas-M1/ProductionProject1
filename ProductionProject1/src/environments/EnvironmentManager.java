package environments;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Frame;
import main.Optimization;

public class EnvironmentManager {
	
	public int layout [] [];
	Frame f;
	public Environment[] env;
	
	
	public EnvironmentManager(Frame f) {
		
		
		env = new Environment[100];
		layout = new int [f.worldVerSize][f.worldHorSize];
		this.f = f;
		getEnvironment();
		layout("/layout/gameEnvironment2.txt");
		
		
	}
	
	public void getEnvironment() {                                             //Gets each image stored in the environment package containing all of the graphics that make up the environment
		
		
			
			tileSort(10, "woodFloor", false);
			tileSort(11, "sand1", false);
			tileSort(12, "sandEdge-topRight", false);
			tileSort(13, "BrickWall1", true);
			tileSort(14, "newWater", true);
			tileSort(15, "dirt1", false);
			tileSort(16, "mediumChest-1", false);
			tileSort(17, "sandPath1", false);
			tileSort(18, "sandEdge-topLeft", false);
			tileSort(19, "cactus1", true);
			tileSort(20, "outerSand1-topRight", false);
			tileSort(21, "outerSand2-right", false);
			tileSort(22, "outerSand3-right", false);
			tileSort(23, "outerSand4-bottomRight", false);
			tileSort(24, "outerSand5-bottom", false);
			tileSort(25, "outerSand6-bottom", false);
			tileSort(26, "outerSand7-bottomLeft", false);
			tileSort(27, "outerSand8-left", false);
			tileSort(28, "outerSand9-left", false);
			tileSort(29, "outerSand10-topLeft", false);
			tileSort(30, "outerSand11-top", false);
			tileSort(31, "outerSand12-top", false);
			tileSort(32, "water-topRight", true);
			tileSort(33, "water-right", true);
			tileSort(34, "water-bottomRight", true);
			tileSort(35, "water-bottom", true);
			tileSort(36, "water-bottomLeft", true);
			tileSort(37, "water-left", true);
			tileSort(38, "water-topLeft", true);
			tileSort(39, "water-top", true);
			tileSort(40, "rock1", false);
			tileSort(41, "rock2", false);
			tileSort(42, "rock3", false);
			tileSort(43, "castle-topRight", true);
			tileSort(44, "castle-right", true);
			tileSort(45, "castle-bottomRight", true);
			tileSort(46, "castle-bottom", true);
			tileSort(47, "castle-bottomLeft", true);
			tileSort(48, "castle-left", true);
			tileSort(49, "castle-topLeft", true);
			tileSort(50, "castle-top", true);
			
			












	}
	
	public void tileSort(int i, String imageName, boolean collision) {
		Optimization o = new Optimization();
		
		try {
			env[i] = new Environment();
			env[i].img = ImageIO.read(getClass().getResourceAsStream("/environment/" + imageName +".png"));
			env[i].img = o.scaleImage(env[i].img, f.realTileSize, f.realTileSize);
			
			env[i].isCollision = collision;
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void layout (String file) {
		try {
			InputStream map = getClass().getResourceAsStream(file);
			BufferedReader b = new BufferedReader(new InputStreamReader(map)); //Reads contents of the text file containing the map layout

			int ver = 0;
			int hor = 0;

			while(ver <f.worldVerSize && hor < f.worldHorSize) {
				String read = b.readLine();
				while(ver < f.worldVerSize) {
					String values[] = read.split(" ");
					
					int val = Integer.parseInt(values[ver]);
					layout[ver][hor] = val;
					ver++;
			}
			if(ver == f.worldVerSize) {
				ver = 0;
				hor++;
			}
		}
		b.close();
			
		}catch(Exception e) {
			
		}
	}
		
	
			
	public void draw(Graphics2D graphics2d) {
		int ver = 0;
		int hor = 0;
		
		
		while(ver < f.worldVerSize && hor < f.worldHorSize) {
			int mapVal = layout[ver][hor]; //whatever value is present in the array is what will be drawn to the screen, e.g, it a 4 is present in the array, water will be printed to the screen in the specified tile position
			int envX = ver * f.realTileSize; //envX and envY refers to the position on the map
			int envY = hor * f.realTileSize;
			int playerX = envX - f.p.envX + f.p.playerX;
			int playerY = envY - f.p.envY + f.p.playerY;
			
			if(envX + f.realTileSize > f.p.envX - f.p.playerX && envX - f.realTileSize < f.p.envX +f.p.playerX && envY + f.realTileSize > f.p.envY - f.p.playerY && envY - f.realTileSize < f.p.envY + f.p.playerY) { //Creates a boundary from the centre of the screen preventing the camera from leaving the map when at the edges
				                                                                                                                                                                                                      //-playerX from the centre (where the player is) to the left. +playerX from the centre to the right
				graphics2d.drawImage(env[mapVal].img, playerX, playerY, null);                                                                                                        //-playerY from the centre (where the player is) to the bottom. +playerY from the centre to the top of the screen
			}
			
			
			ver++;
			
			
			if (ver == f.worldVerSize) {
				ver = 0;
				
				hor++;
				
			}
			
		}
	}

}






















	


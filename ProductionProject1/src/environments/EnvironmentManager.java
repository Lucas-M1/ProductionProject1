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
		
		
		env = new Environment[40];
		layout = new int [f.worldVerSize][f.worldHorSize];
		this.f = f;
		getEnvironment();
		layout("/layout/gameEnvironment2.txt");
		
		
	}
	
	public void getEnvironment() { //Gets each image stored in the environment package containing all of the graphics that make up the environment
		
		
			
			tileSort(10,"woodFloor.png", false);
			tileSort(11,"newGrass-1.png", false);
			tileSort(12,"grass3.png", false);
			tileSort(13,"BrickWall1.png", true);
			tileSort(14,"newWater.png", true);
			tileSort(15,"dirt1.png", false);
			tileSort(16,"mediumChest1.png", false);
			tileSort(17,"stonePath.png", false);
			tileSort(18,"stonePath1.png", false);
			tileSort(19,"largeTree.png", true);
			tileSort(20,"outerGrass1-topRight.png", false);
			tileSort(21,"outerGrass2-right.png", false);
			tileSort(22,"outerGrass3-right.png", false);
			tileSort(23,"outerGrass4-bottomRight.png", false);
			tileSort(24,"outerGrass5-bottom.png", false);
			tileSort(25,"outerGrass6-bottom.png", false);
			tileSort(26,"outerGrass7-bottomLeft.png", false);
			tileSort(27,"outerGrass8-left.png", false);
			tileSort(28,"outerGrass9-left.png", false);
			tileSort(29,"outerGrass10-topLeft.png", false);
			tileSort(30,"outerGrass11-top.png", false);
			tileSort(31,"outerGrass12-top.png", false);
			
			
			/*
			env[11] = new Environment();
			env[11].img = ImageIO.read(getClass().getResourceAsStream("/environment/newGrass-1.png"));
			env[12] = new Environment();
			env[12].img = ImageIO.read(getClass().getResourceAsStream("/environment/grass3.png"));
			env[13] = new Environment();
			env[13].img = ImageIO.read(getClass().getResourceAsStream("/environment/BrickWall1.png"));
			env[14] = new Environment();
			env[14].img = ImageIO.read(getClass().getResourceAsStream("/environment/newWater.png"));
			env[14].isCollision = true;

			env[15] = new Environment();
			env[15].img = ImageIO.read(getClass().getResourceAsStream("/environment/dirt1.png"));
			

			env[16] = new Environment();
			env[16].img = ImageIO.read(getClass().getResourceAsStream("/environment/mediumChest1.png"));
			env[17] = new Environment();
			env[17].img = ImageIO.read(getClass().getResourceAsStream("/environment/stonePath.png"));
			env[18] = new Environment();
			env[18].img = ImageIO.read(getClass().getResourceAsStream("/environment/stonePath1.png"));
			env[19] = new Environment();
			env[19].img = ImageIO.read(getClass().getResourceAsStream("/environment/largeTree.png"));
			env[19].isCollision = true;
			
			env[20] = new Environment();
			env[20].img = ImageIO.read(getClass().getResourceAsStream("/environment/outerGrass1-topRight.png"));
			env[21] = new Environment();
			env[21].img = ImageIO.read(getClass().getResourceAsStream("/environment/outerGrass2-right.png"));
			
			env[22] = new Environment();
			env[22].img = ImageIO.read(getClass().getResourceAsStream("/environment/outerGrass3-right.png"));
			
			env[23] = new Environment();
			env[23].img = ImageIO.read(getClass().getResourceAsStream("/environment/outerGrass4-bottomRight.png"));
			
			env[24] = new Environment();
			env[24].img = ImageIO.read(getClass().getResourceAsStream("/environment/outerGrass5-bottom.png"));
			
			env[25] = new Environment();
			env[25].img = ImageIO.read(getClass().getResourceAsStream("/environment/outerGrass6-bottom.png"));
			
			env[26] = new Environment();
			env[26].img = ImageIO.read(getClass().getResourceAsStream("/environment/outerGrass7-bottomLeft.png"));
			
			env[27] = new Environment();
			env[27].img = ImageIO.read(getClass().getResourceAsStream("/environment/outerGrass8-left.png"));
			
			env[28] = new Environment();
			env[28].img = ImageIO.read(getClass().getResourceAsStream("/environment/outerGrass9-left.png"));
			*/
			
			
			
			
			
		
	}
	
	public void tileSort(int i, String imageName, boolean collision) {
		Optimization o = new Optimization();
		
		try {
			env[i] = new Environment();
			env[i].img = ImageIO.read(getClass().getResourceAsStream("/environment/" + imageName + ".png"));
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






















	


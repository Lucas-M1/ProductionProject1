package environments;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Frame;

public class EnvironmentManager {
	
	public int layout [] [];
	Frame f;
	public Environment[] env;
	
	public EnvironmentManager(Frame f) {
		
		
		env = new Environment[10];
		layout = new int [f.worldVerSize][f.worldHorSize];
		this.f = f;
		getEnvironment();
		layout("/layout/gameEnvironment2.txt");
		
		
	}
	
	public void getEnvironment() { //Gets each image stored in the environment package containing all of the graphics that make up the environment
		
		try {
			env[0] = new Environment();
			env[0].img = ImageIO.read(getClass().getResourceAsStream("/environment/grass1.png"));
			env[1] = new Environment();
			env[1].img = ImageIO.read(getClass().getResourceAsStream("/environment/grass2.png"));
			env[2] = new Environment();
			env[2].img = ImageIO.read(getClass().getResourceAsStream("/environment/grass3.png"));
			env[3] = new Environment();
			env[3].img = ImageIO.read(getClass().getResourceAsStream("/environment/wall1.png"));
			env[4] = new Environment();
			env[4].img = ImageIO.read(getClass().getResourceAsStream("/environment/water1.png"));
			env[5] = new Environment();
			env[5].img = ImageIO.read(getClass().getResourceAsStream("/environment/water2-1.png"));
			env[6] = new Environment();
			env[6].img = ImageIO.read(getClass().getResourceAsStream("/environment/water2-2.png"));
			env[7] = new Environment();
			env[7].img = ImageIO.read(getClass().getResourceAsStream("/environment/dirt1.png"));
			env[8] = new Environment();
			env[8].img = ImageIO.read(getClass().getResourceAsStream("/environment/stonePath1.png"));
			env[9] = new Environment();
			env[9].img = ImageIO.read(getClass().getResourceAsStream("/environment/tree1.png"));
			env[9].isCollision = true;
			
			
		}
		catch (IOException e) {
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
				graphics2d.drawImage(env[mapVal].img, playerX, playerY, f.realTileSize, f.realTileSize, null);                                                                                                        //-playerY from the centre (where the player is) to the bottom. +playerY from the centre to the top of the screen
			}
			
			
			ver++;
			
			
			if (ver == f.worldVerSize) {
				ver = 0;
				
				hor++;
				
			}
			
		}
	}

}






















	


package environments;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Frame;

public class EnvironmentManager {
	
	int layout [] [];
	Frame f;
	Environment[] env;
	
	public EnvironmentManager(Frame f) {
		
		
		env = new Environment[10];
		layout = new int [f.maxVerScreenSize][f.maxHorScreenSize];
		this.f = f;
		getEnvironment();
		layout();
		
		
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
			
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void layout () {
		try {
			InputStream map = getClass().getResourceAsStream("/layout/gameEnvironment.txt");
			BufferedReader b = new BufferedReader(new InputStreamReader(map)); //Reads contents of the text file containing the map layout

			int ver = 0;
			int hor = 0;

			while(ver <f.maxVerScreenSize && hor < f.maxHorScreenSize) {
				String read = b.readLine();
				while(ver < f.maxVerScreenSize) {
					String values[] = read.split(" ");
					
					int val = Integer.parseInt(values[ver]);
					layout[ver][hor] = val;
					ver++;
			}
			if(ver == f.maxVerScreenSize) {
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
		int xPos = 0;
		int yPos = 0;
		
		while(ver < f.maxVerScreenSize && hor < f.maxHorScreenSize) {
			int mapVal = layout[ver][hor]; //whatever value is present in the array is what will be drawn to the screen, e.g, it a 4 is present in the array, water will be printed to the screen in the specified tile position
			graphics2d.drawImage(env[mapVal].img, xPos, yPos, f.realTileSize, f.realTileSize, null);
			ver++;
			xPos = xPos + f.realTileSize;
			
			if (ver == f.maxVerScreenSize) {
				ver = 0;
				xPos = 0;
				hor++;
				yPos = yPos + f.realTileSize;
			}
			
		}
	}

}






















	


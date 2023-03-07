package environments;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Frame;

public class EnvironmentManager {
	
	Frame f;
	Environment[] env;
	
	public EnvironmentManager(Frame f) {
		
		
		env = new Environment[10];
		this.f = f;
		
		
		
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

}

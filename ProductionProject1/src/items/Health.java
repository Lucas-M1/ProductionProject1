package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Frame;

public class Health extends WorldItems {
Frame f;
	
	public Health(Frame f) {
		
		this.f = f;
		
		itemName = "health";
		
		try {
			health1 = ImageIO.read(getClass().getResourceAsStream("/character/h1.png"));
			health2 = ImageIO.read(getClass().getResourceAsStream("/character/h2.png"));
			health3 = ImageIO.read(getClass().getResourceAsStream("/character/h3.png"));
			
			
			health1 = op.scaleImage(health1, f.realTileSize, f.realTileSize);
			health2 = op.scaleImage(health2, f.realTileSize, f.realTileSize);
			health3 = op.scaleImage(health3, f.realTileSize, f.realTileSize);
			

			
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
	}

}

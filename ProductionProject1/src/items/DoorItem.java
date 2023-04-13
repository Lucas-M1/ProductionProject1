package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Frame;

public class DoorItem extends WorldItems {
	
	Frame f;
	
	public DoorItem(Frame f) {
		
		this.f = f;
		
		itemName = "door";
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/environment/door1.png"));
			op.scaleImage(img, f.realTileSize, f.realTileSize);

			
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
		isCollision = true;
	}

}
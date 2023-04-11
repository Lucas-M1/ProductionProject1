package items;

import java.io.IOException;

import javax.imageio.ImageIO;

public class DoorItem extends WorldItems {
	
	public DoorItem() {
		
		name = "door";
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/environment/door1.png"));
			
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
		isCollision = true;
	}

}
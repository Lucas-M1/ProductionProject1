package items;

import java.io.IOException;

import javax.imageio.ImageIO;

public class KeyItem extends WorldItems {
	
	public KeyItem() {
		
		name = "key";
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/environment/key1.png"));
			
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
	}

}

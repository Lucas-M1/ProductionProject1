package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Frame;

public class KeyItem extends WorldItems {
	
	Frame f;
	
	public KeyItem(Frame f) {
		
		this.f = f;
		
		itemName = "key";
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/environment/key1.png"));
			op.scaleImage(img, f.realTileSize, f.realTileSize);
			
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
	}

}

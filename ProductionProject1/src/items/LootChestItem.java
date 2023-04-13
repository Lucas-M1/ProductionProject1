package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Frame;

public class LootChestItem extends WorldItems {
	
	Frame f;
	
	public LootChestItem(Frame f) {
		
		this.f = f;
		
		itemName = "chest";
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/environment/mediumChest1.png"));
			op.scaleImage(img, f.realTileSize, f.realTileSize);

			
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
	}

}
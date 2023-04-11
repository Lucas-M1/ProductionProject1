package items;

import java.io.IOException;

import javax.imageio.ImageIO;

public class LootChestItem extends WorldItems {
	
	public LootChestItem() {
		
		name = "chest";
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/environment/mediumChest.png"));
			
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
	}

}
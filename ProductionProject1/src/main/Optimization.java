package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Optimization {
	
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		BufferedImage scale = new BufferedImage(width, height, original.getType());
		Graphics2D g = scale.createGraphics();
		g.drawImage(original, 0, 0, width, height, null);
		g.dispose();
		
		return scale;
		
		
	}

}

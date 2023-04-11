package Characters;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Objects {
	
	public int envX;
	public int envY;
	public int playerSpeed;
	
	
	public BufferedImage l1, l2, l3, upStill, downStill, d1, d2, d3, d4, r1, r2, r3, u1, u2, u3;
	public String direction;
	public Rectangle hitBox;
	public int playerCounter = 0;
	public int playerNumber = 1;
	public boolean collisionDetected = false;
	public int hitBoxX;
	public int hitBoxY;
	

}

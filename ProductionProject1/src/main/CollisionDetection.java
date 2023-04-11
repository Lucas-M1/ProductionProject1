package main;

import Characters.Objects;

public class CollisionDetection {
	
	Frame f;
	
	public CollisionDetection(Frame f) {
		this.f = f;
	}
	
	public void collisionCheck(Objects object) { //Defines the bounds around the player that can collide with objects in the environment
		int collisionNum1; //the hitbox only collides with 2 tiles, one to the right and one to the left
		int collisionNum2;
		int playerLeft = object.envX + object.hitBox.x;
		int playerRight = object.envX + object.hitBox.x + object.hitBox.width;
		int playerTop = object.envY + object.hitBox.y;
		int playerBottom = object.envY + object.hitBox.y + object.hitBox.height;
		
		int playerLeftHor = playerLeft/f.realTileSize;
		int playerRightHor = playerRight/f.realTileSize;
		int playerBottomHor = playerBottom/f.realTileSize;
		int playerTopHor = playerTop/f.realTileSize;
		
		if (object.direction == "left") {
			playerLeftHor = (playerLeft - object.playerSpeed)/f.realTileSize;
			collisionNum1 = f.m.layout[playerLeftHor][playerTopHor];
			collisionNum2 = f.m.layout[playerRightHor][playerBottomHor];
			
			if (f.m.env[collisionNum1].isCollision == true || f.m.env[collisionNum2].isCollision == true) {
				object.collisionDetected = true;
			}
		}
		
		else if (object.direction == "right") {
			playerRightHor = (playerRight + object.playerSpeed)/f.realTileSize;
			collisionNum1 = f.m.layout[playerRightHor][playerTopHor];
			collisionNum2 = f.m.layout[playerRightHor][playerBottomHor];
			
			if (f.m.env[collisionNum1].isCollision == true || f.m.env[collisionNum2].isCollision == true) {
				object.collisionDetected = true;
			}
			
		}
		
		else if (object.direction == "down") {
			playerBottomHor = (playerBottom - object.playerSpeed)/f.realTileSize;
			collisionNum1 = f.m.layout[playerLeftHor][playerBottomHor];
			collisionNum2 = f.m.layout[playerRightHor][playerBottomHor];
			if (f.m.env[collisionNum1].isCollision == true || f.m.env[collisionNum2].isCollision == true) {
				object.collisionDetected = true;
			}
			
		}
		
		else if (object.direction == "up") {
			playerTopHor = (playerTop - object.playerSpeed)/f.realTileSize;
			collisionNum1 = f.m.layout[playerLeftHor][playerTopHor];
			collisionNum2 = f.m.layout[playerRightHor][playerTopHor];
			if (f.m.env[collisionNum1].isCollision == true || f.m.env[collisionNum2].isCollision == true) {
				object.collisionDetected = true;
			}
			
		}
		
	}
	
	public int itemCollision(Objects object, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < f.itm.length; i++) { //loops through all items 
			if(f.itm[i] != null) {
				object.hitBox.y = object.envY + object.hitBox.y; //Gets the player hit box position. i.e, the area that is solid that the player cannot walk through
				object.hitBox.x = object.envX + object.hitBox.x;
				
				f.itm[i].hitBox.y = f.itm[i].envY + f.itm[i].hitBox.y; //Gets the item hit box position
				f.itm[i].hitBox.x = f.itm[i].envX + f.itm[i].hitBox.x;
				
				if (object.direction == "up") {
					object.hitBox.y = object.hitBox.y - object.playerSpeed;
					if (object.hitBox.intersects(f.itm[i].hitBox)) {  //Checks to see if the 2 hit boxes (from the player and the object) are colliding with each other
						System.out.println("collision");

					}
				}
				else if (object.direction == "down") {
					object.hitBox.y = object.hitBox.y + object.playerSpeed;

				}
				else if (object.direction == "right") {
					object.hitBox.x = object.hitBox.x + object.playerSpeed;
				}
				else if (object.direction == "left") {
					object.hitBox.x = object.hitBox.x - object.playerSpeed;
					if (object.hitBox.intersects(f.itm[i].hitBox)) {
						System.out.println("collision");
					}

				}
				object.hitBox.x = object.hitBoxX; //restore hit box values back to their defaults after the collision
				object.hitBox.y = object.hitBoxY;
				f.itm[i].hitBox.x = f.itm[i].hitBoxX;
				f.itm[i].hitBox.y = f.itm[i].hitBoxY;

			}
		}
		return index;
		
	}

}

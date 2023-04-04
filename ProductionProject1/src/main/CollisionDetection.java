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

}

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
		
		int playerLeftVer = playerLeft/f.realTileSize;
		int playerRightVer = playerRight/f.realTileSize;
		int playerBottomHor = playerBottom/f.realTileSize;
		int playerTopHor = playerTop/f.realTileSize;
		
		if (object.direction == "left") {
			playerLeftVer = (playerLeft - object.playerSpeed)/f.realTileSize;
			collisionNum1 = f.m.layout[playerLeftVer][playerTopHor];
			collisionNum2 = f.m.layout[playerLeftVer][playerBottomHor];
			
			if (f.m.env[collisionNum1].isCollision == true || f.m.env[collisionNum2].isCollision == true) {
				object.collisionDetected = true;
			}
		}
		
		else if (object.direction == "right") {
			playerRightVer = (playerRight + object.playerSpeed)/f.realTileSize;
			collisionNum1 = f.m.layout[playerRightVer][playerTopHor];
			collisionNum2 = f.m.layout[playerRightVer][playerBottomHor];
			
			if (f.m.env[collisionNum1].isCollision == true || f.m.env[collisionNum2].isCollision == true) {
				object.collisionDetected = true;
			}
			
		}
		
		else if (object.direction == "down") {
			playerBottomHor = (playerBottom - object.playerSpeed)/f.realTileSize;
			collisionNum1 = f.m.layout[playerLeftVer][playerBottomHor];
			collisionNum2 = f.m.layout[playerRightVer][playerBottomHor];
			if (f.m.env[collisionNum1].isCollision == true || f.m.env[collisionNum2].isCollision == true) {
				object.collisionDetected = true;
			}
			
		}
		
		else if (object.direction == "up") {
			playerTopHor = (playerTop - object.playerSpeed)/f.realTileSize;
			collisionNum1 = f.m.layout[playerLeftVer][playerTopHor];
			collisionNum2 = f.m.layout[playerRightVer][playerTopHor];
			if (f.m.env[collisionNum1].isCollision == true || f.m.env[collisionNum2].isCollision == true) {
				object.collisionDetected = true;
			}
			
		}
		
	}
	
	public int itemCollision(Objects object, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < f.itm.length; i++) { 
			if(f.itm[i] != null) {
				object.hitBox.y = object.envY + object.hitBox.y; //Gets the player hit box position. i.e, the area that is solid that the player cannot walk through
				object.hitBox.x = object.envX + object.hitBox.x;
				
				f.itm[i].hitBox.y = f.itm[i].envY + f.itm[i].hitBox.y; //Gets the item hit box position
				f.itm[i].hitBox.x = f.itm[i].envX + f.itm[i].hitBox.x;
				
				if (object.direction == "up") {
					object.hitBox.y = object.hitBox.y - object.playerSpeed;
					if (object.hitBox.intersects(f.itm[i].hitBox)) {  //Checks to see if the 2 hit boxes (from the player and the object) are colliding with each other
						if (f.itm[i].isCollision == true) {
							object.collisionDetected = true;
						}
						if (player == true) {
							index = i;
						}

					}
				}
				else if (object.direction == "down") {
					object.hitBox.y = object.hitBox.y + object.playerSpeed;
					if (object.hitBox.intersects(f.itm[i].hitBox)) {
						if (f.itm[i].isCollision == true) {
							object.collisionDetected = true;
						}
						if (player == true) {
							index = i;
						}
						
					}

				}
				else if (object.direction == "right") {
					object.hitBox.x = object.hitBox.x + object.playerSpeed;
					if (object.hitBox.intersects(f.itm[i].hitBox)) {
						if (f.itm[i].isCollision == true) {
							object.collisionDetected = true;
						}
						if (player == true) {
							index = i;
						}
						
					}
				}
				else if (object.direction == "left") {
					object.hitBox.x = object.hitBox.x - object.playerSpeed;
					if (object.hitBox.intersects(f.itm[i].hitBox)) {
						if (f.itm[i].isCollision == true) {
							object.collisionDetected = true;
						}
						if (player == true) {
							index = i;
						}
						
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
	
	public int entityCollision (Objects object, Objects[] npc) {
		int index = 999;
		
		for(int i = 0; i < npc.length; i++) { //loops through target NPC array to check for any collision
			if(npc[i] != null) {
				object.hitBox.y = object.envY + object.hitBox.y; //Gets the player hit box position. i.e, the area that is solid that the player cannot walk through
				object.hitBox.x = object.envX + object.hitBox.x;
				
				npc[i].hitBox.y = npc[i].envY + npc[i].hitBox.y; //Gets the item hit box position
				npc[i].hitBox.x = npc[i].envX + npc[i].hitBox.x;
				
				if (object.direction == "up") {
					object.hitBox.y = object.hitBox.y - object.playerSpeed;
					if (object.hitBox.intersects(npc[i].hitBox)) {  //Checks to see if the 2 hit boxes (from the player and the object) are colliding with each other						
							object.collisionDetected = true;						
							index = i; //index of npc collided with
					}
				}
				else if (object.direction == "down") {
					object.hitBox.y = object.hitBox.y + object.playerSpeed;
					if (object.hitBox.intersects(npc[i].hitBox)) {
						
							object.collisionDetected = true;						
							index = i;												
					}

				}
				else if (object.direction == "right") {
					object.hitBox.x = object.hitBox.x + object.playerSpeed;
					if (object.hitBox.intersects(npc[i].hitBox)) {
						
							object.collisionDetected = true;												
							index = i;						
					}
				}
				else if (object.direction == "left") {
					object.hitBox.x = object.hitBox.x - object.playerSpeed;
					if (object.hitBox.intersects(npc[i].hitBox)) {
						
							object.collisionDetected = true;
							index = i;					
					}

				}
				object.hitBox.x = object.hitBoxX; //restore hit box values back to their defaults after the collision
				object.hitBox.y = object.hitBoxY;
				npc[i].hitBox.x = npc[i].hitBoxX;
				npc[i].hitBox.y = npc[i].hitBoxY;

			}
		}
		return index;
	}
	
	public void checkPlayer(Objects object) {
		object.hitBox.y = object.envY + object.hitBox.y; //Gets the player hit box position. i.e, the area that is solid that the player cannot walk through
		object.hitBox.x = object.envX + object.hitBox.x;
		
		f.p.hitBox.y = f.p.envY + f.p.hitBox.y; //Gets the item hit box position
		f.p.hitBox.x = f.p.envX + f.p.hitBox.x;
		
		if (object.direction == "up") {
			object.hitBox.y = object.hitBox.y - object.playerSpeed;
			if (object.hitBox.intersects(f.p.hitBox)) {  //Checks to see if the 2 hit boxes (from the player and the object) are colliding with each other						
					object.collisionDetected = true;						
					
			}
		}
		else if (object.direction == "down") {
			object.hitBox.y = object.hitBox.y + object.playerSpeed;
			if (object.hitBox.intersects(f.p.hitBox)) {
				
					object.collisionDetected = true;						
																	
			}

		}
		else if (object.direction == "right") {
			object.hitBox.x = object.hitBox.x + object.playerSpeed;
			if (object.hitBox.intersects(f.p.hitBox)) {
				
					object.collisionDetected = true;												
											
			}
		}
		else if (object.direction == "left") {
			object.hitBox.x = object.hitBox.x - object.playerSpeed;
			if (object.hitBox.intersects(f.p.hitBox)) {
				
					object.collisionDetected = true;
										
			}

		}
		object.hitBox.x = object.hitBoxX; //restore hit box values back to their defaults after the collision
		object.hitBox.y = object.hitBoxY;
		f.p.hitBox.x = f.p.hitBoxX;
		f.p.hitBox.y = f.p.hitBoxY;
		
	}

}

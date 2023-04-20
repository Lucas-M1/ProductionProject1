package Characters;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Frame;
import main.Optimization;

public class NPC extends Objects {
	
	public NPC(Frame f) {
		super(f);
		
		playerSpeed = 2;
		direction = "down";
		playerImage();
		dialogueSet();
	}
	
	
public void playerImage() { //Gets the png images from the resource package so they can be displayed on the screen
		                    //Setup method called from object class
		
		
		l1 = setup("/npc/NPC-RunningLeft1");
		l2 = setup("/npc/NPC-RunningLeft2");
		l3 = setup("/npc/NPC-LeftStationary");
		u1 = setup("/npc/NPC-RunningForward1");
		u2 = setup("/npc/NPC-RunningForward2");
		u3 = setup("/npc/NPC-ForwardStationary");
		d1 = setup("/npc/NPC-RunningDown1");
		d2 = setup("/npc/NPC-RunningDown2");
		d3 = setup("/npc/NPC-DownStationary");
		r1 = setup("/npc/NPC-RunningRight1");
		r2 = setup("/npc/NPC-RunningRight2");
		r3 = setup("/npc/NPC-RightStationary");
	}

	public void behaviour() {
		
		interval++;
		if (interval == 150) { //When the NPC sprite changes direction, it then will not do it again for 130 frames
			Random r = new Random();
			int randomInt = r.nextInt(100)+1;
			
			if (randomInt <= 25) {
				direction = "down";
			}
			if (randomInt > 25 && randomInt <= 50) {
				direction = "up";
				
			}
			if (randomInt > 50 && randomInt <= 75) {
				direction = "left";
				
			}
			if (randomInt > 75 && randomInt <= 100) {
				direction = "right";
				
			}
			interval = 0;
		}
		
		
		
	}
	
	public void dialogueSet() {
		dialogueOptions[0] = "hello";
		dialogueOptions[1] = "I am a desert dweller";
		dialogueOptions[2] = "It's rare to see people on this \nisland, what brings you here?";
		dialogueOptions[3] = "Legend has it there is treasure \non this island";
		dialogueOptions[4] = "I heard if you can find the keys, \nyou can open the treasure rooms";
		dialogueOptions[5] = "Good luck to you on your travels \nand watch out, i've seen \ndangerous creatures around this land";



	}
	
	public void dealogueText() {
		if(dialogueOptions[dialogueIndex] == null) { //If there is no more text to be displayed in the dialogue box, the text in the box starts from index one again
			dialogueIndex = 0; 
		}
		f.hud.currentDialogue = dialogueOptions[dialogueIndex];
		dialogueIndex++;
		
		if (f.p.direction == "up") {
			direction = "down";
		}
		else if (f.p.direction == "down") {
			direction = "up";
		}
		else if (f.p.direction == "left") {
			direction = "right";
		}
		else if (f.p.direction == "right") {
			direction = "left";
		}
	}
	
	
	
	

	
}

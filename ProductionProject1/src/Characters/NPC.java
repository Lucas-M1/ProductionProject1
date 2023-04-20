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
		dialogueOptions[1] = "It's rare to see people on this island, what brings you here?";
		dialogueOptions[2] = "Legend has it there is treasure on this island";
		dialogueOptions[3] = "Good luck to you on your travels";



	}
	
	public void dealogueText() {
		f.hud.currentDialogue = dialogueOptions[dialogueIndex];
		dialogueIndex++;
	}
	
	
	
	

	
}

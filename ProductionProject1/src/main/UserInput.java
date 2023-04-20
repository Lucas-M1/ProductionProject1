package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInput implements KeyListener {
	
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	Frame f;
	
	
	public UserInput(Frame f) {
		this.f = f;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {                         //When a key is pressed, it's pressed value is set to true 
		int key = e.getKeyCode();
		
		if(f.state == f.titleScreen) {
			if (key == KeyEvent.VK_W) {
				f.hud.num--;
				
				if(f.hud.num < 0) {
					f.hud.num = 2;
				}
				
			}
			if (key == KeyEvent.VK_S) {
				f.hud.num++;
				
				if(f.hud.num > 2) {
					f.hud.num = 0;
				}
				
			}
			
			if(key == KeyEvent.VK_ENTER) {
				if(f.hud.num == 0) {
					f.state = f.play;
				}
			
				if(f.hud.num == 0) {
					
				}
				if(f.hud.num == 1) {
					System.exit(0);
				}
			}
		}
		
		if(f.state == f.play) {
			if (key == KeyEvent.VK_W) {
				up = true;
				
			}
			if (key == KeyEvent.VK_A) {
				left = true;
				
			}
			if (key == KeyEvent.VK_S) {
				down = true;
				
			}
			if (key == KeyEvent.VK_D) {
				right = true;
				
			}
			if (key == KeyEvent.VK_ESCAPE) {
				f.state = f.paused;

				
			}
			
		}
		
		else if (f.state == f.paused) {
			if (key == KeyEvent.VK_ESCAPE) {
				f.state = f.play;

				
			}
			
		}
		
		else if (f.state == f.dialogue) {
			if(key == KeyEvent.VK_E) {
				f.state = f.play;
			}
			
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) { //When the key is released and so is no longer pressed in, it's pressed value returns to false
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
			up = false;
			
		}
		if (key == KeyEvent.VK_A) {
			left = false;
			
		}
		if (key == KeyEvent.VK_S) {
			down = false;
			
		}
		if (key == KeyEvent.VK_D) {
			right = false;
			
		}
		
		
	}

}

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInput implements KeyListener {
	
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) { //When a key is pressed, it's pressed value is set to true 
		int key = e.getKeyCode();
		
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

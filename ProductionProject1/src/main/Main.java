package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("PP Game");
		
		Frame frame = new Frame();
		window.add(frame);
		
		window.pack(); //Ensures game window is the size of the preferred size
		
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		frame.gameThread(); //Starts the game thread when the program is run

	}

}

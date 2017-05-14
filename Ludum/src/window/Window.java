package window;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {

	public Window(int width, int height, String title, Game game) {
		
		Dimension dimension = new Dimension(width, height);
		game.setPreferredSize(dimension);
		game.setMaximumSize(dimension);
		game.setMinimumSize(dimension);
		
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
}

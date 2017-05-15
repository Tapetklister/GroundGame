package window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameOverScreen {
	
	public Rectangle playB = new Rectangle(Game.WIDTH / 4, 200, Game.WIDTH / 2, 50);
	public Rectangle quitB = new Rectangle(Game.WIDTH / 4, 300, Game.WIDTH / 2, 50);
	
	public void render(Graphics graphics) {
		
		Graphics2D g2d = (Graphics2D) graphics;
		
		Font font1 = new Font("arial", Font.ROMAN_BASELINE, 50);
		graphics.setFont(font1);
		graphics.setColor(Color.RED);
		graphics.drawString("Game Over", Game.WIDTH / 4, 100);
		
		Font font2 = new Font("arial", Font.ROMAN_BASELINE, 30);
		graphics.setFont(font2);
		graphics.drawString("Replay", playB.x + 100, playB.y + 35);
		graphics.drawString("Quit", quitB.x + 110, quitB.y + 35);
		g2d.draw(playB);
		g2d.draw(quitB);
	}
}

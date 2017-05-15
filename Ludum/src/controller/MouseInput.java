package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import window.Game;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		
		if (Game.State != Game.STATE.GAME) {
			int mouseX = event.getX();
			int mouseY = event.getY();
			if (mouseX >= Game.WIDTH / 4 && mouseX <= Game.WIDTH - Game.WIDTH / 4) {
				if (mouseY >= 200 && mouseY <= 250) {
					
					switch (Game.State) {
					case MENU:
						Game.State = Game.STATE.GAME;
						break;
					case GAMEOVER:
						Game.willRestart = true;
						break;
					default:
						break;
					}
				}
				if (mouseY >= 300 && mouseY <= 350) {
					System.exit(1);
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}

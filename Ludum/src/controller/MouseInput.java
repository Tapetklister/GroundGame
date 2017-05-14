package controller;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import window.Game;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		
		if (Game.State == Game.STATE.MENU) {
			int mouseX = event.getX();
			int mouseY = event.getY();
			if (mouseX >= Game.WIDTH / 4 && mouseX <= Game.WIDTH - Game.WIDTH / 4) {
				if (mouseY >= 200 && mouseY <= 250) {
					Game.State = Game.STATE.GAME;
				}
				if (mouseY >= 300 && mouseY <= 350) {
					System.exit(1);
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
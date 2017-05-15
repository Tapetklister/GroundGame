package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.GameObject;
import model.ObjectId;
import model.Player;
import window.Game;
import window.Handler;

public class KeyInput extends KeyAdapter {

	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent event) {
		
		int keyCode = event.getKeyCode();
		
		switch (Game.State) {
		case GAME:
			for (int i = 0; i < handler.objectList.size(); i++) {
				GameObject object = handler.objectList.get(i);
				if (object.getId() == ObjectId.Player) {
					
					Player player = (Player) object;
					
					switch (keyCode) {
					case KeyEvent.VK_D:
						player.moveRight();
						break;
					case KeyEvent.VK_A:
						player.moveLeft();
						break;
					default:
						break;
					}
					
					if (keyCode == KeyEvent.VK_W && !object.isJumping()) {
						object.setJumping(true);
						object.setVelY(-10);
					}
					if (keyCode == KeyEvent.VK_SPACE) {
						player.shoot();
					}
				}
			}
			break;
		case GAMEOVER:
			if (keyCode == KeyEvent.VK_SPACE) {
				Game.willRestart = true;
			}
		default:
			break;
		}
		
		switch (keyCode) {
		case KeyEvent.VK_ESCAPE:
			Game.State = Game.STATE.MENU;
		}
	}
	
	public void keyReleased(KeyEvent event) {
		
		int keyCode = event.getKeyCode();
		
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject object = handler.objectList.get(i);
			if (object.getId() == ObjectId.Player) {
				
				Player player = (Player) object;
				
				switch (keyCode) {
				case KeyEvent.VK_D:
					
					player.setMoveRight(false);
					break;
				case KeyEvent.VK_A:
					player.setMoveLeft(false);
					break;
				default:
					break;
				}
			}
		}
	}
	
}

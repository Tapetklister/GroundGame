package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.GameObject;
import model.ObjectId;
import window.Handler;

public class KeyInput extends KeyAdapter {

	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent event) {
		
		int keyCode = event.getKeyCode();
		
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject object = handler.objectList.get(i);
			if (object.getId() == ObjectId.Player) {
				
				switch (keyCode) {
				case KeyEvent.VK_D:
					object.setVelX(5);
					break;
				case KeyEvent.VK_A:
					object.setVelX(-5);
					break;
				default:
					break;
				}
				
				if (keyCode == KeyEvent.VK_W && !object.isJumping()) {
					object.setJumping(true);
					object.setVelY(-10);
				}
			}
		}
		
		switch (keyCode) {
		case KeyEvent.VK_ESCAPE:
			System.exit(1);
		
		}
	}
	
	public void keyReleased(KeyEvent event) {
		
		int keyCode = event.getKeyCode();
		
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject object = handler.objectList.get(i);
			if (object.getId() == ObjectId.Player) {
				
				switch (keyCode) {
				case KeyEvent.VK_D:
					object.setVelX(0);
					break;
				case KeyEvent.VK_A:
					object.setVelX(0);
					break;
				default:
					break;
				}
			}
		}
	}
	
}

package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import objects.Block;
import window.Game;
import window.Handler;

public class Enemy extends GameObject {

	private float MAX_SPEED = 7.0f;
	private Handler handler;
	
	public Enemy(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		setGravity(0.3f);
		this.handler = handler;
	}

	@Override
	public void tick(LinkedList<GameObject> objectList) {
		
		x += velX;
		y += velY;
		
		if (x > Game.WIDTH) x = 0;
		if (x < 0) x = Game.WIDTH;
		
		velY += gravity;
		
		if (velY > MAX_SPEED) {
			velY = MAX_SPEED;
		}
		
		Collision(objectList);
	}
	
	private void Collision(LinkedList<GameObject> objectList) {
		
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject object = handler.objectList.get(i);
			if (object.getId() == ObjectId.Block) {
				
				Block block = (Block) object;
				
				if (getBounds().intersects(object.getBounds())) {
					object.destroy();
					Block newKidOnTheBlock = new Block(object.getX(), object.getY(), ObjectId.Block, 20);
					handler.addObject(newKidOnTheBlock);
					handler.removeObject(object);
					velY = -15;
				}
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		// TODO Auto-generated method stub
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect((int)x, (int)y, 32, 32);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

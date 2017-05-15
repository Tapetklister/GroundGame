package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import objects.Block;
import window.Handler;

public class Projectile extends GameObject {

	public int speed = 10;
	private Handler handler;
	
	public Projectile(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(LinkedList<GameObject> objectList) {
		// TODO Auto-generated method stub
		y -= speed;
		Collision(objectList);
	}
	
	private void Collision(LinkedList<GameObject> objectList) {
		
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject object = handler.objectList.get(i);
			if (object.getId() == ObjectId.Enemy) {
				if (getBounds().intersects(object.getBounds())) {
					object.destroy();
					destroy();
					handler.points += 1;
				}
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		// TODO Auto-generated method stub
		graphics.setColor(Color.CYAN);
		graphics.fillRect((int)x, (int)y, 2, 10);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 2, 10);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		handler.removeObject(this);
	}

}

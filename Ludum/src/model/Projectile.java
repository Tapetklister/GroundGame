package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import window.Game;
import window.Handler;

public class Projectile extends GameObject {

	public int speed = 10;
	private Handler handler;
	private int height = 40;
	private int width = 4;
	
	public Projectile(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void tick(LinkedList<GameObject> objectList) {
		y -= speed;
		Collision(objectList);
	}
	
	private void Collision(LinkedList<GameObject> objectList) {
		
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject object = handler.objectList.get(i);
			if (object.getId() == ObjectId.Enemy) {
				
				Enemy enemy = (Enemy) object;
				
				if (getBounds().intersects(enemy.getBounds())) {
					
					if (enemy.Type == Enemy.TYPE.GREEN) {
						Enemy enemy1 = new Enemy(object.getX(), object.getY(), handler, ObjectId.Enemy, Enemy.TYPE.CLUSTER);
						Enemy enemy2 = new Enemy(object.getX(), object.getY(), handler, ObjectId.Enemy, Enemy.TYPE.CLUSTER);
						enemy1.setVelX(3);
						enemy1.setVelY(-10);
						enemy2.setVelX(-3);
						enemy2.setVelY(-10);
						handler.addObject(enemy1);
						handler.addObject(enemy2);
					}
					object.destroy();
					destroy();
					Game.score += 1;
				}
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.CYAN);
		graphics.fillRect((int)x, (int)y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	@Override
	public void destroy() {
		handler.removeObject(this);
	}

}

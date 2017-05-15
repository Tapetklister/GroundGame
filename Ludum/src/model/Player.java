package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import window.Game;
import window.Handler;

public class Player extends GameObject {

	private final float MAX_SPEED = 10;
	private float width = 32;
	private float height = 64;
	private Handler handler;
	
	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		setGravity(1f);
		this.handler = handler;
	}

	@Override
	public void tick(LinkedList<GameObject> objectList) {
		x += velX;
		y += velY;
		
		if (isFalling || isJumping) {
			velY += gravity;
		}
		if (velY > MAX_SPEED) {
			velY = MAX_SPEED;
		}
		
		Collision(objectList);
	}
	
	private void Collision(LinkedList<GameObject> objectList) {
		
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject object = handler.objectList.get(i);
			if (object.getId() == ObjectId.Block) {
				
				if (getBounds().intersects(object.getBounds())) {
					y = object.getY() - height;
					velY = 0;
					isFalling = false;
					isJumping = false;
				} else isFalling = true;
				
				if (getBoundsRight().intersects(object.getBounds())) x = x - width/10;
				if (getBoundsLeft().intersects(object.getBounds())) x = x + width/10;
			}
			if (object.getId() == ObjectId.Wall) {
				if (getBoundsRight().intersects(object.getBounds())) x = object.getX() - width;
				if (getBoundsLeft().intersects(object.getBounds())) x = 36;
			}
			if (object.getId() == ObjectId.Enemy) {
				if (getBounds().intersects(object.getBounds())) {
					Game.State = Game.STATE.GAMEOVER;
				}
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		
		graphics.setColor(Color.YELLOW);
		graphics.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) ((int) x + (width/2)-(int)((width/2)/2)), (int) ((int)y + (height/2)), (int)(width/2), (int)(height / 2));
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x+(int)(width/2)-(int)((width/2)/2), (int)y, (int)(width/2), (int)(height / 2));
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width - 5), (int)y + 5, 5, (int)height -10);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y + 5, 5, (int)height - 10);
	}

	@Override
	public void destroy() {
		handler.removeObject(this);
	}
	
	public void shoot() {
		Projectile proj = new Projectile(x, y, handler, ObjectId.Projectile);
		handler.addObject(proj);
	}

}

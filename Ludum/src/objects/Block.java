package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import model.GameObject;
import model.ObjectId;
import window.Handler;

public class Block extends GameObject {

	public int type; 
	public Rectangle bounds;
	private Graphics graphics;
	private Handler handler;
	
	private int height = 32;
	private int width = 64;
	
	public Block(float x, float y, ObjectId id, Handler handler, int type) {
		super(x, y, id);
		this.type = type;
		setGravity(0.0f);
		this.handler = handler;
		
		if (type != 20)
			bounds = new Rectangle((int) x, (int) y, 64, 32); else {
				bounds = new Rectangle(0, 0, 0, 0);
			}
	}

	@Override
	public void tick(LinkedList<GameObject> objectList) {
			
	}

	@Override
	public void render(Graphics g) {
		
		this.graphics = g;
		
		if (type != 20) {
			switch (type) {
			case 0:
				graphics.setColor(Color.WHITE);
				break;
			case 1:
				graphics.setColor(Color.YELLOW);
				break;
			case 2:
				graphics.setColor(Color.RED);
				break;
			case 3:
				graphics.setColor(Color.PINK);
				break;
			case 4:
				graphics.setColor(Color.BLUE);
				break;
			case 5:
				graphics.setColor(Color.GREEN);
				break;
			case 6:
				graphics.setColor(Color.CYAN);
				break;
			case 7:
				graphics.setColor(Color.MAGENTA);
				break;
			case 10:
				graphics.setColor(Color.DARK_GRAY);
				break;
			default:
				graphics.setColor(Color.WHITE);
				break;
			}
			graphics.fillRect((int)x, (int)y, width, height);
			graphics.setColor(Color.BLACK);
			graphics.drawRect((int)x, (int)y, width, height);
		}
	}

	@Override
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void destroy() {
		handler.removeObject(this);
	}

}

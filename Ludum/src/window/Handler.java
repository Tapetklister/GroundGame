package window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import model.Enemy;
import model.EnemySpawner;
import model.GameObject;
import model.ObjectId;
import objects.Block;

public class Handler {
	
	public LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	public EnemySpawner enemySpawner = new EnemySpawner(this);
	public boolean gameOver = false;
	
	private GameObject tempObject;
	
	public void tick() {
		if (Game.State == Game.STATE.GAME) {
			for (int i = 0; i < objectList.size(); i++) {
				tempObject = objectList.get(i);
				tempObject.tick(objectList);
				
				if (tempObject.getX() > Game.WIDTH + 100 ||
						tempObject.getX() < -100 ||
						tempObject.getY() > Game.HEIGHT + 100 ||
						tempObject.getY() < -100) {
					removeObject(tempObject);
				}
			}
		}
	}
	
	public void render(Graphics graphics) {
		if (Game.State == Game.STATE.GAME) {
			for (int i = 0; i < objectList.size(); i++) {
				tempObject = objectList.get(i);
				tempObject.render(graphics);
			}
		
			Graphics2D g2d = (Graphics2D) graphics;
			Font pointsFont = new Font("arial", Font.ROMAN_BASELINE, 30);
			graphics.setFont(pointsFont);
			graphics.setColor(Color.CYAN);
			graphics.drawString("" + Game.score, 50, 50);
		}
	}
	
	public void addObject(GameObject object) {
		objectList.add(object);
	}
	
	public void removeObject(GameObject object) {
		objectList.remove(object);
	}
	
	public void createGround() {
		
		int rowIndex = 0;
		for (int yy = 640; yy < Game.HEIGHT - 32; yy += 32) {
			for (int xx = 32; xx < Game.WIDTH-64; xx += 64) {
				addObject(new Block(xx, yy, ObjectId.Block, this, rowIndex));
			}
			rowIndex++;
		}
	}
	
	public void createWalls() {
		for (int yy = 0; yy < Game.HEIGHT - 32; yy += 32) {
			GameObject leftBlock = new Block(-32, yy, ObjectId.Wall, this, 10);
			GameObject rightBlock = new Block(Game.WIDTH-46, yy, ObjectId.Wall, this, 10);
			addObject(leftBlock);
			addObject(rightBlock);
		}
	}
	
	public void spawnEnemy() {
		if (Game.State == Game.STATE.GAME) {
			Enemy enemy = enemySpawner.spawn();
			addObject(enemy);
		}
	}
}

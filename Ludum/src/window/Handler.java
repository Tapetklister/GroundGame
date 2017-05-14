package window;

import java.awt.Graphics;
import java.util.LinkedList;

import model.Enemy;
import model.EnemySpawner;
import model.GameObject;
import model.ObjectId;
import model.Projectile;
import objects.Block;

public class Handler {
	
	public LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	private int numberOfRows = 4;
	
	public float enemySpawnInterval = 3.0f;
	
	public EnemySpawner enemySpawner = new EnemySpawner(this);
	
	public void tick() {
		for (int i = 0; i < objectList.size(); i++) {
			tempObject = objectList.get(i);
			tempObject.tick(objectList);
		}
	}
	
	public void render(Graphics graphics) {
		for (int i = 0; i < objectList.size(); i++) {
			tempObject = objectList.get(i);
			tempObject.render(graphics);
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
			
			System.out.println(rowIndex);
			
			for (int xx = 32; xx < Game.WIDTH-64; xx += 64) {
				addObject(new Block(xx, yy, ObjectId.Block, rowIndex));
			}
			rowIndex++;
		}
	}
	
	public void createWalls() {
		
		for (int yy = 0; yy < Game.HEIGHT - 32; yy += 32) {
			GameObject leftBlock = new Block(-32, yy, ObjectId.Wall, 10);
			GameObject rightBlock = new Block(Game.WIDTH-46, yy, ObjectId.Wall, 10);
			addObject(leftBlock);
			addObject(rightBlock);
		}
	}
	
	public void spawnEnemy() {
		Enemy enemy = enemySpawner.spawn();
		addObject(enemy);
	}
}

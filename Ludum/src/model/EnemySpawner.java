package model;

import java.util.Random;

import window.Game;
import window.Handler;

public class EnemySpawner {

	private Random rand;
	private Thread thread;
	private Handler handler;
	private int spawnPoint;
	
	public EnemySpawner(Handler handler) {
		rand = new Random(Game.WIDTH);
		this.handler = handler;
	}
	
	public Enemy spawn() {
		float enemyVelX = rand.nextFloat() * 3;
		
		Enemy enemy = new Enemy(Game.WIDTH / 2, 0, handler, ObjectId.Enemy);
		enemy.setVelX(enemyVelX);
		return enemy;
	}
	
}

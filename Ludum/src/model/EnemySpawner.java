package model;

import java.util.Random;

import window.Game;
import window.Handler;

public class EnemySpawner {

	private Random rand;
	private Thread thread;
	private Handler handler;
	private int spawnPoint;
	private int type;
	
	public EnemySpawner(Handler handler) {
		rand = new Random(Game.WIDTH);
		this.handler = handler;
	}
	
	public Enemy spawn() {
		float enemyVelX = rand.nextFloat() * 3;
		int typeGen = rand.nextInt(5);
		if (typeGen > 0)
			type = 1;
		else
			type = 0;
		
		
		Enemy enemy = new Enemy(Game.WIDTH / 2, 0, handler, ObjectId.Enemy, type);
		enemy.setVelX(enemyVelX);
		return enemy;
	}
	
}

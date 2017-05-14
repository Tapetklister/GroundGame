package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.image.BufferStrategy;

import controller.KeyInput;
import controller.MouseInput;
import model.ObjectId;
import model.Player;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -5582403193394555353L;
	public static int WIDTH, HEIGHT;
	private boolean isRunning = false;
	private Handler handler;
	public Thread thread;
	
	private Menu menu;
	
	public static enum STATE {
		MENU,
		GAME,
		GAMEOVER
	};
	
	public static STATE State = STATE.MENU;
	
	public synchronized void start() {
		
		if (isRunning) return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		
		init();
		while (handler.gameOver == false) {
			
			this.requestFocus();
			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0;
			double ns = 1000000000/ amountOfTicks;
			double delta = 0;
			long timer = System.currentTimeMillis();
			int updates = 0;
			int frames = 0;
			long enemyTimer = System.currentTimeMillis();
			while (isRunning) {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				while (delta >= 1) {
					tick();
					updates += 1;
					delta -= 1;
				}
				render();
				frames += 1;
				
				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					frames = 0;
					updates = 0;
				}
				if (System.currentTimeMillis() - enemyTimer > 3000) {
					handler.spawnEnemy();
					enemyTimer += 3000;
				}
			}
		}
	}
	
	public void init() {
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		handler = new Handler();
		handler.addObject(new Player(Game.WIDTH / 2, Game.HEIGHT / 2, handler, ObjectId.Player));
		handler.createGround();
		handler.createWalls();
		
		menu = new Menu();
		
		this.addMouseListener(new MouseInput());
		this.addKeyListener(new KeyInput(handler));
	}
	
	public void tick() {
		handler.tick();
	}
	
	public void render() {
		
		BufferStrategy strategy = this.getBufferStrategy();
		if (strategy == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graphics = strategy.getDrawGraphics();
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		
		if (State == STATE.GAME)
			handler.render(graphics);
		else if (State == STATE.MENU)
			menu.render(graphics);
		
		graphics.dispose();
		strategy.show();
	}
	
	public static void main(String[] args) {
		
		new Window(580, 896, "Ludum", new Game());
	}

}

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

public class MainClass extends Canvas implements Runnable{

	private static final long serialVersionUID = 7251719333869962260L;
	public static int DEFWIDTH = 1150, DEFHEIGHT = 800;
	public int WIDTH = 1200, HEIGHT = 800;
	private Thread thread;
	private boolean running = false;
	
	Player player;
	Spawner spawner;
	UI ui;
	StartScreen startMenu;
	EndScreen endscreen;

	
	public STATE gameState = STATE.Start;
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			System.exit(1);
		}
	}
	
	public MainClass() {
		new Window(DEFWIDTH, DEFHEIGHT, "Shine", this);
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		player = new Player(WIDTH/2, HEIGHT/2, this);
		spawner = new Spawner(this);
		ui = new UI(player, this);
		startMenu = new StartScreen(this);
		endscreen = new EndScreen(this);

		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(player);
		this.addMouseListener(startMenu);
		this.addMouseListener(endscreen);
	}

	public void run() {
		long lastTime = System.nanoTime();
		double ticks = 60.0;
		double ns = 1000000000 / ticks;
		double delta = 0.0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) {
				tick();
				delta--;
			}
			
			if (running) {
				render();
			}
		}
		
		stop();
	}

	public void tick() {
		this.requestFocus();
		System.out.println(gameState);
		
		if (gameState == STATE.Start) {
			startMenu.tick();
		} 

		if (gameState == STATE.Game) {
			player.tick();
			spawner.tick();
			ui.tick();
			
			spawner.spawnNewMonsters();
			spawner.removeDead();
			player.removeBullets();
		}
		
		if (gameState == STATE.Restart) {
			endscreen.tick();
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (gameState == STATE.Start) {
			startMenu.render(g);
		} 

		if (gameState == STATE.Game) {
			player.render(g);
			spawner.render(g);
			ui.render(g);
		}
		
		if (gameState == STATE.Restart) {
			endscreen.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public void reset() {
		player = new Player(WIDTH/2, HEIGHT/2, this);
		spawner = new Spawner(this);
		ui = new UI(player, this);
		startMenu = new StartScreen(this);
		endscreen = new EndScreen(this);
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(player);
		this.addMouseListener(startMenu);
		this.addMouseListener(endscreen);
	}
	
	public static void main(String[] args) {
		new MainClass();
	}
	
	public float clamp(float n, int min, int max) {
		if (n > max) {
			n = max;
		} else if (n < min) {
			n = min;
		}
		
		return n;
	}
	
	public float dist(float x1, float y1, float x2, float y2) {
		float xdist = (float)(Math.pow(Math.abs(x1 - x2), 2));
		float ydist = (float)(Math.pow(Math.abs(y1 - y2), 2));
		
		float cdist = (float)(Math.sqrt(xdist + ydist));
		
		return cdist;
	}
}

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;


import java.awt.AlphaComposite;          
import java.awt.Color;

public class Player extends MouseAdapter{
	PVector pos;
    double energy = 50;
	double minLightRadius = 0;
	double maxLightRadius = 400;
	double lightRadius = 10;
	double playerRadius = 25;
	boolean expendEnergy = false;
	boolean dead = false;
	float crownAngle = 0;
	
	LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	
	Random r = new Random();
	MainClass main;
	
	Player(float x, float y, MainClass main){
		pos = new PVector(x, y);
		this.main = main;
	}
	
	public void tick() {
		playerRadius = energy/2;
		lightRadius -= .1; 
		energy -= .005;
		crownAngle += .01f;

		if (expendEnergy && energy > 10) {
			energy -= .2;
			lightRadius += 5;
			expendEnergy = false;
		}
				
		LinkedList<Bullet> temp = new LinkedList<Bullet>();
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i).copy();
			temp.add(b);
		}
		
		for (Bullet b: temp) {
			b.tick();
		}
	
		lightRadius = main.clamp((float)lightRadius, (int)minLightRadius, (int)maxLightRadius);
		energy = main.clamp((float)energy, 0, 50);
		playerRadius = energy/2;
		kill();
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setComposite(makeTransparent(.33f));
		g.setColor(new Color(255, 255, 255));
		g.fillOval((int)(pos.x - lightRadius), (int)(pos.y - lightRadius), (int)(lightRadius * 2), (int)(lightRadius * 2));
		g.fillOval((int)(pos.x - lightRadius/2), (int)(pos.y - lightRadius/2), (int)(lightRadius), (int)(lightRadius));
		
		g.setColor(new Color(242, 222, 120));
		g2.setComposite(makeTransparent(1));
		g.fillOval((int)(pos.x - playerRadius), (int)(pos.y - playerRadius), (int)(playerRadius * 2), (int)(playerRadius * 2));
		
		showCrown(g);
		LinkedList<Bullet> temp = new LinkedList<Bullet>();
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i).copy();
			temp.add(b);
		}
		
		for (Bullet b: temp) {
			b.render(g);
		}
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}
	
	void fire(PVector target) {
		PVector vel = new PVector(target.x - (float)(pos.x), target.y - (float)(pos.y));
		PVector ppos = new PVector(pos.x, pos.y);
		vel.setMag(15);
		
		bullets.add(new Bullet(ppos, vel, main));
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		PVector mouse = new PVector(mouseX, mouseY);
		
		if (bullets.size() < 10000) {
			if (main.gameState == STATE.Game) {
				fire(mouse);
				energy -= 1;
			}
		}
	}
	
	public void removeBullets() {
		LinkedList<Bullet> temp = new LinkedList<Bullet>();
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i).copy();
			temp.add(b);
		}
		
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			
			if (b.pos.x > main.WIDTH || b.pos.x < 0 || b.pos.y < 0 || b.pos.y > main.HEIGHT) {
				bullets.remove(b);
				i--;
			}
			
			if (b.remove) {
				bullets.remove(b);
				i--;
			}
		}
	}
	
	public void kill() {
		for (int i = 0; i < main.spawner.monsters.size(); i++) {
			Monster m = main.spawner.monsters.get(i);
			
			float d = main.dist(m.pos.x, m.pos.y, pos.x, pos.y);
			
			if (d < m.radius + playerRadius) {
				dead = true;
			}
		}
		
		if (energy == 0) {
			dead = true;
		}
		
		if (dead) {
			main.gameState = STATE.Restart;
		}
	}
	
	public void showCrown(Graphics g) {
		for (int i = 0; i < 10; i++) {
			float angleSpacing = (float)(Math.PI * 2)/10;
			float angle = (float)angleSpacing * i + crownAngle;
			float radius = (float)playerRadius - 1;
			
			int[] xs = new int[3];
			int[] ys = new int[3];
			
			xs[0] = (int)((pos.x)+(radius * Math.cos(angle + (Math.PI * 2)/20)));
			xs[1] = (int)((pos.x)+(radius * Math.cos(angle - (Math.PI * 2)/20)));
			xs[2] = (int)((pos.x)+((radius + (playerRadius/4)) * Math.cos(angle)));
			ys[0] = (int)((pos.y)+(radius * Math.sin(angle + (Math.PI * 2)/20)));
			ys[1] = (int)((pos.y)+(radius * Math.sin(angle - (Math.PI * 2)/20)));
			ys[2] = (int)((pos.y)+((radius + (playerRadius/4)) * Math.sin(angle)));
			
			g.fillPolygon(xs, ys, 3);
		}
	}
}

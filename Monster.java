import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;


public class Monster {
	PVector pos;
	PVector vel;
	PVector acc;
	int maxVel = 5;
	float velAdd = .1f;
	int armor = 0;
	int radius = 10;
	int points = 0;
	
	boolean dead = false;
	
	MainClass main;
	
	Monster(float x, float y, float vx, float vy, int armor, MainClass main){
		pos = new PVector(x, y);
		vel = new PVector(vx, vy);
		acc = new PVector(0, 0);
		
		this.armor = armor;
		this.main = main;
		
		radius = 50;
		points = 3 + (armor * 1);
	}
	
	public void tick() {
		attack();
		
		vel.mult(.95f);
		vel.limit(5);
		pos.add(vel);
		
		collide();
	}
	
	public void attack() {
		if (main.dist(pos.x, pos.y, main.player.pos.x, main.player.pos.y) < main.player.lightRadius/2) {
			velAdd = .05f;
		} else if (main.dist(pos.x, pos.y, main.player.pos.x, main.player.pos.y) < main.player.lightRadius){
			velAdd = .1f;
		} else {
			velAdd = .2f;
		}
		
		PVector steer = new PVector(main.player.pos.x - pos.x, main.player.pos.y - pos.y);
		steer.setMag(velAdd);
		vel.add(steer);
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g.setColor(new Color(150, 0, 0));
		g.fillOval((int)(pos.x - radius), (int)(pos.y - radius), radius * 2, radius * 2);
		
		for (int i = 0; i < armor; i++) {
			int armorWidth = 10;
			int armorRadius = (int)(radius + (armorWidth * i));
			int armorRadius2 = (int)(armorWidth/2) + (int)(radius + (armorWidth * i));
			
			g.setColor(new Color(50 + ((int) i * 30), 50 + ((int) i * 30), 50 + ((int) i * 30)));
			g2.setStroke(new BasicStroke(armorWidth));
			g.drawOval((int)(pos.x - armorRadius), (int)(pos.y - armorRadius), armorRadius * 2, armorRadius * 2);
			g.setColor(Color.darkGray);
			g2.setStroke(new BasicStroke(2));
			g.drawOval((int)(pos.x - armorRadius2), (int)(pos.y - armorRadius2), armorRadius2 * 2, armorRadius2 * 2);
		}
	}
	
	public void collide() {
		LinkedList<Bullet> temp = new LinkedList<Bullet>();
		for (int i = 0; i < main.player.bullets.size(); i++) {
			Bullet b = main.player.bullets.get(i).copy();
			temp.add(b);
		}
		
		Iterator<Bullet> iterator = temp.iterator();
		
		while(iterator.hasNext()) {
			Bullet b = iterator.next();
			
			float d = main.dist(b.pos.x, b.pos.y, pos.x, pos.y);
			
			if (d < b.radius + radius) {
				b.remove = true;
				
				if (armor > 0) {
					armor--;
				} else {
					dead = true;
					main.player.energy += points;
				}
			}
		}
		
		main.player.bullets = temp;
	}
	
	public Monster copy() {
		Monster m = new Monster(this.pos.x, this.pos.y, this.vel.x, this.vel.y, this.armor, this.main);
		m.dead = dead;
		return m;
	}
}

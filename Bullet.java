import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	PVector pos;
	PVector vel;
	int radius = 5;
	boolean remove = false;
	
	MainClass main;
	
	Bullet(PVector pos, PVector vel, MainClass main){
		this.pos = pos;
		this.vel = vel;
		
		this.main = main;
	}
	
	public void tick() {
		pos.add(vel);
		
		if (main.dist(pos.x, pos.y, main.player.pos.x, main.player.pos.y) > 1000) {
			remove = true;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(252, 252, 140));
		g.fillOval((int)(pos.x - radius), (int)(pos.y - radius), radius * 2, radius * 2);
	}
	
	public Bullet copy() {
		Bullet b = new Bullet(this.pos, this.vel, this.main);
		b.remove = remove;
		b.radius = radius;
		return b;
	}
}

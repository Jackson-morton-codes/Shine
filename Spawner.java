import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Spawner {
	LinkedList<Monster> monsters = new LinkedList<Monster>();
	Random r = new Random();
	MainClass main;
	int level = 1;
	
	boolean spawnDelaySet = false;
	int resetTo = 500;
	int spawnDelay = 500;
	
	
	Spawner(MainClass main){
		this.main = main;
	}
	
	public void tick() {
		Iterator<Monster> iterator = monsters.iterator();

		while (iterator.hasNext()) {
			Monster m = iterator.next();
			m.tick();
		}
	}
	
	public void render(Graphics g) {
		Iterator<Monster> iterator = monsters.iterator();

		while (iterator.hasNext()) {
			Monster m = iterator.next();
			m.render(g);
		}
	}
	
	public void spawnNewMonsters() {
		if (level == 1) {
			if (spawnDelaySet == false) {
				resetTo = 500;
				spawnDelay = resetTo;
				spawnDelaySet = true;
			}
			
			if (spawnDelay > 0) {
				spawnDelay--;
			} else {
				float angle = (float) (r.nextFloat() * (Math.PI * 2));
				float x = 750 * (float)Math.cos(angle);
				float y = 750 * (float)Math.sin(angle);
				float vx = 0;
				float vy = 0;
			
				monsters.add(new Monster(x + main.WIDTH/2, y + main.HEIGHT/2, vx, vy, 0, main));
			
				resetTo -= (r.nextInt(20) + 20);
				spawnDelay = resetTo;
				//System.out.println(resetTo);
			}
			
			if (resetTo < 50) {
				level++;
				spawnDelaySet = false;
			}
		} else if (level == 2) {
			if (spawnDelaySet == false) {
				resetTo = 400;
				spawnDelay = resetTo;
				spawnDelaySet = true;
			}
			
			if (spawnDelay > 0) {
				spawnDelay--;
			} else {
				float angle = (float) (r.nextFloat() * (Math.PI * 2));
				float x = 750 * (float)Math.cos(angle);
				float y = 750 * (float)Math.sin(angle);
				float vx = 0;
				float vy = 0;
			
				monsters.add(new Monster(x + main.WIDTH/2, y + main.HEIGHT/2, vx, vy, 1, main));
			
				resetTo -= (r.nextInt(15) + 15);
				spawnDelay = resetTo;
				//System.out.println(resetTo);
			}
			
			if (resetTo < 40) {
				level++;
				spawnDelaySet = false;
			}
		} else if (level == 3) {
			if (spawnDelaySet == false) {
				resetTo = 300;
				spawnDelay = resetTo;
				spawnDelaySet = true;
			}
			
			if (spawnDelay > 0) {
				spawnDelay--;
			} else {
				float angle = (float) (r.nextFloat() * (Math.PI * 2));
				float x = 750 * (float)Math.cos(angle);
				float y = 750 * (float)Math.sin(angle);
				float vx = 0;
				float vy = 0;
			
				monsters.add(new Monster(x + main.WIDTH/2, y + main.HEIGHT/2, vx, vy, 2, main));
			
				resetTo -= (r.nextInt(10) + 15);
				spawnDelay = resetTo;
				//System.out.println(resetTo);
			}
			
			if (resetTo < 30) {
				level++;
				spawnDelaySet = false;
			}
		} else if (level == 4) {
			if (spawnDelaySet == false) {
				resetTo = 200;
				spawnDelay = resetTo;
				spawnDelaySet = true;
			}
			
			if (spawnDelay > 0) {
				spawnDelay--;
			} else {
				float angle = (float) (r.nextFloat() * (Math.PI * 2));
				float x = 750 * (float)Math.cos(angle);
				float y = 750 * (float)Math.sin(angle);
				float vx = 0;
				float vy = 0;
			
				monsters.add(new Monster(x + main.WIDTH/2, y + main.HEIGHT/2, vx, vy, 2, main));
			
				resetTo -= (r.nextInt(10) + 10);
				spawnDelay = resetTo;
				//System.out.println(resetTo);
			}
			
			if (resetTo < 20) {
				level++;
				spawnDelaySet = false;
			}
		} else if (level == 5) {
			if (spawnDelaySet == false) {
				resetTo = 100;
				spawnDelay = resetTo;
				spawnDelaySet = true;
			}
			
			if (spawnDelay > 0) {
				spawnDelay--;
			} else {
				float angle = (float) (r.nextFloat() * (Math.PI * 2));
				float x = 750 * (float)Math.cos(angle);
				float y = 750 * (float)Math.sin(angle);
				float vx = 0;
				float vy = 0;
			
				monsters.add(new Monster(x + main.WIDTH/2, y + main.HEIGHT/2, vx, vy, 3, main));
			
				resetTo -= (r.nextInt(2) + 3);
				spawnDelay = resetTo;
				//System.out.println(resetTo);
			}
			
			if (resetTo < 10) {
				resetTo = 10;
			}
		}
	}
	
	public void removeDead() {
		Iterator<Monster> iterator = monsters.iterator();
		
		Monster chosen = null;
		
		while(iterator.hasNext()) {
			Monster m = iterator.next();
			
			if (m.dead) {
				chosen = m;
			}
		}
		
		monsters.remove(chosen);
	}
}

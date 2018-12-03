import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class UI {
	Player player;
	MainClass main;
	
	int energy = 0;
	int light = 0;
	
	Rectangle lightBar;
	Rectangle energyBar;
	boolean knows = false;
	
//	BufferedImage flashlight = null;
//	BufferedImage bolt = null;
	
	UI(Player player, MainClass main){
		this.player = player;
		this.main = main;
		
		lightBar = new Rectangle(25, 25, 200, 40);
		energyBar = new Rectangle(main.WIDTH - 225, 25, 200, 40);
		
//		try {
//			flashlight = ImageIO.read(new File("Data/Flashlight.png"));
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			bolt = ImageIO.read(new File("Data/Bolt.png"));
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public void tick() {
		energy = (int)player.energy * 2;
		light = (int)player.lightRadius/4;
		
		if (light > 10) {
			knows = true;
		}
		
//		energy = 1;
//		light = 1;
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g.setColor(new Color(240, 240, 120));
		g.fillRect(energyBar.x, energyBar.y, energy * 2, energyBar.height);
		g.setColor(Color.white);
		g.fillRect(lightBar.x, lightBar.y, light * 2, lightBar.height);
		
		g.setColor(Color.darkGray);
		g2.setStroke(new BasicStroke(5));
		g.drawRect(energyBar.x, energyBar.y, energyBar.width, energyBar.height);
		g.drawRect(lightBar.x, lightBar.y, lightBar.width, lightBar.height);
		
		g.setColor(Color.white);
		g.setFont(new Font("Bahnschrift", 1, 30));
		g.drawString("Wave " + main.spawner.level, 505, 50);
		
		if (knows == false) {
			g.drawString("Space - emit light (light slows monsters)", 250, main.HEIGHT - 30);
		}
		
//		if (flashlight != null && bolt != null) {
//			g.drawImage(flashlight, lightBar.x + lightBar.width + 10, lightBar.y, null);
//			g.drawImage(bolt, energyBar.x - 50, energyBar.y, null);
//		}
	}
}

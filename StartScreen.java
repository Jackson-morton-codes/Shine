import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StartScreen extends MouseAdapter{
	Rectangle start;
	Rectangle quit;
	
	Image shine;
	MainClass main;
	
	boolean onStart = false;
	boolean onQuit = false;
	
	StartScreen(MainClass main){
		this.main = main;
		
		start = new Rectangle(main.WIDTH/2 - ((main.WIDTH/2)/2)+ 100, main.HEIGHT/2 - 25, main.WIDTH/2 - (200), 100);
		quit = new Rectangle(main.WIDTH/2 - ((main.WIDTH/2)/2)+100, main.HEIGHT/2 + 175, main.WIDTH/2 - (200), 100);
		
	//	shine = ImageIO.read(new FileInputStream(new File("Data/Shine.pn")));
		
//		try {
//			shine = ImageIO.read(new File("Data/Shine.PNG"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		File file = new File("Data/Shine.PNG"); // I have bear.jpg in my working directory  
//	    FileInputStream fis = new FileInputStream(file);  
//	    BufferedInputStream bis = new BufferedInputStream(fis);
//	    shine = ImageIO.read(fis); //reading the image file  
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		int mouseX = MouseInfo.getPointerInfo().getLocation().x;
		int mouseY = MouseInfo.getPointerInfo().getLocation().y;
			
		if (onStart) {
			g.setColor(new Color(200, 200, 50));
			g.fillRect(start.x, start.y, start.width, start.height);
		}
		
		if (onQuit) {
			g.setColor(Color.lightGray);
			g.fillRect(quit.x, quit.y, quit.width, quit.height);
		}
		
		
		g.setColor(Color.gray);
		g2.setStroke(new BasicStroke(4));
		g.drawRect(start.x, start.y, start.width, start.height);
		g.drawRect(quit.x, quit.y, quit.width, quit.height);
		
		g.setFont(new Font("Arial", 4, 40));
		g.setColor(Color.white);
		g.drawString("Start", start.x + 130, start.y + 60);
		
		g.setColor(new Color(250, 250, 125));
		g.drawString("Quit", quit.x + 140, quit.y + 60);
		
		g.setFont(new Font("Book Antiqua", 4, 200));
		g.setColor(new Color(250, 250, 125));
		g.drawString("Shine!", 295, 255);
		g.setColor(Color.white);
		g.drawString("Shine!", 290, 250);
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if (mouseX > start.x && mouseX < start.x + start.width && mouseY > start.y && mouseY < start.y + start.height) {
			onStart = true;
		} else {
			onStart = false;
		}
		
		if (mouseX > quit.x && mouseX < quit.x + quit.width && mouseY > quit.y && mouseY < quit.y + quit.height) {
			onQuit = true;
		} else {
			onQuit = false;
		}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if(main.gameState == STATE.Start) {
		if (mouseX > start.x && mouseX < start.x + start.width && mouseY > start.y && mouseY < start.y + start.height) {
			main.gameState = STATE.Game;
		}
		
		if (mouseX > quit.x && mouseX < quit.x + quit.width && mouseY > quit.y && mouseY < quit.y + quit.height) {
			System.exit(1);
		}
		}
	}
}

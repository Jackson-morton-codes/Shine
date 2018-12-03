import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EndScreen extends MouseAdapter{
	Rectangle restart;
	MainClass main;
	
	EndScreen(MainClass main){
		this.main = main;
		
		restart = new Rectangle(main.WIDTH/2 - 200, main.HEIGHT/2 + 50, 400, 125);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setStroke(new BasicStroke(6));
		g.setColor(Color.gray);
		g.drawRect(restart.x, restart.y, restart.width, restart.height);
		
		g.setColor(new Color(100, 0, 0));
		g.fillRect(restart.x, restart.y, restart.width, restart.height);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 4, 40));
		g.drawString("Restart", restart.x + 135, restart.y + 75);
		
		g.setFont(new Font("Felix Titling", 4, 150));
		g.setColor(new Color(100, 0, 0));
		g.drawString("Drained", 210, 260);
		g.setColor(new Color(150, 0, 0));
		g.drawString("Drained", 200, 250);
		
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (main.gameState == STATE.Restart) {
		if (mx > restart.x && mx < restart.x + restart.width && my > restart.y && my < restart.y + restart.height) {
			main.reset();
			main.gameState = STATE.Start;
		}
		}
	}
}

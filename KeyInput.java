import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	MainClass main;
	
	KeyInput(MainClass main){
		this.main = main;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		System.out.println("wooh");

		if (key == KeyEvent.VK_SPACE) {main.player.expendEnergy = true;};

	}
}

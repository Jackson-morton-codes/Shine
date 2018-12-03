import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	Window(int w, int h, String title, MainClass main){
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(w, h));
		frame.setMinimumSize(new Dimension(w, h));
		frame.setMaximumSize(new Dimension(w, h));
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(main);
		frame.setVisible(true);
		main.start();
	}
}

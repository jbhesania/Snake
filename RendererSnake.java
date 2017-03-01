package snake;

import java.awt.Graphics;

import javax.swing.JPanel;

public class RendererSnake extends JPanel {
	
	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Snake.snake.repaint(g);
	}
	

}

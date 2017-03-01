package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * @author joyaan
 *
 */
public class Snake implements KeyListener, MouseListener, ActionListener {
	
	public static Snake snake;
	public final int WIDTH = 800, HEIGHT=800;
	public final int GRID = 40;
	public final int GWIDTH = WIDTH/GRID, GHEIGHT =HEIGHT/GRID;
	public RendererSnake renderer;
	public LinkedList<Block> snakey;
	public LinkedList<Character> dirs;
	public Block Mouse;
	public boolean started, gameOver;
	public char direction;
	

	public Snake() {
		renderer = new RendererSnake();

		snakey = new LinkedList<Block>();
		snakey.add(new Block(0,4));
		snakey.add(new Block(0,3));
		snakey.add(new Block(0,2));
		snakey.add(new Block(0,1));
		snakey.add(new Block(0,0));
		dirs = new LinkedList<Character>();

		JFrame jframe = new JFrame("Snakey Snake");
		jframe.add(renderer);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(true);
		jframe.addKeyListener(this);
		
		Mouse = new Block((int)(Math.random()*GRID), (int)(Math.random()*GRID));

		Timer timer = new Timer(60, this);
		timer.start();
		
		
	}

	
	public void determineLegal() {
		for(int i=1; i<snakey.size(); i++) {
			if(snakey.get(0).equals(snakey.get(i))) gameOver = true;
		}
		if(snakey.get(0).x < 0 || snakey.get(0).x > GRID) gameOver = true;
		if(snakey.get(0).y < 0 || snakey.get(0).y > GRID) gameOver = true;
		
	}
	
	public void print(ArrayList<Block> snakey) {
		for(Block x : snakey) System.out.print(x + " ||| ");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(dirs.size() > 0) direction = dirs.remove(0).charValue();
		if(started && !gameOver) {
			if(direction == 'n') {
				snakey.add(0, new Block(snakey.get(0)));
				snakey.get(0).y--;
				if(!eaten()) snakey.removeLast();
			}
			if(direction == 's') {
				snakey.add(0, new Block(snakey.get(0)));
				snakey.get(0).y++;
				if(!eaten()) snakey.removeLast();
			}
			if(direction == 'e') {
				snakey.add(0, new Block(snakey.get(0)));
				snakey.get(0).x++;
				if(!eaten()) snakey.removeLast();
			}
			if(direction == 'w') {
				snakey.add(0, new Block(snakey.get(0)));
				snakey.get(0).x--;
				if(!eaten()) snakey.removeLast();
			}
			determineLegal();
		}
		renderer.repaint();
	}
	
	public boolean eaten() {
		if(snakey.getFirst().x == Mouse.x && snakey.getFirst().y == Mouse.y) {
			Mouse = new Block((int)(Math.random()*GRID), (int)(Math.random()*GRID));
			renderer.repaint();
			return true;
		}
		else return false;
	}
	public void repaint(Graphics g) {
		g.setColor(Color.blue.darker().darker());
		g.fillRect(0, 0,WIDTH, HEIGHT);
		
		g.setColor(Color.red);
		for(Block x : snakey) {
			g.fillRect(x.x*GHEIGHT, x.y*GWIDTH, GWIDTH, GHEIGHT);
		}
		g.setColor(Color.yellow.brighter());
		g.fillRect(snakey.get(0).x*GHEIGHT, snakey.get(0).y*GHEIGHT, GWIDTH, GHEIGHT);
		
		if(started) {
			g.setColor(Color.CYAN);
			g.fillRect(Mouse.x*GHEIGHT, Mouse.y*GWIDTH, GWIDTH, GHEIGHT);
		}

		g.setColor(Color.white);
		g.setFont(new Font("Comic Sans MS", 1, 80));
		
		if(gameOver) {
			g.drawString("Game OVER!! :((", 80, HEIGHT/2-100);
			g.drawString("BOOHOO :((", 120, HEIGHT/2 + 80);
			
		}

		if(started && !gameOver) {
			g.drawString(String.valueOf(snakey.size()-5),WIDTH/2, HEIGHT/7);
		}
		if(!started && !gameOver) {
			g.drawString("Ready? Set?", WIDTH/4, HEIGHT/2 - HEIGHT/10);
			g.drawString("SNAKE!", WIDTH/4 + 30, HEIGHT/2 + HEIGHT/10);

		}
	
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(gameOver) {
			gameOver = false;
			started = false;
			snakey.clear();
			snakey.add(new Block(0,4));
			snakey.add(new Block(0,3));
			snakey.add(new Block(0,2));
			snakey.add(new Block(0,1));
			snakey.add(new Block(0,0));
			Mouse = new Block((int)(Math.random()*GRID), (int)(Math.random()*GRID));
			
		}
		else {
			if(!started) started = true;
			if(dirs.size() == 0 && (direction != 'n' && direction != 's' && direction != 'e' &&direction != 'w')) {
				//if( e.getKeyCode() == KeyEvent.VK_UP) dirs.add('n');		CANT GO NORTH FIRST TIME
				if( e.getKeyCode() == KeyEvent.VK_DOWN) dirs.add('s');
				if( e.getKeyCode() == KeyEvent.VK_RIGHT) dirs.add('e');
				if( e.getKeyCode() == KeyEvent.VK_LEFT) dirs.add('w');
			}
			else if (dirs.size() == 0 ) {
				if( e.getKeyCode() == KeyEvent.VK_UP) {
					if(direction != 's') dirs.add('n');
				}
				if( e.getKeyCode() == KeyEvent.VK_DOWN) {
					if(direction != 'n') dirs.add('s');
				}
				if( e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if( direction != 'w') dirs.add('e');
				}
				if( e.getKeyCode() == KeyEvent.VK_LEFT) {
					if( direction != 'e') dirs.add('w');
				}
			}
			
			else if (dirs.size()  >0 ) {
				if( e.getKeyCode() == KeyEvent.VK_UP) {
					if(dirs.getLast().charValue() != 's') dirs.add('n');
				}
				if( e.getKeyCode() == KeyEvent.VK_DOWN) {
					if(dirs.getLast().charValue() != 'n') dirs.add('s');
				}
				if( e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if(dirs.getLast().charValue() != 'w') dirs.add('e');
				}
				if( e.getKeyCode() == KeyEvent.VK_LEFT) {
					if(dirs.getLast().charValue() != 'e' ) dirs.add('w');
					}
				}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		snake = new Snake();

	}

}

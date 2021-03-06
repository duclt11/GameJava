package snake;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends Applet implements Runnable, KeyListener {
	Graphics gfx;
	java.awt.Image img;
	Thread thread;
	Snake snake;
	Token token;
	boolean gameOver;
	public void init() {
		this.resize(400, 400);
		gameOver = false;
		img = createImage(400, 400);
		gfx = img.getGraphics();
		this.addKeyListener(this);
		snake = new Snake();
		token = new Token(snake);
		thread = new Thread(this);
		thread.start();
		

	}

	public void paint(Graphics g) {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, 400, 400);
		if(!gameOver) {
			snake.draw(gfx);
			token.draw(gfx);
		}
		else {
			gfx.setColor(Color.WHITE);
			gfx.drawString("Game Over"+"\nScore: "+token.getScore(), 150, 150);
		}
		g.drawImage(img, 0, 0, null);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void repaint(Graphics g) {
		paint(g);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (;;) {
			if(!gameOver) {
				snake.move();
				token.snakeCollision();
				this.checkGameOver();
			}
			
			this.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void checkGameOver() {
		if(snake.getX()<0 || snake.getX()>396) {
			this.gameOver = true;
		}
		if(snake.getY()<0 || snake.getY()>396) {
			this.gameOver = true;
		}
		if(snake.snakeCollision()) {
			this.gameOver = true;
		}
		//System.out.println(gameOver);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!snake.isMoving()) {
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_RIGHT
					) {
				snake.setisMoving(true);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (snake.getyDir() != 1) {
				snake.setyDir(-1);
				snake.setxDir(0);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (snake.getyDir() != -1) {
				snake.setyDir(1);
				snake.setxDir(0);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (snake.getxDir() != 1) {
				snake.setxDir(-1);
				snake.setyDir(0);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (snake.getxDir() != -1) {
				snake.setxDir(1);
				snake.setyDir(0);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

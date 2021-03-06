package snake;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Snake {
	List<Point> snakePoint;
	int xDir, yDir;

	boolean isMoving, elongate;

	final int STARTSIZE = 20, STARTX = 150, STARTY = 150;

	public Snake() {
		snakePoint = new ArrayList<Point>();
		xDir = 0;
		yDir = 0;
		isMoving = false;
		elongate = false;
		snakePoint.add(new Point(STARTX, STARTY));
		for (int i = 1; i < STARTSIZE; i++) {
			snakePoint.add(new Point(STARTX - i * 4, STARTY)); // draw snake ooooooooooooooo0
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		for (Point p : snakePoint) {
			g.fillRect(p.getX(), p.getY(), 4, 4);
		}

	}

	public void move() {
		if (isMoving) {
			Point temp = snakePoint.get(0);
			Point last = snakePoint.get(snakePoint.size() - 1);
			Point newStart = new Point(temp.getX() + xDir * 4, temp.getY() + yDir * 4);
			for (int i = snakePoint.size() - 1; i >= 1; --i) {
				snakePoint.set(i, snakePoint.get(i - 1));
//				int a = snakePoint.get(i).getX();
//				int b = snakePoint.get(i).getY();

			}
			snakePoint.set(0, newStart);
			if (elongate) {
				snakePoint.add(last);
				elongate = false;
			}
//			if(this.getX() >= 396) {
//				snakePoint.get(0).setX(0);
//			}
//			if(this.getX() <=0) {
//				snakePoint.get(0).setX(400);
//			}
//			if(this.getY() >=396) {
//				snakePoint.get(0).setY(0);
//			}
//			if(this.getY() <=0) {
//				snakePoint.get(0).setY(400);
//			}
		}
//		System.out.println(snakePoint.get(0).getX() +" "+ snakePoint.get(0).getY());
		this.snakeCollision();
		// System.out.println(elongate);
	}

	public boolean snakeCollision() {
		for (int i = 1; i < snakePoint.size(); i++) {
			if (snakePoint.get(i).getX() == this.getX() && snakePoint.get(i).getY() == this.getY()) {
				return true;
			}
		}
		return false;
	}

	public boolean getElongate() {
		return elongate;
	}

	public void setElongate(boolean e) {
		elongate = e;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setisMoving(boolean b) {
		isMoving = b;
	}

	public int getxDir() {
		return xDir;
	}

	public void setxDir(int xDir) {
		this.xDir = xDir;
	}

	public int getyDir() {
		return yDir;
	}

	public void setyDir(int yDir) {
		this.yDir = yDir;
	}

	// Tra ve toa do x cua dau con ran
	public int getX() {
		return snakePoint.get(0).getX();
	}

	// Tra ve toa do y cua dau con ran
	public int getY() {
		return snakePoint.get(0).getY();
	}
}

public class Point implements Comparable<Point> {
	
	private int x;
	private int y;
	
	public Point(int xIn, int yIn) {
		this.x = xIn;
		this.y = yIn;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void setX(int xIn) {
		x = xIn;
	}

	public void setY(int yIn) {
		y = yIn;
	}
	
	public String toString() {
		return x + " " + y;
	}

	@Override
	public boolean equals(Object o) {
		if (this.x == ((Point)o).x && this.y == ((Point)o).y) return true;
		else return false;
	}

	@Override
	public int compareTo(Point o) {
		if (this.x == ((Point)o).x && this.y == ((Point)o).y) return 0; //consistent with equals
		else if (this.x < ((Point)o).x) return -1;
		else if (this.x > ((Point)o).x) return 1;
		else if (this.y < ((Point)o).y) return -1;
		else if (this.y > ((Point)o).y) return 1;
		return 0; //stub
	}
	
}

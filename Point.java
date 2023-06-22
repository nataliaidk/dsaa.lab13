package dsaa.lab13;

public class Point {
	public long x;

	public long y;

	public Point(long x, long y) {
		this.x = x;
		this.y = y;
	}
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}

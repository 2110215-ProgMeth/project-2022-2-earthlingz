package utils;

public class Vector2D {

	private double x, y;

	public Vector2D() {
		this.x = 0;
		this.y = 0;
	}

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D(Vector2D other) {
		this.x = other.getX();
		this.y = other.getY();
	}

	public Vector2D(Vector2D start, Vector2D finish) {
		this.x = finish.getX() - start.getX();
		this.y = finish.getY() - start.getY();
	}

	public void add(double deltaX, double deltaY) {
		this.x += deltaX;
		this.y += deltaY;
	}

	public void add(Vector2D other) {
		this.x += other.getX();
		this.y += other.getY();
	}

	public static Vector2D add(Vector2D v1, Vector2D v2) {
		return new Vector2D(v1.getX() + v2.getX(), v1.getY() + v2.getY());
	}

	public void multiply(double multiplier) {
		this.x *= multiplier;
		this.y *= multiplier;
	}

	public static Vector2D multiply(Vector2D vector2d, double multiplier) {
		return new Vector2D(vector2d.getX() * multiplier, vector2d.getY() * multiplier);
	}

	public double dot(Vector2D v1, Vector2D v2) {
		return v1.getX() * v2.getX() + v1.getY() * v2.getY();
	}

	public double getSize() {
		return Math.hypot(this.x, this.y);
	}
	
	public Vector2D  getDirectionalVector() {
		return Vector2D.multiply(this, 1/this.getSize());
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2D other = (Vector2D) obj;
		if (this.x == other.x && this.y == other.y) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}

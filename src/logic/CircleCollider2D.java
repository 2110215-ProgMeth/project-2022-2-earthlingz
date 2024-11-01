package logic;

import utils.LogicUtility;
import utils.Vector2D;

public class CircleCollider2D extends Collider2D {

	private Vector2D center;
	private double radius;

	public CircleCollider2D(Vector2D center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	public boolean containPoint(Vector2D point) {
		return new Vector2D(this.center, point).getSize() <= this.getRadius();
	}
	
	@Override
	public void translate(Vector2D vector) {
		this.center.add(vector);
	}
	
	@Override
	public boolean collideWith(Collider2D other) {
		if (other instanceof BoxCollider2D) {
			BoxCollider2D box = (BoxCollider2D) other;
			return LogicUtility.checkBoxCollideWithCircle(box, this);
		} else if (other instanceof CircleCollider2D) {
			CircleCollider2D circle = (CircleCollider2D) other;
			return new Vector2D(this.center, circle.center).getSize() <= this.getRadius() + circle.getRadius();
		}
		return false;
	}

	@Override
	public Vector2D getCenter() {
		return center;
	}

	public void setCenter(Vector2D center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		if (radius < 0) {
			radius = 0;
		}
		this.radius = radius;
	}

}

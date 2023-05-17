package utils;

public class BoxCollider2D extends Collider2D {

	public Vector2D ltPos;
	public Vector2D rbPos;

	public BoxCollider2D(Vector2D ltPos, Vector2D rbPos) {
		this.ltPos = ltPos;
		this.rbPos = rbPos;
	}

	public BoxCollider2D(Vector2D ltPos, double width, double height) {
		this.ltPos = ltPos;
		this.rbPos = Vector2D.add(ltPos, new Vector2D(width, height));
	}

	public void translate(double deltaX, double deltaY) {
		this.ltPos.add(deltaX, deltaY);
		this.rbPos.add(deltaX, deltaY);
	}

	public boolean containPoint(Vector2D point) {
		return point.getX() >= ltPos.getX() && point.getY() >= ltPos.getY() && point.getX() <= rbPos.getX()
				&& point.getY() <= rbPos.getY();
	}

	@Override
	public boolean collideWith(Collider2D other) {
		if (other instanceof BoxCollider2D) {
			BoxCollider2D box = (BoxCollider2D) other;
			return this.ltPos.getX() < box.rbPos.getX() && this.ltPos.getY() < box.rbPos.getY()
					&& this.rbPos.getX() > box.ltPos.getX() && this.rbPos.getY() > box.ltPos.getY();
		} else if (other instanceof CircleCollider2D) {
			CircleCollider2D circle = (CircleCollider2D) other;
			return LogicUtility.checkBoxCollideWithCircle(this, circle);
		}
		return false;
	}

	public Vector2D getCenter() {
		return Vector2D.multiply(new Vector2D(this.ltPos, this.rbPos), 0.5);
	}

	public double getWidth() {
		return rbPos.getX() - ltPos.getX();
	}

	public double getHeight() {
		return rbPos.getY() - ltPos.getY();
	}

}

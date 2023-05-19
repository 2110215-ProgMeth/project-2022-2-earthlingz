package utils;

public class BoxCollider2D extends Collider2D {

	private Vector2D ltPos;
	private Vector2D rbPos;

	public BoxCollider2D(Vector2D ltPos, Vector2D rbPos) {
		this.ltPos = ltPos;
		this.rbPos = rbPos;
	}

	public BoxCollider2D(Vector2D centerPosition, double width, double height) {
		this.ltPos = Vector2D.add(centerPosition, new Vector2D(-width / 2, -height / 2));
		this.rbPos = Vector2D.add(centerPosition, new Vector2D(width / 2, height / 2));
	}

	@Override
	public void translate(Vector2D vector) {
		this.ltPos.add(vector);
		this.rbPos.add(vector);
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

	@Override
	public Vector2D getCenter() {
		return Vector2D.multiply(Vector2D.add(this.ltPos, this.rbPos), 0.5);
	}

	public double getWidth() {
		return rbPos.getX() - ltPos.getX();
	}

	public double getHeight() {
		return rbPos.getY() - ltPos.getY();
	}

	public Vector2D getLtPos() {
		return ltPos;
	}

	public void setLtPos(Vector2D ltPos) {
		this.ltPos = ltPos;
	}

	public Vector2D getRbPos() {
		return rbPos;
	}

	public void setRbPos(Vector2D rbPos) {
		this.rbPos = rbPos;
	}

}

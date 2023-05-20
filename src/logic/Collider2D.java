package logic;

import utils.Vector2D;

public abstract class Collider2D {
	public abstract Vector2D getCenter();
	public abstract void translate(Vector2D vector);
	public abstract boolean collideWith(Collider2D other);
}

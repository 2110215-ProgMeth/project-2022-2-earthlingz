package gameObject;

import render.Renderable;
import utils.Vector2D;

public abstract class GameObject implements Renderable {

	protected Vector2D position;
	protected int z;
	protected boolean visible, destroyed;

	protected GameObject() {
		this.visible = true;
		this.destroyed = false;
		this.position = new Vector2D();
	}

	protected GameObject(Vector2D position) {
		this.visible = true;
		this.destroyed = false;
		this.position = position;
	}
	
	public void translate(Vector2D vector) {
		this.position.add(vector);
	}
	
	public void destroy() {
		this.destroyed = true;
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public int getZ() {
		return z;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

}
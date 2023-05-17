package entity;

import utils.Collider2D;
import utils.Time;
import utils.Vector2D;

public abstract class PhysicsEntity extends Entity {

	protected Collider2D collider;
	protected double mass;
	protected Vector2D velocity;
	protected Vector2D acceleration;
	protected boolean isKinematic;
	protected boolean bouncable;
	
	public PhysicsEntity() {
		super();
		this.velocity = new Vector2D();
		this.acceleration = new Vector2D();
		this.isKinematic = true;
		this.bouncable = false;
		this.mass = 1;
	}
	
	public PhysicsEntity(Vector2D position) {
		super(position);
		this.velocity = new Vector2D();
		this.acceleration = new Vector2D();
		this.isKinematic = true;
		this.bouncable = false;
		this.mass = 1;
	}
	
	public PhysicsEntity(Vector2D position,double mass) {
		super(position);
		this.velocity = new Vector2D();
		this.acceleration = new Vector2D();
		this.isKinematic = true;
		this.bouncable = false;
		this.mass = mass;
	}

	public Vector2D calculateNewPosition() {
		if(!isKinematic) {
			return this.getPosition();
		}
		double deltaTime = Time.getDeltaTime();
		Vector2D newPosition = new Vector2D(this.position);
		this.velocity.add(Vector2D.multiply(this.acceleration, deltaTime));
		newPosition.add(Vector2D.multiply(this.velocity, deltaTime));
		return newPosition;
	}

	public boolean checkCollide(PhysicsEntity other) {
		return this.collider.collideWith(other.collider);
	}

	public Vector2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	public Vector2D getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2D acceleration) {
		this.acceleration = acceleration;
	}

	public boolean isKinematic() {
		return isKinematic;
	}

	public void setKinematic(boolean isKinematic) {
		this.isKinematic = isKinematic;
	}

	public boolean isBouncable() {
		return bouncable;
	}

	public void setBouncable(boolean bouncable) {
		this.bouncable = bouncable;
	}
	
}

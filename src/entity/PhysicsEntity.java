package entity;

import java.time.Duration;

import utils.Collider2D;
import utils.Time;
import utils.Vector2D;

public abstract class PhysicsEntity extends Entity {

	protected Collider2D collider;
	protected double mass;
	protected Vector2D velocity;
	protected Vector2D additionVelocity;
	protected Vector2D acceleration;
	protected Vector2D additionAcceleration;
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

	public PhysicsEntity(Vector2D position, double mass) {
		super(position);
		this.velocity = new Vector2D();
		this.acceleration = new Vector2D();
		this.isKinematic = true;
		this.bouncable = false;
		this.mass = mass;
	}

	public Vector2D calculateNewPosition() {
		if (!isKinematic) {
			return this.getPosition();
		}
		double deltaTime = Time.getDeltaTime();
		Vector2D newPosition = new Vector2D(this.position);
		this.velocity.add(Vector2D.multiply(this.acceleration, deltaTime));
		newPosition.add(Vector2D.multiply(this.velocity, deltaTime));
		return newPosition;
	}

	public void addForce(Vector2D force, long durationMillisec) {
		new Thread(() -> {
			final Vector2D forceAcceleration = Vector2D.multiply(force, 1 / this.mass);
			this.additionAcceleration.add(forceAcceleration);
			try {
				Thread.sleep(durationMillisec);
			} catch (InterruptedException e) {
				this.additionAcceleration.add(Vector2D.multiply(forceAcceleration,-1));
				return;
			}
			this.additionAcceleration.add(Vector2D.multiply(forceAcceleration,-1));
		}).start();
	}
	
	public void addImpulse(Vector2D impulse) {
			this.velocity.add(Vector2D.multiply(impulse, 1 / this.mass));
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

	public Collider2D getCollider() {
		return collider;
	}

	public void setCollider(Collider2D collider) {
		this.collider = collider;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public Vector2D getAdditionVelocity() {
		return additionVelocity;
	}

	public void setAdditionVelocity(Vector2D additionVelocity) {
		this.additionVelocity = additionVelocity;
	}

	public Vector2D getAdditionAcceleration() {
		return additionAcceleration;
	}

	public void setAdditionAcceleration(Vector2D additionAcceleration) {
		this.additionAcceleration = additionAcceleration;
	}

}

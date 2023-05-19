package gameObject;

import config.Config;
import input.InputManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import utils.Collider2D;
import utils.Time;
import utils.Vector2D;

public abstract class PhysicsObject extends GameObject {

	protected Collider2D collider;
	protected double mass;
	protected Vector2D velocity;
	protected Vector2D decayableVelocity;
	protected Vector2D acceleration;
	protected boolean isKinematic;
	protected boolean isGrounded;
	protected boolean bouncable;

	public PhysicsObject(Collider2D collider) {
		super();
		this.collider = collider;
		this.velocity = new Vector2D();
		this.decayableVelocity = new Vector2D();
		this.acceleration = new Vector2D();
		this.isKinematic = true;
		this.bouncable = false;
		this.mass = 1;
	}

	public PhysicsObject(Collider2D collider, Vector2D position) {
		super(position);
		this.collider = collider;
		this.velocity = new Vector2D();
		this.decayableVelocity = new Vector2D();
		this.acceleration = new Vector2D();
		this.isKinematic = true;
		this.bouncable = false;
		this.mass = 1;
	}

	public PhysicsObject(Collider2D collider, Vector2D position, double mass) {
		super(position);
		this.collider = collider;
		this.velocity = new Vector2D();
		this.decayableVelocity = new Vector2D();
		this.acceleration = new Vector2D();
		this.isKinematic = true;
		this.bouncable = false;
		this.mass = mass;
	}

	public Vector2D calculateDeltaVelocity() {
		return Vector2D.multiply(this.acceleration, Time.getDeltaTimeSecond());
	}

	public Vector2D calculateDeltaPosition() {
		return Vector2D.multiply(Vector2D.add(this.velocity, this.decayableVelocity), Time.getDeltaTimeSecond());
	}

	public void decayVelocity() {
		this.decayableVelocity.multiply(1 / (Math.pow(1.001, mass)));
		if (this.decayableVelocity.getSize() < 1) {
			this.decayableVelocity = new Vector2D();
		}
	}

	public void gravitate(double gravity) {
		if (!isGrounded) {
			this.acceleration.setY(gravity);
		}
	}

	@Override
	public void translate(Vector2D vector) {
		this.position.add(vector);
		this.collider.translate(vector);
	}

	public void addImpulse(Vector2D impulse) {
		this.velocity.add(Vector2D.multiply(impulse, 1 / this.mass));
	}

	public boolean checkCollide(PhysicsObject other) {
		return this.collider.collideWith(other.collider);
	}
	
	@Override
	public void setPosition(Vector2D position) {
		this.translate(new Vector2D(this.position, position));
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

	public boolean isGrounded() {
		return this.isGrounded;
	}

	public void setGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
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

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}



}

package rocket;

import gameObject.Earthling;
import gameObject.PhysicsObject;
import javafx.scene.canvas.GraphicsContext;
import logic.Collider2D;
import utils.Resource;
import utils.Vector2D;

public abstract class Rocket extends PhysicsObject {

	protected double radius;
	protected double explosionRadius;	
	protected int damage;
	protected int pushPower;
	protected Earthling owner;

	public Rocket(Earthling owner, Vector2D position, Vector2D velocity, Collider2D collider) {
		super(collider, position);
		this.owner = owner;
		this.velocity = velocity;
		this.radius = 16;
		this.explosionRadius = 32;
		this.mass = 1;
	}

	public abstract void triggerCollide();

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(Resource.rocket, this.position.getX() - this.radius / 2, this.position.getY() - this.radius / 2,
				this.radius, this.radius);
	}

	public Earthling getOwner() {
		return owner;
	}

	public void setOwner(Earthling owner) {
		this.owner = owner;
	}
}

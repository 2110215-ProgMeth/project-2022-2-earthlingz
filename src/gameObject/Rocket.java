package gameObject;

import input.InputManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import utils.BoxCollider2D;
import utils.CircleCollider2D;
import utils.Resource;
import utils.Vector2D;

public class Rocket extends PhysicsObject {

	private double radius;

	public Rocket(Vector2D position) {
		super(new CircleCollider2D(position, 16), position);
		this.radius = 16;
	}

	public Rocket(Vector2D position, double mass) {
		super(new CircleCollider2D(position, 16), position, mass);
		this.radius = 16;
	}
	
	public Rocket(Vector2D position, double mass, Vector2D velocity) {
		super(new CircleCollider2D(position, 16), position, mass);
		this.radius = 16;
		this.velocity = velocity;
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(Resource.rocket, this.position.getX() - this.radius / 2,
				this.position.getY() - this.radius / 2, this.radius, this.radius);
	}
}

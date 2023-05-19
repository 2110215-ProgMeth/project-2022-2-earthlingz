package gameObject;

import input.InputManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import utils.BoxCollider2D;
import utils.CircleCollider2D;
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

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}

	public void update() {
		if (InputManager.getKeyPressed(KeyCode.W)) {
		}
		if (InputManager.getKeyPressed(KeyCode.A)) {

		}
		if (InputManager.getKeyPressed(KeyCode.D)) {
			this.velocity.add(position);
		}
		if (InputManager.isLeftClickTriggered()) {
			this.setPosition(new Vector2D(InputManager.mouseX, InputManager.mouseY));
		}
	}
}

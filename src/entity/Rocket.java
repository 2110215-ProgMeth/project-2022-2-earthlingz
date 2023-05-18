package entity;

import input.InputManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.Gravitatable;
import utils.BoxCollider2D;
import utils.Vector2D;

public class Rocket extends PhysicsEntity implements Gravitatable {

	private boolean isGrounded;
	private BoxCollider2D groundCollider;
	private double speed;
	private boolean isWalking;

	public Rocket() {
		super();
	}

	public Rocket(Vector2D position) {
		super(position);
	}

	public Rocket(Vector2D position, double mass, double speed) {
		super(position, mass);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void gravitate(double gravity) {
		if (!isGrounded) {
			this.additionAcceleration.add(new Vector2D(0, gravity * 0.5));
		}
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

	@Override
	public boolean isGrounded() {
		return this.isGrounded;
	}

	public void setGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
	}

}

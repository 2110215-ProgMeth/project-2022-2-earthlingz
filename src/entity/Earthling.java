package entity;

import input.InputManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.Gravitatable;
import utils.BoxCollider2D;
import utils.Vector2D;

public class Earthling extends PhysicsEntity implements Gravitatable {

	private boolean isGrounded;
	private BoxCollider2D groundCollider;

	public Earthling() {
		super();
	}

	public Earthling(Vector2D position) {
		super(position);
	}

	public Earthling(Vector2D position, double mass) {
		super(position, mass);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void gravitate(double gravity) {
		if (!isGrounded) {
			this.velocity.add(new Vector2D(0, gravity));
		}
	}

	public void update() {
		if (InputManager.getKeyPressed(KeyCode.W)) {
		}
		if (InputManager.getKeyPressed(KeyCode.A)) {
		} else if (InputManager.getKeyPressed(KeyCode.D)) {
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

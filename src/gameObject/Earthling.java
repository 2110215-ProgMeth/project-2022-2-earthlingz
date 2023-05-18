package gameObject;

import input.InputManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import logic.Gravitatable;
import utils.BoxCollider2D;
import utils.Resource;
import utils.Vector2D;

public class Earthling extends PhysicsObject implements Gravitatable {

	private String name;
	private boolean isGrounded;
	private BoxCollider2D groundCollider;
	private double width;
	private double height;
	private double speed;
	private double jumpPower;
	private boolean isWalking;

	public Earthling(Vector2D position, String name) {
		super(new BoxCollider2D(position, 32, 32), position);
		this.name = name;
		this.width = 32;
		this.height = 32;
	}

	public Earthling(Vector2D position, String name, double mass, double speed) {
		super(new BoxCollider2D(position, 32, 32), position, mass);
		this.name = name;
		this.width = 32;
		this.height = 32;
		this.speed = speed;
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(Resource.earthlingIdle, this.position.getX() - this.width / 2,
				this.position.getY() - this.height / 2, this.width, this.height);

	}

	@Override
	public void gravitate(double gravity) {
		if (!isGrounded) {
			this.acceleration.add(new Vector2D(0, gravity * 0.5));
		}
	}

	public void update() {
		this.velocity = new Vector2D(0, 0);
		if (InputManager.getKeyPressed(KeyCode.A)) {
			this.velocity = new Vector2D(-speed, 0);
			System.out.println(this.name + "pressA");
		}
		if (InputManager.getKeyPressed(KeyCode.D)) {
			this.velocity = new Vector2D(speed, 0);
		}
		if (InputManager.getKeyPressed(KeyCode.SPACE)) {
			this.addImpulse(new Vector2D(0, -jumpPower));
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

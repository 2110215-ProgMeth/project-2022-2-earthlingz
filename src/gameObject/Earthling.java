package gameObject;

import input.InputManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import utils.BoxCollider2D;
import utils.Resource;
import utils.Vector2D;

public class Earthling extends PhysicsObject {

	private String name;
	private BoxCollider2D groundCollider;
	private double width;
	private double height;
	private double speed;
	private double jumpPower;
	private boolean isWalking;
	private boolean isJumping;

	public Earthling(Vector2D position, String name) {
		super(new BoxCollider2D(position, 32, 32), position);
		this.groundCollider = new BoxCollider2D(Vector2D.add(this.position, new Vector2D(0, 16)), 32, 1);
		this.name = name;
		this.width = 32;
		this.height = 32;
	}

	public Earthling(Vector2D position, String name, double mass, double speed, double jumpPower) {
		super(new BoxCollider2D(position, 32, 32), position, mass);
		this.groundCollider = new BoxCollider2D(Vector2D.add(this.position, new Vector2D(0, 16)), 30, 1);
		this.name = name;
		this.width = 32;
		this.height = 32;
		this.speed = speed;
		this.jumpPower = jumpPower;
	}
	
	@Override
	public void translate(Vector2D vector) {
		this.position.add(vector);
		this.collider.translate(vector);
		this.groundCollider.translate(vector);
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(Resource.earthlingIdle, this.position.getX() - this.width / 2,
				this.position.getY() - this.height / 2, this.width, this.height);

	}

	public void updateState() {
		this.velocity.setX(0);
		this.isWalking = false;
		if (InputManager.getKeyPressed(KeyCode.A)) {
			this.velocity.setX(-speed);
			this.isWalking = true;
		} else if (InputManager.getKeyPressed(KeyCode.D)) {
			this.velocity.setX(speed);
			this.isWalking = true;
		}
		if (InputManager.getKeyPressed(KeyCode.W)) {
			if (this.isGrounded && !this.isJumping) {
				this.velocity.setY(-jumpPower);
				this.isJumping = true;
			}
		}
		this.isGrounded = false;
		if (InputManager.isLeftClickTriggered()) {
			this.setPosition(new Vector2D(InputManager.mouseX, InputManager.mouseY));
			this.acceleration = new Vector2D();
			this.velocity = new Vector2D();
			System.out.println("click"+new Vector2D(InputManager.mouseX, InputManager.mouseY));
		}
	}

	public void collideGround() {
		this.isGrounded = true;
		this.isJumping = false;
		if(this.velocity.getY() > 0) {
			this.velocity.setY(0);
		}
		this.acceleration.setY(0);
	}
	
	@Override
	public void setPosition(Vector2D position) {
		this.translate(new Vector2D(this.position, position));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BoxCollider2D getGroundCollider() {
		return groundCollider;
	}

	public void setGroundCollider(BoxCollider2D groundCollider) {
		this.groundCollider = groundCollider;
	}

}

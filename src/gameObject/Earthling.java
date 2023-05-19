package gameObject;

import config.Config;
import input.InputManager;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.GameplayManager;
import rocket.NormalRocket;
import scene.SceneManager;
import utils.BoxCollider2D;
import utils.Resource;
import utils.Vector2D;

public class Earthling extends PhysicsObject {

	private String name;
	private int health;
	private boolean isPlayer;
	private double width;
	private double height;
	private BoxCollider2D groundCollider;
	private double speed;
	private double jumpPower;
	private double firePower;
	private boolean isWalking;
	private boolean isJumping;

	public Earthling(Vector2D position, boolean isPlayable) {
		super(new BoxCollider2D(position, Config.earthlingHitboxWidth, Config.earthlingHitboxHeight), position,
				Config.earthlingMass);
		this.z = 10;
		this.name = "Earthling";
		this.health = 100;
		this.isPlayer = isPlayable;
		this.width = Config.earthlingWidth;
		this.height = Config.earthlingHeight;
		this.groundCollider = new BoxCollider2D(
				Vector2D.add(this.position, new Vector2D(0, Config.earthlingHeight / 2)),
				Config.earthlingHitboxWidth - 2, 1);
		this.speed = Config.earthlingSpeed;
		this.jumpPower = Config.earthlingJumpPower;
		this.firePower = Config.earthlingFirePower;
		this.isWalking = false;
		this.isJumping = false;
	}

	public Earthling(Vector2D position, String name, boolean isPlayable) {
		super(new BoxCollider2D(position, Config.earthlingHitboxWidth, Config.earthlingHitboxHeight), position,
				Config.earthlingMass);
		this.z = 10;
		this.name = name;
		this.health = 100;
		this.isPlayer = isPlayable;
		this.width = Config.earthlingWidth;
		this.height = Config.earthlingHeight;
		this.groundCollider = new BoxCollider2D(
				Vector2D.add(this.position, new Vector2D(0, Config.earthlingHeight / 2)),
				Config.earthlingHitboxWidth - 2, 1);
		this.speed = Config.earthlingSpeed;
		this.jumpPower = Config.earthlingJumpPower;
		this.firePower = Config.earthlingFirePower;
		this.isWalking = false;
		this.isJumping = false;
	}

	public Earthling(Vector2D position, String name, boolean isPlayable, double mass, double speed, double jumpPower,
			double firePower) {
		super(new BoxCollider2D(position, Config.earthlingHitboxWidth, Config.earthlingHitboxHeight), position, mass);
		this.z = 10;
		this.name = name;
		this.health = 100;
		this.isPlayer = isPlayable;
		this.width = 32;
		this.height = 32;
		this.groundCollider = new BoxCollider2D(
				Vector2D.add(this.position, new Vector2D(0, Config.earthlingHeight / 2)),
				Config.earthlingHitboxWidth - 2, 1);
		this.speed = speed;
		this.jumpPower = jumpPower;
		this.firePower = firePower;
		this.isWalking = false;
		this.isJumping = false;
	}

	public void shootRocket() {
//		this.triggerShootRocket = true;
		Vector2D mousePosition = new Vector2D(InputManager.mouseX, InputManager.mouseY);
		Vector2D pointingDirection = new Vector2D(this.getPosition(), mousePosition).getDirectionalVector();
		BoxCollider2D box = (BoxCollider2D) this.collider;
		Vector2D startPosition = Vector2D.add(this.getPosition(),
				Vector2D.multiply(pointingDirection, new Vector2D(box.getWidth(), box.getHeight()).getSize()));
		Vector2D rocketVelocity = Vector2D.multiply(pointingDirection, this.firePower);

		Platform.runLater(() -> GameplayManager.getInstance().addNewObject(new NormalRocket(this, startPosition, rocketVelocity)));
//		earthling.setTriggerShootRocket(false);
	}

	public void recieveDamage(int damage) {
		this.health -= damage;
		System.out.println(this.health);
		if (this.health <= 0) {
			this.destroyed = true;
		}
	}

	@Override
	public void translate(Vector2D vector) {
		this.position.add(vector);
		this.collider.translate(vector);
		this.groundCollider.translate(vector);
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.translate(this.position.getX(), this.position.getY());

		gc.drawImage(Resource.earthlingIdle, -this.width / 2, -this.height / 2, this.width, this.height);

		// Calculate the angle between the object and the mouse position
		if (this.isPlayer) {
			double angle = Math.atan2(InputManager.mouseY - this.position.getY(),
					InputManager.mouseX - this.position.getX());
			gc.rotate(Math.toDegrees(angle));
			gc.drawImage(Resource.bazooka, -20, -10, 40, 20);

			gc.rotate(-Math.toDegrees(angle));
		}

		gc.translate(-this.position.getX(), -this.position.getY());

	}

	public void updateState() {
		if (this.isPlayer) {
			this.velocity.setX(0);
			this.isWalking = false;
			if (InputManager.getKeyPressed(KeyCode.A)) {
				this.velocity.setX(-speed);
				this.isWalking = true;
			} else if (InputManager.getKeyPressed(KeyCode.D)) {
				this.velocity.setX(speed);
				this.isWalking = true;
			}
			if (InputManager.getKeyPressed(KeyCode.SPACE)) {
				if (this.isGrounded && !this.isJumping) {
					this.velocity.setY(-jumpPower);
					this.isJumping = true;
				}
			}
			this.isGrounded = false;
			if (InputManager.isLeftClickTriggered()) {
				this.shootRocket();
//				this.setPosition(new Vector2D(InputManager.mouseX, InputManager.mouseY));
//				this.acceleration = new Vector2D();
//				this.velocity = new Vector2D();
//				System.out.println("click" + new Vector2D(InputManager.mouseX, InputManager.mouseY));
			}
		} else {
			this.velocity.setX(0);
			this.isGrounded = false;
		}

	}

	public void collideGround() {
		this.isGrounded = true;
		this.isJumping = false;
		if (this.velocity.getY() > 0) {
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}

	public BoxCollider2D getGroundCollider() {
		return groundCollider;
	}

	public void setGroundCollider(BoxCollider2D groundCollider) {
		this.groundCollider = groundCollider;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getJumpPower() {
		return jumpPower;
	}

	public void setJumpPower(double jumpPower) {
		this.jumpPower = jumpPower;
	}

	public double getFirePower() {
		return firePower;
	}

	public void setFirePower(double firePower) {
		this.firePower = firePower;
	}

}

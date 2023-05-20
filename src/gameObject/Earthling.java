package gameObject;

import config.Config;
import input.InputManager;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.BoxCollider2D;
import logic.GameplayManager;
import rocket.NormalRocket;
import rocket.PushRocket;
import rocket.VerticalRocket;
import utils.Resource;
import utils.Time;
import utils.Vector2D;

public class Earthling extends PhysicsObject {

	public static enum RocketType {
		NormalRocket, VerticalRocket, PushRocket
	}

	private int team;
	private String name;
	private int health;
	private boolean isPlayer;
	private double width;
	private double height;
	private BoxCollider2D groundCollider;
	private double speed;
	private double jumpPower;
	private double maxFirePower;
	private boolean isCharging;
	private double currentChargeRate;
	private double chargeDuration;
	private boolean isFacingRight;
	private boolean isWalking;
	private boolean isJumping;

	public Earthling(Vector2D position, int team, boolean isPlayable) {
		super(new BoxCollider2D(position, Config.earthlingHitboxWidth, Config.earthlingHitboxHeight), position,
				Config.earthlingMass);
		this.z = 10;
		this.name = "Earthling";
		this.health = 100;
		this.team = team;
		this.isPlayer = isPlayer;
		this.width = Config.earthlingWidth;
		this.height = Config.earthlingHeight;
		this.groundCollider = new BoxCollider2D(
				Vector2D.add(this.position, new Vector2D(0, Config.earthlingHeight / 2)),
				Config.earthlingHitboxWidth - 2, 1);
		this.speed = Config.earthlingSpeed;
		this.jumpPower = Config.earthlingJumpPower;
		this.maxFirePower = Config.earthlingMaxFirePower;
		this.isFacingRight = true;
		this.isJumping = false;
	}

	public Earthling(Vector2D position, int team, boolean isPlayable, String name) {
		this(position, team, isPlayable);
		this.name = name;
	}

	public Earthling(Vector2D position, int team, boolean isPlayable, String name, double mass, double speed,
			double jumpPower, double maxFirePower) {
		this(position, team, isPlayable, name);
		this.mass = mass;
		this.speed = speed;
		this.jumpPower = jumpPower;
		this.maxFirePower = maxFirePower;
	}

	@Override
	public void translate(Vector2D vector) {
		this.position.add(vector);
		this.collider.translate(vector);
		this.groundCollider.translate(vector);
	}

	@Override
	public void render(GraphicsContext gc) {
		int direction = (this.isFacingRight ? -1 : 1);

		gc.translate(this.position.getX(), this.position.getY());

		gc.drawImage(Resource.earthlingIdle, direction * this.width / 2, -this.height / 2, -direction * this.width,
				this.height);

		// Calculate the angle between the object and the mouse position
		if (this.isPlayer) {

			double angle = Math.atan2(InputManager.mouseY - this.position.getY(),
					InputManager.mouseX - this.position.getX());
			gc.rotate(Math.toDegrees(angle));
			gc.drawImage(Resource.bazooka, -30, -10, 60, 20);

			gc.rotate(-Math.toDegrees(angle));
		}

		gc.translate(-this.position.getX(), -this.position.getY());

	}

	public void shootRocket(double power) {
		Vector2D mousePosition = new Vector2D(InputManager.mouseX, InputManager.mouseY);
		Vector2D pointingDirection = new Vector2D(this.getPosition(), mousePosition).getDirectionalVector();
		BoxCollider2D box = (BoxCollider2D) this.collider;
		Vector2D startPosition = Vector2D.add(this.getPosition(),
				Vector2D.multiply(pointingDirection, new Vector2D(box.getWidth(), box.getHeight()).getSize()));
		Vector2D rocketVelocity = Vector2D.multiply(pointingDirection, power);
		Platform.runLater(() -> GameplayManager.getInstance()
				.addNewObject(new NormalRocket(this, startPosition, rocketVelocity)));
//		Platform.runLater(() -> GameplayManager.getInstance()
//				.addNewObject(new VerticalRocket(this, startPosition, rocketVelocity)));
//		Platform.runLater(() -> GameplayManager.getInstance()
//		.addNewObject(new PushRocket(this, startPosition, rocketVelocity)));
	}

	public void recieveDamage(int damage) {
		this.health -= damage;
		System.out.println(this.team + " " + this.health);
		if (this.health <= 0) {
			this.destroyed = true;
			Platform.runLater(() -> GameplayManager.getInstance()
					.addNewObject(new Corpse(this)));
		}
	}

	public void updateState() {
		if (this.isPlayer) {
			this.velocity.setX(0);
			this.isWalking = false;
			if (InputManager.getKeyPressed(KeyCode.A)) {
				this.velocity.setX(-speed);
				this.isWalking = true;
				this.isFacingRight = false;
			} else if (InputManager.getKeyPressed(KeyCode.D)) {
				this.velocity.setX(speed);
				this.isWalking = true;
				this.isFacingRight = true;
			}
			if (InputManager.getKeyPressed(KeyCode.SPACE)) {
				if (this.isGrounded && !this.isJumping) {
					this.velocity.setY(-jumpPower);
					this.isJumping = true;
				}
			}
			this.isGrounded = false;
			if (InputManager.isLeftClickTriggered()) {
				this.chargeDuration = 0;
				this.isCharging = true;
			}
			if (this.isCharging) {
				this.chargeDuration += Time.getDeltaTimeSecond();
				if (InputManager.isMouseLeftDown()) {
					this.currentChargeRate = (Math.abs(Math.sin(this.chargeDuration / (4 * Math.PI))) * 90 + 10);
					System.out.println(this.currentChargeRate + "%");
				} else if (this.chargeDuration > 0.5) {
					this.shootRocket(currentChargeRate / 100 * this.maxFirePower);
					this.isCharging = false;
				} else {
					this.isCharging = false;
				}
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

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
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

	public double getMaxFirePower() {
		return maxFirePower;
	}

	public void setMaxFirePower(double maxFirePower) {
		this.maxFirePower = maxFirePower;
	}

	public boolean isFacingRight() {
		return isFacingRight;
	}

	public void setFacingRight(boolean isFacingRight) {
		this.isFacingRight = isFacingRight;
	}
}

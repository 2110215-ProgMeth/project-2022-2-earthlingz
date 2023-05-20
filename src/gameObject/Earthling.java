package gameObject;

import config.Config;
import input.InputManager;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import logic.BoxCollider2D;
import logic.GameplayManager;
import rocket.NormalRocket;
import rocket.PushRocket;
import rocket.Rocket;
import rocket.VerticalRocket;
import utils.Resource;
import utils.Time;
import utils.Vector2D;

public class Earthling extends PhysicsObject {

	public static enum RocketType {
		NormalRocket, VerticalRocket, PushRocket
	}

	private int team;
	private Image sprite_idle;
	private Image sprite_dead;
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
	private RocketType rocketType;
	private MediaPlayer chargeSound;
	private boolean isFacingRight;
	private boolean isWalking;
	private boolean isJumping;

	public Earthling(Vector2D position, int team, boolean isPlayer) {
		super(new BoxCollider2D(position, Config.earthlingHitboxWidth, Config.earthlingHitboxHeight), position,
				Config.earthlingMass);
		this.z = 10;
		this.name = "Earthling";
		this.health = 100;
		this.team = team;
		if (team == 0) {
			this.sprite_idle = Resource.earthling_idle_green;
			this.sprite_dead = Resource.earthling_dead_green;
		} else {
			this.sprite_idle = Resource.earthling_idle_red;
			this.sprite_dead = Resource.earthling_dead_red;
		}
		this.isPlayer = isPlayer;
		this.width = Config.earthlingWidth;
		this.height = Config.earthlingHeight;
		this.groundCollider = new BoxCollider2D(
				Vector2D.add(this.position, new Vector2D(0, Config.earthlingHeight / 2)),
				Config.earthlingHitboxWidth - 2, 1);
		this.speed = Config.earthlingSpeed;
		this.jumpPower = Config.earthlingJumpPower;
		this.maxFirePower = Config.earthlingMaxFirePower;
		this.rocketType = RocketType.NormalRocket;
		this.chargeSound = new MediaPlayer(Resource.sound_earthlingCharge);
		this.isFacingRight = true;
		this.isJumping = false;
	}

	public Earthling(Vector2D position, int team, boolean isPlayer, String name) {
		this(position, team, isPlayer);
		this.name = name;
	}

	public Earthling(Vector2D position, int team, boolean isPlayer, String name, double mass, double speed,
			double jumpPower, double maxFirePower) {
		this(position, team, isPlayer, name);
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

		gc.drawImage(this.sprite_idle, direction * this.width / 2, -this.height / 2, -direction * this.width,
				this.height);

		// Calculate the angle between the object and the mouse position
		if (this.isPlayer) {
			double bazookaWidth = Config.bazookaWidth;
			double bazookaHeight = Config.bazookaHeight;

			double angle = Math.atan2(InputManager.mouseY - this.position.getY(),
					InputManager.mouseX - this.position.getX());
			gc.rotate(Math.toDegrees(angle));
			gc.drawImage(Resource.sprite_bazooka, -bazookaWidth / 2, -bazookaHeight / 2, bazookaWidth, bazookaHeight);

			gc.rotate(-Math.toDegrees(angle));
		}
		
		// Draw HP
        gc.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, Config.earthlingHpTextSize));
        gc.setTextAlign(TextAlignment.CENTER);
        switch(this.team) {
		case 0:
	        gc.setFill(Color.GREEN);
	        gc.fillText(this.health+"", 0,-this.height+10);
			break;
		case 1:
	        gc.setFill(Color.RED);
	        gc.fillText(this.health+"", 0,-this.height+10);
			break;
		default:
	        gc.setFill(Color.GREEN);
	        gc.fillText(this.health+"", 0,-this.height+10);
			break;
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
		Rocket rocket;
		switch (this.rocketType) {
		case NormalRocket:
			rocket = new NormalRocket(this, startPosition, rocketVelocity);
			break;
		case VerticalRocket:
			rocket = new VerticalRocket(this, startPosition, rocketVelocity);
			break;
		case PushRocket:
			rocket = new PushRocket(this, startPosition, rocketVelocity);
			break;
		default:
			rocket = new NormalRocket(this, startPosition, rocketVelocity);
			break;
		}
		Platform.runLater(() -> GameplayManager.getInstance().addNewObject(rocket));
		GameplayManager.getInstance().onPlayerShootRocket(this.rocketType);
	}

	public void recieveDamage(int damage) {
		this.health -= damage;
		System.out.println(this.team + " " + this.health);
		if (this.health <= 0) {
			this.destroy();
		} else {
			Resource.playSound(Resource.sound_earthlingHurt);
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		Platform.runLater(() -> GameplayManager.getInstance().addNewObject(new Corpse(this, this.sprite_dead)));
		Resource.playSound(Resource.sound_earthlingDead);
		this.chargeSound.stop();
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
				this.chargeSound.setRate(0.6);
				this.chargeSound.play();
				this.chargeSound.setOnEndOfMedia(() -> {
					this.chargeSound.seek(Duration.ZERO);
					this.chargeSound.setRate(this.currentChargeRate / 100 * 1.2 + 0.6);
				});
			}
			if (this.isCharging) {
				this.chargeDuration += Time.getDeltaTimeSecond();
				if (InputManager.isMouseLeftDown()) {
					this.currentChargeRate = (Math.abs(Math.sin(this.chargeDuration / (4 * Math.PI))) * 90 + 10);
					System.out.println(this.currentChargeRate + "%");
				} else if (this.chargeDuration > 0.5) {
					this.shootRocket(currentChargeRate / 100 * this.maxFirePower);
					this.isCharging = false;
					this.currentChargeRate = 0;
					this.chargeSound.stop();
				} else {
					this.isCharging = false;
					this.currentChargeRate = 0;
					this.chargeSound.stop();
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

	public RocketType getRocketType() {
		return rocketType;
	}

	public void setRocketType(RocketType rocketType) {
		this.rocketType = rocketType;
	}

	public double getCurrentChargeRate() {
		return currentChargeRate;
	}

	public MediaPlayer getChargeSound() {
		return chargeSound;
	}

	public boolean isFacingRight() {
		return isFacingRight;
	}

	public void setFacingRight(boolean isFacingRight) {
		this.isFacingRight = isFacingRight;
	}
}

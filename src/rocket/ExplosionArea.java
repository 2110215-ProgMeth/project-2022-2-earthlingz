package rocket;

import gameObject.Earthling;
import gameObject.FloorBox;
import gameObject.PhysicsObject;
import input.InputManager;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import logic.BoxCollider2D;
import logic.Collider2D;
import logic.GameplayManager;
import utils.Resource;
import utils.Vector2D;

public class ExplosionArea extends PhysicsObject {

	public enum ExplosionType {
		Circle, Rectangle
	}

	private ExplosionType type;
	private int explosionDamage;
	private int pushPower;

	public ExplosionArea(Collider2D collider, ExplosionType type, Vector2D position, int explosionDamage,
			int pushPower) {
		super(collider, position);
		this.z = 10;
		this.isKinematic = false;
		this.type = type;
		this.explosionDamage = explosionDamage;
		this.pushPower = pushPower;
	}

	public void explode(PhysicsObject physicsObject) {
		if (physicsObject instanceof Earthling) {
			Earthling earthling = (Earthling) physicsObject;
			Platform.runLater(() -> earthling.recieveDamage(this.explosionDamage));
		} else if (physicsObject instanceof FloorBox) {
			FloorBox floorBox = (FloorBox) physicsObject;
			floorBox.setDestroyed(true);
		}
		if (physicsObject.isKinematic()) {
			Vector2D direction = new Vector2D(this.getPosition(), physicsObject.getPosition()).getDirectionalVector();
			physicsObject.addImpulse(Vector2D.multiply(direction, this.pushPower), true);
		}
		this.setDestroyed(true);
	}

	public void updateState() {
		this.setDestroyed(true);
	}

	@Override
	public void render(GraphicsContext gc) {
		Vector2D center = this.getCollider().getCenter();
		if (this.type == ExplosionType.Circle) {

			gc.drawImage(Resource.rocket, center.getX() - 128 / 2, center.getY() - 128 / 2, 128, 128);
		}

	}

}

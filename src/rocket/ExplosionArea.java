package rocket;

import gameObject.Earthling;
import gameObject.FloorBox;
import gameObject.PhysicsObject;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import logic.GameplayManager;
import utils.Collider2D;
import utils.Resource;
import utils.Vector2D;

public class ExplosionArea extends PhysicsObject {

	private int explosionDamage;

	public ExplosionArea(Collider2D collider, Vector2D position, int explosionDamage) {
		super(collider, position);
		this.z = 10;
		this.explosionDamage = explosionDamage;
		this.isKinematic = false;
	}

	public void explode(PhysicsObject physicsObject) {
		if (physicsObject instanceof Earthling) {
			Earthling earthling = (Earthling) physicsObject;
			Platform.runLater(() -> earthling.recieveDamage(this.explosionDamage));
		} else if (physicsObject instanceof FloorBox) {
			FloorBox floorBox = (FloorBox) physicsObject;
			floorBox.setDestroyed(true);
		}
		this.setDestroyed(true);
	}
	
	public void updateState() {
		this.setDestroyed(true);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(Resource.rocket, this.position.getX() - 128 / 2,
				this.position.getY() - 128 / 2, 128, 128);
	}

}

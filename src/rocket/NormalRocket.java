package rocket;

import config.Config;
import gameObject.Earthling;
import gameObject.FloorBox;
import gameObject.PhysicsObject;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import logic.BoxCollider2D;
import logic.CircleCollider2D;
import logic.GameplayManager;
import rocket.ExplosionArea.ExplosionType;
import utils.Resource;
import utils.Vector2D;

public class NormalRocket extends Rocket {

	private int damage;
	private int pushPower;

	public NormalRocket(Earthling owner, Vector2D position, Vector2D velocity) {
		super(owner, position, velocity);
		this.owner = owner;
		this.radius = 16;
		this.explosionRadius = 64;
		this.velocity = velocity;
		this.damage = Config.normalRocketDamage;
		this.pushPower = 10;
	}

	@Override
	public void triggerCollide() {
		Platform.runLater(() -> GameplayManager.getInstance()
				.addNewObject(new ExplosionArea(new CircleCollider2D(this.getPosition(), this.explosionRadius),
						ExplosionType.Circle, this.getPosition(), this.damage, this.pushPower)));
		this.setDestroyed(true);
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(Resource.rocket, this.position.getX() - this.radius / 2, this.position.getY() - this.radius / 2,
				this.radius, this.radius);
	}

}

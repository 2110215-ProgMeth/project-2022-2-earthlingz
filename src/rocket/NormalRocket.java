package rocket;

import config.Config;
import gameObject.Earthling;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import logic.CircleCollider2D;
import logic.GameplayManager;
import rocket.ExplosionArea.ExplosionType;
import utils.Resource;
import utils.Vector2D;

public class NormalRocket extends Rocket {

	public NormalRocket(Earthling owner, Vector2D position, Vector2D velocity) {
		super(owner, position, velocity, new CircleCollider2D(position,Config.normalRocketRadius));
		this.radius = Config.normalRocketRadius;
		this.explosionRadius = Config.normalRocketExplosionRadius;
		this.damage = Config.normalRocketDamage;
		this.pushPower = Config.normalRocketPushPower;
	}

	@Override
	public void triggerCollide() {
		Platform.runLater(() -> GameplayManager.getInstance()
				.addNewObject(new ExplosionArea(new CircleCollider2D(this.getPosition(), this.explosionRadius),
						ExplosionType.Circle, this.getPosition(),true, this.damage, this.pushPower)));
		this.setDestroyed(true);
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(Resource.sprite_rocket, this.position.getX() - this.radius / 2, this.position.getY() - this.radius / 2,
				this.radius, this.radius);
	}

}

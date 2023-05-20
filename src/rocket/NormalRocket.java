package rocket;

import config.Config;
import gameObject.Earthling;
import javafx.application.Platform;
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
		this.sprite = Resource.sprite_rocket_normal;
		this.explosionSound = Resource.sound_explosionNormal;
	}

	@Override
	public void triggerCollide() {
		Platform.runLater(() -> GameplayManager.getInstance()
				.addNewObject(new ExplosionArea(new CircleCollider2D(this.getPosition(), this.explosionRadius),
						ExplosionType.Circle, this.getPosition(),true, this.damage, this.pushPower)));
		this.destroy();
	}

}

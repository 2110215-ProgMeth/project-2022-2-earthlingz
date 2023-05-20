package rocket;

import config.Config;
import gameObject.Earthling;
import javafx.application.Platform;
import logic.CircleCollider2D;
import logic.GameplayManager;
import rocket.ExplosionArea.ExplosionType;
import utils.Resource;
import utils.Vector2D;

public class PushRocket extends Rocket {

	public PushRocket(Earthling owner, Vector2D position, Vector2D velocity) {
		super(owner, position, velocity,new CircleCollider2D(position,Config.pushRocketRadius));
		this.radius = Config.pushRocketRadius;
		this.explosionRadius = Config.pushRocketExplosionRadius;
		this.damage = Config.pushRocketDamage;
		this.pushPower = Config.pushRocketPushPower;
		this.sprite = Resource.sprite_rocket_push;
		this.explosionSound = Resource.sound_explosionPush;
	}

	@Override
	public void triggerCollide() {
		Platform.runLater(() -> GameplayManager.getInstance()
				.addNewObject(new ExplosionArea(new CircleCollider2D(this.getPosition(), this.explosionRadius),
						ExplosionType.Circle, this.getPosition(),false, this.damage, this.pushPower)));
		this.destroy();
	}

}

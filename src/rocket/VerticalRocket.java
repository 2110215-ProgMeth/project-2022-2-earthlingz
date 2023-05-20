package rocket;

import config.Config;
import gameObject.Earthling;
import javafx.application.Platform;
import logic.BoxCollider2D;
import logic.CircleCollider2D;
import logic.GameplayManager;
import rocket.ExplosionArea.ExplosionType;
import utils.Resource;
import utils.Vector2D;

public class VerticalRocket extends Rocket {

	public VerticalRocket(Earthling owner, Vector2D position, Vector2D velocity) {
		super(owner, position, velocity,new CircleCollider2D(position,Config.verticalRocketRadius));
		this.radius = Config.verticalRocketRadius;
		this.explosionRadius = Config.verticalRocketExplosionRadius;
		this.damage = Config.verticalRocketDamage;
		this.pushPower = Config.verticalRocketPushPower;
		this.sprite = Resource.sprite_rocket_vertical;
		this.explosionSound = Resource.sound_explosionVertical;
	}

	@Override
	public void triggerCollide() {
		Platform.runLater(() -> GameplayManager.getInstance()
				.addNewObject(new ExplosionArea(new BoxCollider2D(this.getPosition(), this.explosionRadius,Config.screenHeight*1.5),
						ExplosionType.Rectangle, this.getPosition(),true, this.damage, this.pushPower)));
		this.destroy();
	}

}

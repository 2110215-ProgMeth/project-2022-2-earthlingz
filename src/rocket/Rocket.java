package rocket;

import gameObject.Earthling;
import gameObject.PhysicsObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.Collider2D;
import utils.Resource;
import utils.Vector2D;

public abstract class Rocket extends PhysicsObject {

	protected double radius;
	protected double explosionRadius;	
	protected int damage;
	protected int pushPower;
	protected Earthling owner;
	protected Image sprite;
	protected AudioClip explosionSound;

	public Rocket(Earthling owner, Vector2D position, Vector2D velocity, Collider2D collider) {
		super(collider, position);
		this.owner = owner;
		this.velocity = velocity;
		this.radius = 16;
		this.explosionRadius = 32;
		this.mass = 1;
		this.sprite = Resource.sprite_rocket;
		this.explosionSound = Resource.sound_explosionNormal;
	}

	public abstract void triggerCollide();

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(this.sprite, this.position.getX() - this.radius / 2, this.position.getY() - this.radius / 2,
				this.radius, this.radius);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		this.explosionSound.play();
	}

	public Earthling getOwner() {
		return owner;
	}

	public void setOwner(Earthling owner) {
		this.owner = owner;
	}
}

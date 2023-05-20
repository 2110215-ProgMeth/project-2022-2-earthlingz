package gameObject;

import config.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.Resource;
import utils.Vector2D;

public class Corpse extends PhysicsObject {

	private Image sprite;
	private double width;
	private double height;
	private boolean wasFacingRight;

	public Corpse(Earthling earthling,Image sprite) {
		super(earthling.getCollider(), earthling.getPosition());
		this.z = 10;
		this.width = Config.earthlingWidth;
		this.height = Config.earthlingHeight;
		this.sprite = sprite;
		this.wasFacingRight = earthling.isFacingRight();
		this.velocity = new Vector2D(0, -Config.gravity*4);
	}

	@Override
	public void translate(Vector2D vector) {
		this.position.add(vector);
		this.collider.translate(vector);
	}

	@Override
	public void render(GraphicsContext gc) {
		int direction = (this.wasFacingRight ? -1 : 1);

		gc.translate(this.position.getX(), this.position.getY());

		gc.drawImage(this.sprite, direction * this.width / 2, -this.height / 2, -direction * this.width,
				this.height);

		gc.translate(-this.position.getX(), -this.position.getY());
	}

	@Override
	public void setPosition(Vector2D position) {
		this.translate(new Vector2D(this.position, position));
	}
}
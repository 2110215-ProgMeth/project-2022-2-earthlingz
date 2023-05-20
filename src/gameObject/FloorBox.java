package gameObject;

import config.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.BoxCollider2D;
import utils.Vector2D;

public class FloorBox extends PhysicsObject {

	private double width;
	private double height;
	private Image texture;

	public FloorBox(Vector2D position, Image texture) {
		super(new BoxCollider2D(position, Config.floorBoxWidth,Config.floorBoxHeight),position);
		this.z = -5;
		this.width = Config.floorBoxWidth;
		this.height = Config.floorBoxHeight;
		this.isKinematic = false;
		this.texture = texture;
	}	

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(texture, this.position.getX() - this.width / 2,
				this.position.getY() - this.height / 2, this.width, this.height);
	}

}

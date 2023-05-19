package gameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.BoxCollider2D;
import utils.Resource;
import utils.Vector2D;

public class FloorBox extends PhysicsObject {

	private double width;
	private double height;
	private Image texture;

	public FloorBox(Vector2D position, Image texture) {
		super(new BoxCollider2D(position, 32,32),position);
		this.z = -5;
		this.width = 32;
		this.height = 32;
		this.isKinematic = false;
		this.texture = texture;
	}	

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(texture, this.position.getX() - this.width / 2,
				this.position.getY() - this.height / 2, this.width, this.height);
	}

}

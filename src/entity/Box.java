package entity;

import javafx.scene.canvas.GraphicsContext;
import utils.Vector2D;

public class Box extends PhysicsEntity {


	public Box() {
		super();
		this.isKinematic = false;
	}

	public Box(Vector2D position) {
		super(position);
		this.isKinematic = false;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}

	public void update() {
	}


}

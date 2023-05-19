package scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import render.Renderable;
import render.RenderableManager;

public abstract class ScenePane extends StackPane {

	protected Canvas canvas;

	public ScenePane(double width, double height) {
		this.setWidth(width);
		this.setHeight(height);
		canvas = new Canvas(width, height);
		this.getChildren().add(canvas);
		this.setVisible(true);
	}

	public void renderComponent() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.save();
		for (Renderable renderableObject : RenderableManager.getInstance().getRenderableList()) {
			if (renderableObject.isVisible() && !renderableObject.isDestroyed()) {
				renderableObject.render(gc);
			}
		}
	}

	public abstract void updateScene();
}

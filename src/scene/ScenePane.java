package scene;

import input.InputManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import render.Renderable;
import render.RenderableManager;

public abstract class ScenePane extends StackPane {

	private Canvas canvas;

	public ScenePane(double width, double height) {
		this.setWidth(width);
		this.setHeight(height);
		canvas = new Canvas(width, height);
		this.getChildren().add(canvas);
		this.setVisible(true);
		this.addListerner();
	}

	private void addListerner() {
		this.setOnKeyPressed((KeyEvent event) -> {
			InputManager.setKeyPressed(event.getCode(), true);
		});

		this.setOnKeyReleased((KeyEvent event) -> {
			InputManager.setKeyPressed(event.getCode(), false);
		});

		this.setOnMousePressed((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputManager.mouseLeftDown();
		});

		this.setOnMouseReleased((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputManager.mouseLeftRelease();
		});

		this.setOnMouseEntered((MouseEvent event) -> {
			InputManager.mouseOnScreen = true;
		});

		this.setOnMouseExited((MouseEvent event) -> {
			InputManager.mouseOnScreen = false;
		});

		this.setOnMouseMoved((MouseEvent event) -> {
			if (InputManager.mouseOnScreen) {
				InputManager.mouseX = event.getX();
				InputManager.mouseY = event.getY();
			}
		});

		this.setOnMouseDragged((MouseEvent event) -> {
			if (InputManager.mouseOnScreen) {
				InputManager.mouseX = event.getX();
				InputManager.mouseY = event.getY();
			}
		});
	}

	public void renderComponent() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		for (Renderable renderableObject : RenderableManager.getInstance().getRenderableList()) {
			if (renderableObject.isVisible() && !renderableObject.isDestroyed()) {
				renderableObject.render(gc);
			}
		}
	}
	
	public abstract void updateScene();
}

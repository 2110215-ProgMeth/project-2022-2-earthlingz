package render;

import javafx.scene.canvas.GraphicsContext;

public interface Renderable {
	public int getZ();

	public void render(GraphicsContext gc);

	public boolean isDestroyed();

	public boolean isVisible();

}

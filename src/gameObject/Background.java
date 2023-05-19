package gameObject;

import config.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background extends GameObject {

	private double width;
	private double height;
	private Image backgroundImage;

	public Background(Image backgroundImage) {
		super();
		this.width = Config.screenWidth;
		this.height = Config.screenHeight;
		this.z = -1000;
		this.backgroundImage = backgroundImage;
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(backgroundImage, this.position.getX(), this.position.getY(), width, height);
	}

}
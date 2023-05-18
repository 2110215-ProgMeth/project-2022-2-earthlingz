package utils;

import javafx.scene.paint.Color;
import javafx.scene.image.*;

public class LogicUtility {

	public static boolean checkBoxCollideWithCircle(BoxCollider2D box, CircleCollider2D circle) {
		Vector2D displacement = new Vector2D(box.getCenter(), circle.getCenter());
		double distanceX = Math.abs(displacement.getX());
		double distanceY = Math.abs(displacement.getY());
		// If the distance between center point of the circle and the rectangle for each
		// dimension is more than radius, return false.
		if (distanceX > box.getWidth() / 2 + circle.getRadius()) {
			return false;
		}
		if (distanceY > (box.getHeight() / 2 + circle.getRadius())) {
			return false;
		}
		// return true for all area except the corner
		if (distanceX <= (box.getWidth() / 2)) {
			return true;
		}
		if (distanceY <= (box.getHeight() / 2)) {
			return true;
		}
		// Check the collision between circle corner and the rectangle corner
		double cornerDistance = Vector2D.add(displacement, new Vector2D(-box.getWidth() / 2, -box.getHeight() / 2))
				.getSize();
		return cornerDistance <= circle.getRadius();
	}

	public static Image reColor(Image inputImage, Color sourceColor, Color finalColor) {
		int W = (int) inputImage.getWidth();
		int H = (int) inputImage.getHeight();
		WritableImage outputImage = new WritableImage(W, H);
		PixelReader reader = inputImage.getPixelReader();
		PixelWriter writer = outputImage.getPixelWriter();
		float ocR = (float) sourceColor.getRed();
		float ocG = (float) sourceColor.getGreen();
		float ocB = (float) sourceColor.getBlue();
		float ncR = (float) finalColor.getRed();
		float ncG = (float) finalColor.getGreen();
		float ncB = (float) finalColor.getBlue();
		java.awt.Color oldColor = new java.awt.Color(ocR, ocG, ocB);
		java.awt.Color newColor = new java.awt.Color(ncR, ncG, ncB);
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				int argb = reader.getArgb(x, y);
				java.awt.Color pixelColor = new java.awt.Color(argb, true);
				writer.setArgb(x, y, pixelColor.equals(oldColor) ? newColor.getRGB() : pixelColor.getRGB());
			}
		}
		return outputImage;
	}
}

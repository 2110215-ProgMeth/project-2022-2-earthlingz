package utils;

public class LogicUtility {
	public static boolean checkBoxCollideWithCircle(BoxCollider2D box, CircleCollider2D circle) {
		Vector2D displacement = new Vector2D(box.getCenter(), circle.center);
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
}

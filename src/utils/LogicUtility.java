package utils;

import logic.BoxCollider2D;
import logic.CircleCollider2D;
import logic.Collider2D;

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

	public static Vector2D calculatePositionFixer(Vector2D currentPosition, Collider2D collider, Collider2D other) {
		Vector2D deltaPosition = new Vector2D(collider.getCenter(), other.getCenter());
		if (deltaPosition.getSize() < 1) {
			return new Vector2D();
		}
		Vector2D fixedPosition = other.getCenter();
		fixedPosition.add(calculateBackwardVector(currentPosition, deltaPosition, collider));
		fixedPosition.add(calculateBackwardVector(currentPosition, deltaPosition, other));

		if (Math.abs(deltaPosition.getY()) >= Math.abs(deltaPosition.getX())) {
			fixedPosition.setX(currentPosition.getX());
		} else {
			fixedPosition.setY(currentPosition.getY());
		}
		return new Vector2D(currentPosition, fixedPosition);
	}

	private static Vector2D calculateBackwardVector(Vector2D currentPosition, Vector2D deltaPosition,
			Collider2D collider) {
		if (collider instanceof BoxCollider2D) {
			BoxCollider2D box = (BoxCollider2D) collider;
			return new Vector2D(-Math.signum(deltaPosition.getX()) * (box.getWidth() / 2),
					-Math.signum(deltaPosition.getY()) * (box.getHeight() / 2));

		}
		if (collider instanceof CircleCollider2D) {
			CircleCollider2D circle = (CircleCollider2D) collider;
			return Vector2D.multiply(deltaPosition.getDirectionalVector(), -circle.getRadius());
		} else {
			return new Vector2D();
		}
	}

	public static String getTeamName(int teamIndex) {
		switch (teamIndex) {
		case 0:
			return "GREEN";
		case 1:
			return "RED";
		case 2:
			return "BLUE";
		default:
			return "GREEN";
		}
	}

}

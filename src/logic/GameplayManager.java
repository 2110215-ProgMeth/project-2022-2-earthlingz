package logic;

import java.util.ArrayList;
import java.util.List;

import config.Config;
import gameObject.FloorBox;
import gameObject.Background;
import gameObject.Earthling;
import gameObject.GameObject;
import gameObject.PhysicsObject;
import javafx.scene.shape.Box;
import render.RenderableManager;
import utils.BoxCollider2D;
import utils.LogicUtility;
import utils.Resource;
import utils.Time;
import utils.Vector2D;

public class GameplayManager {

	private List<GameObject> gameObjectContainer;
	private List<PhysicsObject> physicsObjectContainer;

	public GameplayManager() {
		this.gameObjectContainer = new ArrayList<GameObject>();
		this.physicsObjectContainer = new ArrayList<PhysicsObject>();

		this.initializeGameplay();

		this.addNewObject(new Background(Resource.background_space));

		this.addNewObject(new Earthling(new Vector2D(), "amo", 10, 10, 30));
		this.addNewObject(new FloorBox(new Vector2D(0, 128)));

		this.addNewObject(new FloorBox(new Vector2D(128, 512)));
		this.addNewObject(new FloorBox(new Vector2D(192, 512)));

		this.addNewObject(new FloorBox(new Vector2D(256, 512)));

//		this.addNewObject(new Earthling(new Vector2D(256, 256), "gus", 10, 10));

		System.out.println(this.physicsObjectContainer);
	}

	protected void initializeGameplay() {
		return;
	}

	protected void addNewObject(GameObject gameObject) {
		this.gameObjectContainer.add(gameObject);
		if (gameObject instanceof PhysicsObject) {
			this.physicsObjectContainer.add((PhysicsObject) gameObject);
		}
		RenderableManager.getInstance().add(gameObject);
	}

	public void updateGameplay() {
		for (PhysicsObject physicsObject : this.physicsObjectContainer) {
			if (physicsObject instanceof Earthling) {
				Earthling earthling = (Earthling) physicsObject;

				earthling.updateState();
				for (PhysicsObject po : this.physicsObjectContainer) {
					if (earthling == po) {
						continue;
					}
					if (earthling.getGroundCollider().collideWith(po.getCollider())) {
						earthling.collideGround();
					}
				}
			}
			if (physicsObject.isKinematic()) {
				physicsObject.gravitate(Config.gravity);
//				System.out.println("p=" + physicsObject.getPosition());
//				System.out.println("v=" + physicsObject.getVelocity());
//				System.out.println("a=" + physicsObject.getAcceleration());
				physicsObject.getVelocity().add(physicsObject.calculateDeltaVelocity());
				Vector2D deltaPosition = physicsObject.calculateDeltaPosition();
				physicsObject.translate(deltaPosition);
				for (PhysicsObject other : this.physicsObjectContainer) {
					if (physicsObject == other) {
						continue;
					}
					if (physicsObject.getCollider().collideWith(other.getCollider())) {
						physicsObject.translate(LogicUtility.calculatePositionFixer(physicsObject.getPosition(),
								deltaPosition, physicsObject.getCollider(), other.getCollider()));
					}
				}
			}
		}

	}
}

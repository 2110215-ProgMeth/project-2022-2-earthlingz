package logic;

import java.util.ArrayList;
import java.util.List;

import config.Config;
import gameObject.FloorBox;
import gameObject.Background;
import gameObject.Earthling;
import gameObject.GameObject;
import gameObject.PhysicsObject;
import javafx.application.Platform;
import map.GameMap;
import render.RenderableManager;
import rocket.ExplosionArea;
import rocket.Rocket;
import scene.GameplayScenePane;
import utils.LogicUtility;
import utils.Vector2D;

public class GameplayManager {

	private static GameplayManager instance = null;
	private GameplayScenePane scene;
	private List<GameObject> gameObjectContainer;
	private List<PhysicsObject> physicsObjectContainer;

	private int teamCount;
	private int currentTeam;
	private int turnCount;
	private int currentTurn;
	private boolean endTurn;
	private List<ArrayList<Earthling>> teamMembersContainer;
	private List<Integer> lastPlayerIndexes;
	private Earthling currentPlayer;

	public GameplayManager(GameplayScenePane scene) {
		this.scene = scene;
		this.gameObjectContainer = new ArrayList<GameObject>();
		this.physicsObjectContainer = new ArrayList<PhysicsObject>();

		this.initializeGameplay();
	}

	public static void initializeGameplayManager(GameplayScenePane scene) {
		GameplayManager.instance = new GameplayManager(scene);
	}

	public static GameplayManager getInstance() {
		return instance;
	}

	private void initializeGameplay() {
		this.turnCount = 0;
		this.currentTurn = 0;
		this.teamCount = Config.teamAmount;
		this.currentTeam = 0;

		this.teamMembersContainer = new ArrayList<ArrayList<Earthling>>();
		this.lastPlayerIndexes = new ArrayList<Integer>();
		for (int i = 0; i < teamCount; i++) {
			this.teamMembersContainer.add(new ArrayList<Earthling>());
			this.lastPlayerIndexes.add(0);
		}

		this.initializeMap(Config.selectedMap);

		this.setCurrentPlayer(this.teamMembersContainer.get(this.currentTeam).get(0));

		System.out.println(this.teamMembersContainer);

//		this.addNewObject(new Earthling(new Vector2D(), true, "amo"));
//		this.addNewObject(new Earthling(new Vector2D(256, 256), false, "gus", 10, 10, 30, 100));

	}

	private void initializeMap(GameMap map) {
		this.addNewObject(new Background(map.getBackgroundImage()));
		for (int y = 0; y < GameMap.mapRow; y++) {
			for (int x = 0; x < GameMap.mapCol; x++) {
				if (map.getObject(x, y) == 1) {
					this.addNewObject(new FloorBox(new Vector2D(x * 32, y * 32), map.getFloorBoxImage()));
				}
				if (map.getObject(x, y) == 2) {
					Earthling earthling = new Earthling(new Vector2D(x * 32, y * 32), this.currentTeam, false);
					this.addNewObject(earthling);
					this.teamMembersContainer.get(this.currentTeam).add(earthling);
					this.currentTeam = (currentTeam + 1) % teamCount;
				}
			}
		}
	}

	private void setCurrentPlayer(Earthling earthling) {
		earthling.setPlayer(true);
		this.currentPlayer = earthling;
	}

	public void addNewObject(GameObject gameObject) {
		this.gameObjectContainer.add(gameObject);
		if (gameObject instanceof PhysicsObject) {
			this.physicsObjectContainer.add((PhysicsObject) gameObject);
		}
		RenderableManager.getInstance().add(gameObject);
	}

	public void updateLogic() {

		this.processTurn();

		for (PhysicsObject physicsObject : this.physicsObjectContainer) {
			this.processState(physicsObject);
			this.processCollision(physicsObject);
			if (physicsObject.isKinematic()) {
				this.processPosition(physicsObject);
			}
		}

		for (GameObject gameObject : this.gameObjectContainer) {
			this.processOutOfBound(gameObject);
		}
		this.updateContainer();
	}

	private void processTurn() {
		if (this.endTurn) {
			this.currentPlayer.setPlayer(false);
			this.lastPlayerIndexes.set(this.currentTeam, this.lastPlayerIndexes.get(this.currentTeam) + 1);
			do {
				this.currentTeam = (currentTeam + 1) % teamCount;
			} while (this.teamMembersContainer.get(this.currentTeam).size() == 0);
			int newPlayerIndex = this.lastPlayerIndexes.get(currentTeam);
			if (newPlayerIndex >= this.teamMembersContainer.get(this.currentTeam).size()) {
				newPlayerIndex = 0;
				this.lastPlayerIndexes.set(this.currentTeam, 0);
			}
			this.setCurrentPlayer(this.teamMembersContainer.get(this.currentTeam).get(newPlayerIndex));
			this.endTurn = false;
		}
		int remainingTeamCount = 0;
		for (ArrayList<Earthling> team : this.teamMembersContainer) {
			if (team.size() > 0) {
				remainingTeamCount += 1;
			}
		}
		if (remainingTeamCount <= 1) {
			this.endGame();
			System.out.println("END");
		}

	}

	public void endTurn() {
		this.endTurn = true;
	}

	private void endGame() {
		this.scene.endGame();
	}

	private void processState(PhysicsObject physicsObject) {
		if (physicsObject instanceof Earthling) {
			Earthling earthling = (Earthling) physicsObject;
			earthling.updateState();
		}

		if (physicsObject instanceof ExplosionArea) {
			ExplosionArea explosion = (ExplosionArea) physicsObject;
			explosion.updateState();
		}
	}

	private void processCollision(PhysicsObject physicsObject) {
		for (PhysicsObject other : this.physicsObjectContainer) {
			if (physicsObject.equals(other)) {
				continue;
			}
			if (physicsObject instanceof Earthling) {
				Earthling earthling = (Earthling) physicsObject;
				if (other instanceof Rocket) {
					continue;
				}
				if (earthling.getGroundCollider().collideWith(other.getCollider())) {
					earthling.collideGround();
				}
			}

			if (physicsObject instanceof Rocket) {
				Rocket rocket = (Rocket) physicsObject;
				if (other.equals(rocket.getOwner())) {
					continue;
				}
				if (other instanceof Rocket) {
					continue;
				}
				if (rocket.getCollider().collideWith(other.getCollider())) {
					rocket.triggerCollide();
					return;
				}
			}

			if (physicsObject instanceof ExplosionArea) {
				ExplosionArea explosion = (ExplosionArea) physicsObject;
				if (physicsObject.getCollider().collideWith(other.getCollider())) {
					explosion.explode(other);
				}
			}
		}

	}

	private void processPosition(PhysicsObject physicsObject) {
		physicsObject.gravitate(Config.gravity);
		physicsObject.decayVelocity();
		physicsObject.getVelocity().add(physicsObject.calculateDeltaVelocity());
		physicsObject.translate(physicsObject.calculateDeltaPosition());
		if (physicsObject instanceof Earthling) {
			for (PhysicsObject other : this.physicsObjectContainer) {
				if (physicsObject == other) {
					continue;
				}
				if (other instanceof Rocket || other instanceof ExplosionArea) {
					continue;
				}
				if (physicsObject.getCollider().collideWith(other.getCollider())) {
					physicsObject.translate(LogicUtility.calculatePositionFixer(physicsObject.getPosition(),
							physicsObject.getCollider(), other.getCollider()));
				}
			}
		}
	}

	private void processOutOfBound(GameObject gameObject) {
		Vector2D position = gameObject.getPosition();
		double x = position.getX();
		double y = position.getY();
		double threshold = Config.outOfBoundThreshold;
		if (x > Config.screenWidth + threshold || x < -threshold || y > Config.screenHeight + threshold
				|| y < -Config.outOfBoundUpperThreshold) {
			gameObject.setDestroyed(true);
		}
	}

	private void updateContainer() {
		for (GameObject gameObject : this.gameObjectContainer) {
			if (gameObject.isDestroyed()) {
				Platform.runLater(() -> this.gameObjectContainer.remove(gameObject));
				System.out.println("remove" + gameObject.getClass());
				if (gameObject instanceof PhysicsObject) {
					Platform.runLater(() -> this.physicsObjectContainer.remove(gameObject));
				}
			}
		}
		for (ArrayList<Earthling> team : this.teamMembersContainer) {
			for (Earthling earthling : team) {
				if(earthling.isDestroyed()) {
					Platform.runLater(() -> team.remove(earthling));
				}
			}
		}
	}
}

package logic;

import java.util.ArrayList;
import java.util.List;

import config.Config;
import gameObject.Earthling;
import gameObject.GameObject;
import gameObject.PhysicsObject;
import render.RenderableManager;
import utils.Vector2D;

public class GameplayManager {
	
	private List<GameObject> gameObjectContainer;
	private List<Gravitatable> physicsEntityContainer;
	private List<Gravitatable> gravitatableContainer;
	
	public GameplayManager(){
		this.gameObjectContainer = new ArrayList<GameObject>();
		this.gravitatableContainer = new ArrayList<Gravitatable>();
	
		this.initializeGameplay();
		
		this.addNewObject(new Earthling(new Vector2D(), "amo",10,10));

		this.addNewObject(new Earthling(new Vector2D(256,256), "gus",10,10));
//		Field field = new Field();
//		RenderableManager.getInstance().add(field);
//		tank = new Tank(320,240);
//		mine = new Mine(100,100);
//		addNewObject(tank);
//		addNewObject(mine);
	}
	
	protected void initializeGameplay() {
		return;
	}
	
	protected void addNewObject(GameObject gameObject){
		this.gameObjectContainer.add(gameObject);
		if(gameObject instanceof PhysicsObject) {
			this.gravitatableContainer.add((Gravitatable) gameObject);
		}
		if(gameObject instanceof Gravitatable) {
			this.gravitatableContainer.add((Gravitatable) gameObject);
		}
		RenderableManager.getInstance().add(gameObject);
	}
	
	public void updateGameplay(){
//		tank.update();
//		if(!mine.isDestroyed() && tank.collideWith(mine)){
//			mine.onCollision(tank);
//		}
		for (GameObject gameObject : this.gameObjectContainer) {
			if(gameObject instanceof PhysicsObject) {
				if(gameObject instanceof Earthling) {
					Earthling earthling = (Earthling) gameObject;
					earthling.update();
					earthling.setPosition(earthling.calculateNewPosition());
					System.out.println(earthling.getName());
					System.out.println("p="+earthling.getPosition());
					System.out.println("v="+earthling.getVelocity());
				} else if(gameObject instanceof Earthling) {
					
				}
			}
		}
		for (Gravitatable gravitatable : this.gravitatableContainer) {
			if (!gravitatable.isGrounded()){
//				gravitatable.gravitate(Config.gravity);
			}
		}
	}
}

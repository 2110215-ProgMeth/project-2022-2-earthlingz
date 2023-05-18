package logic;

import java.util.ArrayList;
import java.util.List;

import config.Config;
import entity.Earthling;
import entity.Entity;
import entity.PhysicsEntity;
import render.RenderableManager;

public class GameplayManager {
	
	private List<Entity> gameObjectContainer;
	private List<Gravitatable> gravitatableContainer;
	
	public GameplayManager(){
		this.gameObjectContainer = new ArrayList<Entity>();
		this.gravitatableContainer = new ArrayList<Gravitatable>();
	
		this.initializeGameplay();
//		Field field = new Field();
//		RenderableManager.getInstance().add(field);
//		tank = new Tank(320,240);
//		mine = new Mine(100,100);
//		addNewObject(tank);
//		addNewObject(mine);
	}
	
	protected void initializeGameplay() {
		
	}
	
	protected void addNewObject(Entity entity){
		this.gameObjectContainer.add(entity);
		if(entity instanceof Gravitatable) {
			this.gravitatableContainer.add((Gravitatable) entity);
		}
		RenderableManager.getInstance().add(entity);
	}
	
	public void updateGameplay(){
//		tank.update();
//		if(!mine.isDestroyed() && tank.collideWith(mine)){
//			mine.onCollision(tank);
//		}
		for (Entity gameObject : this.gameObjectContainer) {
			if(gameObject instanceof PhysicsEntity) {
				if(gameObject instanceof Earthling) {
					
				} else if(gameObject instanceof Earthling) {
					
				}
			}
		}
		for (Gravitatable gravitatable : this.gravitatableContainer) {
			if (!gravitatable.isGrounded()){
				gravitatable.gravitate(Config.gravity);
			}
		}
	}
}

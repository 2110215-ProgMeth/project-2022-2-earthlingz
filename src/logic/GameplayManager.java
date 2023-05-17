package logic;

import java.util.ArrayList;
import java.util.List;

import config.Config;
import entity.Entity;
import render.RenderableManager;

public class GameplayManager {
	
	private List<Entity> gameObjectContainer;
	private List<Gravitatable> gravitatableContainer;
	
	public GameplayManager(){
		this.gameObjectContainer = new ArrayList<Entity>();
		this.gravitatableContainer = new ArrayList<Gravitatable>();
	
//		Field field = new Field();
//		RenderableManager.getInstance().add(field);
//		tank = new Tank(320,240);
//		mine = new Mine(100,100);
//		addNewObject(tank);
//		addNewObject(mine);
	}
	
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableManager.getInstance().add(entity);
	}
	
	public void logicUpdate(){
//		tank.update();
//		if(!mine.isDestroyed() && tank.collideWith(mine)){
//			mine.onCollision(tank);
//		}
		for (Gravitatable gravitatable : this.gravitatableContainer) {
			if (!gravitatable.isGrounded()){
				gravitatable.gravitate(Config.gravity);
			}
		}
	}
}

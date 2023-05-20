package config;

import map.*;

public class Config {
	
	//// Dynamic Settings
	public static int teamAmount = 2;
	public static GameMap selectedMap = new SnowMap();
	
	//// Static Settings
	
	// General Settings
	public static final double screenWidth = 1280;
	public static final double screenHeight = 800;
	public static final double buttonWidth = 1280;
	public static final double buttonHeight = 800;
	
	// Title Scene Settings
	public static final int gameTitleTextSize = 120;
	public static final String gameTitleTextColor = "#ff2f2b";
	public static final double titleSceneButtonWidth = 500;
	public static final double titleSceneButtonHeight = 100;
	public static final int titleSceneButtonTextSize = 30;
	
	
	// Gameplay Settings
	public static final double outOfBoundThreshold = 64;
	public static final double outOfBoundUpperThreshold = 300;
	
	public static final double gravity = 10;
	
	public static final double floorBoxWidth = 32;
	public static final double floorBoxHeight = 32;
	
	public static final double earthlingWidth = 36;
	public static final double earthlingHeight = 48;
	public static final double earthlingHitboxWidth = 48;
	public static final double earthlingHitboxHeight = 48;
	public static final double earthlingMass = 10;
	public static final double earthlingSpeed = 10;
	public static final double earthlingJumpPower = 65;
	public static final double earthlingMaxFirePower = 70;
	
	public static final double bazookaWidth = 60;
	public static final double bazookaHeight = 25 ;
	
	public static final int normalRocketDamage = 30;
	public static final int normalRocketRadius = 32;
	public static final int normalRocketExplosionRadius = 96;
	public static final int normalRocketPushPower = 300;
	
	public static final int verticalRocketDamage = 0;
	public static final int verticalRocketRadius = 16;
	public static final int verticalRocketExplosionRadius = 16;
	public static final int verticalRocketPushPower = 0;
	
	public static final int pushRocketDamage = 5;
	public static final int pushRocketRadius = 16;
	public static final int pushRocketExplosionRadius = 128;
	public static final int pushRocketPushPower = 500;

	

}

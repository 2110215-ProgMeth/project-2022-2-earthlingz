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
	public static final double buttonWidth = 500;
	public static final double buttonHeight = 100;
	public static final double soundEffectVolumeLevel = 0.2;
	public static final double musicVolumeLevel = 0.15;
	
	// Title Scene Settings
	public static final int gameTitleTextSize = 130;
	public static final String gameTitleTextColor = "#ff2f2b";
	public static final double titleSceneButtonWidth = 500;
	public static final double titleSceneButtonHeight = 100;
	public static final int titleSceneButtonTextSize = 30;
	
	// Gameplay Scene Settings
	public static final double anchorPadding = 30.0;
	public static final double vBoxSpacing = 5.0;
	public static final int gameStateTextSize = 35;
	public static final int playerNameTextSize = 30;
	public static final int rocketAmountTextSize = 20;
	public static final int chargeLabelTextSize = 30;
	public static final int chargePercentTextSize = 60;
	public static final double playerImageWidth = 120;
	public static final double playerImageHeight = 160;
	public static final double rocketImageWidth = 120;
	public static final double rocketImageHeight = 120;
	
	public static final double gameEndPaneWidth = 800;
	public static final double gameEndPaneHeight = 600;
	public static final double gameEndButtonWidth = 400;
	public static final double gameEndButtonHeight = 100;
	public static final int gameEndWinnerTextSize = 60;
	public static final int gameEndButtonTextSize = 25;
	public static final double gameEndBorderWidth = 10;
	
	// Gameplay Logic Settings
	public static final double outOfBoundThreshold = 64;
	public static final double outOfBoundUpperThreshold = 300;
	
	public static final double gravity = 10;
	
	public static final double floorBoxWidth = 32;
	public static final double floorBoxHeight = 32;
	
	public static final double earthlingWidth = 36;
	public static final double earthlingHeight = 48;
	public static final double earthlingHitboxWidth = 36;
	public static final double earthlingHitboxHeight = 42;
	public static final double earthlingMass = 10;
	public static final double earthlingSpeed = 15;
	public static final double earthlingJumpPower = 65;
	public static final double earthlingMaxFirePower = 70;
	public static final double earthlingHpTextSize = 20;
	
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
	public static final int pushRocketPushPower = 750;
	
	public static final int pushRocketAmount = 10;
	public static final int verticalRocketAmount = 2;

	

}

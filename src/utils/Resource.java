package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

public class Resource {

	public static Image gameTitleBackground;
	
	public static AudioClip  gameTitleTheme;
	public static AudioClip  explosionSound;

	public static Image background_space;
	public static Image floor_snow;
	
	public static Image earthlingIdle;

	public static Image bazooka;
	public static Image rocket;
	
	static {
		loadResource();
	}
	
	public static void loadResource() {
		gameTitleBackground = new Image(ClassLoader.getSystemResource("background/gameTitle.jpg").toString());
		gameTitleTheme = new AudioClip(ClassLoader.getSystemResource("sound/gameTitle.mp3").toString());
		explosionSound = new AudioClip(ClassLoader.getSystemResource("sound/Explosion.wav").toString());

		background_space = new Image(ClassLoader.getSystemResource("background/spaceBackground.png").toString());
		floor_snow = new Image(ClassLoader.getSystemResource("texture/floor_snow.png").toString());
		earthlingIdle = new Image(ClassLoader.getSystemResource("entity/black-circle.png").toString());
//		earthlingIdle = new Image(ClassLoader.getSystemResource("sprite/idle.png").toString());
		bazooka = new Image(ClassLoader.getSystemResource("entity/rectangle-32.png").toString());
		rocket = new Image(ClassLoader.getSystemResource("entity/redcircle.png").toString());
	}
}

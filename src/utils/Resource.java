package utils;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Resource {

	// Title Scene
	public static Image background_title;
	public static AudioClip music_title;

	// Gameplay Scene
	public static AudioClip sound_normalRocketExplosion;

	public static Image background_space;
	public static Image background_ruin;
	public static Image background_ancient;

	public static Image floor_snow;
	public static AudioClip sound_boxBreak;

	public static Image earthlingIdle;
	public static Image earthlingCorpse;
	public static AudioClip sound_earthlingHurt;
	public static AudioClip sound_earthlingDead;

	public static Image sprite_bazooka;
	public static Image sprite_rocket;
	public static AudioClip sound_explosionNormal;
	public static AudioClip sound_explosionVertical;
	public static AudioClip sound_explosionPush;

	public static Image circleExplosionArea;
	public static Image rectangleExplosionArea;

	static {
		loadResource();
	}

	public static void loadResource() {
		background_title = new Image(ClassLoader.getSystemResource("background/title.jpg").toString());
		music_title = new AudioClip(ClassLoader.getSystemResource("music/title.mp3").toString());

		background_space = new Image(ClassLoader.getSystemResource("background/space.png").toString());
		background_ruin = new Image(ClassLoader.getSystemResource("background/ruin.png").toString());
		background_ancient = new Image(ClassLoader.getSystemResource("background/ancient.png").toString());
		floor_snow = new Image(ClassLoader.getSystemResource("texture/floor_snow.png").toString());

		earthlingIdle = new Image(ClassLoader.getSystemResource("sprite/idle.png").toString());
		earthlingCorpse = new Image(ClassLoader.getSystemResource("sprite/dead_1.png").toString());
		sprite_bazooka = new Image(ClassLoader.getSystemResource("entity/rectangle-32.png").toString());

		sprite_rocket = new Image(ClassLoader.getSystemResource("entity/rocket.png").toString());

		circleExplosionArea = new Image(ClassLoader.getSystemResource("entity/redcircle.png").toString());
		rectangleExplosionArea = new Image(ClassLoader.getSystemResource("entity/redrectangle.png").toString());
	}
}

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

	public static Image earthling_idle_red;
	public static Image earthling_dead_red;
	public static Image earthling_idle_green;
	public static Image earthling_dead_green;
	public static AudioClip sound_earthlingHurt;
	public static AudioClip sound_earthlingDead;

	public static Image sprite_bazooka;
	public static Image sprite_rocket;
	public static AudioClip sound_explosionNormal;
	public static AudioClip sound_explosionVertical;
	public static AudioClip sound_explosionPush;

	public static Image explosionArea_circle;
	public static Image explosionArea_rectangle;

	static {
		loadResource();
	}

	public static void loadResource() {
		background_title = new Image(ClassLoader.getSystemResource("background/title.png").toString());
		music_title = new AudioClip(ClassLoader.getSystemResource("music/title.mp3").toString());

		background_space = new Image(ClassLoader.getSystemResource("background/space.png").toString());
		background_ruin = new Image(ClassLoader.getSystemResource("background/ruin.png").toString());
		background_ancient = new Image(ClassLoader.getSystemResource("background/ancient.png").toString());
		
		floor_snow = new Image(ClassLoader.getSystemResource("texture/floor_snow.png").toString());

		earthling_idle_red = new Image(ClassLoader.getSystemResource("sprite/robot_red.png").toString());
		earthling_dead_red = new Image(ClassLoader.getSystemResource("sprite/robot_red_dead.png").toString());
		earthling_idle_green = new Image(ClassLoader.getSystemResource("sprite/robot_green.png").toString());
		earthling_dead_green = new Image(ClassLoader.getSystemResource("sprite/robot_green_dead.png").toString());
		sprite_bazooka = new Image(ClassLoader.getSystemResource("entity/bazooka.png").toString());

		sprite_rocket = new Image(ClassLoader.getSystemResource("entity/rocket.png").toString());

		explosionArea_circle = new Image(ClassLoader.getSystemResource("entity/redcircle.png").toString());
		explosionArea_rectangle = new Image(ClassLoader.getSystemResource("entity/redrectangle.png").toString());
	}
}

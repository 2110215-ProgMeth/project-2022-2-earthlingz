package utils;

import config.Config;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class Resource {

	// Title Scene
	public static Image background_title;
	public static AudioClip music_title;

	// Gameplay Scene
	public static AudioClip sound_normalRocketExplosion;

	public static Image background_space;
	public static Image background_ruin;
	public static Image background_ancient;
	public static AudioClip music_battle_space;
	public static AudioClip music_battle_ruin;
	public static AudioClip music_battle_ancient;

	public static Image floor_snow;
	public static AudioClip sound_boxBreak;

	public static Image earthling_idle_red;
	public static Image earthling_dead_red;
	public static Image earthling_idle_green;
	public static Image earthling_dead_green;
	public static Media  sound_earthlingCharge;
	public static AudioClip sound_earthlingHurt;
	public static AudioClip sound_earthlingDead;

	public static Image sprite_bazooka;
	public static Image sprite_rocket;
	public static Image sprite_rocket_normal;
	public static Image sprite_rocket_vertical;
	public static Image sprite_rocket_push;
	public static AudioClip sound_explosionNormal;
	public static AudioClip sound_explosionVertical;
	public static AudioClip sound_explosionPush;

	public static Image explosionArea_circle;
	public static Image explosionArea_rectangle;

	static {
		System.out.println("START LOADING RESOURCE");
		loadResource();
		System.out.println("FINISHED LOADING RESOURCE");
	}

	public static void loadResource() {
		background_title = new Image(ClassLoader.getSystemResource("background/title.png").toString());
		music_title = new AudioClip(ClassLoader.getSystemResource("music/title.mp3").toString());

		background_space = new Image(ClassLoader.getSystemResource("background/space.png").toString());
		background_ruin = new Image(ClassLoader.getSystemResource("background/ruin.png").toString());
		background_ancient = new Image(ClassLoader.getSystemResource("background/ancient.png").toString());
		music_battle_space = new AudioClip(ClassLoader.getSystemResource("music/battle_space.mp3").toString());
		music_battle_ruin = new AudioClip(ClassLoader.getSystemResource("music/battle_ruin.mp3").toString());
		music_battle_ancient = new AudioClip(ClassLoader.getSystemResource("music/battle_ancient.mp3").toString());

		floor_snow = new Image(ClassLoader.getSystemResource("texture/floor_snow.png").toString());
		sound_boxBreak = new AudioClip(ClassLoader.getSystemResource("sound/box_break.wav").toString());

		earthling_idle_red = new Image(ClassLoader.getSystemResource("sprite/robot_red.png").toString());
		earthling_dead_red = new Image(ClassLoader.getSystemResource("sprite/robot_red_dead.png").toString());
		earthling_idle_green = new Image(ClassLoader.getSystemResource("sprite/robot_green.png").toString());
		earthling_dead_green = new Image(ClassLoader.getSystemResource("sprite/robot_green_dead.png").toString());
		sound_earthlingCharge = new Media(ClassLoader.getSystemResource("sound/earthling_chargeRocket.wav").toString());
		sound_earthlingHurt = new AudioClip(ClassLoader.getSystemResource("sound/earthling_hurt.wav").toString());
		sound_earthlingDead = new AudioClip(ClassLoader.getSystemResource("sound/earthling_dead.wav").toString());

		sprite_bazooka = new Image(ClassLoader.getSystemResource("entity/bazooka.png").toString());
		sprite_rocket = new Image(ClassLoader.getSystemResource("entity/rocket.png").toString());
		sprite_rocket_normal = new Image(ClassLoader.getSystemResource("entity/rocket_normal.png").toString());
		sprite_rocket_vertical = new Image(ClassLoader.getSystemResource("entity/rocket_vertical.png").toString());
		sprite_rocket_push = new Image(ClassLoader.getSystemResource("entity/rocket_push.png").toString());
		sound_explosionNormal= new AudioClip(ClassLoader.getSystemResource("sound/explosion_normal.wav").toString());
		sound_explosionVertical= new AudioClip(ClassLoader.getSystemResource("sound/explosion_vertical.wav").toString());
		sound_explosionPush= new AudioClip(ClassLoader.getSystemResource("sound/explosion_push.wav").toString());

		explosionArea_circle = new Image(ClassLoader.getSystemResource("entity/redcircle.png").toString());
		explosionArea_rectangle = new Image(ClassLoader.getSystemResource("entity/redrectangle.png").toString());
	}
	
	public static void playSound(AudioClip sound) {
		sound.setVolume(Config.soundEffectVolumeLevel);
		sound.play();
	}
	
	public static void playSoundLoop(AudioClip sound) {
		sound.setVolume(Config.musicVolumeLevel);
		sound.setCycleCount(AudioClip.INDEFINITE);
		sound.play();
	}
}

package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

public class Resource {

	public static Image gameTitleBackground;
	public static AudioClip  gameTitleTheme;
	public static AudioClip  explosionSound;
	
	static {
		loadResource();
	}
	
	public static void loadResource() {
		gameTitleBackground = new Image(ClassLoader.getSystemResource("background/gameTitle.jpg").toString());
		gameTitleTheme = new AudioClip(ClassLoader.getSystemResource("sound/gameTitle.mp3").toString());
		explosionSound = new AudioClip(ClassLoader.getSystemResource("sound/Explosion.wav").toString());
	}
}

package map;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public abstract class GameMap {

	public static final int mapRow = 26;
	public static final int mapCol = 41;
	protected int[][] mapSheet;
	protected Image backgroundImage;
	protected Image floorBoxImage;
	protected AudioClip battleTheme;
	
	public int getObject(int x, int y) {
		if (x < 0 || x >= mapSheet[0].length || y < 0 || y >= mapSheet.length)
			return 0;
		return mapSheet[y][x];
	}

	public int[][] getMapSheet() {
		return mapSheet;
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public Image getFloorBoxImage() {
		return floorBoxImage;
	}

	public AudioClip getBattleTheme() {
		return battleTheme;
	}

}

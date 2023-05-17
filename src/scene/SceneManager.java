package scene;

import config.Config;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

	private static SceneManager instance = null;
	private final Stage primaryStage;
	private ScenePane currentScenePane;

	public SceneManager(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("EarthlingZ");
		this.currentScenePane = new TitleScenePane(Config.screenWidth, Config.screenHeight);
		Scene scene = new Scene(currentScenePane);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
	public static void initializeSceneManager(Stage primaryStage) {
		SceneManager.instance = new SceneManager(primaryStage);
	}

	public static SceneManager getInstance() {
		return instance;
	}

	public void changeScene(ScenePane scenePane) {
		this.currentScenePane = scenePane;
		Scene scene = new Scene(scenePane);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
	public void updateScene() {
		this.currentScenePane.updateScene();
	}
	
	public void closeGame() {
		this.primaryStage.close();
	}

}

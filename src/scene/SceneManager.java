package scene;

import config.Config;
import input.InputManager;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneManager {

	private static SceneManager instance = null;
	private final Stage primaryStage;
	private ScenePane currentScenePane;

	public SceneManager(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("EarthlingZ");
		this.primaryStage.setResizable(false);
		this.currentScenePane = new TitleScenePane(Config.screenWidth, Config.screenHeight);
//		this.currentScenePane = new GameplayScenePane(Config.screenWidth, Config.screenHeight);
		Scene scene = new Scene(currentScenePane);
		this.addListerner(scene);
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
		this.addListerner(scene);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}

	public void updateScene() {
		this.currentScenePane.updateScene();
	}

	private void addListerner(Scene scene) {
		scene.setOnKeyPressed((KeyEvent event) -> {
			InputManager.setKeyPressed(event.getCode(), true);
		});

		scene.setOnKeyReleased((KeyEvent event) -> {
			InputManager.setKeyPressed(event.getCode(), false);
		});

		scene.setOnMousePressed((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputManager.mouseLeftDown();
		});

		scene.setOnMouseReleased((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputManager.mouseLeftRelease();
		});

		scene.setOnMouseEntered((MouseEvent event) -> {
			InputManager.mouseOnScreen = true;
		});

		scene.setOnMouseExited((MouseEvent event) -> {
			InputManager.mouseOnScreen = false;
		});

		scene.setOnMouseMoved((MouseEvent event) -> {
			if (InputManager.mouseOnScreen) {
				InputManager.mouseX = event.getX();
				InputManager.mouseY = event.getY();
			}
		});

		scene.setOnMouseDragged((MouseEvent event) -> {
			if (InputManager.mouseOnScreen) {
				InputManager.mouseX = event.getX();
				InputManager.mouseY = event.getY();
			}
		});
	}

	public void closeGame() {
		this.primaryStage.close();
	}

}

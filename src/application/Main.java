package application;

import scene.SceneManager;
import utils.Time;
import input.InputManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import render.RenderableManager;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		SceneManager.initializeSceneManager(primaryStage);
		Time.setStartTime(System.nanoTime());

		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				Time.setCurrentTime(now);
				RenderableManager.getInstance().updateRenderableList();
				SceneManager.getInstance().updateScene();
				InputManager.updateInputState();
			}
		};
		animation.start();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}

package scene;

import config.Config;
import gui.ButtonTemplate;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import render.RenderableManager;
import utils.Resource;

public class TitleScenePane extends ScenePane {

	private Text gameTitleText;
	private Button startButton;
	private Button exitButton;

	private AudioClip titleTheme;

	public TitleScenePane(double width, double height) {
		super(width, height);

		VBox buttonPane = new VBox();
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setPrefWidth(Config.titleSceneButtonWidth);
		buttonPane.setSpacing(40);

		this.initializeGameTitleText();
		this.initializeStartButton();
		this.initializeExitButton();
		buttonPane.getChildren().addAll(gameTitleText, startButton, exitButton);

		ImageView background = new ImageView(Resource.background_title);
		background.setFitWidth(Config.screenWidth);
		background.setFitHeight(Config.screenHeight);

		this.getChildren().addAll(background, buttonPane);

		titleTheme = Resource.music_title;
		Resource.playSoundLoop(titleTheme);

	}

	private void initializeGameTitleText() {
		this.gameTitleText = new Text("EarthlingZ");
		this.gameTitleText.setFill(Color.valueOf(Config.gameTitleTextColor));
		this.gameTitleText.setStrokeWidth(8);
		this.gameTitleText.setStroke(Color.BLACK);
		this.gameTitleText.setFont(Resource.getFont(Resource.font_normal, Config.gameTitleTextSize));
	}

	private void initializeStartButton() {
		this.startButton = new ButtonTemplate("START NEW GAME", Config.titleSceneButtonWidth,
				Config.titleSceneButtonHeight, Config.titleSceneButtonTextSize);
		this.startButton.setOnAction(e -> this.startGame());
	}

	private void startGame() {
		System.out.println("START NEW GAME");
		titleTheme.stop();
		RenderableManager.getInstance().clear();
		SceneManager.getInstance().changeScene(new GameplayScenePane(Config.screenWidth, Config.screenHeight));
	}

	private void initializeExitButton() {
		this.exitButton = new ButtonTemplate("EXIT", Config.titleSceneButtonWidth, Config.titleSceneButtonHeight,
				Config.titleSceneButtonTextSize);
		this.exitButton.setOnAction(e -> this.exitGame());
	}

	private void exitGame() {
		System.out.println("EXIT GAME");
		SceneManager.getInstance().closeGame();
	}

	@Override
	public void updateScene() {
		this.renderComponent();
	}
}

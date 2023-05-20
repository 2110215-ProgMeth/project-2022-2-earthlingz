package scene;

import config.Config;
import gui.ButtonTemplate;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import render.RenderableManager;
import utils.Resource;

public class TitleScenePane extends ScenePane {

	private Text gameTitleText;
	private Button startButton;
	private Button exitButton;

	private MediaPlayer mediaPlayer;
	private AudioClip theme;

	public TitleScenePane(double width, double height) {
		super(width, height);

		VBox buttonPane = new VBox();
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setPrefWidth(500);
		buttonPane.setSpacing(20);

		this.initializeGameTitleText();
		this.initializeStartButton();
		this.initializeExitButton();
		buttonPane.getChildren().addAll(gameTitleText, startButton, exitButton);

		ImageView background = new ImageView(Resource.background_title);
//		background.setPreserveRatio(true);
		background.setFitWidth(Config.screenWidth);
		background.setFitHeight(Config.screenHeight);

		this.getChildren().addAll(background, buttonPane);

//		Resource.gameTitleTheme.play();

//		Media theme = new Media(ClassLoader.getSystemResource("music/testLoop.mp3").toString());
//		mediaPlayer = new MediaPlayer(theme);
//		mediaPlayer.setOnEndOfMedia(new Runnable() {
//			public void run() {
//				mediaPlayer.seek(Duration.ZERO);
//			}
//		});
//		mediaPlayer.setCycleCount(Duration.INDEFINITE);
//		mediaPlayer.play();
		
		theme = new AudioClip(ClassLoader.getSystemResource("music/testLoop.mp3").toString());
		theme.setCycleCount(AudioClip.INDEFINITE);
		theme.play();

	}

	private void initializeGameTitleText() {
		this.gameTitleText = new Text("EarthlingZ");
		this.gameTitleText.setFill(Color.valueOf(Config.gameTitleTextColor));
		this.gameTitleText.setStrokeWidth(4);
		this.gameTitleText.setStroke(Color.BLACK);
		this.gameTitleText
				.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, Config.gameTitleTextSize));
	}

	private void initializeStartButton() {
		this.startButton = new ButtonTemplate("START NEW GAME", Config.titleSceneButtonWidth,
				Config.titleSceneButtonHeight, Config.titleSceneButtonTextSize);
		this.startButton.setOnAction(e -> this.startGame());
	}

	private void startGame() {
		Resource.music_title.stop();
		theme.stop();
		RenderableManager.getInstance().clear();
		SceneManager.getInstance().changeScene(new GameplayScenePane(Config.screenWidth, Config.screenHeight));
	}

	private void initializeExitButton() {
		this.exitButton = new ButtonTemplate("EXIT", Config.titleSceneButtonWidth, Config.titleSceneButtonHeight,
				Config.titleSceneButtonTextSize);
		this.exitButton.setOnAction(e -> this.exitGame());
	}

	private void exitGame() {
		SceneManager.getInstance().closeGame();
	}

	@Override
	public void updateScene() {
		this.renderComponent();
	}
}

package scene;

import config.Config;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utils.Resource;

public class TitleScenePane extends ScenePane {

	private Text gameTitleText;
	private Button startButton;
	private Button exitButton;

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

		ImageView background = new ImageView(Resource.gameTitleBackground);
//		background.setPreserveRatio(true);
		background.setFitHeight(Config.screenHeight);

		this.getChildren().addAll(background, buttonPane);
		
		System.out.println(this.getWidth()+" "+this.getHeight());
		
		Resource.gameTitleTheme.play();

	}

	private void initializeGameTitleText() {
		this.gameTitleText = new Text("EarthlingZ");
		this.gameTitleText.setFont(new Font(35));
	}

	private void initializeStartButton() {
		this.startButton = new Button("START NEW GAME");
		this.startButton.setPrefWidth(300);
		this.startButton.setOnAction(e -> this.startGame());
	}

	private void startGame() {
		SceneManager.getInstance().changeScene(new GameplayScenePane(Config.screenWidth, Config.screenHeight));
		Resource.gameTitleTheme.stop();
	}

	private void initializeExitButton() {
		this.exitButton = new Button("EXIT");
		this.exitButton.setPrefWidth(300);
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

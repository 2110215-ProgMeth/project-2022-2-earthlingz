package gui;

import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import render.RenderableManager;
import scene.GameplayScenePane;
import scene.SceneManager;
import scene.TitleScenePane;
import utils.Resource;

public class GameEndPane extends StackPane {

	private Text winnerText;
	private ImageView winnerBackground;
	private ButtonTemplate playAgainButton;
	private ButtonTemplate backToTitleButton;
	private VBox buttonPane;
	private VBox mainPane;

	public GameEndPane(GameplayScenePane scenePane, int winnerTeam) {
		super();
		this.setMaxWidth(Config.gameEndPaneWidth);
		this.setMaxHeight(Config.gameEndPaneHeight);

		this.mainPane = new VBox();
		this.mainPane.setSpacing(50);
		this.mainPane.setAlignment(Pos.CENTER);

		switch (winnerTeam) {
		case 0:
			this.initializeGreenWinnerTeam();
			break;
		case 1:
			this.initializeRedWinnerTeam();
			break;
		default:
			this.initializeGreenWinnerTeam();
			break;
		}

		this.winnerText.setFont(Resource.getFont(Resource.font_normal, Config.gameEndWinnerTextSize));
		this.winnerText.setStrokeWidth(4);
		this.winnerText.setStroke(Color.WHITE);
		this.winnerBackground.setFitWidth(Config.gameEndPaneWidth);
		this.winnerBackground.setFitHeight(Config.gameEndPaneHeight);

		this.buttonPane = new VBox();
		this.buttonPane.setSpacing(20);
		this.buttonPane.setAlignment(Pos.CENTER);

		this.playAgainButton = new ButtonTemplate("PLAY AGAIN", Config.gameEndButtonWidth, Config.gameEndButtonHeight,
				Config.gameEndButtonTextSize);
		this.playAgainButton.setOnAction(e -> this.playAgain());

		this.backToTitleButton = new ButtonTemplate("BACK TO TITLE", Config.gameEndButtonWidth,
				Config.gameEndButtonHeight, Config.gameEndButtonTextSize);
		this.backToTitleButton.setOnAction(e -> this.backToTitle());

		this.buttonPane.getChildren().addAll(this.playAgainButton, this.backToTitleButton);

		this.mainPane.getChildren().addAll(this.winnerText, this.buttonPane);

		this.getChildren().addAll(this.winnerBackground, this.mainPane);
	}

	private void initializeGreenWinnerTeam() {
		this.setBorder(new Border(new BorderStroke(Color.MEDIUMAQUAMARINE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				new BorderWidths(Config.gameEndBorderWidth))));
		this.setBackground(new Background(new BackgroundFill(Color.MEDIUMSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		this.winnerText = new Text("TEAM GREEN WIN!!!");
		this.winnerText.setFill(Color.GREEN);
		this.winnerBackground = new ImageView(Resource.background_green_win);
	}

	private void initializeRedWinnerTeam() {
		this.setBorder(new Border(new BorderStroke(Color.LIGHTCORAL, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				new BorderWidths(Config.gameEndBorderWidth))));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
		this.winnerText = new Text("TEAM RED WIN!!!");
		this.winnerText.setFill(Color.RED);
		this.winnerBackground = new ImageView(Resource.background_red_win);
	}

	private void playAgain() {
		RenderableManager.getInstance().clear();
		SceneManager.getInstance().changeScene(new GameplayScenePane(Config.screenWidth, Config.screenHeight));
	}

	private void backToTitle() {
		RenderableManager.getInstance().clear();
		SceneManager.getInstance().changeScene(new TitleScenePane(Config.screenWidth, Config.screenHeight));
	}
}

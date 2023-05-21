package gui;

import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import render.RenderableManager;
import scene.GameplayScenePane;
import scene.SceneManager;
import scene.TitleScenePane;

public class GameEndPane extends VBox {

	private Text winnerText;
	private ButtonTemplate playAgainButton;
	private ButtonTemplate backToTitleButton;
	private VBox buttonPane;

	public GameEndPane(GameplayScenePane scenePane, int winnerTeam) {
		super();
		this.setMaxWidth(Config.gameEndPaneWidth);
		this.setMaxHeight(Config.gameEndPaneHeight);

		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				new BorderWidths(Config.gameEndBorderWidth))));
		this.setAlignment(Pos.CENTER);
		this.setSpacing(50);

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

		this.winnerText.setFont(
				Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, Config.gameEndWinnerTextSize));
		this.winnerText.setStrokeWidth(4);
		this.winnerText.setStroke(Color.BLACK);

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

		this.getChildren().addAll(this.winnerText, this.buttonPane);
	}

	private void initializeGreenWinnerTeam() {
		this.setBackground(new Background(new BackgroundFill(Color.MEDIUMSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		this.winnerText = new Text("TEAM GREEN WIN!!!");
		this.winnerText.setFill(Color.GREEN);
	}

	private void initializeRedWinnerTeam() {
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
		this.winnerText = new Text("TEAM RED WIN!!!");
		this.winnerText.setFill(Color.RED);
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

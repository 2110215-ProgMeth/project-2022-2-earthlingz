package scene;

import config.Config;
import gameObject.Earthling;
import gui.GameEndPane;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameplayManager;
import utils.Resource;

public class GameplayScenePane extends ScenePane {

	private Text currentTeamText;
	private Text currentTurnText;
	private VBox gameStatePane;

	private Image currentRocketImage;
	private Text currentRocketAmountText;
	private VBox currentRocketPane;

	private Image playerImage;
	private Text playerNameText;
	private VBox playerStatusPane;

	private Text chargePercentText;
	private VBox chargePercentPane;

	private AnchorPane gameplayHUD;

	public GameplayScenePane(double width, double height) {
		super(width, height);
		GameplayManager.initializeGameplayManager(this);
		this.initializeHUD();
	}

	@Override
	public void updateScene() {
		this.renderComponent();
		this.updateHUD();
		GameplayManager.getInstance().updateLogic();
	}

	private void initializeHUD() {
		GameplayManager logic = GameplayManager.getInstance();
		Earthling player = logic.getCurrentPlayer();

		Font gameStateFont = Resource.getFont(Resource.font_pixel,Config.gameStateTextSize);
		Font playerNameFont = Resource.getFont(Resource.font_normal,Config.playerNameTextSize);
		double anchorPadding = Config.anchorPadding;
		double vBoxSpacing = Config.vBoxSpacing;

		this.gameStatePane = new VBox();
		this.gameStatePane.setSpacing(vBoxSpacing);

		this.currentTurnText = new Text("TURN " + logic.getCurrentTurn());
		this.currentTurnText.setFont(gameStateFont);
		this.currentTurnText.setFill(Color.WHITE);

		this.currentTeamText = new Text();
		this.currentTeamText.setFont(gameStateFont);

		this.playerNameText = new Text(player.getName());
		this.playerNameText.setFont(playerNameFont);
		this.playerNameText.setStrokeWidth(2);
		this.playerNameText.setFill(Color.WHITE);

		switch (logic.getCurrentTeam()) {
		case 0:
			this.currentTeamText.setText("TEAM GREEN");
			this.currentTeamText.setFill(Color.GREEN);
			this.playerNameText.setStroke(Color.GREEN);
			this.playerImage = Resource.earthling_idle_green;
			break;
		case 1:
			this.currentTeamText.setText("TEAM RED");
			this.currentTeamText.setFill(Color.RED);
			this.playerNameText.setStroke(Color.RED);
			playerImage = Resource.earthling_idle_red;
			break;
		default:
			this.currentTeamText.setText("TEAM GREEN");
			this.currentTeamText.setFill(Color.GREEN);
			this.playerNameText.setStroke(Color.GREEN);
			playerImage = Resource.earthling_idle_green;
			break;
		}

		this.gameStatePane.getChildren().addAll(this.currentTeamText, this.currentTurnText);

		this.playerStatusPane = new VBox();
		this.playerStatusPane.setSpacing(vBoxSpacing);

		ImageView playerImageView = new ImageView(this.playerImage);
		playerImageView.setFitWidth(Config.playerImageWidth);
		playerImageView.setFitHeight(Config.playerImageHeight);

		this.playerStatusPane.getChildren().addAll(playerImageView, this.playerNameText);

		switch (player.getRocketType()) {
		case NormalRocket:
			this.currentRocketImage = Resource.sprite_rocket_normal;
			break;
		case VerticalRocket:
			this.currentRocketImage = Resource.sprite_rocket_vertical;
			break;
		case PushRocket:
			this.currentRocketImage = Resource.sprite_rocket_push;
			break;
		default:
			this.currentRocketImage = Resource.sprite_rocket_normal;
			break;
		}

		this.currentRocketPane = new VBox();
		this.currentRocketPane.setSpacing(vBoxSpacing);

		ImageView rocketImageView = new ImageView(this.currentRocketImage);
		rocketImageView.setFitWidth(Config.rocketImageWidth);
		rocketImageView.setFitHeight(Config.rocketImageHeight);

		if (logic.isSelectingNormalRocket()) {
			this.currentRocketAmountText = new Text("amount: ∞");
		} else {
			this.currentRocketAmountText = new Text("amount: " + logic.getCurrentRocketAmount());
		}
		this.currentRocketAmountText.setFont(Resource.getFont(Resource.font_pixel,Config.rocketAmountTextSize));
		this.currentRocketAmountText.setFill(Color.WHITE);

		this.currentRocketPane.setAlignment(Pos.TOP_RIGHT);
		this.currentRocketPane.getChildren().addAll(rocketImageView, this.currentRocketAmountText);

		this.chargePercentPane = new VBox();
		this.chargePercentPane.setSpacing(vBoxSpacing);

		Text chargeLabel = new Text("POWER");
		chargeLabel.setFont(Resource.getFont(Resource.font_pixel,Config.chargeLabelTextSize));
		chargeLabel.setFill(Color.WHITE);

		this.chargePercentText = new Text("0%");
		this.chargePercentText.setFont(Resource.getFont(Resource.font_pixel,Config.chargePercentTextSize));
		this.chargePercentText.setFill(Color.AQUA);

		this.chargePercentPane.setAlignment(Pos.BOTTOM_RIGHT);
		this.chargePercentPane.getChildren().addAll(chargeLabel, this.chargePercentText);

		this.gameplayHUD = new AnchorPane();

		AnchorPane.setTopAnchor(this.gameStatePane, anchorPadding);
		AnchorPane.setLeftAnchor(this.gameStatePane, anchorPadding);

		AnchorPane.setBottomAnchor(this.playerStatusPane, anchorPadding);
		AnchorPane.setLeftAnchor(this.playerStatusPane, anchorPadding);

		AnchorPane.setTopAnchor(this.currentRocketPane, anchorPadding);
		AnchorPane.setRightAnchor(this.currentRocketPane, anchorPadding);

		AnchorPane.setBottomAnchor(this.chargePercentPane, anchorPadding);
		AnchorPane.setRightAnchor(this.chargePercentPane, anchorPadding);

		this.gameplayHUD.getChildren().addAll(this.gameStatePane, this.playerStatusPane, this.currentRocketPane,
				this.chargePercentPane);
		this.getChildren().add(gameplayHUD);
	}

	private void updateHUD() {
		GameplayManager logic = GameplayManager.getInstance();
		Earthling player = logic.getCurrentPlayer();

		this.currentTurnText.setText("TURN " + logic.getCurrentTurn());

		switch (logic.getCurrentTeam()) {
		case 0:
			this.currentTeamText.setText("TEAM GREEN");
			this.currentTeamText.setFill(Color.GREEN);
			this.playerNameText.setStroke(Color.GREEN);
			this.playerImage = Resource.earthling_idle_green;
			break;
		case 1:
			this.currentTeamText.setText("TEAM RED");
			this.currentTeamText.setFill(Color.RED);
			this.playerNameText.setStroke(Color.RED);
			playerImage = Resource.earthling_idle_red;
			break;
		default:
			this.currentTeamText.setText("TEAM GREEN");
			this.currentTeamText.setFill(Color.GREEN);
			this.playerNameText.setStroke(Color.GREEN);
			playerImage = Resource.earthling_idle_green;
			break;
		}

		ImageView playerImageView = new ImageView(this.playerImage);
		playerImageView.setFitWidth(Config.playerImageWidth);
		playerImageView.setFitHeight(Config.playerImageHeight);

		this.playerNameText.setText(player.getName());

		this.playerStatusPane.getChildren().clear();
		this.playerStatusPane.getChildren().addAll(playerImageView, this.playerNameText);

		switch (player.getRocketType()) {
		case NormalRocket:
			this.currentRocketImage = Resource.sprite_rocket_normal;
			break;
		case VerticalRocket:
			this.currentRocketImage = Resource.sprite_rocket_vertical;
			break;
		case PushRocket:
			this.currentRocketImage = Resource.sprite_rocket_push;
			break;
		default:
			this.currentRocketImage = Resource.sprite_rocket_normal;
			break;
		}

		ImageView rocketImageView = new ImageView(this.currentRocketImage);
		rocketImageView.setFitWidth(Config.rocketImageWidth);
		rocketImageView.setFitHeight(Config.rocketImageHeight);

		if (logic.isSelectingNormalRocket()) {
			this.currentRocketAmountText.setText("amount: ∞");
		} else {
			this.currentRocketAmountText.setText("amount: " + logic.getCurrentRocketAmount());
		}

		this.currentRocketPane.getChildren().clear();
		this.currentRocketPane.getChildren().addAll(rocketImageView, this.currentRocketAmountText);

		this.chargePercentText.setText(((int) Math.ceil(player.getCurrentChargeRate())) + "%");

	}

	public void endGame() {
		System.out.println("GAMEPLAY END");
		this.getChildren().add(new GameEndPane(this, GameplayManager.getInstance().getCurrentTeam()));
	}

}

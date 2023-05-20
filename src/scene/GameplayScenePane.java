package scene;

import config.Config;
import gui.ButtonTemplate;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameplayManager;
import render.RenderableManager;
import utils.Resource;

public class GameplayScenePane extends ScenePane {

	private Text gameText;

	private HBox statusBox;
	private StackPane gameStatePane;
	private StackPane statusPane;
	private AnchorPane gameplayHUD;

	public GameplayScenePane(double width, double height) {
		super(width, height);
		this.initializeHUD();
		GameplayManager.initializeGameplayManager(this);
	}

	private void initializeHUD() {
		
//		VBox buttonPane = new VBox();
//		buttonPane.setAlignment(Pos.CENTER);
//		buttonPane.setPrefWidth(500);
//		buttonPane.setSpacing(20);		
//		Button startButton = new Button("BACK TO TITLE");
//		startButton.setPrefWidth(300);
//		startButton.setOnAction(e -> this.backToTitle());
//		buttonPane.getChildren().add(startButton);
//		this.getChildren().add(buttonPane);
//		this.getChildren().addAll(new ImageView(Resource.gameTitleBackground),buttonPane);
		this.gameText = new Text("Gameplay");
		this.gameText.setFont(new Font(35));
		this.getChildren().add(gameText);
		
		Text testText = new Text("Test");
		testText.setFont(new Font (35));
		statusPane = new StackPane();
		statusPane.getChildren().addAll(testText);
		
		gameStatePane = new StackPane();
		
		
		gameStatePane.getChildren().addAll(new ButtonTemplate("TEST", 200,100,50));
//		statusBox = new HBox();
//		statusBox.setAlignment(Pos.CENTER);
//		statusBox.setSpacing(5);
//		statusBox.setPadding(new Insets(5));
//		statusBox.getChildren().addAll(statusBarWaveText, coinImage, coinText);
//		statusPane = new StackPane();
//		statusPane.getChildren().addAll(statusPaneImage, statusBox);
//
//		healthPotionText = new GameText("", Config.SCREEN_H / 20, Color.WHITE);
//		healthPotionText.setAlignment(Pos.CENTER);
//		healthPotionText.setTranslateY(Config.SCREEN_H / 25);
//		healthPotionpPaneImage = ResourceManager.getImageView(ImageResource.HEALTH_POTION_PANE,
//				(int) (Config.SCREEN_W / 10), (int) (Config.SCREEN_H / 6.5));
//		healhtPotionBox = new StackPane();
//		healhtPotionBox.getChildren().addAll(healthPotionpPaneImage, healthPotionText);
//
//		ammoText = new GameText("", Config.SCREEN_H / 20, Color.WHITE);
//		ammoText.setAlignment(Pos.CENTER);
//		ammoPaneImage = ResourceManager.getImageView(ImageResource.AMMO_PANE, (int) (Config.SCREEN_W / 10),
//				(int) (Config.SCREEN_H / 15));
//		ammoBox = new StackPane();
//		ammoBox.getChildren().addAll(ammoPaneImage, ammoText);
		
		gameplayHUD = new AnchorPane();
//		AnchorPane.setTopAnchor(statusPane, 10.0);
//		AnchorPane.setLeftAnchor(statusPane, 10.0);
//
//		AnchorPane.setTopAnchor(healhtPotionBox, 10.0);
//		AnchorPane.setRightAnchor(healhtPotionBox, 10.0);

		AnchorPane.setBottomAnchor(statusPane, 10.0);
		AnchorPane.setLeftAnchor(statusPane, 10.0);
		gameplayHUD.getChildren().addAll(statusPane);
		this.getChildren().addAll(gameplayHUD);
//		gameplayHUD.getChildren().addAll(ammoBox, healhtPotionBox, statusPane, towerUI);
	}
	
	@Override
	public void updateScene() {
		this.renderComponent();
		GameplayManager.getInstance().updateLogic();
	}

	private void backToTitle() {
		RenderableManager.getInstance().clear();
		SceneManager.getInstance().changeScene(new TitleScenePane(Config.screenWidth, Config.screenHeight));
	}

}

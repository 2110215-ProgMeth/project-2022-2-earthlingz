package scene;

import config.Config;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameplayManager;
import render.RenderableManager;
import utils.Resource;

public class GameplayScenePane extends ScenePane {

	private GameplayManager gameLogic;
	private Text gameText;

	public GameplayScenePane(double width, double height) {
		super(width, height);
		VBox buttonPane = new VBox();
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setPrefWidth(500);
		buttonPane.setSpacing(20);		
		Button startButton = new Button("BACK TO TITLE");
		startButton.setPrefWidth(300);
		startButton.setOnAction(e -> this.backToTitle());
		buttonPane.getChildren().add(startButton);
		this.getChildren().add(buttonPane);
//		this.getChildren().addAll(new ImageView(Resource.gameTitleBackground),buttonPane);
		this.gameText = new Text("Gameplay");
		this.gameText.setFont(new Font(35));

		this.gameLogic = new GameplayManager();

//		Image image = new Image(ClassLoader.getSystemResource("sprite/idle.png").toString());
//
//        ImageView imageView = new ImageView(image);
//        imageView.setClip(new ImageView(image));
//
//        ColorAdjust monochrome = new ColorAdjust();
//        monochrome.setSaturation(-1.0);
//
//        Blend blush = new Blend(
//                BlendMode.MULTIPLY,
//                monochrome,
//                new ColorInput(
//                        0,
//                        0,
//                        imageView.getImage().getWidth(),
//                        imageView.getImage().getHeight(),
//                        Color.GREEN
//                )
//        );
//
//        imageView.effectProperty().bind(
//                Bindings
//                    .when(imageView.hoverProperty())
//                        .then((Effect) blush)
//                        .otherwise((Effect) null)
//        );
//
//        imageView.setCache(true);
//        imageView.setCacheHint(CacheHint.SPEED);
//		
//		this.getChildren().addAll(new Group(imageView),this.gameText);
//		GraphicsContext gc = canvas.getGraphicsContext2D();
//		gc.drawImage(Resource.earthlingIdle, 0, 0, 32, 32);
	}

	@Override
	public void updateScene() {
		this.gameLogic.updateGameplay();
		this.renderComponent();
	}

	private void backToTitle() {
		RenderableManager.getInstance().clear();
		SceneManager.getInstance().changeScene(new TitleScenePane(Config.screenWidth, Config.screenHeight));
	}

}

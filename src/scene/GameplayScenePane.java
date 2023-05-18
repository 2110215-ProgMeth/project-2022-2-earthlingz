package scene;

import config.Config;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameplayManager;
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
		Button startButton = new Button("START NEW GAME");
		startButton.setPrefWidth(300);
		startButton.setOnAction(e -> this.backToTitle());
		buttonPane.getChildren().add(startButton);
		this.getChildren().addAll(new ImageView(Resource.gameTitleBackground),buttonPane);
		this.gameLogic = new GameplayManager();
		this.gameText = new Text("Gameplay");
		this.gameText.setFont(new Font(35));
		this.getChildren().addAll(this.gameText);
		
		System.out.println(this.getWidth()+" "+this.getHeight());
	}

	@Override
	public void updateScene() {
		this.gameLogic.updateGameplay();
		this.renderComponent();
	}
	
	private void backToTitle() {
		SceneManager.getInstance().changeScene(new TitleScenePane(Config.screenWidth, Config.screenHeight));
	}

}

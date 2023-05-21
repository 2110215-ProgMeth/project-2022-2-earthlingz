package gui;

import javafx.scene.control.Button;
import utils.Resource;

public class ButtonTemplate extends Button {

	public ButtonTemplate(String text, double width, double height, int fontSize) {
		super(text);
		String style = "-fx-background-color: #4e4ecf;"
//				+ "-fx-background-radius: 20;"
				+ "-fx-background-insets: 4, 4;"
				+ "-fx-border-color: #000000;"
//				+ "-fx-border-radius: 20;"
				+ "-fx-border-width: 8;"
				+ "-fx-text-fill: #ffffff;"
				+ "-fx-padding: 12 16;";
		this.setStyle(style);
		this.setMaxWidth(width);
		this.setMaxHeight(height);
		this.setFont(Resource.getFont(Resource.font_pixel, fontSize));
	}

}

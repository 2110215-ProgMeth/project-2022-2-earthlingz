package input;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class InputManager {

	public static double mouseX,mouseY;
	public static boolean mouseOnScreen = true;
	private static boolean isMouseLeftDown = false;
	private static boolean isLeftClickedLastTick = false;
	private static boolean isMouseRightDown = false;
	private static boolean isRightClickedLastTick = false;
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>(); 
	
	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}
	public static void setKeyPressed(KeyCode keycode,boolean pressed) {
		if(pressed){
			if(!keyPressed.contains(keycode)){
				keyPressed.add(keycode);
			}
		}else{
			keyPressed.remove(keycode);
		}
//		System.out.println(keyPressed);
	}
	
	public static void mouseLeftDown(){
		isMouseLeftDown = true;
		isLeftClickedLastTick = true;
	}
	
	public static void mouseLeftRelease(){
		isMouseLeftDown = false;
	}
	
	public static boolean isLeftClickTriggered(){
		return isLeftClickedLastTick;
	}
	
	public static void mouseRightDown(){
		isMouseRightDown = true;
		isRightClickedLastTick = true;
	}
	
	public static void mouseRightRelease(){
		isMouseRightDown = false;
	}
	
	public static boolean isRightClickTriggered(){
		return isRightClickedLastTick;
	}
	
	public static boolean isMouseLeftDown() {
		return isMouseLeftDown;
	}
	public static void updateInputState(){
		isLeftClickedLastTick = false;
		isRightClickedLastTick = false;
	}
	
}

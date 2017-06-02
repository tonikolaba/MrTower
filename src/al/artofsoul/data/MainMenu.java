package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import al.artofsoul.ndihma.StateManger;
import al.artofsoul.ndihma.StateManger.GameState;
import al.artofsoul.userinterface.UI;

public class MainMenu {

	private Texture background;
	private UI menuUI;
	// add the button on screen
	public MainMenu() {
		background = QuickLoad("/res/menu/mainPhoto32");
		menuUI = new UI();
		menuUI.addButton("Play", "/res/menu/playButton", WIDTH / 2 - 128, (int) (HEIGHT * 0.33f));
		menuUI.addButton("Editor", "/res/menu/editorButton", WIDTH / 2 - 128, (int) (HEIGHT * 0.50f));
		menuUI.addButton("Quit", "/res/menu/quitButton", WIDTH / 2 - 128, (int) (HEIGHT * 0.67f));	
	}
	// check if a button is clicked by user then do..
	private void updateButtons() {
		if (Mouse.isButtonDown(0)) {
			if (menuUI.isButtonClicked("Play"))
				StateManger.setState(GameState.GAME);
			if (menuUI.isButtonClicked("Editor"))
				StateManger.setState(GameState.EDITOR);
			if (menuUI.isButtonClicked("Quit"))
				System.exit(0);
		}
	}
	
	public void update () {
		VizatoKatrorTex (background, 0, 0, 1400, 770); // fotot e ekranit
		menuUI.draw();
		updateButtons();
	}
}

package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.*;
import static al.artofsoul.ndihma.Leveler.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import al.artofsoul.userinterface.UI;
import al.artofsoul.userinterface.UI.Menu;

public class Editor{

	private PllakaFusha grid;
	private int index;
	private PllakaType[] types;
	private UI editorUI;
	private Menu tilePickerMenu;
	private Texture menuBackground;

	public Editor() {
		this.grid = LoadMap("res/map/harta");
		this.index = 0;
		this.types = new PllakaType[3];
		this.types[0] = PllakaType.Grass;
		this.types[1] = PllakaType.Dirt;
		this.types[2] = PllakaType.Water;
		this.menuBackground = QuickLoad("/res/menu/tileBackground");
		setupUI();
	}
	
	private void setupUI () {
		editorUI = new UI();
		editorUI.createMenu("TilePicker", 896, 50, 64, 480, 1, 0, 0); //640, 50, 64, 320, 1, 0, 0 for 32
		tilePickerMenu = editorUI.getMenu("TilePicker");
		tilePickerMenu.quickAdd("Grass", "/res/tiles/bari32");
		tilePickerMenu.quickAdd("Dirt", "/res/tiles/toka32");
		tilePickerMenu.quickAdd("Water", "/res/tiles/uj32");
		
	}

	public void update() {
		draw();

		// Handle Mouse Input
		if (Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);
			if (mouseClicked) {
				if(tilePickerMenu.isButtonClicked("Grass")) {
					index = 0;
				} else if (tilePickerMenu.isButtonClicked("Dirt")){
					index = 1;
				} else if (tilePickerMenu.isButtonClicked("Water")){
					index = 2;
				}else {
					shtoPllaka();
				}
			}
		}
		
		// Handle keyboard input
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT
					&& Keyboard.getEventKeyState()) {
				moveIndex();
			}
			// saving editor map create by user with S key from keyboard
			if (Keyboard.getEventKey() == Keyboard.KEY_S
					&& Keyboard.getEventKeyState()) {
				SaveMap("res/map/harta", grid);
			}
		}
	}
	
	private void draw () {
		VizatoKatrorTex(menuBackground, 896, 0, 64, 480); //(QuickLoad("tileBackground"), 1280, 0, 96, 640); 
		grid.draw();
		editorUI.draw();
	}

	private void shtoPllaka() {
		grid.shtoPllaka((int) Math.floor(Mouse.getX() / TILE_SIZE),
				(int) Math.floor((HEIGHT - Mouse.getY() - 1) / TILE_SIZE),
				types[index]);
	}
	//Allows editor to change which PllakaType is selected
	private void moveIndex(){
		index++;
		if (index > types.length - 1){
			index = 0;
		}
	}
}

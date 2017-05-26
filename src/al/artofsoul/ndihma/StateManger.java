package al.artofsoul.ndihma;

import static al.artofsoul.ndihma.Leveler.LoadMap;

import al.artofsoul.data.Editor;
import al.artofsoul.data.Game;
import al.artofsoul.data.MainMenu;
import al.artofsoul.data.PllakaFusha;

public class StateManger {
	
	public static enum GameState {
		MAINMENU, GAME, EDITOR
	}
	
	public static GameState gameState = GameState.MAINMENU;
	public static MainMenu mainMenu;
	public static Game game;
	public static Editor editor;
	
	public static long nextSecond = System.currentTimeMillis() + 1000;
	public static int framesInLastSecond = 0;
	public static int framesInCurrentSecond = 0;
	
	static PllakaFusha map = LoadMap("res/map/harta"); // when the map is reading
		
		/*
			{0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1},
			{0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
			{0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
			{0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
			{0, 2, 2, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1},
			{0, 2, 2, 2, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1},
			{0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1},
			{0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},	
			*/

	
	public static void update(){
		switch(gameState){
		case MAINMENU:
			if (mainMenu == null)
				mainMenu = new MainMenu();
			mainMenu.update();
			break;
		case GAME:
			if (game == null)
				game = new Game(map);
			game.update();
			break;
		case EDITOR:
			if (editor == null)
				editor = new Editor();
			editor.update();
			break;
		}
		
		long currentTime = System.currentTimeMillis();
		if (currentTime > nextSecond) {
			nextSecond += 1000;
			framesInLastSecond = framesInCurrentSecond;
			framesInCurrentSecond = 0;
			System.out.println(framesInLastSecond + "fps");
		}
		framesInCurrentSecond++;
	}
	
	public static void setState(GameState newState) {
		gameState = newState;
	}

}

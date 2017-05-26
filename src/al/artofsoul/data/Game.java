package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import al.artofsoul.ndihma.StateManger;
import al.artofsoul.userinterface.UI;
import al.artofsoul.userinterface.UI.Menu;

public class Game {
	
	private PllakaFusha grid;
	private Lojtari lojtari;
	private ValaManager valaManager;
	private UI gameUI;
	private Menu towerPickerMenu;
	private Texture menuBackground;
	private Armiku[] armikuLlojet;
	
	
	public Game(PllakaFusha grid) {
		this.grid = grid;
		armikuLlojet = new Armiku[3];
		armikuLlojet[0] = new ArmikuAlien( 2, 0, grid);
		armikuLlojet[1] = new ArmikuUFO( 2, 0, grid);
		armikuLlojet[2] = new ArmikuPlane( 2, 0, grid);
		/*
		valaManager = new ValaManager(new Armiku(QuickLoad("armik"), grid.merrPllaka(2, 0), grid, TILE_SIZE, TILE_SIZE, 70, 25),
		2, 2);
		*/
		valaManager = new ValaManager (armikuLlojet, 3, 3);
		lojtari = new Lojtari(grid, valaManager);
		lojtari.setup();
		gameUI = new UI();
		this.menuBackground = QuickLoad("res/menu/towersBackground"); // tower background photo
		setupUI();
	}
	
	private void setupUI() {
		gameUI = new UI();
		gameUI.createMenu("TowerPicker", 896, 50, 64, 480, 1, 0, 0); // 1280 ends of tile, 96 width, 100 padding; //640, 50, 64, 320, 1, 0, 0
		towerPickerMenu = gameUI.getMenu("TowerPicker");
		towerPickerMenu.quickAdd ("BlueCannon", "/res/player/topiBluefullVog");
		towerPickerMenu.quickAdd("IceCannon", "/res/player/topiIcefullVog");
	}
	
	private void updateUI() {
		gameUI.draw();
		gameUI.drawString(902, 250, "Lives: " + Lojtari.Lives);
		gameUI.drawString(902, 300, "Gold: " + Lojtari.Gold);
		gameUI.drawString(902, 350, "Wave: " + valaManager.getWaveNumber());
		gameUI.drawString(0, 0, StateManger.framesInLastSecond + " fps");
		
			if (Mouse.next()) {
				boolean mouseClicked = Mouse.isButtonDown(0);
				if (mouseClicked) {
					if(towerPickerMenu.isButtonClicked("BlueCannon"))
						lojtari.pickTower(new TowerCannonBlue(TowerType.CannonBlue, grid.merrPllaka(0, 0), valaManager.getCurrentWave().getArmikuList()));
					if(towerPickerMenu.isButtonClicked("IceCannon"))
						lojtari.pickTower(new TowerCannonIce(TowerType.CannonIce, grid.merrPllaka(0, 0), valaManager.getCurrentWave().getArmikuList()));
				}
			}
		
		}
	
	public void update(){
		VizatoKatrorTex(menuBackground, 896, 0, 64, 480); //704, 0, 64, 320) vizatojm beckgroundin e buttonave qe do te perdorim gjate lojes
		grid.draw();
		valaManager.update();
		lojtari.update();
		updateUI();
	}
}

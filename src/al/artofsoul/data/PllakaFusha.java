package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.*;

public class PllakaFusha {

	public Pllaka[][] map;
	private int pllakaWide, pllakaHigh;
	
	public PllakaFusha() {
		this.pllakaWide = 28; // tile width on game
		this.pllakaHigh = 15; //  tile height on game
		map = new Pllaka [pllakaWide][pllakaHigh];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map [i][j] = new Pllaka(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, PllakaType.Grass);
			}
		}
	}
	
	public PllakaFusha(int[][] newMap){
		this.pllakaWide = newMap[0].length;
		this.pllakaHigh = newMap.length;
		map = new Pllaka[pllakaWide][pllakaHigh];
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[i].length; j++){
				switch (newMap[j][i]){
				case 0:
					map [i][j] = new Pllaka(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, PllakaType.Grass);
					break;
				case 1:
					map [i][j] = new Pllaka(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, PllakaType.Dirt);
					break;
				case 2:
					map [i][j] = new Pllaka(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, PllakaType.Water);
					break;
				}
			}
		}
	}
	
	public void shtoPllaka(int xCoord, int yCoord, PllakaType type){
	map[xCoord][yCoord] = new Pllaka(xCoord * TILE_SIZE, yCoord * TILE_SIZE, TILE_SIZE, TILE_SIZE, type);	
	}
	
	public Pllaka merrPllaka (int xPlace, int yPlace){
		if (xPlace < pllakaWide && yPlace < pllakaHigh && xPlace > -1 && yPlace > -1)
			return map [xPlace][yPlace];
		else
			return new Pllaka(0, 0, 0, 0, PllakaType.NULL);
	}
	
	public void draw() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				map[i][j].draw();
			}
		}
	}

	public int getPllakaWide() {
		return pllakaWide;
	}

	public void setPllakaWide(int pllakaWide) {
		this.pllakaWide = pllakaWide;
	}

	public int getPllakaHigh() {
		return pllakaHigh;
	}

	public void setPllakaHigh(int pllakaHigh) {
		this.pllakaHigh = pllakaHigh;
	}
	
}

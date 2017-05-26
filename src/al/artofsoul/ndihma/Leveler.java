package al.artofsoul.ndihma;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import al.artofsoul.data.Pllaka;
import al.artofsoul.data.PllakaFusha;
import al.artofsoul.data.PllakaType;

public class Leveler {
	
	public static void SaveMap(String mapName, PllakaFusha grid) {
		String mapData = "";
		for (int i = 0; i < grid.getPllakaWide(); i++) {
			for (int j = 0; j < grid.getPllakaHigh(); j++) {
				mapData += getTileID(grid.merrPllaka(i, j));
			}
		}
		try {
		File file = new File(mapName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(mapData);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static PllakaFusha LoadMap(String mapName) {
		PllakaFusha grid = new PllakaFusha();
		try {
			BufferedReader br = new BufferedReader(new FileReader(mapName));
			String data = br.readLine();
			for (int i = 0; i < grid.getPllakaWide(); i++) {
				for (int j = 0; j < grid.getPllakaHigh(); j++) {
					grid.shtoPllaka(i, j, getTileType(data.substring(i * grid.getPllakaHigh() + j, i * grid.getPllakaHigh() + j + 1)));
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return grid;
	}
	
	public static PllakaType getTileType(String ID) {
		PllakaType type = PllakaType.NULL;
		switch (ID) {
		case "0":
			type = PllakaType.Grass;
			break;
		case "1":
			type = PllakaType.Dirt;
			break;
		case "2":
			type = PllakaType.Water;
			break;
		case "3":
			type = PllakaType.NULL;
			break;
		}
		
		
		return type;
	}
	
	public static String getTileID(Pllaka t) {
	 
		String ID = "E";
		switch(t.getType()){
		case Grass:
			ID = "0";
			break;
		case Dirt:
			ID = "1";
			break;
		case Water:
			ID = "2";
			break;
		case NULL:
			ID = "3";
			break;
		}
		return ID;
	}
}

package al.artofsoul.data;

public enum PllakaType {
	
	Grass ("res/tiles/bari32", true), Dirt ("res/tiles/toka32", false), Water ("res/tiles/uj32", false), NULL("res/tiles/uj32", false);
	
	String textureName;
	boolean buildable;
	
	PllakaType(String textureName, boolean buildable){
		this.textureName = textureName;
		this.buildable = buildable;
	}
}

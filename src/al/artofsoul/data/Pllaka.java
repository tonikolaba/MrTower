package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.*;

import org.newdawn.slick.opengl.Texture;

public class Pllaka {
	
	private float x, y;
	private int width, height;
	private Texture texture;
	private PllakaType type;
	private boolean occupied;
	
	public Pllaka(float x, float y, int width, int height, PllakaType type){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.texture = QuickLoad(type.textureName);
		if (type.buildable)
			occupied = false;
		else
			occupied = true;
	}
	
	public void draw(){
		VizatoKatrorTex(texture, x, y, width, height); // shkurtojm deklarimin tek boot file
	}

	public float getX() {
		return x;
	}
	
	public int getXPlace(){
		return (int) x / TILE_SIZE;
	}
	
	public int getYPlace(){
		return (int) y / TILE_SIZE;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public PllakaType getType() {
		return type;
	}

	public void setType(PllakaType type) {
		this.type = type;
	}
	
	public boolean getOccupied () {
		return occupied;
	}
	
	public void setOccupied (boolean occupied) {
		this.occupied = occupied;
	}

}

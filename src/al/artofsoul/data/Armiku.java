package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.*;
import static al.artofsoul.ndihma.Ora.*;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class Armiku implements Entity{
	private int width, height, currentCheckpoint;
	private float speed, x, y, health, startHealth;// hiddenHealth;
	private Texture texture, healthBackground, healthForeground, healthBorder;
	private Pllaka filloPllaka;
	private boolean first, alive;
	private PllakaFusha grid;
	
	private ArrayList<Checkpoint> checkpoints;
	private int[] directions;
	
	//default constructor
	public Armiku(int tilleX, int tileY, PllakaFusha grid) {
		this.texture = QuickLoad("/res/armiku/armikBlue32");
		this.healthBackground = QuickLoad("/res/armiku/healthBack"); // enemy statusi photo
		this.healthForeground = QuickLoad("/res/armiku/healthForeg");
		this.healthBorder = QuickLoad("/res/armiku/healthBord");
		this.filloPllaka = grid.merrPllaka(tilleX, tileY);
		this.x = filloPllaka.getX();
		this.y = filloPllaka.getY();
		this.width = TILE_SIZE;
		this.height = TILE_SIZE;
		this.speed = 40;
		this.health = 40;
		this.startHealth = health;
		this.grid = grid;
		this.first = true;
		this.alive = true;
		this.checkpoints = new ArrayList<Checkpoint>();
		this.directions = new int [2];
		// x direction
		this.directions[0] = 0;
		// y direction
		this.directions[1] = 0;
		this.directions = findNextD(filloPllaka);
		this.currentCheckpoint = 0;
		populateCheckpointlist();
	
	}
	
	public Armiku(Texture texture, Pllaka filloPllaka, PllakaFusha grid, int width, 
			int height, float speed, float health){
		this.texture = texture;
		this.healthBackground = QuickLoad("/res/armiku/healthBack"); // enemy statusi photo
		this.healthForeground = QuickLoad("/res/armiku/healthForeg");
		this.healthBorder = QuickLoad("/res/armiku/healthBord");
		this.filloPllaka = filloPllaka;
		this.x = filloPllaka.getX();
		this.y = filloPllaka.getY();
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.health = health;
		this.startHealth = health;
		//this.hiddenHealth = health;
		this.grid = grid;
		this.first = true;
		this.alive = true;
		this.checkpoints = new ArrayList<Checkpoint>();
		this.directions = new int [2];
		// x direction
		this.directions[0] = 0;
		// y direction
		this.directions[1] = 0;
		this.directions = findNextD(filloPllaka);
		this.currentCheckpoint = 0;
		populateCheckpointlist();
	}
	
	public void update(){
		// check if it's the first time this class is updated, if so do nothing
		if (first)
			first = false;
		else {
			if (checkpointReached()) {
				//check if there are more checkpoints before moving on
				if (currentCheckpoint + 1 == checkpoints.size())
					endOfMazeReached();
					//System.out.println("Enamy reached end of maze");
				else
					currentCheckpoint++;
			} else {
				// if not at a checkpoint, continue in current direction
				x += Delta() * checkpoints.get(currentCheckpoint).getxDirection() * speed;
				y += Delta() * checkpoints.get(currentCheckpoint).getyDirection() * speed;
			}
		}
	}
	// run when last checkpoint is reached by enemy
	private void endOfMazeReached() {
		Lojtari.modifyLives(-1);
		die();
	}

	private boolean checkpointReached(){
		boolean reached = false;
		Pllaka t = checkpoints.get(currentCheckpoint).getPllaka();
		// check if position reached tile within variance of 3 (arbitrary)
		if ( x > t.getX() - 3 && 
				x < t.getX() + 3 &&
				y > t.getY() - 3 &&
				y < t.getY() + 3){
		
			reached = true;
			x = t.getX();
			y = t.getY();
		}	
		return reached;
	}
	
	private void populateCheckpointlist() {
		//Add first checkpoint manually based on filloPllaka
		checkpoints.add(findNextC(filloPllaka, directions = findNextD(filloPllaka)));
		
		int counter = 0;
		boolean cont = true;
		while (cont){
			int[] currentD = findNextD(checkpoints.get(counter).getPllaka());
			//check if next direction/checkpoints exists, end after 20 checkpoints (arbitrary)
			if (currentD[0] == 2 || counter == 20) {
				cont = false;
			} else {
				checkpoints.add(findNextC(checkpoints.get(counter).getPllaka(),
						directions = findNextD(checkpoints.get(counter).getPllaka())));
			}
			counter++;
		}
	}
	
	private Checkpoint findNextC(Pllaka s, int[] dir) {
		Pllaka next = null;
		Checkpoint c = null;
		
		//boolean per te deklaruar nese next kontrolli eshte gjetur
		boolean found = false;
		// Integer to increment each loop
		int counter = 1;
		
		while (!found) {
			if(s.getXPlace() + dir[0] * counter == grid.getPllakaWide() ||
					s.getYPlace() + dir[1] * counter == grid.getPllakaHigh() ||
					s.getType() != 
					grid.merrPllaka(s.getXPlace() + dir[0] * counter,
							s.getYPlace() + dir[1] * counter).getType()){
				found = true;
				// move counter back 1 to find pllaka before new PllakaType
				counter -= 1;
				next = grid.merrPllaka(s.getXPlace() + dir[0] * counter,
						s.getYPlace() + dir[1] * counter);
			}	
			counter++;
		}
		c = new Checkpoint (next, dir[0], dir[1]);
		return c;
	}
	
	private int [] findNextD(Pllaka s) {
		int[] dir = new int[2];
		Pllaka u = grid.merrPllaka(s.getXPlace(), s.getYPlace() - 1); // Up
		Pllaka r = grid.merrPllaka(s.getXPlace() + 1, s.getYPlace()); // right
		Pllaka d = grid.merrPllaka(s.getXPlace(), s.getYPlace() + 1); // down
		Pllaka l = grid.merrPllaka(s.getXPlace() - 1, s.getYPlace()); // left
		//check if current inhabited pllakatype maches pallakatype above, right, down or left
		if (s.getType() == u.getType() && directions[1] != 1) {
			dir[0] = 0;
			dir[1] = -1;
		} else if (s.getType() == r.getType() && directions[0] != -1) {
			dir[0] = 1;
			dir[1] = 0;
		} else if (s.getType() == d.getType() && directions[1] != -1) {
			dir[0] = 0;
			dir[1] = 1;
		} else if (s.getType() == l.getType() && directions[0] != 1) {
			dir[0] = -1;
			dir[1] = 0;
		} else {
			dir[0] = 2;
			dir[1] = 2;
			//System.out.println("No dierction found");
		}
		return dir;
	}
	
	// take damage from external source
	public void damage(int amount) {
		health -= amount;
		if (health <= 0) {
			die();
			Lojtari.modifyGold(5);
		}
	}
	
	private void die(){
		alive = false;
	}
	
	public void draw(){
		float healthPercentage = health / startHealth;
		// enemy texture 
		VizatoKatrorTex(texture, x, y, width, height);
		// health bar textures
		VizatoKatrorTex(healthBackground, x, y - 16, width, 8);
		VizatoKatrorTex(healthForeground, x, y - 16, TILE_SIZE * healthPercentage, 8);
		VizatoKatrorTex(healthBorder, x, y - 16, width, 8);
	}

	/*
	 * 

	
	public void reduceHiddenHealth(float amount) {
		hiddenHealth -= amount;
	}

	public float getHiddenHealth() {
		return hiddenHealth;
	}
	 */
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

	public float getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getX() {
		return x;
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

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public void setTexture (String textureName) {
		this.texture = QuickLoad(textureName);
	}

	public Pllaka getFilloPllaka() {
		return filloPllaka;
	}

	public void setFilloPllaka(Pllaka filloPllaka) {
		this.filloPllaka = filloPllaka;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}
	public PllakaFusha getTileGrid(){
		return grid;
	}
	public boolean isAlive(){
		return alive;
	}
	

}

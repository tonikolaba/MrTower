package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.*;
import static al.artofsoul.ndihma.Ora.*;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Vala {

	private float timeSinceLastSpawn, spawnTime;
	private Armiku[] armikuLlojet;
	private CopyOnWriteArrayList<Armiku> armikuList;
	private int enemiesPerWave, enemiesSpawned;
	private boolean waveCompleted;

	public Vala(Armiku[] armikuLlojet, float spawnTime, int enemiesPerWave) {
		this.armikuLlojet = armikuLlojet;
		this.spawnTime = spawnTime;
		this.enemiesPerWave = enemiesPerWave;
		this.timeSinceLastSpawn = 0;
		this.enemiesSpawned = 0; 
		this.armikuList = new CopyOnWriteArrayList<Armiku>();
		this.waveCompleted = false;
		
		spawn();
	}

	public void update() {
		//Assume all enemies are dead, until for loop proves otherwise
		boolean allEnemiesDead = true;
		if (enemiesSpawned < enemiesPerWave){
			timeSinceLastSpawn += Delta();
			if (timeSinceLastSpawn > spawnTime) {
				spawn();
				timeSinceLastSpawn = 0;
			}
		}
		for (Armiku e : armikuList) {
			if (e.isAlive()) {
				allEnemiesDead = false;
				e.update();
				e.draw();
			} else
				armikuList.remove(e);
		}
		if (allEnemiesDead)
			waveCompleted = true;
	}

	private void spawn() {
		int enemyChosen = 0;
		Random random = new Random();
		enemyChosen = random.nextInt(armikuLlojet.length);
		
		armikuList.add(new Armiku(armikuLlojet[enemyChosen].getTexture(), armikuLlojet[enemyChosen]
				.getFilloPllaka(), armikuLlojet[enemyChosen].getTileGrid(), TILE_SIZE, TILE_SIZE, armikuLlojet[enemyChosen]
				.getSpeed(), armikuLlojet[enemyChosen].getHealth()));
		enemiesSpawned++;
	}
	
	public boolean isCompleted(){
		return waveCompleted;
	}
	public CopyOnWriteArrayList<Armiku> getArmikuList() {
		return armikuList;
	}
}

package al.artofsoul.data;

public class ValaManager {
	
	private float timeBetweenEnemies;
	private int waveNumber, enemiesPerWave;
	private Armiku[] armikuLlojet;
	private Vala currentWave;
	
	public ValaManager(Armiku[] armikuLlojet, float timeBetweenEnemies, int enemiesPerWave) { 
		this.armikuLlojet = armikuLlojet;
		this.timeBetweenEnemies = timeBetweenEnemies;
		this.enemiesPerWave = enemiesPerWave;
		//this.timeSinceLastWave = 0;
		this.waveNumber = 0;
		this.currentWave = null;
		newWave();
	}
		
	public void update() {
		if(!currentWave.isCompleted())
			currentWave.update();
		else
			newWave();
	}
	
	private void newWave() {
		currentWave = new Vala(armikuLlojet, timeBetweenEnemies, enemiesPerWave);
		waveNumber++;
		System.out.println("begin wave" +waveNumber);
	}
	
	public Vala getCurrentWave() {
		return currentWave;
	}
	
	public int getWaveNumber() {
		return waveNumber;
	}
}

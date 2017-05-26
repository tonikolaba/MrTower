package al.artofsoul.data;


import static al.artofsoul.ndihma.Artist.*;
import static al.artofsoul.ndihma.Ora.*;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public class KullaTop {
	
	private float x, y, timeSinceLastShot, firingSpeed, angle;
	private int width, height, range;
	private Texture baseTexture, cannonTexture;
	private ArrayList<Projectile> projectiles;
	private CopyOnWriteArrayList<Armiku> armiqt;
	private Armiku target;
	private boolean targeted;
	
	
	public KullaTop (Texture baseTexture, Pllaka filloPllaka, int demage, int range, CopyOnWriteArrayList<Armiku> armiqt){
		this.baseTexture = baseTexture;
		this.cannonTexture = QuickLoad("/res/player/topiBazaVog"); //plumi for 64, topiBaza for 32
		this.x = filloPllaka.getX();
		this.y = filloPllaka.getY();
		this.width = (int) filloPllaka.getWidth();
		this.height = (int) filloPllaka.getHeight();
		this.range = range;
		this.timeSinceLastShot = 0;
		this.firingSpeed = 3;
		this.projectiles = new ArrayList<Projectile>();
		this.armiqt = armiqt;
		this.targeted = false;
	}
	
	
	
	private Armiku acquireTarget() {
		Armiku closest = null;
		float closestDistance = 1000;
		for (Armiku e: armiqt) {
			if (isInRange(e) && findDistance(e) < closestDistance) {
				closestDistance = findDistance(e);
				closest = e;
			}
		}
		if (closest != null)
			targeted = true;
		return closest;
	}
	
	private boolean isInRange(Armiku e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		if (xDistance < range && yDistance < range)
			return true;
		return false;
	}
	
	private float findDistance(Armiku e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance;
	}
	
	private float calculateAngle () {
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp) - 90;	
	}
	
	private void shoot(){
		timeSinceLastShot = 0;
		//projectiles.add(new ProjectileIceball(QuickLoad("plumip"), target, x + TILE_SIZE / 2 - TILE_SIZE / 4, y + TILE_SIZE / 2 - TILE_SIZE / 4, 32, 32, 900, 10));
	}
	
	public void updateEnemyLists(CopyOnWriteArrayList<Armiku> newList) {
		armiqt = newList;
	}
	
	public void update(){
		if (!targeted) {
			target = acquireTarget();
		}
		
		if (target == null || target.isAlive() == false)
			targeted = false;
		
		timeSinceLastShot += Delta();
		if(timeSinceLastShot > firingSpeed)
			shoot();
		
		for (Projectile p: projectiles)
			p.update();
		
		angle = calculateAngle();
		draw();
	}
	
	public void draw(){
		VizatoKatrorTex(baseTexture, x, y, width, height);
		VizatoKatrorTexRot(cannonTexture, x, y, width, height, angle);
	}

}

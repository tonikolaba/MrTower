package al.artofsoul.data;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerCannonBlue extends Tower {
	
	public TowerCannonBlue (TowerType type, Pllaka merrPllaka, CopyOnWriteArrayList<Armiku> armiqt) {
		super(type, merrPllaka, armiqt);
	}
	
	@Override
	public void shoot (Armiku target) {
		super.projectiles.add(new ProjectileCannonball(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		//super.target.reduceHiddenHealth(super.type.projectileType.damage);
	}
}

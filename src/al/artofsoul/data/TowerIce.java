package al.artofsoul.data;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerIce extends Tower {

	public TowerIce(TowerType type, Pllaka filloPllaka, CopyOnWriteArrayList<Armiku> armiqt) {
		super(type, filloPllaka, armiqt);
	}
	
	@Override
	public void shoot (Armiku target) {
		super.projectiles.add(new ProjectileIceball(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
	}
}

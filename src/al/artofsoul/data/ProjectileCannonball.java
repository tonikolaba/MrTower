package al.artofsoul.data;

public class ProjectileCannonball extends Projectile {

	public ProjectileCannonball (ProjectileType type, Armiku target, float x, float y, int width, int height) {
		super(type,target, x, y, width, height);
	}
	
	@Override
	public void damage() {
		super.damage();
	}
}

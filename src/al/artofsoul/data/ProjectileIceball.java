package al.artofsoul.data;

public class ProjectileIceball extends Projectile {

	public ProjectileIceball(ProjectileType type, Armiku target, float x, float y, int width, int height) {
		super(type, target, x, y, width, height);
	}
	
	@Override
	public void damage() {
		super.getTarget().setSpeed(4f);
		super.damage();
	}
}

package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.*;

import org.newdawn.slick.opengl.Texture;

public enum TowerType {
	
	CannonRed(new Texture[]{QuickLoad("/res/player/topiBazaVog"), QuickLoad("/res/player/topiRedplumbiVog")}, ProjectileType.CannonBall, 10, 1000, 3, 0),
	CannonBlue(new Texture[]{QuickLoad("/res/player/topiBazaVog"), QuickLoad("/res/player/topiBlueplumbiVog")}, ProjectileType.CannonBall, 30, 1000, 3, 15),
	CannonIce(new Texture[]{QuickLoad("/res/player/topiBazaVog"), QuickLoad("/res/player/topiIceplumbiVog")}, ProjectileType.IceBall, 10, 1000, 3, 20),;
	
	
	Texture[] textures;
	ProjectileType projectileType;
	int damage, range, cost;
	float firingSpeed;
	
	TowerType(Texture[] textures, ProjectileType projectileType, int damage, int range, float firingSpeed, int cost) {
		this.textures = textures;
		this.projectileType = projectileType;
		this.damage = damage;
		this.range = range;
		this.firingSpeed = firingSpeed;
		this.cost = cost;
	}
}

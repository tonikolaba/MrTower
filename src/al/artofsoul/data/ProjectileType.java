package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.*;

import org.newdawn.slick.opengl.Texture;

public enum ProjectileType {
	
	CannonBall(QuickLoad("/res/player/plumbiBlueVog"), 10, 200),
	IceBall(QuickLoad("/res/player/plumbiIceVog"), 6, 450);
	
	Texture texture;
	int damage;
	float speed;
	
	ProjectileType(Texture texture, int damage, float speed) {
		this.texture = texture;
		this.damage = damage;
		this.speed = speed;
	}
}

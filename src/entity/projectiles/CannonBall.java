package entity.projectiles;

import entity.Mob;
import io.Animation;
import io.Texture;
import io.Textures;

public class CannonBall extends Projectile {

	public CannonBall(float x, float y, Mob parent, float dir) {
		super(x, y, parent, dir, new Animation(1, new Texture[] {
				Textures.CANNON_BALL_TEXTURE
		}));
	}

}

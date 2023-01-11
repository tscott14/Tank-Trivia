package entity.projectiles;

import java.util.ArrayList;
import java.util.List;

import entity.Mob;
import entity.tanks.Boss;
import entity.tanks.Player;
import io.Animation;
import render.shaders.ShaderProgram;
import world.Level;
import world.Tile;
import world.Tiles;

public abstract class Projectile extends Mob {
	public static List<Projectile> projectiles = new ArrayList<Projectile>();
	private float dx, dy;
	private Mob parent;

	public Projectile(float x, float y, Mob parent, float dir, Animation texture) {
		super(x * 8, y * 8, 1, 40, 0.25f, texture);
		this.parent = parent;
		dx = (float) Math.cos(dir);
		dy = (float) Math.sin(dir);
		projectiles.add(this);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		transform.pos.x += dx * delta * speed;
		transform.pos.y += dy * delta * speed;
		Tile tile = Level.BossLevel.getTile((int) (transform.pos.x / 2), (int) (transform.pos.y / 2));
		if (tile != null) {
			if (tile.isSolid()) this.destroy();
		}
		if (tile == Tiles.NULLPTR_TILE) {
			this.destroy();
		}
		if (this.isColliding(Player.player, 0.5f) && parent != Player.player) {
			this.destroy();
			Player.player.damage(1);
		}
		if (this.isColliding(Boss.getCurrentBoss(), 2.0f) && parent != Boss.getCurrentBoss()) {
			this.destroy();
			Boss.getCurrentBoss().damage(1);
		}

	}

	@Override
	public void render(ShaderProgram shader) {
		super.render(shader);
	}

	public void destroy() {
		projectiles.remove(this);
	}

	public static void renderProjectiles(ShaderProgram shader) {
		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).render(shader);
	}

	public static void updateProjectiles(float delta) {
		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).update(delta);
	}

	public static void cleanUp() {
		projectiles.clear();
	}

}

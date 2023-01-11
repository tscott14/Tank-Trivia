package entity;

import java.util.ArrayList;
import java.util.List;

import io.Animation;
import model.Model;
import render.Camera;
import render.shaders.ShaderProgram;
import utils.Assets;
import world.Level;
import world.Tile;

public abstract class Mob extends Entity {
	protected static List<Mob> mobs = new ArrayList<Mob>();
	private static Level level = Level.BossLevel;

	protected Animation texture;
	protected Model model;
	protected float speed = 5;
	public float rot = (float) Math.PI;
	protected boolean isDead;
	public int health;

	private int dir = 0;

	public Mob(float x, float y, int health, float speed, float size, Animation texture) {
		super(x, y, size);
		this.speed = speed;
		this.texture = texture;
		this.health = health;
		this.model = new Model(Assets.vertices, Assets.textures, Assets.indices);
		mobs.add(this);
	}

	public void update(float delta) {
		if (health <= 0) kill();
	}

	protected void move(float xa, float ya, float delta) {

		if (xa != 0 && ya != 0) {
			move(xa, 0, delta);
			move(0, ya, delta);
			return;
		}

		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;

		if (!collision(xa, ya, delta)) {
			transform.pos.x += (xa * speed * delta);
			transform.pos.y += (ya * speed * delta);
		}

	}

	private boolean collision(float xa, float ya, float delta) {
		int x = (int) (transform.pos.x);
		int y = (int) (transform.pos.y);
		boolean solid = false;
		// 180 450
		rot %= (Math.PI * 2);
		if (rot < 0) rot = (float) ((Math.PI * 2) + rot);
		System.out.println(Math.toDegrees(rot));
		float r1 = (float) Math.toRadians(0);
		float r2 = (float) Math.toRadians(90);
		float r3 = (float) Math.toRadians(180);
		float r4 = (float) Math.toRadians(270);

		if (rot > r1 && rot < r2) {
			for (int c = 0; c < 4; c++) {
				int xt = (int) ((x + (xa - 0.001)) + c % 2 * 1 + 1) / 2;
				int yt = (int) ((y + (ya + 0.001)) + c / 2 * 1 + 1) / 2;
				Tile t = level.getTile(xt, yt);
				if (t != null) if (t.isSolid()) solid = true;
			}
		} else if (rot > r2 && rot < r3) {
			for (int c = 0; c < 4; c++) {
				int xt = (int) ((x + (xa - 0.001)) + c % 2 * 1 + 1) / 2;
				int yt = (int) ((y + (ya - 0.001)) + c / 2 * 1 + 1) / 2;
				Tile t = level.getTile(xt, yt);
				if (t != null) if (t.isSolid()) solid = true;
			}
		} else if (rot > r3 && rot < r4) {
			for (int c = 0; c < 4; c++) {
				int xt = (int) ((x + (xa + 0.001)) + c % 2 * 1 + 1) / 2;
				int yt = (int) ((y + (ya - 0.001)) + c / 2 * 1 + 1) / 2;
				Tile t = level.getTile(xt, yt);
				if (t != null) if (t.isSolid()) solid = true;
			}
		} else if (rot > r4 && rot < (r1+360)) {
			for (int c = 0; c < 4; c++) {
				int xt = (int) ((x + (xa + 0.001)) + c % 2 * 1 + 1) / 2;
				int yt = (int) ((y + (ya + 0.001)) + c / 2 * 1 + 1) / 2;
				Tile t = level.getTile(xt, yt);
				if (t != null) if (t.isSolid()) solid = true;
			}
		}

		// if (rot > r1 && rot < r2) {
		// for (int c = 0; c < 4; c++) {
		// int xt = (int) ((x + (xa - 0.001)) + c % 2 * 1 + 1) / 2;
		// int yt = (int) ((y + (ya - 0.001)) + c / 2 * 1 + 1) / 2;
		// Tile t = level.getTile(xt, yt);
		// if (t != null) if (t.isSolid()) solid = true;
		// }
		// } else {
		// for (int c = 0; c < 4; c++) {
		// int xt = (int) ((x + (xa + 0.001)) + c % 2 * 1 + 1) / 2;
		// int yt = (int) ((y + (ya + 0.001)) + c / 2 * 1 + 1) / 2;
		// Tile t = level.getTile(xt, yt);
		// if (t != null) if (t.isSolid()) solid = true;
		// }
		// }

		return solid;
	}

	public void render(ShaderProgram shader) {
		shader.start();
		shader.setUniform("sampler", 0);
		shader.setUniform("projection",
				transform.getProjection(Camera.camera.getProjection()).rotate((float) (rot), 0, 0, 1));
		shader.setUniform("scale", size);
		texture.bind();
		model.render();
	}

	public int getDir() {
		return dir;
	}

	public void damage(int amount) {
		health -= amount;
	}

	public void heal(int amount) {
		health += amount;
	}

	public boolean isDead() {
		return isDead;
	}

	public void kill() {
		isDead = true;
	}

	public boolean isColliding(Mob mob, float distance) {
		float dx = Math.abs(mob.getX() - getX());
		float dy = Math.abs(mob.getY() - getY());
		return dx < distance && dy < distance;
	}

}

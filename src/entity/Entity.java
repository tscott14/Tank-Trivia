package entity;

import render.shaders.ShaderProgram;
import world.Level;

public abstract class Entity {

	public Transform transform;
	protected float size;

	public Entity(float x, float y, float size) {
		transform = new Transform();
		transform.scale = Level.BossLevel.getScale();
		this.size = size;
		setPosition(x, y);
	}

	public void setPosition(float x, float y) {
		transform.pos.x = x*size;
		transform.pos.y = y*size;
	}

	public float getX() {
		return transform.pos.x/2;
	}

	public float getY() {
		return transform.pos.y/2;
	}

	public float getSize() {
		return size;
	}

	public abstract void update(float delta);

	public abstract void render(ShaderProgram shader);

}

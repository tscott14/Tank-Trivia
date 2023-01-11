package gui;

import org.joml.Vector2f;

import io.Animation;
import io.Texture;
import model.Model;
import render.shaders.ShaderProgram;
import utils.Assets;
import window.Window;

public class GUI {
	protected float x, y, w, h;
	protected Model model;
	protected Animation texture;
	protected boolean isActive;

	public GUI(float x, float y, float w, float h, Animation texture) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		isActive = true;
		createModel();
		this.texture = texture;
	}

	public GUI(float x, float y, float w, float h, Texture texture) {
		this(x, y, w, h, new Animation(1, texture));
	}

	public void enable() {
		isActive = true;
	}

	public void disable() {
		isActive = false;
	}

	private void createModel() {
		model = new Model(new float[] {
				x - (w / 2), y + (h / 2), 0, //
				x + (w / 2), y + (h / 2), 0, //
				x + (w / 2), y - (h / 2), 0, //
				x - (w / 2), y - (h / 2), 0 //
		}, Assets.textures, Assets.indices);
	}

	public void render(ShaderProgram shader) {
		Vector2f pos = Window.win.getInput().getMousePosition();
		texture.bind(1);
		shader.start();
		shader.setUniform("sampler", 1);
		shader.setUniform("mousePos", new Vector2f(pos.x, pos.y));
		model.render();
		shader.stop();
	}

	public void update() {}

}

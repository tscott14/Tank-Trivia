package gui.managers;

import entity.tanks.Player;
import gui.GUI;
import io.Textures;
import render.shaders.ShaderProgram;

public class HealthBarManager {
	private GUI[] health = new GUI[10];

	public HealthBarManager() {
		for (int i = 0; i < health.length; i++) {
			health[i] = new GUI((i * 0.037f)+0.58f, 0.785f, 0.037f, 0.05f,Textures.HEALTH_LEVEL_GUI_TEXTURE);
		}
	}

	public void render(ShaderProgram shader) {
		for (int i = 0; i < Player.player.health; i++) {
			health[i].render(shader);
		}
	}

	public void update() {
		
	}

}

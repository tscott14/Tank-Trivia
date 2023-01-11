package gui.managers;

import entity.tanks.Boss;
import gui.GUI;
import io.Textures;
import render.shaders.ShaderProgram;

public class BossHealthManager {
	private GUI[] health = new GUI[Boss.getCurrentBoss().health];

	public BossHealthManager() {
		for (int i = 0; i < health.length; i++) {
			health[i] = new GUI((i * 0.007f)+0.58f, 0.585f, 0.007f, 0.05f,Textures.BOSS_HEALTH_GUI_TEXTURE);
		}
	}
	
	public void render(ShaderProgram shader) {
		for (int i = 0; i < Boss.getCurrentBoss().health; i++) {
			health[i].render(shader);
		}
	}
	
public void update() {
		
	}
}

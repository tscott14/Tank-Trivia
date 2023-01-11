package gui.managers;

import entity.tanks.Player;
import gui.GUI;
import io.Textures;
import render.shaders.ShaderProgram;

public class AmmoBarManager {
	private GUI[] ammo = new GUI[32];

	public AmmoBarManager() {
		for (int i = 0; i < ammo.length; i++) {
			ammo[i] = new GUI((i * 0.0115f)+0.57f, 0.335f, 0.0115f, 0.05f,Textures.AMMO_LEVEL_GUI_TEXTURE);
		}
	}

	public void render(ShaderProgram shader) {
		for (int i = 0; i < Player.player.num_of_shots; i++) {
			ammo[i].render(shader);
		}
	}

	public void update() {
		
	}

}

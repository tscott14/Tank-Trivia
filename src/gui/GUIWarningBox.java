package gui;

import io.Animation;
import io.Texture;
import io.Textures;
import render.shaders.ShaderProgram;

public class GUIWarningBox extends GUI {
	private GUIButton YES, NO;
	private boolean beingChecked;
	public int status = 0;
	
	public static final int QUIT_GAME = 1;
	public static final int QUIT_SESSION = 2;
	public static final int DEATH_MESSAGE = 3;
	public static final int WIN_MESSAGE = 3;

	public GUIWarningBox(Texture texture) {
		super(0, 0, 0.75f, 0.75f, texture);
		YES = new GUIButton(-0.15f, -0.15f, 0.15f, 0.15f, Textures.YES_GUI_TEXTURE);
		NO = new GUIButton(0.15f, -0.15f, 0.15f, 0.15f, Textures.NO_GUI_TEXTURE);
	}

	public boolean yesPressed() {
		return YES.isPressed();
	}

	public boolean noPressed() {
		return NO.isPressed();
	}

	public boolean isBeingChecked() {
		return beingChecked;
	}

	public void setBeingChecked(boolean bc) {
		this.beingChecked = bc;
	}

	public void update() {
		if (beingChecked) {
			super.update();
			YES.update();
			NO.update();
		}
	}

	public void setTexture(Texture texture) {
		this.texture = new Animation(1, texture);
	}

	public void render(ShaderProgram shader) {
		if (beingChecked) {
			super.render(shader);
			YES.render(shader);
			NO.render(shader);
		}
	}
	
	public void setStatus(int i){
		this.status = i;
	}
	
	public int getStatus(){
		return status;
	}

}

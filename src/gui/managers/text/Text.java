package gui.managers.text;

import gui.GUI;
import io.Animation;
import io.Textures;
import render.shaders.ShaderProgram;
import window.Window;

public class Text {
	private static final String CHARACTER_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.!? 0123456789";

	private String text;
	private GUI[] characters;
	private float XX, YY;

	public Text(String text, float xx, float yy, float scansize, float size) {
		this.text = text;
		this.XX = xx;
		this.YY = yy;
		characters = new GUI[text.length()];

		float realitiveToScreenSize = size * (1.0f / Window.win.getHeight());

		float xCoord = 0;
		float yCoord = 0;
		for (int i = 0; i < characters.length; i++) {

			characters[i] = new GUI((xCoord / (size * 1.3f)) + xx, -(yCoord / size) + yy, realitiveToScreenSize,
					realitiveToScreenSize, getTextureFromCharacter(text.charAt(i)));

			xCoord++;

			if (xCoord > scansize) xCoord = 0;
			if (xCoord == 0) yCoord+=1.2f;
		}

	}

	protected Animation getTextureFromCharacter(char character) {
		int ind = CHARACTER_TABLE.indexOf(character);
		if (ind != -1 && !(ind >= CHARACTER_TABLE.length()))
			return new Animation(1, Textures.CHARACTERS_ANIMATION[CHARACTER_TABLE.indexOf(character)]);
		return new Animation(1, Textures.ERROR_TEXTURE);
	}

	public void render(ShaderProgram shader) {
		for (int i = 0; i < text.length(); i++) {
			GUI c = characters[i];
			if (c != null) c.render(shader);
		}
	}

	public float getXX() {
		return XX;
	}

	public float getYY() {
		return YY;
	}

}

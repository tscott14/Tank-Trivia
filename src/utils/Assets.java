package utils;

import model.Model;
import window.Window;

public final class Assets {
	private Assets() {}

	public static final String TILE_SHADER_PATH = "tile";
	public static final String GUI_SHADER_PATH = "gui";
	public static final String ENTITY_SHADER_PATH = "entity";
	public static final String FONT_SHADER_PATH = "font";

	public static final float ASPECT_RATIO = (float) Window.win.getWidth() / (float) Window.win.getHeight();

	public static final float[] vertices = //
			{ //
					1, -1, 0, //
					-1, -1, 0, //
					-1, 1, 0, //
					1, 1, 0//
			};
	public static final float[] textures = //
			{ //
					0, 0, //
					1, 0, //
					1, 1, //
					0, 1//
			};
	public static final int[] indices = //
			{ //
					0, 1, 2, //
					2, 3, 0//
			};
	public static Model model = new Model(Assets.vertices, Assets.textures, Assets.indices);

}

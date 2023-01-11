package world;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;

import io.Animation;
import io.Texture;
import render.Camera;
import render.shaders.ShaderProgram;

public class Tile {
	public static byte num_of_tiles = 0;
	public static List<Tile> tiles = new ArrayList<Tile>();
	private static Camera cam = Camera.camera;

	private int id;
	private boolean solid;
	private Animation texture;

	public Tile(Animation texture) {
		this.texture = texture;
		this.id = num_of_tiles++;
		this.solid = false;
		tiles.add(this);
	}
	
	public Tile(Texture texture) {
		this(new Animation(1, texture));
	}

	public void render(int x, int y, ShaderProgram shader, Matrix4f worldTranslation) {
		TileRenderer.renderTile(this, x, y, shader, worldTranslation, cam);
	}

	public Animation getTexture() {
		return texture;
	}

	public Tile setSolid() {
		this.solid = true;
		return this;
	}

	public boolean isSolid() {
		return solid;
	}

	public int getId() {
		return id;
	}

	public static int getTileLimit() {
		return num_of_tiles;
	}

}

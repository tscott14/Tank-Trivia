package world;

import org.joml.Matrix4f;

import render.Camera;
import render.shaders.ShaderProgram;
import utils.Assets;

public abstract class TileRenderer {

	public static void init() {}

	public static void renderTile(Tile tile, int x, int y, ShaderProgram shader, Matrix4f world, Camera cam) {
		shader.start();
		Matrix4f tile_pos = new Matrix4f().translate(x * 2, y * 2, 0);
		Matrix4f target = new Matrix4f().identity();

		cam.getProjection().mul(world, target);
		target.mul(tile_pos);

		shader.setUniform("sampler", 0);
		shader.setUniform("projection", target);

		tile.getTexture().bind(0);


		Assets.model.render();

	}
}

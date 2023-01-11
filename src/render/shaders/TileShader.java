package render.shaders;

import static utils.Assets.TILE_SHADER_PATH;

public class TileShader extends ShaderProgram {

	public TileShader() {
		super(TILE_SHADER_PATH);
	}

	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "uvs");
	}

}

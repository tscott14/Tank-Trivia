package render.shaders;

import static utils.Assets.*;

public class GUIShader extends ShaderProgram {

	public GUIShader() {
		super(GUI_SHADER_PATH);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "uvs");
	}

}

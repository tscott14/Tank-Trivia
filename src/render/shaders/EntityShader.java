package render.shaders;
import static utils.Assets.ENTITY_SHADER_PATH;;
public class EntityShader extends ShaderProgram{

	public EntityShader() {
		super(ENTITY_SHADER_PATH);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "uvs");
	}

	

}

package render.shaders;

import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import utils.Utils;

public abstract class ShaderProgram {
	private int program;
	private int vs, fs;

	private static FloatBuffer buffer = BufferUtils.createFloatBuffer(4 * 4);
	private static final String shaderPath = "./shaders/";

	public ShaderProgram(String path) {
		vs = loadShader(path + ".vs", GL_VERTEX_SHADER);
		fs = loadShader(path + ".fs", GL_FRAGMENT_SHADER);
		program = glCreateProgram();
		glAttachShader(program, vs);
		glAttachShader(program, fs);
		bindAttributes();
		glLinkProgram(program);
		glValidateProgram(program);
	}

	private int getUniformLocation(String name) {
		int res = glGetUniformLocation(program, name);
		if (res != -1) return res;
		else {
			System.err.println("Could not find uniform! (" + name + ")");
			System.exit(1);
		}
		return -69;
	}

	public void setUniform(String name, int value) {
		glUniform1i(getUniformLocation(name), value);
	}

	public void setUniform(String name, float value) {
		glUniform1f(getUniformLocation(name), value);
	}

	public void setUniform(String name, Matrix4f value) {
		value.get(buffer);
		glUniformMatrix4fv(getUniformLocation(name), false, buffer);
	}

	public void setUniform(String name, Vector2f value) {
		glUniform2f(getUniformLocation(name), value.x, value.y);
	}

	public void start() {
		glUseProgram(program);
	}

	public void stop() {
		glUseProgram(0);
	}

	public void cleanUp() {
		stop();
		glDetachShader(program, vs);
		glDetachShader(program, fs);
		glDeleteShader(vs);
		glDeleteShader(fs);
		glDeleteProgram(program);
	}

	protected abstract void bindAttributes();

	protected void bindAttribute(int attrib, String variableName) {
		glBindAttribLocation(program, attrib, variableName);
	}

	private static int loadShader(String file, int type) {
		String src = Utils.readFile(shaderPath + file);
		int id = glCreateShader(type);
		glShaderSource(id, src);
		glCompileShader(id);
		if (glGetShaderi(id, GL_COMPILE_STATUS) != GL_TRUE) {
			System.out.println(glGetShaderInfoLog(id, 500));
			System.err.println("Could not compile shader! " + file);
			System.exit(1);
		}
		return id;
	}

}

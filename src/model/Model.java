package model;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;

import io.Texture;

public class Model {
	private static List<Integer> vbos = new ArrayList<Integer>();

	private int draw_count;
	private int v_id;
	private int t_id;
	private int i_id;

	public Model(float[] verticies, float[] uvs, int[] indices) {
		draw_count = indices.length;
		v_id = createBufferObject(GL_ARRAY_BUFFER, verticies);
		t_id = createBufferObject(GL_ARRAY_BUFFER, uvs);
		i_id = createBufferObject(GL_ELEMENT_ARRAY_BUFFER, indices);
	}

	private int createBufferObject(int type, float[] data) {
		int id = glGenBuffers();
		vbos.add(id);
		glBindBuffer(type, id);
		glBufferData(type, createFloatBuffer(data), GL_STATIC_DRAW);
		glBindBuffer(type, 0);
		return id;
	}

	private int createBufferObject(int type, int[] data) {
		int id = glGenBuffers();
		vbos.add(id);
		glBindBuffer(type, id);
		glBufferData(type, createIntBuffer(data), GL_STATIC_DRAW);
		glBindBuffer(type, 0);
		return id;
	}

	private IntBuffer createIntBuffer(int[] data) {
		IntBuffer res = BufferUtils.createIntBuffer(data.length);
		res.put(data);
		res.flip();
		return res;
	}

	private FloatBuffer createFloatBuffer(float[] data) {
		FloatBuffer res = BufferUtils.createFloatBuffer(data.length);
		res.put(data);
		res.flip();
		return res;
	}

	public int getDraw_count() {
		return draw_count;
	}

	public void render() {
		if (Texture.hasSwapedTextures) {
			Texture.hasSwapedTextures = false;
			glEnableVertexAttribArray(0);
			glEnableVertexAttribArray(1);
			{
				glBindBuffer(GL_ARRAY_BUFFER, v_id);
				glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

				glBindBuffer(GL_ARRAY_BUFFER, t_id);
				glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
				{
					glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
					glDrawElements(GL_TRIANGLES, draw_count, GL_UNSIGNED_INT, 0);
					glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
				}
				glBindBuffer(GL_ARRAY_BUFFER, 0);
			}
			glDisableVertexAttribArray(1);
			glDisableVertexAttribArray(0);
		}
	}

	public static void cleanUp() {
		for (int v : vbos)
			glDeleteBuffers(v);
	}
}

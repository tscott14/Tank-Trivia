package render;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import window.Window;

public class Camera {
	public static Camera camera = new Camera(Window.win.getWidth(), Window.win.getHeight());

	private Vector3f position;
	private Matrix4f projection;

	public Camera(int width, int height) {
		position = new Vector3f();
		projection = new Matrix4f().setOrtho2D(-width / 2, width / 2, height / 2, -height / 2);
	}

	public void setPosition(float x, float y, float z) {
		this.position.x = x;
		this.position.y = y;
		this.position.z = z;
	}

	public void setPosition(Vector3f pos) {
		setPosition(pos.x, pos.y, pos.z);
	}

	public void changePosition(float x, float y, float z) {
		this.position.x += x;
		this.position.y += y;
		this.position.z += z;
	}

	public Vector3f getPosition() {
		return position;
	}

	public Matrix4f getProjection() {
		return projection.translate(position, new Matrix4f());
	}

	public void reset() {
		position.set(0);
	}
}

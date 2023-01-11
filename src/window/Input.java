package window;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.DoubleBuffer;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

public class Input {
	private long window;

	private boolean keys[];
	private boolean buttons[];
	private float mouseX, mouseY;

	public Input(long window) {
		this.window = window;
		this.keys = new boolean[GLFW_KEY_LAST];
		this.buttons = new boolean[GLFW_MOUSE_BUTTON_LAST];
		for (int i = 0; i < GLFW_KEY_LAST; i++)
			keys[i] = false;
		glfwSetCursorPos(window, 100, 100);

	}

	public boolean isKeyDown(int key) {
		return glfwGetKey(window, key) == 1;
	}

	public boolean isKeyPressed(int key) {
		return (isKeyDown(key) && !keys[key]);
	}

	public boolean isKeyReleased(int key) {
		return (!isKeyDown(key) && keys[key]);
	}

	public boolean isMouseButtonDown(int b) {
		return glfwGetMouseButton(window, b) == 1;
	}

	public boolean isMouseButtonPressed(int b) {
		return (isMouseButtonDown(b) && !buttons[b]);
	}

	public boolean isMouseButtonReleased(int b) {
		return (!isMouseButtonDown(b) && buttons[b]);
	}

	public Vector2f getMousePosition() {
		mouseX = getCursorPosX();
		mouseY = getCursorPosY();
		if (mouseX < 0) mouseX = 0;
		if (mouseY < 0) mouseY = 0;
		mouseX -= (Window.win.getWidth() / 2);
		mouseY -= (Window.win.getHeight() / 2);
		return new Vector2f(mouseX, mouseY);
	}

	private float getCursorPosX() {
		DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window, posX, null);
		return (float) posX.get(0);
	}

	private float getCursorPosY() {
		DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window, null, posY);
		return (float) posY.get(0);
	}

	public void update() {
		for (int i = 0; i < GLFW_KEY_LAST; i++)
			keys[i] = isKeyDown(i);
		for (int i = 0; i < GLFW_MOUSE_BUTTON_LAST; i++)
			buttons[i] = isMouseButtonDown(i);
	}

}

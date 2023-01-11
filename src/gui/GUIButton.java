package gui;

import io.Texture;
import window.Input;
import window.Window;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector2f;

public class GUIButton extends GUI {
	private Input in = Window.win.getInput();
	private float xx = x * Window.win.getWidth() / 2.0f;
	private float yy = y * Window.win.getHeight() / 2.0f;
	private float ww = w * Window.win.getWidth() / 2.0f;
	private float hh = h * Window.win.getHeight() / 2.0f;

	public GUIButton(float x, float y, float w, float h, Texture texture) {
		super(x, y, w, h, texture);
	}

	public void update() {
		super.update();
	}

	public boolean isPressed() {
		return isHovered() && in.isMouseButtonReleased(GLFW_MOUSE_BUTTON_1);
	}

	public boolean isHovered() {
		Vector2f pos = in.getMousePosition();
		float dx = Math.abs(xx - pos.x);
		float dy = Math.abs(-pos.y - yy);
		boolean res = (dx < (ww / 2)) && (dy < (hh / 2));
		return res;
	}

}

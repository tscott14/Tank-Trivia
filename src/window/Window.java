package window;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;

public class Window {
	private long window;

	public static Window win;

	private int width, height;
	private String title;

	private Input input;

	public Window(int width, int height) {
		setSize(width, height);
	}

	public Window(String title) {
		setSize(glfwGetVideoMode(glfwGetPrimaryMonitor()).width(), glfwGetVideoMode(glfwGetPrimaryMonitor()).height());
		win = this;
		createWindow(title);
	}

	public void createWindow(String title) {
		this.title = title;
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		window = glfwCreateWindow(width, height, title, glfwGetPrimaryMonitor(), 0);
		if (window == 0) throw new IllegalStateException("Failed to create window!");
		glfwSetWindowPos(window, (glfwGetVideoMode(glfwGetPrimaryMonitor()).width() - width) / 2,
				(glfwGetVideoMode(glfwGetPrimaryMonitor()).height() - height) / 2);
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		// setCallbacks();
		input = new Input(window);
	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}

	public void swapBuffers() {
		glfwSwapBuffers(window);
	}

	public void update() {
		input.update();
		glfwPollEvents();
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getGameCanvasSize() {
		return height;
	}

	public long getWindowID() {
		return window;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Input getInput() {
		return input;
	}

	public static void setCallbacks() {
		glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
	}

}

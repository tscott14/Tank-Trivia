package game;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import io.Texture;
import model.Model;
import utils.Timer;
import window.Window;

public class Main {
	// https://www.youtube.com/watch?v=VH9KAhjXVFM&list=PLILiqflMilIxta2xKk2EftiRHD4nQGW0u
	public static void main(String[] args) {
		if (!glfwInit()) { throw new RuntimeException("Could not init GLFW!"); }
		Window win = new Window("WWII Review!");
		GL.createCapabilities();
		Game.init();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glClearColor(0.0f, 0.0f, 0.0f, 1);
		double frame_cap = 1.0 / 60.0;
		double time = Timer.getTime();
		double frame_time = 0;
		@SuppressWarnings("unused")
		int frames = 0;
		double unprocessed = 0;
		while (!win.shouldClose()) {
			boolean can_render = false;
			double time_2 = Timer.getTime();
			double passed = time_2 - time;
			unprocessed += passed;
			frame_time += passed;
			time = time_2;
			while (unprocessed >= frame_cap) {
				win.update();
				Game.update((float) frame_cap);
				unprocessed -= frame_cap;
				can_render = true;
				if (frame_time >= 1.0) {
					frame_time = 0;
					//System.out.println("FPS: " + frames);
					frames = 0;
				}
			}
			if (can_render) {
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				Game.render();
				frames++;
				win.swapBuffers();
			}
		}
		Texture.cleanUp();
		Model.cleanUp();
		glfwTerminate();
	}
}

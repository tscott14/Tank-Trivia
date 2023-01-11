package io;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;

import utils.Utils;

public class Texture {
	private static List<Integer> textures = new ArrayList<Integer>();
	protected static List<String> texture_names = new ArrayList<String>();
	private static Texture bindedTexture;
	public static boolean hasSwapedTextures;
	private boolean smooth;
	private final int id;
	private int width, height;

	protected Texture() {
		id = 0;
		System.exit(1);
	}

	public Texture(String path, boolean smooth) {
		this.smooth = smooth;
		texture_names.add(path);
		id = loadImageFromFile(path);
	}

	public static Texture[] loadTexture(String path, int frames, boolean smooth) {
		Texture[] t = new Texture[frames];

		for (int i = 0; i < frames; i++) {
			t[i] = new Texture(path + "/" + path + "_" + (toFourDecimals(i)), smooth);
		}

		return t;
	}

	private static String toFourDecimals(int i) {
		String ind = i + "";
		String res = "";
		if (ind.length() == 0) return null;
		if (ind.length() == 1) res = "000" + i;
		if (ind.length() == 2) res = "00" + i;
		if (ind.length() == 3) res = "0" + i;
		if (ind.length() == 4) res = ind;
		if (ind.length() > 4) return null;
		return res;
	}

	public int loadImageFromFile(String path) {
		BufferedImage bi;
		bi = Utils.loadImage("./res/textures/" + path + ".png");
		this.width = bi.getWidth();
		this.height = bi.getHeight();
		int[] pixels_raw = new int[width * height];
		bi.getRGB(0, 0, width, height, pixels_raw, 0, width);
		return createTexture(convertIntegerArrayToByteBuffer(pixels_raw));
	}

	private ByteBuffer convertIntegerArrayToByteBuffer(int[] pixels_raw) {
		ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int pixel = pixels_raw[j + (i * width)];
				pixels.put((byte) ((pixel >> 16) & 0xff)); // RED
				pixels.put((byte) ((pixel >> 8) & 0xff)); // GREEN
				pixels.put((byte) ((pixel >> 0) & 0xff)); // BLUE
				pixels.put((byte) ((pixel >> 24) & 0xff)); // ALPHA
			}
		}
		pixels_raw = null;
		pixels.flip();
		return pixels;
	}

	public int createTexture(ByteBuffer pixels) {
		int id = glGenTextures();
		textures.add(id);
		glBindTexture(GL_TEXTURE_2D, id);
		if (smooth) {
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		} else {
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		}
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
		return id;
	}

	public int getId() {
		return id;
	}

	public void bind(int sampler) {
		if (!(sampler >= 0 && sampler <= 31)) return;
		hasSwapedTextures = true;
		glActiveTexture(GL_TEXTURE0 + sampler);
		glBindTexture(GL_TEXTURE_2D, id);
	}

	public static void cleanUp() {
		for (int t : textures)
			glDeleteTextures(t);
	}

	public static Texture getBindedTexture() {
		return bindedTexture;
	}

}

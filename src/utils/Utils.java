package utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Utils {

	public static String readFile(String path) {
		StringBuilder res = new StringBuilder();
		BufferedReader read = null;
		try {
			read = new BufferedReader(new FileReader(new File(path)));
			String line = null;
			while ((line = read.readLine()) != null) {
				res.append(line).append("\n");
			}
			read.close();
			return res.toString();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.print("Could not load file! : " + path);
			System.exit(1);
		}
		return null;
	}

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Could not load file! " + path);
			System.exit(1);
		}
		return null;
	}

	public static int[] removeAlpha(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int a = pixels[i];
			pixels[i] = (a & 0xff0000) | (a & 0xff00) | (a & 0xff);
		}
		return pixels;
	}

	public static float pythag(float x1, float y1, float x2, float y2) {
		float x = x1 - x2;
		float y = y1 - y2;
		return (float) Math.sqrt(x * x + y * y);
	}

}

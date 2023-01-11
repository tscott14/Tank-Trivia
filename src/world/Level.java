package world;

import java.awt.image.BufferedImage;

import org.joml.Matrix4f;

import entity.tanks.Boss;
import entity.tanks.Player;
import render.Camera;
import render.shaders.ShaderProgram;
import utils.Utils;
import window.Window;

public class Level {

	public static Level BossLevel = Level.loadLevelFromFile("arena", Window.win);;

	private int[] worldTiles;
	private int width, height;
	private int scale;

	@SuppressWarnings("unused")
	private static Window win;
	private static Camera cam = Camera.camera;

	private final Matrix4f worldTranslation;

	public Level(int w, int h, Window win) {
		width = w;
		height = h;
		scale = (int) (Window.win.getWidth() / (1920.0 / 48.0));
		worldTiles = new int[width * height];
		Level.win = win;

		worldTranslation = new Matrix4f().scale(scale);
	}

	public static Level loadLevelFromFile(String path, Window win) {
		BufferedImage img = Utils.loadImage("./res/levels/" + path + ".png");
		int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
		Level world = new Level(img.getWidth(), img.getHeight(), win);
		Utils.removeAlpha(pixels);
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				world.setTile(Tiles.getTile(pixels[x + y * img.getWidth()]), x, y);
			}
		}

		return world;
	}

	public void render(ShaderProgram shader) {
		int pX = (int) -((cam.getPosition().x / scale) / 2);
		int pY = (int) -((cam.getPosition().y / scale) / 2);
		int x0 = pX - 10;
		int y0 = pY - 8;
		int x1 = pX + 12;
		int y1 = pY + 10;

		for (int i = y0; i < y1; i++) {
			for (int j = x0; j < x1; j++) {
				int index = (j + i * width);
				Tile tile;
				if (j < 0 || i < 0 || j >= width || i >= height) tile = Tiles.BRICK_TILE;
				else tile = Tiles.getTile(worldTiles[index]);
				if (tile != null) tile.render(j, i, shader, worldTranslation);
			}

		}
	}

	public void setTile(Tile tile, int x, int y) {
		worldTiles[x + y * width] = tile.getId();
	}

	public Tile getTile(int x, int y) {
		int index = (x + y * width);
		if (index >= 0 && index < worldTiles.length) return Tiles.getTile(worldTiles[index]);
		return Tiles.NULLPTR_TILE;
	}

	public int getScale() {
		return scale;
	}
	
	public static void changeLevel(){
		Boss.currentBoss=Math.min(Boss.currentBoss+1, 2);
		Player.player.reset();
		Boss.reset();
		
	}

	private static void win() {
		System.out.println("You win!");
	}

	public static void restart() {
		Boss.reset();
		Player.player.reset();
	}

}

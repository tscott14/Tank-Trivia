package world;

import static io.Textures.*;

public final class Tiles {

	public static final Tile NULLPTR_TILE = new Tile(ERROR_TEXTURE);
	public static final Tile STONE_TILE = new Tile(STONE_TEXTURE).setSolid();
	public static final Tile DIRT_TILE = new Tile(DIRT_TEXTURE);
	public static final Tile GRASS_TILE = new Tile(GRASS_TEXTURE);
	public static final Tile SAND_TILE = new Tile(SAND_TEXTURE);
	public static final Tile STONEBRICK_TILE = new Tile(STONEBRICK_TEXTURE).setSolid();
	public static final Tile BRICK_TILE = new Tile(BRICK_TEXTURE).setSolid();
	public static final Tile CRATE_TILE = new Tile(CRATE_TEXTURE).setSolid();
	public static final Tile WATER_TILE = new Tile(WATER_TEXTURE);
	public static final Tile LAVA_TILE = new Tile(LAVA_TEXTURE);

	private static final int NULLPTR_COL = 0x0;
	private static final int STONE_COL = 0x1;
	private static final int DIRT_COL = 0x2;
	private static final int GRASS_COL = 0x3;
	private static final int SAND_COL = 0x4;
	private static final int STONEBRICK_COL = 0x5;
	private static final int BRICK_COL = 0x6;
	private static final int CRATE_COL = 0x7;
	private static final int WATER_COL = 0x8;
	private static final int LAVA_COL = 0x9;

	public static Tile getTile(int color) {
		if (color < 0 || color > LAVA_COL) return NULLPTR_TILE;

		switch (color) //
		{
		case NULLPTR_COL:
			return NULLPTR_TILE;
		case STONE_COL:
			return STONE_TILE;
		case DIRT_COL:
			return DIRT_TILE;
		case GRASS_COL:
			return GRASS_TILE;
		case SAND_COL:
			return SAND_TILE;
		case STONEBRICK_COL:
			return STONEBRICK_TILE;
		case BRICK_COL:
			return BRICK_TILE;
		case CRATE_COL:
			return CRATE_TILE;
		case WATER_COL:
			return WATER_TILE;
		case LAVA_COL:
			return LAVA_TILE;
		default:
			System.out.println("Could not find colorToTile " + color);
			System.exit(1);
			break;
		}

		return null;
	}
}

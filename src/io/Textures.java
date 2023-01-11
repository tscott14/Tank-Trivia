package io;

public final class Textures extends Texture {

	public static final Texture ERROR_TEXTURE = new Texture("tiles/nullptr", false);
	public static final Texture STONE_TEXTURE = new Texture("tiles/stone", false);
	public static final Texture DIRT_TEXTURE = new Texture("tiles/dirt", false);
	public static final Texture GRASS_TEXTURE = new Texture("tiles/grass", false);
	public static final Texture SAND_TEXTURE = new Texture("tiles/sand", false);
	public static final Texture STONEBRICK_TEXTURE = new Texture("tiles/stonebrick", false);
	public static final Texture BRICK_TEXTURE = new Texture("tiles/brick", false);
	public static final Texture CRATE_TEXTURE = new Texture("tiles/crate", false);
	public static final Texture WATER_TEXTURE = new Texture("tiles/water", false);
	public static final Texture LAVA_TEXTURE = new Texture("tiles/lava", false);

	public static final Texture TANK_CANNON_TEXTURE = new Texture("player_cannon", false);
	public static final Texture CANNON_BALL_TEXTURE = new Texture("cannon_ball", false);

	public static final Texture PLAY_BUTTON_TEXTURE = new Texture("gui/play", true);
	public static final Texture HOW_TO_PLAY_BUTTON_TEXTURE = new Texture("gui/howtoplaybutton", true);
	public static final Texture QUTI_BUTTON_TEXTURE = new Texture("gui/quit", true);
	public static final Texture BACK_TO_START_BUTTON_TEXTURE = new Texture("gui/backtostartbutton", true);
	public static final Texture QUIT_GAME_WARNING_BUTTON_TEXTURE = new Texture("gui/quitgamepopup", true);
	public static final Texture DEAD_WARNING_BUTTON_TEXTURE = new Texture("gui/dead", true);
	public static final Texture WIN_GUI_TEXTURE = new Texture("gui/win", true);

	public static final Texture YES_GUI_TEXTURE = new Texture("gui/yes", true);
	public static final Texture NO_GUI_TEXTURE = new Texture("gui/no", true);
	public static final Texture LEAVE_SESSION_GUI_TEXTURE = new Texture("gui/leavesessionpopup", true);
	public static final Texture STATUSBAR_GUI_TEXTURE = new Texture("gui/statusbar", true);
	public static final Texture HOW_TO_PLAY_GUI_TEXTURE = new Texture("gui/howtoplaymenu", false);

	public static final Texture HEALTH_BAR_GUI_TEXTURE = new Texture("gui/healthbar", false);
	public static final Texture AMMO_BAR_GUI_TEXTURE = new Texture("gui/ammobar", false);
	public static final Texture HEALTH_LEVEL_GUI_TEXTURE = new Texture("gui/healthlevel", false);
	public static final Texture BOSS_HEALTH_GUI_TEXTURE = new Texture("gui/bosshealth", false);
	public static final Texture AMMO_LEVEL_GUI_TEXTURE = new Texture("gui/ammolevel", false);

	public static final Texture STATUS_BORDER_GUI_TEXTURE = new Texture("gui/statusborder", false);
	public static final Texture STATUS_BACKGROUND_GUI_TEXTURE = new Texture("gui/StatusBarBackground", false);
	public static final Texture QUESTION_BOARD_GUI_TEXTURE = new Texture("gui/questionsboard", false);
	public static final Texture CHECK_BOX_GUI_TEXTURE = new Texture("gui/checkBox", false);
	public static final Texture BOSS_HEALTH_BAR_GUI_TEXTURE = new Texture("gui/bossHealthBar", false);

	public static final Texture GERMANY_BOSS_TEXTURE = new Texture("germany", false);
	public static final Texture ITALY_BOSS_TEXTURE = new Texture("italy", false);
	public static final Texture JAPANESE_BOSS_TEXTURE = new Texture("japan", false);

	public static final Texture[] PLAYER_ANIMATION = loadTexture("player", 3, false);
	public static final Texture[] EARTH_ANIMATION = loadTexture("earth", 30, true);

	public static final Texture[] CHARACTERS_ANIMATION = loadTexture("font", 66, true);

	public static final Texture getTexture(String tex) {
		String res = tex;
		switch (res.toLowerCase()) {
		case "stone":
			return STONE_TEXTURE;
		case "dirt":
			return DIRT_TEXTURE;
		case "grass":
			return GRASS_TEXTURE;
		case "sand":
			return SAND_TEXTURE;
		case "stonebrick":
			return STONEBRICK_TEXTURE;
		case "brick":
			return BRICK_TEXTURE;
		case "crate":
			return CRATE_TEXTURE;
		case "water":
			return WATER_TEXTURE;
		case "lava":
			return LAVA_TEXTURE;
		default:
			return ERROR_TEXTURE;
		}
	}

	public static final void loadTextures() {
		for (String s : texture_names)
			System.out.println("Loaded [TEXTURE] : " + s.toUpperCase());
	}

}

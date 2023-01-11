package gui.managers;

import gui.GUI;
import gui.managers.questions.QuestionManager;
import io.Textures;
import render.shaders.ShaderProgram;

public class StatusBarManager {

	private GUI healthBar;
	private GUI ammoBar;
	private GUI bossHealthBar;
	private GUI statusBarBorder;
	private GUI statusBarBackground;
	private QuestionManager qm;
	private HealthBarManager hbm;
	private BossHealthManager bhm;
	private AmmoBarManager abm;

	public StatusBarManager() {
		qm = new QuestionManager();
		bossHealthBar = new GUI(0.75f, 0.60f, 0.4f, 0.1f, Textures.BOSS_HEALTH_BAR_GUI_TEXTURE);
		healthBar = new GUI(0.75f, 0.80f, 0.4f, 0.1f, Textures.HEALTH_BAR_GUI_TEXTURE);
		ammoBar = new GUI(0.75f, 0.35f, 0.4f, 0.1f, Textures.AMMO_BAR_GUI_TEXTURE);
		statusBarBackground = new GUI(0.75f, 0, 0.5f, 2, Textures.STATUS_BACKGROUND_GUI_TEXTURE);
		statusBarBorder = new GUI(0.505f, 0, 0.01f, 2, Textures.STATUS_BORDER_GUI_TEXTURE);
		hbm = new HealthBarManager();
		abm = new AmmoBarManager();
		bhm = new BossHealthManager();
	}

	public void render(ShaderProgram shader) {
		statusBarBackground.render(shader);
		healthBar.render(shader);
		ammoBar.render(shader);
		bossHealthBar.render(shader);
		statusBarBorder.render(shader);

		hbm.render(shader);
		abm.render(shader);
		bhm.render(shader);

		qm.render(shader);
	}

	public void update() {
		healthBar.update();
		ammoBar.update();
		statusBarBorder.update();
		statusBarBackground.update();
		bossHealthBar.update();
		qm.update();
		hbm.update();
		abm.update();
		bhm.update();
	}

}

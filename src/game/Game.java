package game;

import static gui.GUIWarningBox.*;

import org.lwjgl.glfw.GLFW;

import audio.Sound;
import entity.projectiles.Projectile;
import entity.tanks.Boss;
import entity.tanks.Player;
import gui.GUI;
import gui.GUIButton;
import gui.GUIWarningBox;
import gui.managers.StatusBarManager;
import io.Animation;
import io.Textures;
import render.Camera;
import render.shaders.EntityShader;
import render.shaders.GUIShader;
import render.shaders.TileShader;
import window.Window;
import world.Level;
import world.TileRenderer;

public abstract class Game {
	private static Window win;
	private static TileShader tileShader;
	private static GUIShader guiShader;
	private static EntityShader entityShader;
	private static Player player;
	private static boolean paused;

	private static GUI earthGUI;
	private static GUI howToPlayGUI;

	private static StatusBarManager statusBar;

	private static GUIButton startButton;
	private static GUIButton howToPlayButton;
	private static GUIButton quit;

	private static GUIButton backToStartButton;
	private static GUIWarningBox warningBox;
	
	private static Sound bg = new Sound("./res/Music.wav");
	
	private static enum States {
		START, HOWTOPLAY, GAME, NULL
	};

	private static States state = States.NULL;

	public static void init() {
		//Textures.loadTextures();

		win = Window.win;
		TileRenderer.init();
		tileShader = new TileShader();
		guiShader = new GUIShader();
		entityShader = new EntityShader();
		warningBox = new GUIWarningBox(Textures.BACK_TO_START_BUTTON_TEXTURE);

		earthGUI = new GUI(0.4f, 0, 1.2f,
				(float) (1.2 * ((double) Window.win.getWidth() / (double) Window.win.getHeight())),
				new Animation(30, Textures.EARTH_ANIMATION));
		startButton = new GUIButton(-0.70f, -0.2f, 0.5f, 0.25f, Textures.PLAY_BUTTON_TEXTURE);
		howToPlayButton = new GUIButton(-0.7f, -0.5f, 0.5f, 0.25f, Textures.HOW_TO_PLAY_BUTTON_TEXTURE);
		quit = new GUIButton(-0.7f, -0.8f, 0.5f, 0.25f, Textures.QUTI_BUTTON_TEXTURE);

		statusBar = new StatusBarManager();

		howToPlayGUI = new GUI(0, 0, 2, 2, Textures.HOW_TO_PLAY_GUI_TEXTURE);
		backToStartButton = new GUIButton(0, -0.75f, 0.50f, 0.25f, Textures.BACK_TO_START_BUTTON_TEXTURE);

		player = new Player(Level.BossLevel);

		switchState(States.START);
	}

	public static void render() {
		switch (state) {
		case START:
			earthGUI.render(guiShader);
			startButton.render(guiShader);
			howToPlayButton.render(guiShader);
			quit.render(guiShader);
			if (warningBox.isBeingChecked()) warningBox.render(guiShader);
			break;
		case HOWTOPLAY:
			howToPlayGUI.render(guiShader);
			backToStartButton.render(guiShader);
			break;
		case GAME:
			tileShader.start();
			Level.BossLevel.render(tileShader);
			player.render(entityShader);
			Projectile.renderProjectiles(entityShader);
			Boss.renderCurrentBoss(entityShader);
			statusBar.render(guiShader);
			warningBox.render(guiShader);
			break;
		default:
			System.err.println("Invalid State!");
			System.exit(1);
			break;
		}
	}

	public static void update(float delta) {
		bg.update();
		switch (state) {
		case START:
			startButton.update();
			if (startButton.isPressed()) switchState(States.GAME);
			howToPlayButton.update();
			if (howToPlayButton.isPressed()) switchState(States.HOWTOPLAY);
			quit.update();
			if (quit.isPressed()) {
				warningBox.setBeingChecked(true);
				warningBox.setStatus(QUIT_GAME);
			}
			if (warningBox.isBeingChecked() && warningBox.getStatus() == QUIT_GAME) {
				if (warningBox.yesPressed()) {
					GLFW.glfwSetWindowShouldClose(win.getWindowID(), true);
				} else if (warningBox.noPressed()) {
					warningBox.setBeingChecked(false);
				}
			}
			break;
		case HOWTOPLAY:
			backToStartButton.update();
			if (backToStartButton.isPressed()) switchState(States.START);
			break;
		case GAME:
			if (!paused) {
				if (win.getInput().isKeyReleased(GLFW.GLFW_KEY_P)) Player.player.setPosition(8, 8);;
				player.update(delta);
				Projectile.updateProjectiles(delta);
				Boss.updateCurrentBoss(delta);
				warningBox.update();
			}

			
			if (win.getInput().isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
				paused = true;
				warningBox.setBeingChecked(true);
				warningBox.setStatus(QUIT_SESSION);
			}
			if(warningBox.isBeingChecked() && warningBox.getStatus() == QUIT_SESSION){
				
				if (warningBox.yesPressed()) {
					warningBox.setBeingChecked(false);
					switchState(States.START);
				} else if (warningBox.noPressed()) {
					warningBox.setBeingChecked(false);
					paused = false;
				}
			}
			if (player.isDead()) {
				warningBox.setTexture(Textures.DEAD_WARNING_BUTTON_TEXTURE);
				warningBox.setBeingChecked(true);
				warningBox.setStatus(DEATH_MESSAGE);
			}
			if (warningBox.isBeingChecked() && warningBox.getStatus() == DEATH_MESSAGE) {
				if (warningBox.yesPressed()) {
					warningBox.setBeingChecked(false);
					Level.restart();

				} else if (warningBox.noPressed()) {
					warningBox.setBeingChecked(false);
					paused = false;
					switchState(States.START);
				}
			}

			if (Boss.win) {
				warningBox.setTexture(Textures.WIN_GUI_TEXTURE);
				warningBox.setBeingChecked(true);
				warningBox.setStatus(WIN_MESSAGE);
			}
			
			if (warningBox.isBeingChecked() && warningBox.getStatus() == WIN_MESSAGE) {
				if (warningBox.yesPressed()) {
					warningBox.setBeingChecked(false);
					paused = false;
					switchState(States.START);
					warningBox.setBeingChecked(false);

				} else if (warningBox.noPressed()) {
					GLFW.glfwSetWindowShouldClose(win.getWindowID(), true);
					System.exit(1);
				}
			}

			statusBar.update();
			break;
		default:
			System.err.println("Invalid State!");
			System.exit(1);
			break;
		}
		
	}

	public static void switchState(States state) {
		switch (state) //
		{
		case START:
			if (Game.state == States.GAME) {
				tileShader.stop();
				Camera.camera.reset();
				Boss.reset();
				Boss.currentBoss=0;
				Boss.win=false;
			} else if (Game.state == States.HOWTOPLAY) {}
			warningBox.setTexture(Textures.QUIT_GAME_WARNING_BUTTON_TEXTURE);
			Game.state = States.START;
			break;
		case HOWTOPLAY:
			if (Game.state == States.START) {} else if (Game.state == States.GAME) {}
			Game.state = States.HOWTOPLAY;
			break;
		case GAME:
			if (Game.state == States.START) {} else if (Game.state == States.HOWTOPLAY) {}
			Player.player.reset();
			Projectile.cleanUp();
			warningBox.setTexture(Textures.LEAVE_SESSION_GUI_TEXTURE);
			paused = false;
			Game.state = States.GAME;
			break;
		default:
			System.err.println("Invalid State!");
			System.exit(1);
			break;
		}
	}

}

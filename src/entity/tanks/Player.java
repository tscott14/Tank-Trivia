package entity.tanks;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import entity.Mob;
import entity.projectiles.CannonBall;
import gui.managers.AmmoBarManager;
import io.Animation;
import io.Textures;
import render.Camera;
import render.shaders.ShaderProgram;
import window.Input;
import window.Window;
import world.Level;

public class Player extends Mob {
	public static Player player = null;
	private static Window win = Window.win;
	private static Input in = win.getInput();
	public static final int INITIAL_NUM_OF_CANNONBALLS = 10;
	private int healthBuffer = 0, timeSinceHit = 0;

	private boolean isActive;

	private TankCannon cannon;
	public int num_of_shots = INITIAL_NUM_OF_CANNONBALLS;

	public Player(Level world) {
		super(8, 8, 10, 10, 1, new Animation(10, Textures.PLAYER_ANIMATION));
		isActive = true;
		cannon = new TankCannon(getX(), getY(), new Animation(1, Textures.TANK_CANNON_TEXTURE));
		player = this;
	}

	public void update(float delta) {
		super.update(delta);
		if (!isDead) {
			timeSinceHit++;
			if (health < 10 && timeSinceHit > 240) {

				healthBuffer++;

				if (healthBuffer > 120) {
					heal(1);
					healthBuffer = 0;
				}
			}

			if (in.isKeyDown(GLFW.GLFW_KEY_W)) move((float) Math.sin(rot), (float) -Math.cos(rot), delta);
			if (in.isKeyDown(GLFW.GLFW_KEY_S)) move((float) -Math.sin(rot), (float) Math.cos(rot), delta);
			if (in.isKeyDown(GLFW.GLFW_KEY_A)) rot -= Math.PI / 2 * delta;
			if (in.isKeyDown(GLFW.GLFW_KEY_D)) rot += Math.PI / 2 * delta;
			cannon.transform.pos.set(transform.pos);
			if (in.getMousePosition().x < (win.getWidth() * 0.75f / 2 - (win.getWidth() * 0.25f / 2.0f))) {

				cannon.rot = (float) (Math.atan2(in.getMousePosition().y,
						in.getMousePosition().x + (((5 * transform.scale)) * (win.getWidth() / win.getHeight())))
						+ (Math.PI / 2));
				if (in.isMouseButtonPressed(GLFW.GLFW_MOUSE_BUTTON_1)) {
					if (num_of_shots > 0) {
						new CannonBall(getX(), getY(), this, (float) (cannon.rot - (Math.PI / 2)));
						num_of_shots--;
					} else {
						System.out.println("Out of shells");
					}
				}
			}
			Camera.camera.getPosition().lerp(transform.pos.mul(-Level.BossLevel.getScale(), new Vector3f())//
					.add(-(5 * transform.scale), 0, 0), 0.5f);
		}
		
	}

	public void damage(int amount) {
		super.damage(amount);
		timeSinceHit = 0;
	}

	public void render(ShaderProgram shader) {
		super.render(shader);
		cannon.render(shader);
	}

	public void reset() {
		health = 10;
		isDead = false;
		num_of_shots = Math.max(num_of_shots - 5, 0);
		transform.pos.set(new Vector3f(8, 8, 0));
		rot = (float) Math.PI;
	}

	public Animation getTexture() {
		return texture;
	}

	public void setTexture(Animation texture) {
		this.texture = texture;
	}

	public void giveCannonBalls(int num) {
		if (num_of_shots + num >= 32 || num_of_shots + num < 0) {

		} else {
			num_of_shots += num;
		}
	}

	public void enable() {
		isActive = true;
	}

	public void disable() {
		isActive = false;
	}

	public boolean isActive() {
		return isActive;
	}

}
package entity.tanks;

import entity.Mob;
import entity.projectiles.CannonBall;
import io.Animation;
import io.Textures;
import render.shaders.ShaderProgram;
import utils.Utils;
import world.Level;

public class Boss extends Mob {
	public static int currentBoss;
	private static boolean isActive;
	//private TankCannon cannon;
	private int timer = 0;
	private static int MAX_HEALTH = 50;
	private static final int POS_X = 40, POS_Y = 40;
	public static boolean win;

	private static Boss[] bosses = new Boss[] {
			new Boss(POS_X, POS_Y, MAX_HEALTH, new Animation(1, Textures.ITALY_BOSS_TEXTURE)),
			new Boss(POS_X, POS_Y, MAX_HEALTH, new Animation(1, Textures.GERMANY_BOSS_TEXTURE)),
			new Boss(POS_X, POS_Y, MAX_HEALTH, new Animation(1, Textures.JAPANESE_BOSS_TEXTURE))
	};

	public Boss(float x, float y, int health, Animation texture) {
		super(x / 4, y / 4, health, 10, 4, texture);
		transform.scale = Level.BossLevel.getScale();
		//cannon = new TankCannon(getX() * 2, getY() * 2, new Animation(1, Textures.TANK_CANNON_TEXTURE));
	}

	public void render(ShaderProgram shader) {
		super.render(shader);
		//cannon.render(shader);
	}

	public void update(float delta) {
		super.update(delta);
		if (!this.isDead()) {
			timer++;
			float y = getY() - Player.player.getY();
			float x = Player.player.getX() - getX();
			if (!Player.player.isDead()) {
				float dist = Utils.pythag(Player.player.getX(), Player.player.getY(), getX(), getY());
				if (dist < 10) {
					rot = (float) -(Math.atan2(y, x) + Math.PI / 2);
					if (timer > 50) {
						timer = 0;
						shoot((float) (rot + Math.PI / 2));
					}
				}
			}
		}
		//cannon.update(delta);
	}

	private void shoot(float deg) {
		new CannonBall(getX(), getY(), this, deg);
	}

	public static void renderCurrentBoss(ShaderProgram shader) {
		bosses[currentBoss].render(shader);
	}

	public static void updateCurrentBoss(float delta) {
		bosses[currentBoss].update(delta);
		if (bosses[currentBoss].isDead) {
			if (currentBoss == 2) win = true;
			Level.changeLevel();
		}
	}

	public static void setCurrentBoss(int current_boss) {
		Boss.currentBoss = current_boss;
	}

	public static void enable(int current_boss) {
		isActive = true;
		setCurrentBoss(current_boss);
	}

	public static void disable() {
		isActive = false;
	}

	public static boolean isActive() {
		return isActive;
	}

	public static Boss getCurrentBoss() {
		return bosses[currentBoss];
	}

	public static void reset() {
		getCurrentBoss().isDead = false;
		getCurrentBoss().setPosition(POS_X / 4, POS_Y / 4);
		getCurrentBoss().rot = 0;
	}
	public static void restoreHealth(){
		getCurrentBoss().health = MAX_HEALTH;
	}

}

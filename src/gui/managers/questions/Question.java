package gui.managers.questions;

import entity.tanks.Player;
import gui.GUIButton;
import gui.managers.text.Text;
import io.Textures;
import render.shaders.ShaderProgram;
import utils.Assets;

public class Question {
	private static int num_of_questions = 0;
	private String question, ans1, ans2, ans3;
	private Text questionGUI, ans1GUI, ans2GUI, ans3GUI;
	private GUIButton b1, b2, b3;
	public boolean bb1, bb2, bb3;
	private int answerIndex = 0, difficulty = 0;
	public int questionID;
	private static final float X = 0.6f, Y = 0.1f;

	public Question(String question, String ans1, String ans2, String ans3, int answerIndex, int difficulty) {
		this.question = question;
		this.ans1 = ans1;
		this.ans2 = ans2;
		this.ans3 = ans3;
		this.answerIndex = answerIndex;
		this.difficulty = difficulty;

		questionID = num_of_questions;
		num_of_questions++;

		float size = 0.03f;

		this.questionGUI = new Text(question, X, Y, 16, 45);
		this.ans1GUI = new Text(ans1, X + size + 0, Y - 0.2f, 16, 45);
		this.ans2GUI = new Text(ans2, X + size + 0, Y - 0.45f, 16, 45);
		this.ans3GUI = new Text(ans3, X + size + 0, Y - 0.7f, 16, 45);

		b1 = new GUIButton(X, Y - 0.2f, size, size * Assets.ASPECT_RATIO, Textures.CHECK_BOX_GUI_TEXTURE);
		b2 = new GUIButton(X, Y - 0.45f, size, size * Assets.ASPECT_RATIO, Textures.CHECK_BOX_GUI_TEXTURE);
		b3 = new GUIButton(X, Y - 0.7f, size, size * Assets.ASPECT_RATIO, Textures.CHECK_BOX_GUI_TEXTURE);

	}

	public void render(ShaderProgram shader) {
		questionGUI.render(shader);
		ans1GUI.render(shader);
		ans2GUI.render(shader);
		ans3GUI.render(shader);
		b1.render(shader);
		b2.render(shader);
		b3.render(shader);
	}

	public void update() {
		if (bb1 = b1.isPressed()) {
			if (answerIndex == 0) {
				Player.player.giveCannonBalls(difficulty);
			}else{
				Player.player.damage(1);
			}
		}
		if (bb2 = b2.isPressed()) {
			if (answerIndex == 1) {
				Player.player.giveCannonBalls(difficulty);
			}else{
				Player.player.damage(1);
			}
		}
		if (bb3 = b3.isPressed()) {
			if (answerIndex == 2) {
				Player.player.giveCannonBalls(difficulty);
			}else{
				Player.player.damage(1);
			}
		}

	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAns1() {
		return ans1;
	}

	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}

	public String getAns2() {
		return ans2;
	}

	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}

	public String getAns3() {
		return ans3;
	}

	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}

}

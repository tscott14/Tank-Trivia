package gui.managers.questions;

import gui.GUI;
import io.Textures;
import render.shaders.ShaderProgram;
import utils.Utils;

public class QuestionManager {
	private GUI questionBox;

	private Question[] questionsObjects;
	private int index;

	public QuestionManager() {
		questionBox = new GUI(0.75f, -0.35f, 0.4f, 1.2f, Textures.QUESTION_BOARD_GUI_TEXTURE);
		String[] raw_questions_strings = Utils.readFile("./res/questions.txt").split("\n");
		index = (int) (Math.random()*raw_questions_strings.length);

		questionsObjects = new Question[raw_questions_strings.length];

		for (int i = 0; i < questionsObjects.length; i++) {
			String[] res = raw_questions_strings[i].split("\t");
			questionsObjects[i] = new Question(res[0], res[1], res[2], res[3], Integer.parseInt(res[4]), Integer.parseInt(res[5]));
		}

	}

	public void render(ShaderProgram shader) {
		questionBox.render(shader);
		questionsObjects[index].render(shader);

	}

	public void update() {
		questionBox.update();
		Question q = questionsObjects[index];
		q.update();
		if (q.bb1 || q.bb2 || q.bb3) {
			index ++;
			if(index >= questionsObjects.length) index = 0;
		}

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}

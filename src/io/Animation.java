package io;

import utils.Timer;

public class Animation {
	private Texture[] frames;
	private int pointer;

	private double elapsedTime;
	private double currentTime;
	private double lastTime;
	private double fps;

	public Animation(int fps, Texture... frames) {
		this.pointer = 0;
		this.elapsedTime = 0;
		this.currentTime = 0;
		this.lastTime = Timer.getTime();
		this.fps = 1.0 / fps;
		this.frames = frames;

	}

	public void bind() {
		bind(0);
	}

	public void bind(int sampler) {
		this.currentTime = Timer.getTime();
		this.elapsedTime += currentTime - lastTime;

		if (elapsedTime >= fps) {
			elapsedTime -= fps;
			pointer++;
		}

		if (pointer >= frames.length) pointer = 0;

		this.lastTime = currentTime;

		frames[pointer].bind(sampler);
	}
	public Texture getFrame(int frame){
		return frames[frame];
	}
}

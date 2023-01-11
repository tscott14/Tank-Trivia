package audio;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	private int t = 0;
	private File sound;
	private double length;
	
	public Sound(String path) {
		init(path);
	}

	public void init(String path){
		sound = new File(path);
	}
	
	public void update(){
		//t++;
		if(t > length){
			t=0;
			playSound(sound);
		}
	}
	
	public void playSound(File sound){
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			length=(clip.getMicrosecondLength()/1000000) * 60;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}

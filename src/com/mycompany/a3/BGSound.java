package com.mycompany.a3;
import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

/**
 * 
 * @author kiryl
 *	This class creates the sound for the looping background noise.
 */

public class BGSound implements Runnable {
	private Media m;
	
	public BGSound(String fileName) {
		while(m == null ) {
			try {
				InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
				m = MediaManager.createMedia(is, "audio/wav", this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/* pauses the background noise when game is paused */
	public void pause() {
		m.pause();
	}
	
	/* plays the background noise when game is in play mode */
	public void play() {
		m.play();
	}
	
	/* when game begins, the background starts to play starting from the beginning */
	@Override
	public void run() {
		m.setTime(0);
		m.play();
	}

}

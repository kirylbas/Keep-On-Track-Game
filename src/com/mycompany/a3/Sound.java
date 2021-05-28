package com.mycompany.a3;

import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

/**
 * Creates the in-game sounds for collisions.
 * @author kiryl
 *
 */

public class Sound {
	private Media m;
	
	public Sound(String fileName) {
		while(m == null ) {
			try {
				InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
				m = MediaManager.createMedia(is, "audio/wav");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void play() {
		m.setTime(0);
		m.play();
	}
}

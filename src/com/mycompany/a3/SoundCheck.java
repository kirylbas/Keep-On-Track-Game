package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Executes command to select/unselect checkbox for sound
 * @author kiryl
 *
 */

public class SoundCheck extends Command {
	private GameWorld gw;
	
	public SoundCheck(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}
	
	/* this method is executed when the sound checkbox is pressed */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.checkSoundBox((CheckBox)ev.getComponent());
	} 
}

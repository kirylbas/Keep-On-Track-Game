package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * This class creates the Accelerate command, which increases the player
 * cyborg's speed + 5 when the accelerate button is pressed.
 * 
 * @author kiryl
 *
 */

public class AccelerateCommand extends Command {
	private GameWorld gw;
	
	public AccelerateCommand(GameWorld gw) {
		super("Accelerate");
		this.gw = gw;
	}
	
	/* calls the accelerate method in gw, which increased the speed */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.accelerate();
	}

}

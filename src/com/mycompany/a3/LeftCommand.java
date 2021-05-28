package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Executes command to steer the player cyborg left when left
 * button is pressed
 * @author kiryl
 *
 */

public class LeftCommand extends Command {
	private GameWorld gw;
	
	public LeftCommand(GameWorld gw) {
		super("Left");
		this.gw = gw;
	}
	
	/* calls the appropriate method in gw to execute this command */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.steerLeft();
	}

}

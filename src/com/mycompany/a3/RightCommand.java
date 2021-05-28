package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Executes command to steer the player cyborg right when right
 * button is pressed
 * @author kiryl
 *
 */

public class RightCommand extends Command {
	private GameWorld gw;

	public RightCommand(GameWorld gw) {
		super("Right");
		this.gw = gw;
	}
	
	/* calls the appropriate method in gw to execute this command */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.steerRight();
	}
}

package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/** 
 * This class executes the command to slow the player cyborg's speed - 5
 * when the brake button is pressed
 * 
 * @author kiryl
 *
 */

public class BrakeCommand extends Command {
	private GameWorld gw;

	public BrakeCommand(GameWorld gw) {
		super("Brake");
		this.gw = gw;
	}
	
	/* calls the brake method in gw which executes this command */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.brake();
	}

}

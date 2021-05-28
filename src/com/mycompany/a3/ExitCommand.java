package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Executes the command to exit the game when the exit button
 * is pressed on the side menu
 * @author kiryl
 *
 */

public class ExitCommand extends Command {
	private GameWorld gw;
	
	public ExitCommand(GameWorld gw) {
		super("Exit");
		this.gw = gw;
	}
	
	/* calls the appropriate method in gw to execute this command */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.exit();		
	}

}

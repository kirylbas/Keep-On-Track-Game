package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Executes the command to display the help information when the
 * help command is pressed
 * @author kiryl
 *
 */

public class HelpCommand extends Command{
	private GameWorld gw;
	
	public HelpCommand(GameWorld gw) {
		super("Help");
		this.gw = gw;
	}
	
	/* calls the appropriate method in gw to execute this command */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.help();
	}

}

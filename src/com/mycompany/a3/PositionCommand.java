package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Creates the command to change the position of a selected object.
 * @author kiryl
 *
 */

public class PositionCommand extends Command {
	private GameWorld gw;
	
	public PositionCommand(GameWorld gw) {
		super("Position");
		this.gw = gw;
	}
	
	/* calls the appropriate method in gw to execute this command */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.position();
	}
}

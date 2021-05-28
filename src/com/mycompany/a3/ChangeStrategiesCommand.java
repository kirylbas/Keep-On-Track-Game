package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Changes the strategy of the NPC. If it currently uses AttackStrategy, then it
 * switches it to MoveToNextBaseStrategy and vice-versa
 * 
 * @author kiryl
 *
 */

public class ChangeStrategiesCommand extends Command {
	private GameWorld gw;
	
	public ChangeStrategiesCommand(GameWorld gw) {
		super("Change Strategies");
		this.gw = gw;
	}
	
	/* calls the changeStrategies method in gw, which executes the change */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.changeStrategies();
	}

}

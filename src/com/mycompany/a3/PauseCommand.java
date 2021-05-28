package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Create the command to pause the game.
 * @author kiryl
 *
 */

public class PauseCommand  extends Command {
	private Button pauseButt;
	private Game g;

	public PauseCommand(Button pauseButt, Game g) {
		super("Pause");
		this.pauseButt = pauseButt;
		this.g = g;
	}
	
	/* calls the method in gw that executes this command */
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (pauseButt.getText() == "Pause") {
			pauseButt.setText("Play");
			g.pause();
		} else {
			pauseButt.setText("Pause");
			g.play();
		}
			
	}
}

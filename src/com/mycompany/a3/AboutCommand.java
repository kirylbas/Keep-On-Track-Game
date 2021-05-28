package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * This class creates the command that outputs the "about" information by calling
 * the about method in gameworld when the bout button is pressed on the side menu
 * 
 * @author kiryl
 */

public class AboutCommand extends Command {
	private GameWorld gw;
	
	public AboutCommand(GameWorld gw) {
		super("About");
		this.gw = gw;
	}
	
	/* calls the method in gw that outputs the about info */
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.aboutDisplay();		
	}

}

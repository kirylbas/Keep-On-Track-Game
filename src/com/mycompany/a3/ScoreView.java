package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 * GRAPHICAL VIEW - outputs current game/player-cyborg state info
 * @author kiryl
 *
 */

public class ScoreView extends Container implements Observer {
	private Label timeLabel;
	private Label livesLabel;
	private Label pCybLBRLabel;
	private Label pCybEnrgLvlLabel;
	private Label pCybDmgLvlLabel;
	private Label soundLabel;

	/* creates all the labels for the title bar container */
	public ScoreView() {
		timeLabel = new Label("Time: ");
		livesLabel = new Label(" Lives: ");
		pCybLBRLabel = new Label(" Player Last Base Reached: ");
		pCybEnrgLvlLabel = new Label(" Player Energy Level: ");
		pCybDmgLvlLabel = new Label(" Player Damage Level: ");
		soundLabel = new Label(" Sound: " );		
	}
	
	public Label getTimeLabel() {
		return timeLabel;
	}
	
	public Label getLivesLabel() {
		return livesLabel;
	}
	
	public Label getPCybLBRLabel() {
		return pCybLBRLabel;
	}
	
	public Label getPCybEnrgLvlLabel() {
		return pCybEnrgLvlLabel;
	}
	
	public Label getPCybDmgLvlLabel() {
		return pCybDmgLvlLabel;
	}
	
	public Label getSoundLabel() {
		return soundLabel;
	}
	
	/* updates all the labels when notifyObservers() is called */
	@Override
	public void update(Observable observable, Object data) {
		GameWorld gw = (GameWorld)observable;
		timeLabel.setText("Time: " + gw.getClock() + "    ");
		livesLabel.setText(" Lives: " + gw.getLives());
		GameObjectCollection gameObjCollection = gw.getGameObjCollection();
		IIterator theGameObjs = gameObjCollection.getIterator();
		while(theGameObjs.hasNext()) {
			GameObject gameObj = theGameObjs.getNext();
			if(gameObj instanceof PlayerCyborg) {
				PlayerCyborg cyb = (PlayerCyborg)gameObj;
				pCybLBRLabel.setText(" Player Last Base Reached: " + cyb.getLastBaseReached());
				pCybEnrgLvlLabel.setText(" Player Energy Level: " + (int)cyb.getEnergyLevel());
				pCybDmgLvlLabel.setText(" Player Damage Level: " + cyb.getDamageLevel() + "  ");
				break;
			}
		}
		String sound;
		if (gw.getSound() == false)
			sound = "OFF";
		else
			sound = "ON";
		soundLabel.setText(" Sound: " + sound + "  ");
	}
}

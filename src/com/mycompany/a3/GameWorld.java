package com.mycompany.a3;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;


/**
 * This class instantiates all the GameObjects and manipulates the world objects
 * and game state data.
 * @author kiryl
 *
 */

/* DATA MODEL */
public class GameWorld extends Observable {
	private int lives;
	private int clock;
	private boolean sound;
	private GameObjectCollection gameObjCollection;
	private int width;
	private int height;
	private BGSound bgSound;
	private Sound collisionSound;
	private Sound chargeSound;
	private Sound deadSound;
	private Sound baseCollision;
	private boolean isPaused;
	private boolean changePosit;
	
	public GameWorld() {
		//gameObjCollection = new GameObjectCollection();
		clock = 0;
		lives = 3;
		isPaused = false;
		changePosit = false;
		sound = true;
	}
	
	/* all the game objects get instantiated in this method and added
	 * to the GameObjectCollection */
	public void init() {
		gameObjCollection = new GameObjectCollection();
		gameObjCollection.add(new Base(this, 1, 500.0f, 500.0f));
		gameObjCollection.add(new Base(this, 2, 1100.0f, 800.0f));
		gameObjCollection.add(new Base(this, 3, 400.0f, 100.0f));
		gameObjCollection.add(new Base(this, 4, 200.0f, 750.0f));
		gameObjCollection.add(new Base(this, 5, 1200.0f, 80.0f));
		gameObjCollection.add(new EnergyStation(this));
		gameObjCollection.add(new EnergyStation(this));
		gameObjCollection.add(new EnergyStation(this));
		gameObjCollection.add(PlayerCyborg.getPlayerCyb(this));
		gameObjCollection.add(new NonPlayerCyborg(this, 800.0f, 500.0f, 500, 500, new MoveToNextBaseStrategy(this)));
		gameObjCollection.add(new NonPlayerCyborg(this, 1000.0f, 600.0f, 500, 500, new AttackStrategy(this)));
		gameObjCollection.add(new NonPlayerCyborg(this, 200.0f, 600.0f, 500, 500,  new MoveToNextBaseStrategy(this)));
		gameObjCollection.add(new Drone(this));
		gameObjCollection.add(new Drone(this));
		gameObjCollection.add(new Drone(this));
	}
	
	/* returns the remaining lives the player has */
	public int getLives() {
		return lives;
	}
	
	/* returns the amount of game ticks that have occurred */
	public int getClock() {
		return clock;
	}
	
	/* returns if sound is on/off */
	public boolean getSound() {
		return sound;
	}
	
	/* returns the GameObjectCollection */
	public GameObjectCollection getGameObjCollection() {
		return gameObjCollection;
	}
	
	/* gets the location of the player cyborg inside the collection */
	public PlayerCyborg getPlayerCyborg() {
		IIterator theGameObjs = gameObjCollection.getIterator();
		PlayerCyborg playerCyb = null;
		while(theGameObjs.hasNext()) {
			GameObject gameObj = theGameObjs.getNext();
			if(gameObj instanceof PlayerCyborg) {
				playerCyb = (PlayerCyborg)gameObj;
				break;
			}
		}
		return playerCyb;
	}
	
	/* increments the clock + 1 when there is a tick */
	public void setClock() {
		clock++;
		myNotifyObservers();	
	
	}
	
	/* sets the sound to a new value */
	public void setSound(boolean newSound) {
		sound = newSound;
		myNotifyObservers();	
	}
	
	/* sets the width of the game world */
	public void setWidth(int w) {
		width = w;
	}
	
	/* gets the width of the game world */
	public int getWidth() {
		return width;
	}
	
	/* sets the height of the game world */
	public void setHeight(int h) {
		height = h;
	}
	
	/* gets the height of the game world */
	public int getHeight() {
		return height;
	}
	
	/* gets if the world is paused */
	public boolean isPaused() {
		return isPaused;
	}
	
	/* sets true when the position button is pressed, false otherwise */
	public void setChangePosit(boolean b) {
		changePosit = b;
	}
	
	/* returns the status of if the position button is pressed */
	public boolean getChangePosit() {
		return changePosit;
	}
	
	/* increases the speed of the player cyborg when accelerate button is invoked */
	public void accelerate() {
		System.out.println("\nCyborg is accelerating.");
		PlayerCyborg pCyb = getPlayerCyborg();
		if(pCyb.getSpeed() != pCyb.getMaximumSpeed() && pCyb.getEnergyLevel() != 0 && pCyb.getDamageLevel() != pCyb.getMaxDamage()) {
			if((pCyb.getMaximumSpeed() - pCyb.getSpeed()) >= 10)
				pCyb.setSpeed(pCyb.getSpeed() + 10);
			else
				pCyb.setSpeed(pCyb.getMaximumSpeed());
		}
		myNotifyObservers();
	}
	
	/* decreases the speed of the player cyborg when brake button is invoked */
	public void brake() {
		System.out.println("\nCyborg is slowing down.");
		PlayerCyborg pCyb = getPlayerCyborg();
		if(pCyb.getEnergyLevel() != 0 && pCyb.getDamageLevel() != pCyb.getMaxDamage()) {
			if(pCyb.getSpeed() >= 110)
				pCyb.setSpeed(pCyb.getSpeed() - 10);
			else
				pCyb.setSpeed(100);
		}
		myNotifyObservers();
	}
	
	/* turns the steering direction of player cyborg 5 left when Left button is invoked */
	public void steerLeft() {
		System.out.println("\nCyborg is steering left.");
		PlayerCyborg pCyb = getPlayerCyborg();
		if(pCyb.getEnergyLevel() != 0 && pCyb.getDamageLevel() != pCyb.getMaxDamage() && pCyb.getSpeed() != 0) {
			if (pCyb.getSteeringDirection() >= -20) // max steering is -40 to the left
				pCyb.setSteeringDirection(pCyb.getSteeringDirection() - 20);
			else 
				System.out.println("Cannot turn further left");
		}
		myNotifyObservers();
	}
	
	/* turns the steering direction of player cyborg 5 right when right button is invoked */
	public void steerRight() {
		System.out.println("\nCyborg is steering right.");
		PlayerCyborg pCyb = getPlayerCyborg();
		if(pCyb.getEnergyLevel() != 0 && pCyb.getDamageLevel() != pCyb.getMaxDamage() && pCyb.getSpeed() != 0) {
			if (pCyb.getSteeringDirection() <= 20) // max steering is 40 to the right
				pCyb.setSteeringDirection(pCyb.getSteeringDirection() + 20);
			else 
				System.out.println("Cannot turn further right");
		}
		myNotifyObservers();
	}
	
	/* pretends that there is a collision between player cyborg npc. This causes damage to
	 * both player and npc. */
	public void cyborgCyborgCollision(Cyborg cyb) {
		System.out.println("\nCyborg has collided with another cyborg.");
		if(sound)
			collisionSound.play();
		if((cyb.getDamageLevel() + 10) < cyb.getMaxDamage()) {
			cyb.setDamageLevel(cyb.getDamageLevel() + 10);
			cyb.setMaximumSpeed((int)(300 * (1 - (double)cyb.getDamageLevel()/cyb.getMaxDamage())));
			cyb.setColor(ColorUtil.red(cyb.getColor()) - 10, ColorUtil.green(cyb.getColor()), ColorUtil.blue(cyb.getColor()));
			if(cyb.getSpeed() > cyb.getMaximumSpeed())
				cyb.setSpeed(cyb.getMaximumSpeed());					
		} else {
			if(cyb instanceof PlayerCyborg) {
				System.out.println("CYBORG DEAD - RE-INIT GAME");
				playerCybDead();
			}
		}
		myNotifyObservers();
	}	
	
	/* displays a textbox to enter a base number that the player cyborg has pretend collided with. */
	public void cyborgBaseCollision(Cyborg cyb, Base base) {
		System.out.println("\nCyborg has collided with base");
		if(sound)
			baseCollision.play();
		if(cyb.getLastBaseReached() + 1 == base.getSequenceNumber()) {
			cyb.setLastBaseReached(base.getSequenceNumber());
			myNotifyObservers();
		}
		if(cyb.getLastBaseReached() == 5) {
			if (cyb instanceof PlayerCyborg)
				win();
			else
				npcWins();
		}
	}
	
	/* pretends that player cyborg has collided with an energy station*/
	public void cyborgEnrgStatCollision(Cyborg cyb, EnergyStation enrgStat) {
		System.out.println("\nCyborg has collided with Energy Station.");
		if(sound)
			chargeSound.play();
		if(enrgStat.getCapacity() == 0) {}
		else {
			if (cyb.getEnergyLevel() + enrgStat.getCapacity() <= cyb.getMaxEnrgLvl()) {
				cyb.setEnergyLevel(cyb.getEnergyLevel() + enrgStat.getCapacity());
				enrgStat.setCapacity(0);
				enrgStat.setColor(ColorUtil.red(enrgStat.getColor()), ColorUtil.green(enrgStat.getColor()) - 50, ColorUtil.blue(enrgStat.getColor()));
				gameObjCollection.add(new EnergyStation(this));
			}
			else {
				enrgStat.setCapacity(enrgStat.getCapacity() - (int)(cyb.getMaxEnrgLvl() - cyb.getEnergyLevel()));
				cyb.setEnergyLevel(cyb.getMaxEnrgLvl());
				enrgStat.setColor(ColorUtil.red(enrgStat.getColor()), ColorUtil.green(enrgStat.getColor()) - 25, ColorUtil.blue(enrgStat.getColor()));
			}
			myNotifyObservers();
		}
	}
	
	/* pretends that player cyborg has collided with a drone */
	public void cyborgDroneCollision(Cyborg cyb, Drone drone) {
		System.out.println("\nCyborg has collided with drone.");
		if(sound)
			collisionSound.play();
		if(cyb.getDamageLevel() + 5 < cyb.getMaxDamage()) {
			cyb.setDamageLevel(cyb.getDamageLevel() + 5);
			cyb.setMaximumSpeed((int)(300 * (1 - (double)cyb.getDamageLevel()/cyb.getMaxDamage())));
			cyb.setColor(ColorUtil.red(cyb.getColor()) - 5, ColorUtil.green(cyb.getColor()), ColorUtil.blue(cyb.getColor()));
			if(cyb.getSpeed() > cyb.getMaximumSpeed())
				cyb.setSpeed(cyb.getMaximumSpeed());
		} else {
			System.out.println("CYBORG DEAD");
				playerCybDead();
			}
			myNotifyObservers();
	}
	
	/* changes the strategy of the npc and increments the npc's last base reached + 1 */
	public void changeStrategies() {
		System.out.println("\nNPC Strategy has been changed.");
		GameObjectCollection gameObjColl = getGameObjCollection();
		IIterator theGameObjs = gameObjColl.getIterator();
		while(theGameObjs.hasNext()) {
			GameObject gameObj = theGameObjs.getNext();
			if(gameObj instanceof NonPlayerCyborg) {
				NonPlayerCyborg npc = (NonPlayerCyborg)gameObj;
				if (((NonPlayerCyborg)gameObj).getStrategy() instanceof AttackStrategy) 
					((NonPlayerCyborg)gameObj).setStrategy(new MoveToNextBaseStrategy(this));
				else
					((NonPlayerCyborg)gameObj).setStrategy(new AttackStrategy(this));
			}
		}
		myNotifyObservers();
	}
	
	/* increments the game clock + 1, moves all the movable game objects
	 * based on their speed and heading */
	public void clockTick() {
		Random rand = new Random();
		int randDroneHead = rand.nextInt(41) - 20;		// generates random number between (-20,20) to be added to the drone's heading
		setClock();
		if(sound)
			bgSound.play();
		else
			bgSound.pause();
		
		GameObjectCollection gameObjColl = getGameObjCollection();
		IIterator theGameObjs = gameObjColl.getIterator();
		while(theGameObjs.hasNext()) {
			GameObject gameObj = theGameObjs.getNext();
			if(gameObj instanceof Movable) {
				if(gameObj instanceof PlayerCyborg) {
					PlayerCyborg pCyb = (PlayerCyborg)gameObj;
					pCyb.setHeading(pCyb.getHeading() + pCyb.getSteeringDirection());	// steering direction is added to heading
					pCyb.move(20);
					pCyb.setEnergyLevel(pCyb.getEnergyLevel() - pCyb.getEnergyConsumptionRate());
					pCyb.setSteeringDirection(0);
					if(pCyb.getEnergyLevel() <= 0) {
						System.out.println("CYBORG RAN OUT OF ENERGY");
						playerCybDead();
					}
				} else if(gameObj instanceof NonPlayerCyborg) {
					NonPlayerCyborg npc = (NonPlayerCyborg)gameObj;
					npc.invokeStrategy();	// calculates the steering direction/heading needed for the npc to move
					npc.move(20);
					npc.setEnergyLevel((int)(npc.getEnergyLevel() - npc.getEnergyConsumptionRate()));
				} else {
					Drone drone = (Drone)gameObj;
					if(drone.getHeading() + randDroneHead >= 0)
						drone.setHeading(drone.getHeading() + randDroneHead);
					else
						drone.setHeading(drone.getHeading() + randDroneHead + 360);
					drone.move(20);
				}
			}
		}
		// checks for collisions between game objects
		theGameObjs = gameObjColl.getIterator();
		while(theGameObjs.hasNext()) {
			ICollider currObj = (ICollider)theGameObjs.getNext();
			IIterator iter2 = gameObjColl.getIterator();
			while(iter2.hasNext()) {
				ICollider otherObj = (ICollider)iter2.getNext();
				if(otherObj != currObj) {
					if(currObj.collidesWith((GameObject)otherObj)) 
						currObj.handleCollision((GameObject)otherObj);	
				}
			}
		}
		myNotifyObservers();
	}
	
	/* if an item is selected and position is pressed, sets the changePosition to true */
	public void position() {
		IIterator theGameObjs = gameObjCollection.getIterator();
		while(theGameObjs.hasNext()) {
			GameObject gameObj = theGameObjs.getNext();
			if(gameObj instanceof Fixed) {
				if(((Fixed)gameObj).isSelected()) {
					changePosit = true;
					break;
				}
			}
		}
	}
	
	/* when play button is pressed, sets the changePosit to false and starts the bg sound if sound is on */
	public void play() {
		changePosit = false;
		if(sound)
			bgSound.play();
		isPaused = false;	
	}
	
	/* stops bg sound and sets the pause flag to true */
	public void pause() {
		bgSound.pause();
		isPaused = true;
		
	}
	
	/* checks if the checkbox in side menu is selected or not and updates the sound accordingly */
	public void checkSoundBox(CheckBox cb) {
		if (cb.isSelected())
			setSound(true);
		else
			setSound(false);
		myNotifyObservers();
	}
	
	/* displays the about information in a Dialog Box */
	public void aboutDisplay() {
		String about = "Kiryl Basiuk\n"
				 + "CSC 133\n"
				 + "Fall 2020\n"
				 + "Keep-On-Track V3.0";
		Command cOk = new Command("OK");
		Dialog.show("About", about, cOk);
	}
	
	/* displays all the values for all the game objects in the game */
	public void map() {
		System.out.println("-----------------------------");
		IIterator theGameObjs = gameObjCollection.getIterator();
		while(theGameObjs.hasNext()) {
			GameObject gameObj = (GameObject)theGameObjs.getNext();
			if (gameObj instanceof PlayerCyborg)
				System.out.print("PLAYER CYBORG: ");
			else if (gameObj instanceof NonPlayerCyborg)
				System.out.print("NPC CYBORG: ");
			else if (gameObj instanceof Base)
				System.out.print("BASE: ");
			else if (gameObj instanceof EnergyStation)
				System.out.print("EnergyStation: ");
			else if (gameObj instanceof Drone)
				System.out.print("DRONE: ");
			System.out.println(gameObj.toString());
		}
		System.out.println("-----------------------------\n");

	}
	
	/* creates the sounds for the game */
	public void createSounds() {
		bgSound = new BGSound("bg_sound.wav");
		collisionSound = new Sound("collision.wav");
		chargeSound = new Sound("charge_sound.wav");
		deadSound = new Sound("robot_death.wav");
		baseCollision = new Sound("base_collision.wav");
	}
	
	/* reinitializes the game of player has died */
	public void playerCybDead() {
		deadSound.play();
		if(lives > 1) {
			System.out.println("RE-INITIALIZING GAME");
			//gameObjCollection.getGameList().clear();
			lives--;			
			init();	//re-initializes the game if there are remaining lives
			myNotifyObservers();
		} else 
			lose();
	}
	
	/* this method is used to call notifyObservers() anytime 
	 * there is a change is game state data */
	public void myNotifyObservers() {
		setChanged();
		notifyObservers();
	}
	
	/* displays a message and game clock if the game is won */
	public void win() {
		System.out.println("CONGRATS! YOU WIN!\nTIME: " + getClock());
		System.exit(0);
	}
	
	/* displays message if NPC gets to every base before cyborg */
	public void npcWins() {
		System.out.println("Game Over! NPC Wins!");
		System.exit(0);
	}
	
	/* displays a message if the game is lost and ends the game */
	public void lose() {
		System.out.println("GAME OVER! YOU LOSE!");
		System.exit(0);
	}
	
	/* displays information about all the keys that could be used to execute commands */
	public void help() {
		String help = "A: Accelerate\n"
				+ "B: Brake\n"
				+ "L: Steer Left\n"
				+ "R: Steer Right\n";
		Command cOk = new Command("OK");
		Dialog.show("Keys", help, cOk);
	}
	
	/* exits the game when exit button is pressed on side menu*/
	public void exit() {
		Command cConfirm = new Command("Confirm");
		Command cCancel = new Command("Cancel");
		Command[] cmds = new Command[] {cConfirm,cCancel};
		Command cInput = Dialog.show("Are you sure you want to exit?", "", cmds);
		
		if(cInput == cConfirm) {
			System.out.println("GAME ENDED BY USER");
			System.exit(0);
		}
	}
}

























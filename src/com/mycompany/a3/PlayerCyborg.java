package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Rectangle;

/**
 * Creates a Player Cyborg game object
 * @author kiryl
 *
 */

public class PlayerCyborg extends Cyborg {
	private GameWorld gw;
	private static PlayerCyborg pCyb;
	private Point parOrigin;
	
	/* creates the player cyborg */
	private PlayerCyborg(GameWorld gw, float x, float y, int maxDamage, int enrgLvl) {
		super(gw,235,30,30,x,y,100,maxDamage,enrgLvl);
		this.gw = gw;
	}
	
	/* makes sure there is only 1 instance of player cyborg */
	public static PlayerCyborg getPlayerCyb(GameWorld gw) {
		if (pCyb == null)
			pCyb = new PlayerCyborg(gw, 500.0f, 500.0f, 100, 100);
		else {
			pCyb.setLocation(500f,500f);
			pCyb.setColor(235,30,30);
			pCyb.setHeading(0);
			pCyb.setSpeed(100);
			pCyb.setSteeringDirection(0);
			pCyb.setMaximumSpeed(300);
			pCyb.setEnergyLevel(100);
			pCyb.setDamageLevel(0);
			pCyb.setLastBaseReached(1);
		}
		return pCyb;
	}
	
	/* draws the player cyborg on the map */
	public void draw(Graphics g, Point pCmpRelPrnt) {
		parOrigin = pCmpRelPrnt;
		g.setColor(getColor());
		g.fillRect((int)(pCmpRelPrnt.getX() - getSize()/2 + pCyb.getLocation().getX()),
				(int)(pCmpRelPrnt.getY() - getSize()/2 + pCyb.getLocation().getY()), getSize(), getSize());
	}
	
	/* checks if player cyborg has collided with a game object */
	@Override
	public boolean collidesWith(GameObject otherGameObj) {
		Rectangle player = new Rectangle((int)(parOrigin.getX() + getLocation().getX() - getSize()/2), 
				(int)(parOrigin.getY() + getLocation().getY() - getSize()/2), getSize(), getSize());
		Rectangle other = new Rectangle((int)(parOrigin.getX() + otherGameObj.getLocation().getX() - otherGameObj.getSize()/2), 
				(int)(parOrigin.getY() + otherGameObj.getLocation().getY() - otherGameObj.getSize()/2), otherGameObj.getSize(), otherGameObj.getSize());
		if (player.intersects(other)) {
			return true;
		}
		if(getCollList().contains(otherGameObj))
			getCollList().remove(otherGameObj);
		return false;
	}
	
	/* handles collisions by calling appropriate method in gw */
	@Override
	public void handleCollision(GameObject otherGameObj) {
		if(!getCollList().contains(otherGameObj)) {
			getCollList().add(otherGameObj);
			if(otherGameObj instanceof Base)
				gw.cyborgBaseCollision(this, (Base)otherGameObj);
			else if(otherGameObj instanceof Cyborg)
				gw.cyborgCyborgCollision((Cyborg)otherGameObj);
			else if(otherGameObj instanceof EnergyStation)
				gw.cyborgEnrgStatCollision(this, (EnergyStation)otherGameObj);
			else if(otherGameObj instanceof Drone)
				gw.cyborgDroneCollision(this, (Drone)otherGameObj);
		}
	}
	
	public String toString() {
		return super.toString();
	}
}

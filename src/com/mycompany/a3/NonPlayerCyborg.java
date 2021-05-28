package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Rectangle;

/**
 * Creates a NPC game object
 * @author kiryl
 *
 */

public class NonPlayerCyborg extends Cyborg {
	private GameWorld gw;
	private IStrategy currStrat;
	private Point parOrigin;

	public NonPlayerCyborg(GameWorld gw, float x, float y, int maxDamage, int enrgLvl, IStrategy strat) {
		super(gw, 235,30,30,x,y,100,maxDamage, enrgLvl);
		this.gw = gw;
		setStrategy(strat);
	}
	
	/* returns the stategy that the NPC is currently using */
	public IStrategy getStrategy() {
		return currStrat;
	}
	
	/* sets the NPC strategy to a new strategy */
	public void setStrategy(IStrategy newStrat) {
		currStrat = newStrat;
	}
	
	/* executes the NPC strategy when there is a clock tick */
	public void invokeStrategy() {
		currStrat.apply(this);
	}
	
	/* draws the NPC on the map */
	public void draw(Graphics g, Point pCmpRelPrnt) {
		parOrigin = pCmpRelPrnt;
		g.setColor(getColor());
		g.drawRect((int)(pCmpRelPrnt.getX() - getSize()/2 + getLocation().getX()),(int)(pCmpRelPrnt.getY() - getSize()/2 + getLocation().getY()), getSize(), getSize());
	}
	
	/* checks if npc has collided with a game object */
	@Override
	public boolean collidesWith(GameObject otherGameObj) {
		Rectangle npc = new Rectangle((int)(parOrigin.getX() + getLocation().getX() - getSize()/2), 
				(int)(parOrigin.getY() + getLocation().getY() - getSize()/2), getSize(), getSize());
		Rectangle other = new Rectangle((int)(parOrigin.getX() + otherGameObj.getLocation().getX() - getSize()/2), 
				(int)(parOrigin.getY() + otherGameObj.getLocation().getY() - getSize()/2), otherGameObj.getSize(), otherGameObj.getSize());
		if (npc.intersects(other)) {
			return true;
		}
		if(getCollList().contains(otherGameObj))
			getCollList().remove(otherGameObj);
		return false;
		
	}
	
	/* handles collision by calling appropriate method in gw */
	@Override
	public void handleCollision(GameObject otherGameObj) {
		if(this.getCollList().contains(otherGameObj) == false) {
			getCollList().add(otherGameObj);
			if(otherGameObj instanceof Base)
				gw.cyborgBaseCollision(this, (Base)otherGameObj);
			else if (otherGameObj instanceof Cyborg)
				gw.cyborgCyborgCollision((Cyborg)otherGameObj);
			else if(otherGameObj instanceof EnergyStation)
				gw.cyborgEnrgStatCollision(this, (EnergyStation)otherGameObj);
			else if(otherGameObj instanceof Drone)
				gw.cyborgDroneCollision(this, (Drone)otherGameObj);
		}
	}
	
	public String toString() {
		String parentS = super.toString();
		String s = " Strategy: ";
		if (currStrat instanceof AttackStrategy)
			s = s + "attack";
		else
			s = s + "next base";
		return parentS + s;
	}
}

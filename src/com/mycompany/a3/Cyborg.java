package com.mycompany.a3;

/**
 * Creates the Cyborg game object
 * @author kiryl
 *
 */

public abstract class Cyborg extends Movable implements ISteerable {
	private int steeringDirection;	// changes the heading, initially 0
	private int maximumSpeed;
	private double energyLevel;
	private double energyConsumptionRate;
	private int damageLevel;
	private int maxDamage;
	private int lastBaseReached;
	private int maxEnrgLvl;
	
	public Cyborg() {
		
	}
	
	/* creates the Player Cyborg and NPC game object */
	public Cyborg(GameWorld gw, int r, int g, int b, float x, float y, int size, int newMaxDamage, int enrgLvl) {
		super(gw,235,30,30,x,y,size);
		setHeading(0);
		setSpeed(100);
		steeringDirection = 0;
		maximumSpeed = 300;
		energyLevel = enrgLvl;
		energyConsumptionRate = .05;
		damageLevel = 0;
		maxDamage = newMaxDamage;
		lastBaseReached = 1;
		maxEnrgLvl = enrgLvl;	
	}
	
	/* changes the direction the cyborg is heading based on its steering
	 * direction when clock ticks */
	public void changeHeading() {
		setHeading(steeringDirection + getHeading());
	}
	
	/* sets the steeringDirection to a new steeringDirection which changes the
	 * cyborg's heading when the clock ticks */
	public void setSteeringDirection(int newSteer) {
		steeringDirection = newSteer;
	}
	
	/* sets the maxSpeed to a new maxSpeed which is based on the
	 * cyborg's damage level */
	public void setMaximumSpeed(int newSpeed) {
		maximumSpeed = newSpeed;
	}
	
	/* sets the energyLevel to a new energyLevel */
	public void setEnergyLevel(double d) {
		energyLevel = d;
	}

	/* sets the damageLebel to a new damageLevel when the cyborg collides
	 * with a drone or another cyborg */
	public void setDamageLevel(int newDamageLvl) {
		damageLevel = newDamageLvl;
	}
	
	/* sets the lastBasedReached to a new lastBaseReached when the cyborg
	 * collides with a base that is 1 greater then it's previous lastBaseReached */
	public void setLastBaseReached(int newLBR) {
		lastBaseReached = newLBR;
	}

	/* returns the steeringDirection */
	public int getSteeringDirection() {
		return steeringDirection;
	}
	
	/* returns the maxSpeed */
	public int getMaximumSpeed() {
		return maximumSpeed;
	}
	
	/* returns the energyLevel */
	public double getEnergyLevel() {
		return energyLevel;
	}
	
	/* returns the energyConsuptionRate */
	public double getEnergyConsumptionRate() {
		return energyConsumptionRate;
	}
	
	/* returns the damageLevel */
	public int getDamageLevel() {
		return damageLevel;
	}
	
	/* returns the maxDamage the cyborg could get before it dies */
	public int getMaxDamage() {
		return maxDamage;
	}
	
	/* returns the lastBaseReached*/
	public int getLastBaseReached() {
		return lastBaseReached;
	}
	
	public int getMaxEnrgLvl() {
		return maxEnrgLvl;
	}
	
	public String toString() {
		String parentS = super.toString();
		String s = "\nSteeringDirection:" + getSteeringDirection() + " MaxSpeed:" + getMaximumSpeed() 
				+ " EnergyLevel:" + (int)getEnergyLevel() + " DamageLevel:" + getDamageLevel() + " LastBaseReached:"
				+ getLastBaseReached() + " EnergyConsRate:" + getEnergyConsumptionRate() + " MaxDamage:" + getMaxDamage();
		return parentS + s;
	}
}

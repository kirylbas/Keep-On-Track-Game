package com.mycompany.a3;

/**
 * Creates the Movable game objects PlayerCyborg, NPC, and Drone
 * @author kiryl
 *
 */

public abstract class Movable extends GameObject {
	private int heading;	// direction of movement; initially 0 for cyborg, between random 0-359 for drone
	private int speed;		// initially positive non-zero value for cyborg, between random 5-10 for drone
	private GameWorld gw;
	
	public Movable() {
	
	}
	
	/* Constructor used by Drone */
	public Movable(GameWorld gw, int r, int g, int b) {
		super(r,g,b);
		this.gw = gw;
	}
	
	/* Constructor used by PlayerCyborg and NPC */
	public Movable(GameWorld gw, int r, int g, int b, float x, float y, int size) {
		super(r,g,b,x,y,size);
		this.gw = gw;
	}
	
	/* sets the direction the movable object is heading */
	public void setHeading(int newHead) {
		heading = newHead;
	}
	
	/* sets the speed of the movable object to a new speed */
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}
	
	/* sets the size of the movable object to a new size */
	public void setSize(int size) {}
	
	/* returns the direction the object is heading */
	public int getHeading() {
		return heading;
	}
	
	/* returns the speed of the object */
	public int getSpeed() {
		return speed;
	}
	
	/* moves the object based on its speed and heading when clock ticks */
	public void move(double time) {
		double dist = getSpeed() * time/1000;
		double theta = Math.toRadians(90.0 - heading);
		double deltaX = Math.cos(theta) * dist;
		double deltaY = Math.sin(theta) * dist;
		double newX = Math.round((getLocation().getX() + deltaX) * 10.0f/10.0f);
		double newY = Math.round((getLocation().getY() + deltaY) * 10.0f/10.0f);
		if(newX > gw.getWidth() - getSize()/2|| newY > gw.getHeight() - getSize()/2 || newX <  0 + getSize()/2 || newY < 0 + getSize()/2) {	// the new location is off the map
			if (this instanceof Drone) {
				if(getHeading() - 180 >= 0)		// rotates the heading 180 back towards the map
					setHeading(getHeading() - 180);
				else
					setHeading(getHeading() + 180);
			} 
			if(newX > gw.getWidth() - getSize()/2) 
				newX = gw.getWidth() - getSize()/2;
			else if (newX < getSize()/2)
				newX = getSize()/2;
			if(newY > gw.getHeight() - getSize()/2)
				newY = gw.getHeight() - getSize()/2;
			else if (newY < getSize()/2)
				newY = getSize()/2;
		}
		setLocation((float)newX, (float)newY);
	}
	
	public String toString() {
		String parentS = super.toString();
		String s = "Heading: " + getHeading() + " Speed: " + getSpeed();
		return parentS + s;
	}
}

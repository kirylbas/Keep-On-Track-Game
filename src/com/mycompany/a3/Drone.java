package com.mycompany.a3;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Rectangle;

/**
 * Creates the drone game object
 * @author kiryl
 *
 */

public class Drone extends Movable {
	private Random rand = new Random();
	private int randSpeed = rand.nextInt(51) + 100;	// generates random num between 5-10 to set as the drone's heading
	private int randHeading = rand.nextInt(360);	// generates random num between 0-359 to set as the drone's steeringDirection
	private Point parOrigin;
	
	public Drone(GameWorld gw) {
		super(gw,160,160,160);
		setSpeed(randSpeed);
		setHeading(randHeading);
	}
	
	/* empty-body overrides the parents method and prevents the drone's color
	 * from being changed */
	public void setColor(int r, int g, int b) {}
	
	/* draws the drone on the map */
	public void draw(Graphics g, Point pCmpRelPrnt) {
		parOrigin = pCmpRelPrnt;
		int[] x = {(int)(pCmpRelPrnt.getX() + getLocation().getX()), (int)(pCmpRelPrnt.getX() + getLocation().getX() + getSize()), (int)(pCmpRelPrnt.getX() + getLocation().getX() + getSize()/2)};
		int[] y = {(int)(pCmpRelPrnt.getY()+ getLocation().getY()),(int)(pCmpRelPrnt.getY() + getLocation().getY()),(int)(pCmpRelPrnt.getY() + getLocation().getY() + getSize())};
		g.setColor(getColor());
		g.drawPolygon(x, y, 3);
	}
	
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
	
	/* colliding with energy station is handled in plaver/npc handleCollision() */
	@Override
	public void handleCollision(GameObject otherGameObj) {}
	
	public String toString() {
		return super.toString();
	}
}

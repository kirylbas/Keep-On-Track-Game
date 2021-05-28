package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Rectangle;

/**
 * Creates the EnergyStation game object
 * @author kiryl
 *
 */

public class EnergyStation extends Fixed {
	private int capacity;	// amount of energy in enrgStat = size
	private GameWorld gw;
	
	/* only value that stays the same between all energy stations is the color;
	 * every other value is randomly determined in the GameObject constructor */
	public EnergyStation(GameWorld gw) {
		super(50,235,50);
		this.gw = gw;
		capacity = getSize();
	}
	
	/* sets the capacity of the energy station */
	public void setCapacity(int newCap) {
		capacity = newCap;
	}
	
	/* returns the capacity of the energy station */
	public int getCapacity() {
		return capacity;
	}
	
	/* draws the enrgy station on the map */
	public void draw(Graphics g, Point pCmpRelPrnt) {
		if (!isSelected() || !gw.isPaused()) {
			g.setColor(getColor());
			g.fillArc((int)(pCmpRelPrnt.getX() - getSize()/2 + getLocation().getX()), (int)(pCmpRelPrnt.getY() - getSize()/2 + getLocation().getY()), 2 * getSize(), 2 * getSize(), 0, 360);
			g.setColor(ColorUtil.BLACK);
			g.drawString(Integer.toString(capacity),(int)(pCmpRelPrnt.getX() + getSize()/4 + getLocation().getX()), (int)(pCmpRelPrnt.getY() + getSize()/4 + getLocation().getY() - 10));
			setSelected(false);
		} else {
			g.setColor(getColor());
			g.drawArc((int)(pCmpRelPrnt.getX() - getSize()/2 + getLocation().getX()), (int)(pCmpRelPrnt.getY() - getSize()/2 + getLocation().getY()), 2 * getSize(), 2 * getSize(), 0, 360);
			g.setColor(ColorUtil.BLACK);
			g.drawString(Integer.toString(capacity),(int)(pCmpRelPrnt.getX() + getSize()/4 + getLocation().getX()), (int)(pCmpRelPrnt.getY() + getSize()/4 + getLocation().getY() - 10));
			setSelected(true);
		}
	}

	@Override
	public boolean collidesWith(GameObject otherGameObj) {
		return false;
	}
	
	/* colliding with energy station is handled in plaver/npc handleCollision() */
	@Override
	public void handleCollision(GameObject otherGameObj) {}
	
	public String toString() {
		String parentS = super.toString();
		String s = " Capacity:" + getCapacity();
		return parentS + s;
	}
}

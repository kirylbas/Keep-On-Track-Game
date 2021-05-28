package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Rectangle;

/**
 * Creates the Base game object
 * 
 * @author kiryl
 *
 */

public class Base extends Fixed {
	private int sequenceNumber;	// the number the base is 
	private GameWorld gw;
	
	public Base() {
		
	}
	
	/* when instantiating, the seqNum and location are passed because
	 * these are the only values that change when instantiating a base */
	public Base(GameWorld gw, int newSeqNum, float x, float y) {
		super(30, 170, 235, x, y, 100);
		this.gw = gw;
		sequenceNumber = newSeqNum;
	}
	
	/* returns the seqNum of the base */
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	
	/* empty-body overrides the grandparent so the Base color cannot be changed */
	public void setColor(int r, int g, int b) {};
	
	/* draws the base on the map */
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int[] x = {(int)(pCmpRelPrnt.getX() + getLocation().getX()), (int)(pCmpRelPrnt.getX() + getLocation().getX() + getSize()), (int)(pCmpRelPrnt.getX() + getLocation().getX() + getSize()/2)};
		int[] y = {(int)(pCmpRelPrnt.getY()+ getLocation().getY()),(int)(pCmpRelPrnt.getY() + getLocation().getY()),(int)(pCmpRelPrnt.getY() + getLocation().getY() + getSize())};
		g.setColor(getColor());
		
		if (!isSelected() || !gw.isPaused()) {
			g.fillPolygon(x, y, 3);
			setSelected(false);
		} else {
			g.drawPolygon(x, y, 3);
			setSelected(true);
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + getSequenceNumber(),(int)(pCmpRelPrnt.getX() + getLocation().getX() + getSize()/4 + 15),(int)(pCmpRelPrnt.getY() + getLocation().getY() + getSize()/4 - 15));
		
	}
	
	@Override
	public boolean collidesWith(GameObject otherGameObj) {
		return false;
	}
	
	/* this collision is handled inside of the player/npc cyborg's handle collision */
	@Override
	public void handleCollision(GameObject otherGameObj) {
		
	}
	
	public String toString() {
		String parentS = super.toString();
		String s = " SeqNumber:" + getSequenceNumber();
		return parentS + s;
	}
}

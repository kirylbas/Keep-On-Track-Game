package com.mycompany.a3;

import com.codename1.charts.models.Point;

/**
 * This is the contrucot for the fixed game objects Base and EnergyStation
 * @author kiryl
 *
 */

public abstract class Fixed extends GameObject implements ISelectable {
	private boolean isSelected;

	public Fixed() {
		
	}
	
	/* Constructor for the EnergyStation game object */
	public Fixed(int r, int g, int b) {
		super(r,g,b);
	}
	
	/* Constructor for the Base game object */
	public Fixed(int r, int g, int b, float x, float y, int size) {
		super(r,g,b, x, y, size);
	}
	
	/* empty-body method that prevents the size of Fixed game objects from being changed */
	public void setSize(int size) {}
	
	@Override
	public void setSelected(boolean b) {
		isSelected = b;
	}

	@Override
	public boolean isSelected() {
		return isSelected;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		float pX = pPtrRelPrnt.getX();
		float pY = pPtrRelPrnt.getY();
		
		float xLoc = pCmpRelPrnt.getX() + getLocation().getX();
		float yLoc = pCmpRelPrnt.getY() + getLocation().getY();
		
		if((pX >= xLoc) && (pX <= xLoc + getSize()) && (pY >= yLoc) && (pY <= yLoc + getSize())) 
			return true;
		return false;
	}	
}

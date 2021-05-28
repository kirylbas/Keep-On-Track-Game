package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

/**
 * Outputs text to console that specifies all the game state data
 * for every game object when there is a change and notifyObservers() is called
 * Acts as a View.
 * @author kiryl
 *
 */

public class MapView extends Container implements Observer {
	private GameWorld gw;
	
	public MapView() {
		
	}
	
	/* notifyObserver() automatically calls this method, which in turn
	 * calls the map method in gw which outputs the graphical game state data*/
	@Override
	public void update(Observable obs, Object arg) {
		gw = (GameWorld)obs;
		gw.map();
		repaint();	
	}
	
	/* draws each game object */
	public void paint(Graphics g) {
		super.paint(g);
		GameObjectCollection gameObjColl= gw.getGameObjCollection();
		IIterator theGameObjs = gameObjColl.getIterator();
		Point parOrigin = new Point(getX(),getY());
		while(theGameObjs.hasNext()) {
			GameObject gameObj = theGameObjs.getNext();
			gameObj.draw(g, parOrigin);
		}
	}
	
	/* checks if pointer is pressed on a selectable game object and moves the 
	 * game object to a new position if it is selected and position button is pressed */
	public void pointerPressed(int x, int y) {
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x,y);
		Point pCmpRelPrnt = new Point(getX(),getY());
		GameObjectCollection gameObjColl= gw.getGameObjCollection();
		IIterator theGameObjs = gameObjColl.getIterator();
		// draws the object that is selected in its "selected" form
		if(gw.isPaused() && !gw.getChangePosit()) {
			while(theGameObjs.hasNext()) {
				GameObject gameObj = theGameObjs.getNext();
				if (gameObj instanceof Fixed) {
					if(((Fixed)gameObj).contains(pPtrRelPrnt,pCmpRelPrnt))
						((Fixed)gameObj).setSelected(true);
					else
						((Fixed)gameObj).setSelected(false);
				}
					
			}
		} 
		// moves selected object to new location when position is pressed
		if(gw.isPaused() && gw.getChangePosit()) {
			while(theGameObjs.hasNext()) {
				GameObject gameObj = theGameObjs.getNext();
				if (gameObj instanceof Fixed) {
					if(((Fixed)gameObj).isSelected())  {
						((Fixed)gameObj).setLocation(x - getX() - gameObj.getSize()/2, y - getY()- gameObj.getSize()/2);
						gw.setChangePosit(false);
						((Fixed)gameObj).setSelected(false);
						break;
					}
				}
			}
		}
		repaint();
	}
}

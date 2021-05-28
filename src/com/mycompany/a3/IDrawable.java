package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

/**
 * Interface for drawing game objects.
 * @author kiryl
 *
 */

public interface IDrawable {
	
	public void draw(Graphics g, Point pCmpRelPrnt);

}

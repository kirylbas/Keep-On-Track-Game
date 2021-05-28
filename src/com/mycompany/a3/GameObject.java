package com.mycompany.a3;
import java.util.ArrayList;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/** 
 * This class creates all the GameObjects in the game
 * @author kiryl
 *
 */
public abstract class GameObject implements IDrawable, ICollider {
	private int size; 						// length of bounding square
	private Point location = new Point(); 	// center is (X,Y); float values ranging from 0.0 - 1000.0
	private int color;						// [r,g,b]
	private ArrayList<GameObject> collList = new ArrayList<GameObject>();
	
	public GameObject() {
		
	}
	
	/* this constructor is used to instantiate energy station & drone */
	public GameObject(int r, int g, int b) {
		color = ColorUtil.rgb(r,g,b);
		Random rand = new Random();
		size = rand.nextInt(51) + 50;
		float randX = rand.nextFloat() * 1500.0f;
		float randY = rand.nextFloat() * 1000.0f;
		location.setX(Math.round(randX * 10.0f)/10.0f);
		location.setY(Math.round(randY * 10.0f)/10.0f);
	}
	
	/* this constructor is used to instantiate player cyborg, npc, & base */
	public GameObject(int r, int g, int b, float x, float y, int size) {
		color = ColorUtil.rgb(r,g,b);
		location.setX(x);
		location.setY(y);
		this.size = size;
	}
	
	/* sets the size of the game objects */
	public void setSize(int newSize) {
		size = newSize;
	}
	
	/* sets the location of the game objects */
	public void setLocation(float newX, float newY) {
		location.setX(newX);
		location.setY(newY);
	}
	
	/* sets the color of the game objects */
	public void setColor(int r, int g, int b) {
		color = ColorUtil.rgb(r,g,b);
	}
	
	/* returns the size of the game objects */
	public int getSize() {
		return size;
	}
	
	/* returns the location of the game objects */
	public Point getLocation() {
		return location;
	}
	
	/* returns the color of the game objects */
	public int getColor() {
		return color;
	}
	
	public ArrayList<GameObject> getCollList() {
		return collList;
	}
	
	public String toString() {
		String s = "Location:[" + getLocation().getX() + "," + getLocation().getY() + "]"
				+ " Color:[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + ","
				+ ColorUtil.blue(getColor()) + "]" + " Size: " + getSize() + " ";
		return s;
	}
}

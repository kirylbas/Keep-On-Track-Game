package com.mycompany.a3;

/**
 * Interface used by GameObjectIterator to check if there are game objects left,
 * getting the next game object, and getting the game object at a specific index
 * @author kiryl
 *
 */

public interface IIterator {
	
	public boolean hasNext();
	
	public GameObject getNext();

	public GameObject getGameObj(int i);

}

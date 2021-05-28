package com.mycompany.a3;

/**
 * Interface used by the GameObjectCollection to add game objects
 * and getting the iterator
 * @author kiryl
 *
 */

public interface ICollection {
	
	public void add(GameObject newGameObj);
	
	public IIterator getIterator();

}

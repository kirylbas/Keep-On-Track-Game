package com.mycompany.a3;

import java.util.ArrayList;

/**
 * Creates a collection which all game objects are stored in. Traversing through the
 * collection is done using the Iterator.
 * 
 * @author kiryl
 *
 */

public class GameObjectCollection implements ICollection {
	private ArrayList<GameObject> gameList;
	
	public GameObjectCollection() {
		gameList = new ArrayList<>();	
	}
	
	/* returns the array list that is instantiated inside the constructor*/
	public ArrayList<GameObject> getGameList() {
		return gameList;
	}

	/* adds game objects to the array list */
	@Override
	public void add(GameObject newGameObj) {
		gameList.add(newGameObj);
	}

	/* returns the Iterator*/
	@Override
	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	/* custom Iterator that is used to traverse through the collection */
	private class GameObjectIterator implements IIterator {
		private int currIndex;
		
		public GameObjectIterator() {
			currIndex = -1;
		}
		
		/* checks if the array list is not empty */
		public boolean hasNext() {
			if(gameList.size() <= 0 || currIndex == gameList.size() - 1) 
				return false;
			return true;
		}
		
		/* gets the next game object in the array list*/
		public GameObject getNext() {
			currIndex++;
			return gameList.get(currIndex);
		}
		
		/* gets the game object at a specified index */
		public GameObject getGameObj(int i) {
			return gameList.get(i);
		}
		
	}
}

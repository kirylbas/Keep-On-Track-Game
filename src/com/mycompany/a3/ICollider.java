package com.mycompany.a3;

/**
 * Interface for detecting and handling collisions between game objects.
 * @author kiryl
 *
 */

public interface ICollider {
	
	public boolean collidesWith(GameObject otherGameObj);
	
	public void handleCollision(GameObject otherGameObj);
}

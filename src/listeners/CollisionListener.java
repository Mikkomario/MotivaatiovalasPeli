package listeners;

import handleds.Colliding;
import handleds.LogicalHandled;

/**
 * CollisionListeners are interested in collisions with other 3D objects and react 
 * to them somehow.
 *
 * @author Gandalf.
 *         Created 6.12.2012.
 */
public interface CollisionListener extends LogicalHandled
{
	/**
	 * @return The x-coordinate of the position with which collisions are checked
	 */
	public int getCollisionX();
	
	/**
	 * @return The y-coordinate of the position with which collisions are checked
	 */
	public int getCollisionY();
	
	/**
	 * @return The z-coordinate of the position with which collisions are checked
	 */
	public int getCollisionZ();
	
	/**
	 * 
	 * This method is called each time the object collides with another object
	 *
	 * @param collidedObject The object with which the listener collided
	 */
	public void onCollision(Colliding collidedObject);
}

package handleds;

/**
 * Colliding objects can tell when they collide with a point
 *
 * @author Gandalf.
 *         Created 12.12.2012.
 */
public interface Colliding extends Handled
{
	/**
	 * 
	 * Tells whether a given point collides with the object
	 *
	 * @param x The x-coordinate of the collision point
	 * @param y The y-coordinate of the collision point
	 * @param z The z-coordinate of the collision point
	 * @return Is the colliding with the object
	 */
	public boolean pointCollides(int x, int y, int z);
	
	/**
	 * @return The z-coordinate of the object's position
	 */
	public double getZ();
	
	/**
	 * @return The x-coordinate of the object's position
	 */
	public double getX();
	
	/**
	 * @return The y-coordinate of the object's position
	 */
	public double getY();
}

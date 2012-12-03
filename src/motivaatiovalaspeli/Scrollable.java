package motivaatiovalaspeli;

/**
 * All scrollable objects are scrolled on the z-axis with a given speed
 *
 * @author Gandalf.
 *         Created 3.12.2012.
 */
public interface Scrollable
{
	/**
	 * @return The object's z-coordinate
	 */
	public double getZ();
	
	/**
	 * Changes the object's z-coordinate
	 * 
	 * @param z The object's new z-coordinate
	 */
	public void setZ(double z);
	
	/**
	 * @return How large the z can become before the object is returned to the 
	 * minimum z
	 */
	public int getMaxZ();
	
	/**
	 * @return How small the z can become before the object
	 */
	public int getMinZ();
	
	/**
	 * @return Is the scrollable object returned to the max / min z when it goes out of range
	 */
	public boolean isReturned();
	
	/**
	 * This method is called when the object goes out of its range (over Maxz or under Minz)
	 * This method should not implement the return to the starting position as it is done 
	 * in the scroller
	 */
	public void onOutOfRange();
}
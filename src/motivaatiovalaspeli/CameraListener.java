package motivaatiovalaspeli;

/**
 * Cameralisteners are interested in camera's position and movement and should 
 * be informed when changes in the former happen
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public interface CameraListener
{	
	/**
	 * 
	 * This method should be called when the camera's position changes and is used 
	 * to keep the object in time with the camera's movement and/or position.
	 *
	 * @param posx The camera's current x-coordinate
	 * @param posy The camera's current y-coordinate
	 * @param posz The camera's current z-coordinate
	 */
	public void informCameraPosition(int posx, int posy, int posz);
	
	/**
	 * @return Is the object currently interested in changes in camera
	 */
	public boolean isActive();
	
	/**
	 * @return Won't the object be interested in listening the camera anymore
	 */
	public boolean isDead();
	
	/**
	 * This method tries to make the listener permanently inactive
	 * 
	 * @return Was the listener made permanently inactive
	 */
	public boolean kill();
	
	/**
	 * This method tries to make the listener inactive
	 *
	 * @return Was the listener made inactive
	 */
	public boolean inActivate();
	
	/**
	 * This method tries to reactivate the listener
	 *
	 * @return Was the listener reactivated
	 */
	public boolean activate();
}

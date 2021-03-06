package listeners;

import handleds.LogicalHandled;

/**
 * Cameralisteners are interested in camera's position and movement and should 
 * be informed when changes in the former happen
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public interface CameraListener extends LogicalHandled
{	
	/**
	 * 
	 * This method should be called when the camera's position changes and is used 
	 * to keep the object in time with the camera's movement and/or position.
	 *
	 * @param posx The camera's current x-coordinate
	 * @param posy The camera's current y-coordinate
	 * @param posz The camera's current z-coordinate
	 * @param xangle The new xangle of the camera
	 * @param yangle The new yangle of the camera
	 */
	public void informCameraPosition(int posx, int posy, int posz, int xangle, int yangle);
}

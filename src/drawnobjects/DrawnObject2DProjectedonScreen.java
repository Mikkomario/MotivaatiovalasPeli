package drawnobjects;

import motivaatiovalaspeli.HelpMath;

/**
 * Works the same as 2DProjected but the position is definded with angles and length
 * from the camera instead of normal coordinates
 *
 * @author Gandalf.
 *         Created 14.12.2012.
 */
public abstract class DrawnObject2DProjectedonScreen extends DrawnObject2DProjected
{
	// ATTRIBUTES	-------------------------------------------------------
	
	private int scrxangle, scryangle, length;
	
	
	// CONSTRUCTOR	-------------------------------------------------------
	
	/**
	 * Creates a new drawnobject to the given position (defined with angles and 
	 * distance).
	 * 
	 * @param scrxangle The object's xangle from the camera (on screen)
	 * @param srcyangle The object's yangle from the camera (on screen)
	 * @param length The object's distance from the camera
	 * @param camerax The camera's position on the x-axis
	 * @param cameray The camera's position on the y-axis
	 * @param cameraz The camera's position on the z-axis
	 * @param cameraxangle The camera's view angle around the x-axis
	 * @param camerayangle The camera's view angle around the y-axis
	 */
	public DrawnObject2DProjectedonScreen(int scrxangle, int srcyangle, int length, 
			int camerax, int cameray, int cameraz, int cameraxangle, 
			int camerayangle)
	{
		super(0, 0, 0, camerax, cameray, cameraz, cameraxangle, camerayangle);
		setPosition(scrxangle, srcyangle, length);
		
		// Initializes attributes
		this.scrxangle = scrxangle;
		this.scryangle = srcyangle;
		this.length = length;
	}
	
	
	// IMPLEMENTED METHODS	------------------------------------------------
	
	/**
	 * Changes the object's position on screen
	 *
	 * @param xangle The object's xangle from the camera
	 * @param yangle The object's yangle from the camera
	 * @param length The object's new distance from the camera
	 */
	public void setPosition(int xangle, int yangle, int length)
	{
		this.scrxangle = xangle;
		this.scryangle = yangle;
		
		int x = getCamX() + (int) HelpMath.lendirX(length, this.scryangle + getCamYAngle());
		int z = getCamZ() + (int) HelpMath.lendirY(length, this.scryangle + getCamYAngle());
		int y = getCamY() + (int) HelpMath.lendirY(length, this.scrxangle + getCamXAngle());
		
		setPosition(x, y, z);
	}
	
	@Override
	public void informCameraPosition(int posx, int posy, int posz, int xangle, int yangle)
	{
		// Changes the object's position
		
		super.informCameraPosition(posx, posy, posz, xangle, yangle);
	}
}

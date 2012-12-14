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
		checkPosition();
	}
	
	@Override
	public void informCameraPosition(int posx, int posy, int posz, int xangle, int yangle)
	{
		// Changes the object's position
		super.informCameraPosition(posx, posy, posz, xangle, yangle);
		// TODO: Might be in the wrong place
		checkPosition();
	}
	
	
	// OTHER METHODS	----------------------------------------------------
	
	private void checkPosition()
	{
		//System.out.println(this.scryangle);
		//System.out.println(HelpMath.lendirX(this.length, this.scryangle));
		//System.out.println(getCamYAngle());
		//System.out.println(HelpMath.lendirX(100, getCamYAngle()) + HelpMath.lendirX(100, this.scryangle));
		//System.out.println(HelpMath.lendirX(this.length, 
		//		this.scryangle + getCamYAngle()));
		//System.out.println(HelpMath.lendirX(this.length, 
		//		this.scryangle));
		//System.out.println();
		//System.out.println(getCamYAngle() + this.scryangle);
		//System.out.println(HelpMath.lendirX(100, getCamYAngle() + this.scryangle));
		//System.out.println(getCamX() + HelpMath.lendirX(100, getCamYAngle() + this.scryangle));
		
		int x = getCamX() + (int) HelpMath.lendirX(this.length, 
				this.scryangle + getCamYAngle());
		int z = getCamZ() + (int) HelpMath.lendirY(this.length, this.scryangle 
				+ getCamYAngle());
		int y = getCamY() + (int) HelpMath.lendirY(this.length, this.scrxangle 
				+ getCamXAngle());
		/*
		int x = getCamX() + (int) HelpMath.lendirX(this.length, 
				this.scryangle) +  (int) HelpMath.lendirX(this.length, 
				getCamYAngle());
		int z = getCamZ() + (int) HelpMath.lendirY(this.length, this.scryangle)+
				(int) HelpMath.lendirY(this.length, getCamYAngle());
		int y = getCamY() + (int) HelpMath.lendirY(this.length, this.scrxangle)+
				(int) HelpMath.lendirY(this.length, getCamXAngle());
		*/
		
		setPosition((double) x, (double) y, (double) z);
		
		//System.out.println(getCamYAngle() + ", " + getCamXAngle());
		//System.out.println(getX() + ", " + getY() + ", " + getZ());
	}
}

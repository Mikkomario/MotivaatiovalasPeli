package motivaatiovalaspeli;

/**
 * Rocks are scattered all over the sea floor and serve as simple obstacles for the player.
 *
 * @author Gandalf.
 *         Created 5.12.2012.
 */
public class Rock extends DrawnObject3D implements Scrollable
{
	// ATTRIBUTES	------------------------------------------------------
	
	private int minZ, maxZ;
	
	
	// CONSTRUCTOR	-------------------------------------------------------

	/**
	 * Creates a new rock to the given position. The rock's appearance is 
	 * randomized automatically.
	 *
	 * @param x The rock's position's x-coordinate (Pxl)
	 * @param y The rock's position's y-coordinate (Pxl)
	 * @param z The rock's position's z-coordinate (Pxl)
	 * @param minZ How far on the z-axis the object can go before it is destroyed (Pxl)
	 * @param maxZ How close on the z-axis the object can come before it is destroyed (Pxl)
	 */
	public Rock(int x, int y, int z, int minZ, int maxZ)
	{
		super(x, y, z);
		// Initializes attributes
		this.minZ = minZ;
		this.maxZ = maxZ;
		
		// TODO Add appearance transforming
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public void setZ(double z)
	{
		setPosition(getX(), getY(), z);
	}

	@Override
	public int getMaxZ()
	{
		return this.maxZ;
	}

	@Override
	public int getMinZ()
	{
		return this.minZ;
	}

	@Override
	public boolean isReturned()
	{
		// Rocks are not returned to the field when they go over maxZ, instead
		// they are destroyed
		return false;
	}

	@Override
	public void onOutOfRange()
	{
		// The rock is destroyed when it goes out of range
		endDrawing();
		//setInvisible();
	}

	@Override
	public void drawSelf3D(MotivaatiovalasPeli applet)
	{
		// Draws a simple box with a size of 64x64
		applet.fill(50);
		applet.stroke(25);
		applet.box(64, 64, 64);
		
		// Resets
		applet.noFill();
		applet.noStroke();
		
		//System.out.println("Rock is alive!");
	}

	@Override
	public int getOriginX()
	{
		// Rocks origin is at the center (32, 32, 32)
		return 32;
	}

	@Override
	public int getOriginY()
	{
		// Rocks origin is at the center (32, 32, 32)
		return 32;
	}

	@Override
	public int getOriginZ()
	{
		// Rocks origin is at the center (32, 32, 32)
		return 32;
	}

	@Override
	public boolean pointCollides(int x, int y, int z)
	{
		// TODO Figure something out here someday
		return false;
	}

	@Override
	public boolean objectCollides(DrawnObject3D d)
	{
		// TODO Same here, nothing yet
		return false;
	}

}

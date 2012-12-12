package model;

import handleds.Colliding;
import scrolling.Scrollable;
import motivaatiovalaspeli.HelpMath;
import motivaatiovalaspeli.MotivaatiovalasPeli;
import drawnobjects.DrawnObject3D;

/**
 * Kuhas try to depress valas and are killed if they collide with it.
 *
 * @author Gandalf.
 *         Created 9.12.2012.
 */
public class Kuha extends DrawnObject3D implements Scrollable, Colliding
{
	// ATTRIBUTES	------------------------------------------------------
	
	private int creationZ;
	private int maxZ;
	
	
	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * Creates a new kuha to the given position with the given information
	 *
	 * @param x The x-coordinate of kuha's position
	 * @param y The y-coordinate of kuha's position
	 * @param z The z-coordinate of kuha's position
	 * @param maxZ  How close the kuha can come before it is destroyed
	 */
	public Kuha(int x, int y, int z, int maxZ)
	{
		super(x, y, z);
		// Initializes attributes
		this.creationZ = z;
		this.maxZ = maxZ;
	}
	
	
	// IMPLEMENTED METHODS	---------------------------------------------

	@Override
	public void drawSelf3D(MotivaatiovalasPeli applet)
	{
		// TODO Add cool models
		applet.fill(255, 255, 0);
		applet.stroke(0);
		
		applet.box(32, 32, 32);
		
		applet.noStroke();
		applet.noFill();
	}

	@Override
	public int getOriginX()
	{
		return 16;
	}

	@Override
	public int getOriginY()
	{
		return 16;
	}

	@Override
	public int getOriginZ()
	{
		return 0;
	}

	@Override
	public boolean pointCollides(int x, int y, int z)
	{
		// Simply tests whether the point is 'close enough'
		return HelpMath.pointDistance( (int) getX(), (int) getY(), (int) getZ(), 
				x, y, z) < 50;
	}

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
		return this.creationZ;
	}

	@Override
	public boolean isReturned()
	{
		return false;
	}

	@Override
	public void onOutOfRange()
	{
		// The kuha is killed
		kill();
	}
}

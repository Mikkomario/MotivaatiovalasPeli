package model;

import scrolling.Scrollable;
import sprites.Sprite;
import sprites.SpriteBank;
import drawnobjects.DrawnObject2DProjected;
import motivaatiovalaspeli.MotivaatiovalasPeli;

/**
 * A seagrass represents a single plant in the bottom of the sea. The plant is 
 * an 2 dimensional object in a 3 dimensional world.
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public class Seagrass extends DrawnObject2DProjected implements Scrollable
{
	// ATTRIBUTES	------------------------------------------------------
	
	private Sprite sprite;
	
	private int minZ, maxZ;
	
	
	// CONSTRUCTOR	------------------------------------------------------

	/**
	 * Creates a new seagrass to the given position facing the other given 
	 * position.
	 *
	 * @param x The object's position's x-coordinate
	 * @param y The object's position's y-coordinate
	 * @param z The object's position's z-coordinate
	 * @param minZ How far the grass can go before it is destroyed
	 * @param maxZ How close the grass can come before it is destroyed
	 * @param camerax The camera's position's x-coordinate
	 * @param cameray The camera's position's y-coordinate
	 * @param cameraz The camera's position's z-coordinate
	 * @param spritebank The bank from which the seagrass sprite can be found
	 */
	public Seagrass(int x, int y, int z, int minZ, int maxZ, int camerax,
			int cameray, int cameraz, SpriteBank spritebank)
	{
		super(x, y, z, camerax, cameray, cameraz);
		this.sprite = spritebank.getSprite("seagrass");
		this.minZ = minZ;
		this.maxZ = maxZ;
	}

	@Override
	public void drawSelf3D(MotivaatiovalasPeli applet)
	{
		applet.image(this.sprite.getSubImage(0), 0, 0);
		/*
		applet.fill(255, 0, 0);
		applet.rect(0, 0, 64, 64);
		applet.noFill();
		*/
		//System.out.println(getZscale());
	}

	@Override
	public int getOriginX()
	{
		return this.sprite.getOriginX();
		//return 32;
	}

	@Override
	public int getOriginY()
	{
		return this.sprite.getOriginY();
		//return 64;
	}

	@Override
	public int getOriginZ()
	{
		return 0;
	}

	@Override
	public void setZ(double z)
	{
		this.setPosition(getX(), getY(), z);
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
		return false;
	}

	@Override
	public void onOutOfRange()
	{
		// When seagrass goes out of range, it is destroyed
		kill();
	}
}

package model;

import handleds.Colliding;

import java.awt.Point;
import java.util.Random;

import scrolling.Scrollable;

import drawnobjects.DrawnObject3D;

import motivaatiovalaspeli.MotivaatiovalasPeli;

/**
 * Rocks are scattered all over the sea floor and serve as simple obstacles for the player.
 *
 * @author Gandalf.
 *         Created 5.12.2012.
 */
public class Rock extends DrawnObject3D implements Scrollable, Colliding
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
		
		randomTransform();
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
		kill();
		//setInvisible();
	}

	@Override
	public void drawSelf3D(MotivaatiovalasPeli applet)
	{
		//addAngle(0, 0, 1);
		// Sets opacity lower if between camera and player
		int alpha = 255;
		
		if (getZ() > 0)
			alpha = (int) ((1 - getZ() / getMaxZ())*255);
			
		// Draws a simple box with a size of 64x64
		applet.fill(50, 50, 50, alpha);
		applet.stroke(40);
		applet.box(64, 64, 64);
		
		// Resets
		applet.noFill();
		applet.noStroke();
		
		//System.out.println("Rock is alive!");
	}

	@Override
	public double getOriginX()
	{
		// Rocks origin is at the center (32, 32, 32)
		return 0;
	}

	@Override
	public double getOriginY()
	{
		// Rocks origin is at the center (32, 32, 32)
		return 0;
	}

	@Override
	public double getOriginZ()
	{
		// Rocks origin is at the center (32, 32, 32)
		return 0;
	}

	@Override
	public boolean pointCollides(int x, int y, int z)
	{
		// Checks the z
		if (z > getZ() + 32*getZscale() || z < getZ() - 32*getZscale())
			return false;
		
		//System.out.println("Zsame");
		
		// Negates the transformation
		Point negatedPoint = negateTransformations2D(x, y);
		//System.out.println(negatedPoint);
		
		if (negatedPoint.x < getX() - 32)
			return false;
		else if (negatedPoint.x > getX() + 32)
			return false;
		else if (negatedPoint.y < getY() -32)
			return false;
		else if (negatedPoint.y > getY() + 32)
			return false;
		else
			return true;
	}
	
	/*
	@Override
	public boolean isVisible()
	{
		return super.isVisible() && getZ() > -500;
	}
	*/
	
	
	// OTHER METHODS	-------------------------------------------------
	
	private void randomTransform()
	{
		Random rand = new Random();
		
		setAngle(0, 0, rand.nextInt(360));
		setScale(1 + rand.nextDouble()*5, 1 + rand.nextDouble()*2, 1 + rand.nextDouble());
	}
	
	/*
	private int getWidth()
	{
		return (int) (64*getXscale());
	}

	private int getHeight()
	{
		return (int) (64*getYscale());
	}
	*/
}

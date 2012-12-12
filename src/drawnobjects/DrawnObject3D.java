package drawnobjects;

import handleds.Drawable;

import java.awt.Point;

import motivaatiovalaspeli.HelpMath;
import motivaatiovalaspeli.MotivaatiovalasPeli;

/**
 * This class represents a creature or object in the game world that is drawn
 * to screen with a sprite (image)
 *
 * @author Gandalf.
 *         Created 26.11.2012.
 */
public abstract class DrawnObject3D implements Drawable
{
	// TODO: Test this class
	
	// ATTRIBUTES	-------------------------------------------------------
	
	private double xscale, yscale, zscale, x, y, z, zangle, xangle, yangle;
	private boolean visible, alive;
	
	
	// CONSTRUCTOR	-------------------------------------------------------
	
	/**
	 * 
	 * Creates a new spriteobject with the given information. Animation and 
	 * visibility are set 
	 * on at default. Scaling and image angle won't be affected.
	 *
	 * @param x The new x-coordinate of the object (Game world Pxl)
	 * @param y The new y-coordinate of the object (Game world Pxl)
	 * @param z The new z-coordinate of the object (Game world Pxl)
	 * @param sprite The Sprite with which the object will be drawn
	 */
	public DrawnObject3D(int x, int y, int z)
	{
		// Initializes the attributes
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.xscale = 1;
		this.yscale = 1;
		this.zscale = 1;
		this.visible = true;
		this.alive = true;
		this.zangle = 0;
		this.xangle = 0;
		this.yangle = 0;
	}
	
	
	// ABSTRACT METHODS	---------------------------------------------------
	
	/**
	 * 
	 * Each subclass must define how it's drawed. The translation and rotation 
	 * are made in this class however.
	 *
	 * @param applet
	 */
	public abstract void drawSelf3D(MotivaatiovalasPeli applet);
	
	/**
	 * @return The Object's origin's x-translation from the left
	 */
	public abstract int getOriginX();
	
	/**
	 * @return The Object's origin's y-translation from the top
	 */
	public abstract int getOriginY();
	
	/**
	 * @return The Object's origin's z-translation from the center
	 */
	public abstract int getOriginZ();
	
	
	// IMPLEMENTED METHODS	-----------------------------------------------

	@Override
	public void drawSelf(MotivaatiovalasPeli applet)
	{
		// TODO: Change this to 3D
		
		applet.pushMatrix();
		
		// Translates the object
		applet.translate((float) this.x, (float) this.y, (float) this.z);
		// Translates the origin
		applet.translate(-getOriginX(), -getOriginY(), -getOriginZ());
		// Rotates it
		applet.rotateX((float) Math.toRadians(getXAngle()));
		applet.rotateY((float) Math.toRadians(getYAngle()));
		applet.rotateZ((float) Math.toRadians(getZAngle()));
		// Scales it
		applet.scale((float) this.xscale, (float) this.yscale, (float) this.zscale);
		
		// Draws it
		drawSelf3D(applet);
		
		applet.popMatrix();
		
	}

	@Override
	public boolean isVisible()
	{
		return this.visible;
	}


	@Override
	public boolean isDead()
	{
		return !this.alive;
	}


	@Override
	public boolean kill()
	{
		// Ends the drawing and also kills the object
		this.alive = false;
		return true;
	}


	@Override
	public boolean setVisible()
	{
		this.visible = true;
		return true;
	}


	@Override
	public boolean setInvisible()
	{
		this.visible = false;
		return true;
	}
	
	
	// GETTERS & SETTERS	-----------------------------------------------

	
	/**
	 * @return The object's rotation around the z-axis in degrees [0, 360[
	 */
	public double getZAngle()
	{
		return this.zangle;
	}
	
	/**
	 * @return The object's rotation around the x-axis in degrees [0, 360[
	 */
	public double getXAngle()
	{
		return this.xangle;
	}
	
	/**
	 * @return The object's rotation around the y-axis in degrees [0, 360[
	 */
	public double getYAngle()
	{
		return this.yangle;
	}
	
	/**
	 * 
	 * Changes how much the object is rotated before drawing
	 *
	 * @param xangle The angle of the drawn sprite in degrees around the x-axis [0, 360[
	 * @param yangle The angle of the drawn sprite in degrees around the y-axis [0, 360[
	 * @param zangle The angle of the drawn sprite in degrees around the z-axis [0, 360[
	 */
	public void setAngle(double xangle, double yangle, double zangle)
	{
		this.zangle = zangle;
		this.xangle = xangle;
		this.yangle = yangle;
		checkAngle();
	}
	
	/**
	 * 
	 * Increases the object's angle by the given amount
	 *
	 * @param xrotation How much the angle around the x-axis is increased (degrees)
	 * @param yrotation How much the angle around the y-axis is increased (degrees)
	 * @param zrotation How much the angle around the z-axis is increased (degrees)
	 */
	public void addAngle(double xrotation, double yrotation, double zrotation)
	{
		this.zangle += zrotation;
		this.xangle += xrotation;
		this.yangle += yrotation;
		//System.out.println(getXAngle());
		checkAngle();
	}
	
	/**
	 * @return How much the sprite is scaled horizontally (from the original 
	 * angle) (default at 1)
	 */
	public double getXscale()
	{
		return this.xscale;
	}
	
	/**
	 * @return How much the sprite is scaled vertically (from the original 
	 * angle) (default at 1)
	 */
	public double getYscale()
	{
		return this.yscale;
	}
	
	/**
	 * @return How much the sprite is scaled z-axelwise (from the original 
	 * angle) (default at 1)
	 */
	public double getZscale()
	{
		return this.zscale;
	}
	
	/**
	 * 
	 * Changes how much the sprite is scaled horizontally and vertically
	 * (from the original angle)
	 *
	 * @param xscale The new horizontal scale of the sprite (default at 1)
	 * @param yscale The new vertical scale of the sprite (default at 1)
	 * @param zscale The new z-axiswise scale of the sprite (default at 1)
	 */
	public void setScale(double xscale, double yscale, double zscale)
	{
		this.xscale = xscale;
		this.yscale = yscale;
		this.zscale = zscale;
	}
	
	/**
	 * @return X-coordinate of the objects position in the game world (pxl)
	 */
	public double getX()
	{
		return this.x;
	}
	
	/**
	 * @return Y-coordinate of the objects position in the game world (pxl)
	 */
	public double getY()
	{
		return this.y;
	}
	
	/**
	 * @return Z-coordinate of the objects position in the game world (pxl)
	 */
	public double getZ()
	{
		return this.z;
	}
	
	/**
	 * 
	 * Changes the object's position in the game world
	 *
	 * @param x The new position's x-coordinate (pxl)
	 * @param y The new position's y-coordinate (pxl)
	 * @param z The new position's z-coordinate (pxl)
	 */
	public void setPosition(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * 
	 * Changes the object's position by the given amount
	 *
	 * @param hspeed How much the object is moved horizontally
	 * @param vspeed How much the object is move vertically
	 * @param zspeed How much the object is move z-axiswise
	 */
	public void addPosition(double hspeed, double vspeed, double zspeed)
	{
		this.x += hspeed;
		this.y += vspeed;
		this.z += zspeed;
	}
	
	
	// OTHER METHODS	---------------------------------------------------
	
	// Restores the angle to between 0 and 360
	private void checkAngle()
	{
		this.xangle = HelpMath.checkDirection(this.xangle);
		this.yangle = HelpMath.checkDirection(this.yangle);
		this.zangle = HelpMath.checkDirection(this.zangle);
	}
	
	/**
	 * 
	 * Scales the object with the given factors. The scaling stacks with previous 
	 * scaling and is not necessarily dependent on the original size of the sprite
	 *
	 * @param xscale How much the object is scaled horizontally
	 * @param yscale How much the object is scaled vertically
	 * @param zscale How much the object is scaled z-axiswise
	 */
	public void scale(double xscale, double yscale, double zscale)
	{
		this.xscale *= xscale;
		this.yscale *= yscale;
		this.zscale *= zscale;
	}
	
	//public abstract boolean pointCollides(int x, int y, int z);
	/*
	{
		// Negates the transformation
		Point negatedPoint = negateTransformations(x, y);
		
		if (negatedPoint.x < getX())
			return false;
		else if (negatedPoint.x > getX() + getSprite().getWidth())
			return false;
		else if (negatedPoint.y < getY())
			return false;
		else if (negatedPoint.y > getY() + getSprite().getHeight())
			return false;
		else
			return true;
	}
	*/
	
	//public abstract boolean objectCollides(DrawnObject3D d);
	/*
	{
		// Negates the transformations for both objects
		Point negatedPosOther =
				negateTransformations((int) s.getX(), (int) s.getY());
		Point negatedPosThis =
				s.negateTransformations((int) getX(), (int) getY());
		
		int widthThis = getSprite().getWidth();
		int widthOther = s.getSprite().getWidth();
		int heightThis = getSprite().getHeight();
		int heightOther = s.getSprite().getHeight();
		
		if (negatedPosOther.x + widthOther < negatedPosThis.x)
			return false;
		else if (negatedPosOther.x > negatedPosThis.x + widthThis)
			return false;
		else if (negatedPosOther.y + heightOther < negatedPosThis.y)
			return false;
		else if (negatedPosOther.y > negatedPosThis.y + heightThis)
			return false;
		else
			return true;
	}
	*/
	
	// Transforms the point so that the collision can be checked without
	// transformations
	/**
	 * 
	 * Transforms the point so that the collision can be checked without
	// transformations. Only works with 2d transfrmations
	 * (xscale, yscale, zangle, originx, originy) and 2d points.
	 *
	 * @param x The x-coordinate of the point to be negated
	 * @param y The y-coordinate of the point to be negated
	 * @return The point where all of the object's transformations are negated
	 */
	public Point negateTransformations2D(int x, int y)
	{
		double tempx = x;
		double tempy = y;
		
		// Rotation
		int prevDir = HelpMath.pointDirection((int) getX(), (int) getY(), x, y);
		int newDir = prevDir - (int) getZAngle();
		int dist = HelpMath.pointDistance((int) getX(), (int) getY(), x, y);
		tempx = getX() + HelpMath.lendirX(dist, newDir);
		tempy = getY() + HelpMath.lendirY(dist, newDir);
		// Scaling
		double xdist = tempx - getX();
		double ydist = tempy - getY();
		double newxdist = xdist*(1/getXscale());
		double newydist = ydist*(1/getYscale());
		tempx -= xdist - newxdist;
		tempy -= ydist - newydist;
		// Origin translate
		tempx += getOriginX();
		tempy += getOriginY();
		
		return new Point((int) tempx, (int) tempy);
	}
}
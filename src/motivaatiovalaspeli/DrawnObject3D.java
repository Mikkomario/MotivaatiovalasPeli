package motivaatiovalaspeli;

import java.awt.Point;

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
	
	private double xscale, yscale, imageSpeed, imageIndex, x, y, angle;
	private boolean visible, alive;
	private String name;
	
	// TODO: Add a (static) databank for all the sprites
	
	
	// CONSTRUCTOR	-------------------------------------------------------
	
	/**
	 * 
	 * Creates a new spriteobject with the given information. Animation and 
	 * visibility are set 
	 * on at default. Scaling and image angle won't be affected.
	 *
	 * @param x The new x-coordinate of the object (Game world Pxl)
	 * @param y The new y-coordinate of the object (Game world Pxl)
	 * @param sprite The Sprite with which the object will be drawn
	 * @param name The name of the object, should be informative
	 */
	public DrawnObject3D(int x, int y, String name)
	{
		// Initializes the attributes
		this.x = x;
		this.y = y;
		this.name = name;
		
		this.xscale = 1;
		this.yscale = 1;
		this.imageSpeed = 0.1;
		this.imageIndex = 0;
		this.visible = true;
		this.alive = true;
		this.angle = 0;
	}
	
	
	// ABSTRACT METHODS	---------------------------------------------------
	
	
	public abstract void drawSelf3D(MotivaatiovalasPeli applet);
	
	
	// IMPLEMENTED METHODS	-----------------------------------------------

	@Override
	public void drawSelf(MotivaatiovalasPeli applet)
	{
		// TODO: Change this to 3D
		
		/*
		// First handles the animation
		animate();
		
		applet.pushMatrix();
		
		// Translates the sprite to the object's position
		applet.translate((float) getX(), (float) getY());
		// and rotates it depending on its angle
		applet.rotate((float) Math.toRadians((getAngle())));
		// and finally scales it depending on it's xscale and yscale
		applet.scale((float) getXscale(), (float) getYscale());
		
		// Finally draws the sprite
		applet.image(getSprite().getSubImage(getImageIndex()),
				-getSprite().getOriginX(), -getSprite().getOriginY());
		
		// Loads the previous transformation
		applet.popMatrix();
		*/
		
	}

	@Override
	public boolean isVisible()
	{
		return this.visible;
	}


	@Override
	public boolean wontBeDrawn()
	{
		return !this.alive;
	}


	@Override
	public boolean endDrawing()
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
	
	@Override
	public String toString()
	{
		return getName();
	}
	
	
	// GETTERS & SETTERS	-----------------------------------------------

	
	/**
	 * @return The rotation of the object in degrees [0, 360[
	 */
	public double getAngle()
	{
		return this.angle;
	}
	
	/**
	 * 
	 * Changes how much the object is rotated before drawing
	 *
	 * @param angle The angle of the drawn sprite in degrees [0, 360[
	 */
	public void setAngle(double angle)
	{
		this.angle = angle;
		checkAngle();
	}
	
	/**
	 * 
	 * Increases the object's angle by the given amount
	 *
	 * @param rotation How much the angle is increased (degrees)
	 */
	public void addAngle(double rotation)
	{
		this.angle += rotation;
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
	 * 
	 * Changes how much the sprite is scaled horizontally and vertically
	 * (from the original angle)
	 *
	 * @param xscale The new horizontal scale of the sprite (default at 1)
	 * @param yscale The new vertical scale of the sprite (default at 1)
	 */
	public void setScale(double xscale, double yscale)
	{
		this.xscale = xscale;
		this.yscale = yscale;
	}
	
	/**
	 * @return How fast the frames in the animation change (animframe / frame)
	 */
	public double getImageSpeed()
	{
		return this.imageSpeed;
	}
	
	/**
	 * Changes how fast the frames in the animation change
	 * 
	 * @param imageSpeed The new animation speed (animframes / frame)
	 */
	public void setImageSpeed(double imageSpeed)
	{
		this.imageSpeed = imageSpeed;
	}
	
	/**
	 * @return Which subimage from the animation is currently drawn [0, numberOfSubimages[
	 */
	public int getImageIndex()
	{
		return (int) this.imageIndex;
	}
	
	/**
	 * Changes which subimage from the animation is currently drawn
	 * 
	 * @param imageIndex The index of the subimage drawn [0, numberOfSubimages[
	 */
	public void setImageIndex(int imageIndex)
	{
		this.imageIndex = imageIndex;
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
	 * 
	 * Changes the object's position in the game world
	 *
	 * @param x The new position's x-coordinate (pxl)
	 * @param y The new position's y-coordinate (pxl)
	 */
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * Changes the object's position by the given amount
	 *
	 * @param hspeed How much the object is moved horizontally
	 * @param vspeed How much the object is move vertically
	 */
	public void addPosition(double hspeed, double vspeed)
	{
		this.x += hspeed;
		this.y += vspeed;
	}
	
	/**
	 * @return The name of the object
	 */
	public String getName()
	{
		return this.name;
	}
	
	
	// OTHER METHODS	---------------------------------------------------
	
	// Restores the angle to between 0 and 360
	private void checkAngle()
	{
		this.angle = Math.abs(this.angle % 360);
		
		if (this.angle < 0)
			this.angle += 360;
	}
	
	/**
	 * 
	 * Scales the object with the given factors. The scaling stacks with previous 
	 * scaling and is not necessarily dependent on the original size of the sprite
	 *
	 * @param xscale How much the image is scaled horizontally
	 * @param yscale How much the image is scaled vertically
	 */
	public void scale(double xscale, double yscale)
	{
		this.xscale *= xscale;
		this.yscale *= yscale;
	}
	
	/**
	 * 
	 * This method checks whether a certain point would collide with the 
	 * object
	 *
	 * @param x The ingame x-coordinate of the point
	 * @param y The ingame y-coordinate of the point
	 * @return Is the point above the object's sprite
	 */
	public abstract boolean pointCollides(int x, int y);
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
	
	/**
	 * 
	 * Checks wheter this spriteobject collides with another spriteobject. 
	 * This method is quite heavy so it's not adviced to be used too often.
	 *
	 * @param s The spriteobject that might be colliding with this object
	 * @return Are the objects overlapping each other
	 */
	public abstract boolean objectCollides(DrawnObject3D s);
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
	/* TODO: Change to 3D
	private Point negateTransformations(int x, int y)
	{
		double tempx = x;
		double tempy = y;
		
		// Rotation
		int prevDir = HelpMath.pointDirection((int) getX(), (int) getY(), x, y);
		int newDir = prevDir - (int) getAngle();
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
		tempx += getSprite().getOriginX();
		tempy += getSprite().getOriginY();
		
		return new Point((int) tempx, (int) tempy);
	}
	*/
	
	// TODO: Change pointCollision (to 2 methods) add object collision
	
	/*
	// Handles the change of the image index
	private void animate()
	{
		this.imageIndex += getImageSpeed();
		this.imageIndex = this.imageIndex % getSprite().getImageNumber();
		
		if (this.imageIndex < 0)
			this.imageIndex += getSprite().getImageNumber();
	}
	*/
}
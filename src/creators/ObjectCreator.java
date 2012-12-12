package creators;

import handleds.Actor;
import handlers.DrawableHandler;

import java.util.Random;

import scrolling.Scroller;


/**
 * Objectcreator creates objects after a certain amount of time has passed. These
 * objects are then added to a drawer and a scroller.
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public abstract class ObjectCreator implements Actor
{
	// ATTRIBUTES	----------------------------------------------------
	
	private static Random rand = new Random();
	
	private int distance, minDistance, maxDistance, width, height, creationZ, maxZ;
	private boolean active, alive;
	
	private DrawableHandler objectDrawer;
	private Scroller objectScroller;
	
	
	// CONSTRUCTOR	----------------------------------------------------
	
	/**
	 * Creates a new object creator with the given information. The creator starts 
	 * creating objects right away.
	 *
	 * @param minDistance How long is the smallest distance between the created objects possible (steps)
	 * @param maxDistance How ling is the longest distance between the created objects possible (steps)
	 * @param width To how wide an area are the rocks positioned (Pxl)
	 * @param height To how high an area are the rocks positioned (Pxl)
	 * @param z What z-coordinate will the new rocks have.
	 * @param maxZ How far can the rocks be scrolled before they disappear
	 * @param objectDrawer The drawablehandler who takes care of draing the rocks
	 * @param objectScroller The scrolled in charge of moving / scrolling the created rocks
	 */
	public ObjectCreator(int minDistance, int maxDistance, int width, int height, 
			int z, int maxZ, DrawableHandler objectDrawer, Scroller objectScroller)
	{
		// Initializes attributes
		this.minDistance = minDistance;
		this.maxDistance = maxDistance;
		this.width = width;
		this.height = height;
		this.creationZ = z;
		this.objectDrawer = objectDrawer;
		this.maxZ = maxZ;
		this.objectScroller = objectScroller;
		
		this.active = true;
		this.alive = true;
		this.distance = this.minDistance + rand.nextInt(this.maxDistance + 1 - this.minDistance);
	}
	
	
	// ABSTRACT METHODS	--------------------------------------------------
	
	/**
	 * 
	 * Creates a new object and adds it to the drawables and scrollables
	 *
	 * @param fieldWidth How wide is the field where the objects are supposed to 
	 * be positioned (Pxl).
	 * @param fieldHeight How long is the field where the objects are supposed to 
	 * be positioned (Pxl).
	 * @param creationZ What z-value will the object's have
	 * @param maxZ How close the objects will come before they are notified
	 */
	public abstract void createObject(int fieldWidth, int fieldHeight, 
			int creationZ, int maxZ);
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public boolean isActive()
	{
		return this.active;
	}

	@Override
	public boolean isDead()
	{
		return !this.alive;
	}

	@Override
	public void act()
	{	
		// Checks if a new objects should be created
		this.distance -= getScroller().getSpeed();
		
		if (this.distance <= 0)
		{
			// Creates the object(s)
			this.distance = getRandomDistance(this.minDistance, this.maxDistance);
			createObject(this.width, this.height, this.creationZ, this.maxZ);
		}
	}

	@Override
	public boolean kill()
	{
		this.alive = false;
		return true;
	}

	@Override
	public boolean inActivate()
	{
		this.active = false;
		return true;
	}

	@Override
	public boolean activate()
	{
		this.active = true;
		return true;
	}
	
	
	// GETTERS & SETTERS	-----------------------------------------------
	
	/**
	 * @return The DrawableHandler that draws the objects
	 */
	public DrawableHandler getDrawableHandler()
	{
		return this.objectDrawer;
	}
	
	/**
	 * @return The Scroller that scrolls the objects
	 */
	public Scroller getScroller()
	{
		return this.objectScroller;
	}
	
	/**
	 * 
	 * Changes how long it takes until the next creation
	 *
	 * @param delay How many steps until the next creation
	 */
	protected void setDelay(int delay)
	{
		this.distance = delay;
	}
	
	
	// OTHER METHODS	--------------------------------------------------
	
	/**
	 * 
	 * Returns a random delay between the two values
	 *
	 * @param minDistance The smallest possible distance
	 * @param maxDistance The largest possible distance
	 * @return Delay between the previous two
	 */
	protected static int getRandomDistance(int minDistance, int maxDistance)
	{
		if (maxDistance + 1 - minDistance > 0)
			return minDistance + rand.nextInt(maxDistance + 1 - minDistance);
		else
			return 0;
	}
	
	/**
	 * @return How much must at least be scrolled before a new object is created
	 */
	protected int getMinDistance()
	{
		return this.minDistance;
	}
}

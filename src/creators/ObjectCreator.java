package creators;

import handleds.Actor;
import handlers.DrawableHandler;

import java.util.Random;

import scrolling.Scroller;


/**
 * TODO Put here a description of what this class does.
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public abstract class ObjectCreator implements Actor
{
	// ATTRIBUTES	----------------------------------------------------
	
	private static Random rand = new Random();
	
	private int delay, minDelay, maxDelay, width, height, creationZ, maxZ;
	private boolean active, alive;
	
	private DrawableHandler objectDrawer;
	private Scroller objectScroller;
	
	
	// CONSTRUCTOR	----------------------------------------------------
	
	/**
	 * Creates a new object creator with the given information. The creator starts 
	 * creating objects right away.
	 *
	 * @param minDelay How long is the smallest delay possible (steps)
	 * @param maxDelay How ling is the longest delay possible (steps)
	 * @param width To how wide an area are the rocks positioned (Pxl)
	 * @param height To how high an area are the rocks positioned (Pxl)
	 * @param z What z-coordinate will the new rocks have.
	 * @param maxZ How far can the rocks be scrolled before they disappear
	 * @param objectDrawer The drawablehandler who takes care of draing the rocks
	 * @param objectScroller The scrolled in charge of moving / scrolling the created rocks
	 */
	public ObjectCreator(int minDelay, int maxDelay, int width, int height, 
			int z, int maxZ, DrawableHandler objectDrawer, Scroller objectScroller)
	{
		// Initializes attributes
		this.minDelay = minDelay;
		this.maxDelay = maxDelay;
		this.width = width;
		this.height = height;
		this.creationZ = z;
		this.objectDrawer = objectDrawer;
		this.maxZ = maxZ;
		this.objectScroller = objectScroller;
		
		this.active = true;
		this.alive = true;
		this.delay = this.minDelay + rand.nextInt(this.maxDelay + 1 - this.minDelay);
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
		// TODO: Change to something else than time (distance?) as progressing 
		// slowly now causes more rocks to be created
		
		// Checks if a new wave of objects should be created
		this.delay--;
		
		if (this.delay <= 0)
		{
			// Creates the object(s)
			this.delay = this.minDelay + rand.nextInt(this.maxDelay + 1 - this.minDelay);
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
}

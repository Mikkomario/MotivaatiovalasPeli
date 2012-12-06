package motivaatiovalaspeli;

import java.util.ArrayList;

/**
 * This class draws all the rocks under its care and also checks collisions 
 * between them and other objects. Only rocks can be drawn with the rockhandler.
 *
 * @author Gandalf.
 *         Created 6.12.2012.
 */
public class RockHandler extends DrawableHandler implements Actor
{
	// ATTRIBUTES	------------------------------------------------------
	
	private ArrayList<CollisionListener> collisionlisteners;
	
	
	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * Creates a new Rockhandler that is still empty. Rockhandlers won't be 
	 * destroyed unless endDrawing method is called. Collisionlisteners must 
	 * be added manually later.
	 */
	public RockHandler()
	{
		super((Drawable) null, false);
		
		this.collisionlisteners = new ArrayList<CollisionListener>();
	}
	
	
	// IMPLEMENTED METHODS	-----------------------------------------------
	
	@Override
	public void addDrawable(Drawable d)
	{
		// RockHandler only handles rocks
		if (d instanceof Rock)
			super.addDrawable(d);
	}
	
	@Override
	public boolean isActive()
	{
		// Handler is active if there is both rocks and listeners and if the 
		// rocks are drawn
		return (getDrawableNumber() != 0 && !this.collisionlisteners.isEmpty()
				&& isVisible());
	}


	@Override
	public boolean isDead()
	{
		// Handler is dead if the rocks wont be drawn anymore
		return wontBeDrawn();
	}


	@Override
	public void act()
	{
		// Checks collision for all of the rocks and informs collisionListeners
		// if needed
		for (int c = 0; c < this.collisionlisteners.size(); c++)
		{
			CollisionListener listener = this.collisionlisteners.get(c);
			
			// Checks all rocks
			for (int r = 0; r < getDrawableNumber(); r++)
			{
				Rock rock = getRock(r);
				
				// Checks collision
				if (rock.pointCollides(listener.getCollisionX(), 
						listener.getCollisionY(), listener.getCollisionZ()))
				{
					// Informs the listener
					listener.onCollision(rock);
					// Only one collision is needed for each step (saves processing time)
					break;
				}
			}
		}
	}


	@Override
	public boolean kill()
	{
		// Killing the handler is same as ending all drawing
		return endDrawing();
	}


	@Override
	public boolean inActivate()
	{
		// Inactivation is the same as setting all invisible (doesn't check collision 
		// if everything is invisible)
		return setInvisible();
	}


	@Override
	public boolean activate()
	{
		// Activating is same as setting all visible again
		return setVisible();
	}
	
	
	// OTHER METHODS	---------------------------------------------------

	private Rock getRock(int index)
	{
		Drawable maybeRock = getDrawable(index);
		
		if (maybeRock instanceof Rock)
			return (Rock) maybeRock;
		else
			return null;
	}
	
	/**
	 * 
	 * Adds a new collisionlistener to listen collisions with handled rocks
	 *
	 * @param c The collisionlistener added
	 */
	public void addCollisionListener(CollisionListener c)
	{
		if (!this.collisionlisteners.contains(c))
			this.collisionlisteners.add(c);
	}
	
	/**
	 * 
	 * Removes a collisionlistener from listening to collisions with the handled 
	 * rocks
	 *
	 * @param c The collisionlistener to be removed
	 */
	public void removeCollisionListener(CollisionListener c)
	{
		if (this.collisionlisteners.contains(c))
			this.collisionlisteners.remove(c);
	}



}

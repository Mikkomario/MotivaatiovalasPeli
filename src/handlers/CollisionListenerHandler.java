package handlers;

import java.util.ArrayList;

import handleds.Actor;
import handleds.Colliding;
import handleds.Handled;
import listeners.CollisionListener;

/**
 * Keeps track of collisionlisteners and informs if any collisions happen
 *
 * @author Gandalf.
 *         Created 12.12.2012.
 */
public class CollisionListenerHandler extends LogicalHandler implements Actor
{
	// ATTRIBUTES	------------------------------------------------------
	
	private ArrayList<Colliding> collidings;
	
	
	// COSTRUCTOR	------------------------------------------------------

	/**
	 * Creates a new CollisionListenerhandler. Listeners must be added manually later
	 *
	 * @param autodeath Will the handler die when it runs out of listeners?
	 */
	public CollisionListenerHandler(boolean autodeath)
	{
		super(autodeath);
		
		// Initializes attributes
		this.collidings = new ArrayList<Colliding>();
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------
	
	@Override
	public void act()
	{
		// Checks collision for all of the colliding objects and informs
		// collisionListeners if needed
		for (int l = 0; l < getHandledNumber(); l++)
		{
			CollisionListener listener = getListener(l);
			
			// Checks all Colliding objects
			for (int c = 0; c < this.collidings.size(); c++)
			{
				Colliding colliding = this.collidings.get(c);
				
				// Checks collision
				if (colliding.pointCollides(listener.getCollisionX(), 
						listener.getCollisionY(), listener.getCollisionZ()))
				{
					// Informs the listener
					listener.onCollision(colliding);
					// Only one collision is needed for each step (saves processing time)
					break;
				}
			}
		}
	}
	
	@Override
	protected void addHandled(Handled h)
	{
		// Only handles listeners
		if (h instanceof CollisionListener)
			super.addHandled(h);
	}
	
	
	// OTHER METHODS	---------------------------------------------------
	
	// Casts a handled to listener
	private CollisionListener getListener(int index)
	{
		Handled maybeListener = getHandled(index);
		
		if (maybeListener instanceof CollisionListener)
			return (CollisionListener) maybeListener;
		else
			return null;
	}
	
	/**
	 * 
	 * Adds a new listener to the informed listeners
	 *
	 * @param c The collisionListener added
	 */
	public void addCollisionListener(CollisionListener c)
	{
		super.addHandled(c);
	}
	
	/**
	 * 
	 * Adds a new colliding object to the ones that are informed to the listeners
	 *
	 * @param c Colliding object added
	 */
	public void addCollidingObject(Colliding c)
	{
		if (c != null && !this.collidings.contains(c))
			this.collidings.add(c);
	}
}

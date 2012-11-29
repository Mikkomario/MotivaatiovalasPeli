package motivaatiovalaspeli;

import java.util.ArrayList;

// TODO: Test this class

/**
 * The object from this class will control multiple actors, calling their 
 * act-methods and removing them when necessary
 *
 * @author Gandalf.
 *         Created 27.11.2012.
 */
public class ActorHandler implements Actor
{
	// ATTRIBUTES	------------------------------------------------------
	
	private ArrayList<Actor> actors;
	
	
	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * This is the recommended version of the constructor.<br>
	 *This method creates a new ActionHandler that will be able to take care of 
	 *new actors. An initialized ActorHandler should contain at least one actor 
	 *before its isDead method is called. Other actors can be added later
	 *
	 *@param initialActors The Actors added to the handler
	 */
	@SuppressWarnings("unchecked")
	public ActorHandler(ArrayList<Actor> initialActors)
	{
		// Initializes the attributes
		if (initialActors != null)
			this.actors = (ArrayList<Actor>) initialActors.clone();
		
		// Checks the parameeter(s)
		if (this.actors == null)
			this.actors = new ArrayList<Actor>();
	}
	
	/**
	 * This is an alternate version of the constructor.<br>
	 *This method creates a new ActionHandler that will be able to take care of 
	 *new actors. An initialized ActorHandler should contain at least one actor 
	 *before its isDead method is called. Other actors can be added later
	 *
	 *@param initialActors The Actors added to the handler
	 */
	public ActorHandler(Actor[] initialActors)
	{
		this.actors = new ArrayList<Actor>();
		
		// Initializes the attributes
		if (initialActors != null)
		{
			for (int i = 0; i < initialActors.length; i++)
			{
				this.actors.add(initialActors[i]);
			}
		}
	}
	
	/**
	 * 
	 * This is an alternate way of creating a new ActorHandler. The handler stops 
	 * functioning when no alive actors exist under its command.
	 *
	 * @param initialActor An actor the handler commands
	 */
	public ActorHandler(Actor initialActor)
	{
		this.actors = new ArrayList<Actor>();
		addActor(initialActor);
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public boolean isActive()
	{
		// Returns false only if all the actors are inactive
		for (int i = 0; i < this.actors.size(); i++)
		{
			if (this.actors.get(i).isActive())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean isDead()
	{
		// Removes the unnecessary dead actors and returns if alive actors still
		// exist
		removeDeadActors();
		return this.actors.isEmpty();
	}

	@Override
	public void act()
	{
		//System.out.println(this.actors.size());
		// This calls for all active actor's act method
		for (int i = 0; i < this.actors.size(); i++)
		{
			//System.out.println("Tries to act");
			if (this.actors.get(i).isActive())
			{
				//System.out.println("Acts");
				this.actors.get(i).act();
			}
		}
	}
	
	@Override
	public boolean kill()
	{
		// tries to kill all the actors, returns false if all the actors
		// could not be inactivated
		boolean returnValue = true;
		
		for (int i = 0; i < this.actors.size(); i++)
		{
			if (!this.actors.get(i).kill())
				returnValue = false;
		}
		
		// Also erases the memory if all the actors were killed
		if (returnValue)
			this.actors.clear();
		
		return returnValue;
	}

	@Override
	public boolean inActivate()
	{
		// tries to inactivate all the actors, returns false if all the actors
		// could not be inactivated
		boolean returnValue = true;
		
		for (int i = 0; i < this.actors.size(); i++)
		{
			if (!this.actors.get(i).inActivate())
				returnValue = false;
		}
		
		return returnValue;
	}

	@Override
	public boolean activate()
	{
		// tries to activate all the actors, returns false if all the actors
		// could not be activated
		boolean returnValue = true;
		
		for (int i = 0; i < this.actors.size(); i++)
		{
			if (!this.actors.get(i).activate())
				returnValue = false;
		}
		
		return returnValue;
	}
	
	
	// OTHER METHODS	---------------------------------------------------
	
	// Removes all the dead actors from the list of actors to save processing
	// time
	private void removeDeadActors()
	{
		for (int i = 0; i < this.actors.size(); i++)
		{	
			if (this.actors.get(i).isDead())
				this.actors.remove(i);
		}
	}
	
	/**
	 *Adds the given actor to the handled actors
	 *
	 * @param a The actor to be added
	 */
	public void addActor(Actor a)
	{
		if (a != null && !this.actors.contains(a))
			this.actors.add(a);
	}
}

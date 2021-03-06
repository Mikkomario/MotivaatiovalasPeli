package handlers;

import handleds.Handled;

import java.util.ArrayList;

/**
 * Handlers specialise in handling certain types of objects. Each handler can 
 * inform its subobjects and can be handled itself.
 *
 * @author Gandalf.
 *         Created 8.12.2012.
 */
public abstract class Handler implements Handled
{
	// ATTRIBUTES	-----------------------------------------------------
	
	private ArrayList<Handled> handleds;
	private boolean autodeath;
	private boolean killed;
	
	
	// CONSTRUCTOR	-----------------------------------------------------
	
	/**
	 * Creates a new handler that is still empty. Handled objects must be added 
	 * manually later. If autodeath is set on, the handled will be destroyed as 
	 * soon as it becomes empty
	 *
	 * @param autodeath Will the handler die automatically when it becomes empty
	 */
	public Handler(boolean autodeath)
	{
		// Initializes attributes
		this.autodeath = autodeath;
		this.killed = false;
		this.handleds = new ArrayList<Handled>();
	}
	
	
	// IMPLEMENTED METHODS	-----------------------------------------------

	@Override
	public boolean isDead()
	{
		// Also checks if some of the handleds should be removed
		removeDeadHandleds();
		
		// The handler is dead if it was killed or if autodeath is on and it's empty
		return this.killed || (this.autodeath && this.handleds.isEmpty());
	}

	@Override
	public boolean kill()
	{
		// Tries to permanently inactivate all subhandleds. If all were successfully
		// killed, this handldler is also killed in the process
		boolean returnValue = true;
		
		for (int i = 0; i < this.handleds.size(); i++)
		{
			if (!this.handleds.get(i).kill())
				returnValue = false;
		}
		
		// Also erases the memory if all the actors were killed
		if (returnValue)
		{
			this.handleds.clear();
			this.killed = true;
		}
		
		return returnValue;
	}
	
	
	// OTHER METHODS	---------------------------------------------------
	
	/**
	 * Adds a new object to the handled objects
	 *
	 * @param h The object to be handled
	 */
	protected void addHandled(Handled h)
	{
		if (h != null && !this.handleds.contains(h))
			this.handleds.add(h);
	}
	
	private void removeDeadHandleds()
	{
		// Removes all the dead handleds from the list to save processing time
		for (int i = 0; i < this.handleds.size(); i++)
		{	
			if (this.handleds.get(i).isDead())
				this.handleds.remove(i);
		}
	}
	
	/**
	 * @return How many objects is the handler currently taking care of
	 */
	protected int getHandledNumber()
	{
		return this.handleds.size();
	}
	
	/**
	 * Returns a single handled from the list of handled objects
	 * 
	 * @param index The index of the handled object
	 * @return The object or null if no such index exists
	 */
	protected Handled getHandled(int index)
	{
		if (index >= 0 && index < getHandledNumber())
			return this.handleds.get(index);
		else
			return null;
	}

}

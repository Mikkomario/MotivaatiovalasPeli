package handlers;

import handleds.Handled;
import handleds.LogicalHandled;

/**
 * LogicalHandlers specialize in logicalhandleds instead of just any handlers. 
 * This class provides some methods necessary for all subclasses and can be used as
 * a logical handled in other handlers.
 *
 * @author Gandalf.
 *         Created 8.12.2012.
 */
public abstract class LogicalHandler extends Handler implements LogicalHandled
{
	// CONSTRUCTOR	-------------------------------------------------------

	/**
	 * Creates a new logicalhandler. Handled objects must be added manually later
	 *
	 * @param autodeath Will the handler die if it runs out of living handleds
	 */
	public LogicalHandler(boolean autodeath)
	{
		super(autodeath);
	}
	
	
	// IMPLEMENTED METHODS	-----------------------------------------------

	@Override
	public boolean isActive()
	{
		// Returns false only if all the handleds are inactive
		for (int i = 0; i < getHandledNumber(); i++)
		{
			if (getLogicalHandled(i).isActive())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean activate()
	{
		// tries to activate all the handled objects, returns false if all the
		// objects could not be activated
		boolean returnValue = true;
		
		for (int i = 0; i < getHandledNumber(); i++)
		{
			if (!getLogicalHandled(i).activate())
				returnValue = false;
		}
		
		return returnValue;
	}

	@Override
	public boolean inActivate()
	{
		// tries to inactivate all the handled objects, returns false if all the objects
		// could not be inactivated
		boolean returnValue = true;
		
		for (int i = 0; i < getHandledNumber(); i++)
		{
			if (!getLogicalHandled(i).inActivate())
				returnValue = false;
		}
		
		return returnValue;
	}
	
	@Override
	protected void addHandled(Handled h)
	{
		// Only logicalhandleds can be added
		if (h instanceof LogicalHandled)
			super.addHandled(h);
	}
	
	// OTHER METHODS	--------------------------------------------------

	// Casts the handled object to an logical object (or null)
	private LogicalHandled getLogicalHandled(int index)
	{
		Handled maybelogical = getHandled(index);
		
		if (maybelogical instanceof LogicalHandled)
			return (LogicalHandled) maybelogical;
		else
			return null;
	}
}

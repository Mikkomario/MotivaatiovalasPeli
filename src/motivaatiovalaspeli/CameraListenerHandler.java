package motivaatiovalaspeli;

import java.util.ArrayList;

/**
 * This class informs all its sublisteners about changes in camera's position
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public class CameraListenerHandler implements CameraListener
{
	// ATTRIBUTES	------------------------------------------------------
	
	private ArrayList<CameraListener> listeners;
	private boolean autodeath, killed;
	
	
	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * Creates a new listenerhandler that will inform all sublisteners about
	 * camera's changes. Listeners must be added manually later.
	 * 
	 * @param autodeath Will the handler stop functioning when it runs out of 
	 * handled listeners
	 */
	public CameraListenerHandler(boolean autodeath)
	{
		// Initializes attributes
		this.listeners = new ArrayList<CameraListener>();
		this.autodeath = autodeath;
		this.killed = false;
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public void informCameraPosition(int posx, int posy, int posz)
	{
		// Informs all sublisteners about the change
		for (int i = 0; i < this.listeners.size(); i++)
		{
			this.listeners.get(i).informCameraPosition(posx, posy, posz);
		}	
	}

	@Override
	public boolean isActive()
	{
		// Returns whether all of the sublisteners are active
		for (int i = 0; i < this.listeners.size(); i++)
		{
			if (this.listeners.get(i).isActive())
				return true;
		}
		return false;
	}


	@Override
	public boolean isDead()
	{
		removeDeadListeners();
		
		if (this.killed)
			return true;
		// If autodeath is on, returns true if all sublisteners are dead
		return this.autodeath && this.listeners.isEmpty();
	}

	// TODO: Add an abstract superclass to handle autodeath and some methods like this
	// Currently not DRY
	@Override
	public boolean kill()
	{
		// tries to set all the listeners permanently inactive, returns false
		// if all the listeners couldn't be made inactive
		boolean returnValue = true;
		
		for (int i = 0; i < this.listeners.size(); i++)
		{
			if (!this.listeners.get(i).kill())
				returnValue = false;
		}
		
		// Erases the memory and kills the handler if all the drawables were ended
		if (returnValue)
		{
			this.listeners.clear();
			this.killed = true;
		}
		
		return returnValue;
	}


	@Override
	public boolean inActivate()
	{
		// tries to inactivate all the listeners, returns false if all the listeners
		// could not be inactivated
		boolean returnValue = true;
		
		for (int i = 0; i < this.listeners.size(); i++)
		{
			if (!this.listeners.get(i).inActivate())
				returnValue = false;
		}
		
		return returnValue;
	}


	@Override
	public boolean activate()
	{
		// tries to activate all the listeners, returns false if all the listeners
		// could not be activated
		boolean returnValue = true;
		
		for (int i = 0; i < this.listeners.size(); i++)
		{
			if (!this.listeners.get(i).activate())
				returnValue = false;
		}
		
		return returnValue;
	}
	
	
	// OTHER METHODS	---------------------------------------------------
	
	// Removes all the dead actors from the list of actors to save processing
	// time
	private void removeDeadListeners()
	{
		for (int i = 0; i < this.listeners.size(); i++)
		{	
			if (this.listeners.get(i).isDead())
				this.listeners.remove(i);
		}
	}
	
	/**
	 * Adds a new cameralistener to the informed cameralisteners
	 * @param c The listener to be addded
	 */
	public void addListener(CameraListener c)
	{
		this.listeners.add(c);
	}
}

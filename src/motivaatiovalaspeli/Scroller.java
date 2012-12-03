package motivaatiovalaspeli;

import java.util.ArrayList;

/**
 * This class scrolls all the scrollable objects at each step
 *
 * @author Gandalf.
 *         Created 3.12.2012.
 */
public class Scroller implements Actor
{
	// ATTRIBUTES	------------------------------------------------------
	
	private ArrayList<Scrollable> scrollables;
	
	private double scrollspeed;
	private boolean active, alive;
	
	
	// CONSTRUCTOR	-----------------------------------------------------
	
	/**
	 * 
	 * Creates a new scroller that scrolls the objects with the given speed.
	 * Scrollable objects are added later using different methods.
	 *
	 * @param scrollspeed The speed with which the objects are moved
	 */
	public Scroller(double scrollspeed)
	{
		// Initializes attributes
		this.scrollspeed = scrollspeed;
		
		this.scrollables = new ArrayList<Scrollable>();
		this.active = true;
		this.alive = true;
	}
	
	
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
		// Does the same thing for each scrollable
		for (int i = 0; i < this.scrollables.size(); i++)
		{
			Scrollable s = this.scrollables.get(i);
			
			// Scrolls the object forward
			s.setZ(s.getZ() + getSpeed());
			
			// Checks if it has gone too far
			if (s.getZ() > s.getMaxZ())
			{
				// If the object wants to be scrolled back
				if (s.isReturned())
					s.setZ(s.getZ() - (s.getMaxZ() - s.getMinZ()));
				
				// Informs the object
				s.onOutOfRange();
			}
			if (s.getZ() < s.getMinZ())
			{
				// If the object wants to be scrolled back
				if (s.isReturned())
					s.setZ(s.getZ() + (s.getMaxZ() - s.getMinZ()));
				
				// Informs the object
				s.onOutOfRange();
			}
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

	
	// GETTERS & SETTERS	---------------------------------------------
	
	/**
	 * @return The speed with which the objects are scrolled on the z-axis
	 */
	public double getSpeed()
	{
		return this.scrollspeed;
	}
	
	/**
	 * Changes the speed at which the objects are moved on the z-axis
	 * @param speed Objects' movement speed
	 */
	public void setSpeed(double speed)
	{
		this.scrollspeed = speed;
	}
	
	
	// OTHER METHODS	--------------------------------------------------
	
	/**
	 * Adds a new scrollable to the list of moved scrollables
	 * 
	 * @param s The scrollable to be added
	 */
	public void addScrollable(Scrollable s)
	{
		if (!this.scrollables.contains(s))
			this.scrollables.add(s);
	}
	
	/**
	 * Removes the scrollable from the list of moved scrollables
	 *
	 * @param s The scrollable to be removed
	 */
	public void removeScrollable(Scrollable s)
	{
		if (this.scrollables.contains(s))
			this.scrollables.remove(s);
	}
}

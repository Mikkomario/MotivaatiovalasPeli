package handlers;

import handleds.Drawable;
import handleds.Handled;
import listeners.CameraListener;
import model.Seagrass;

/**
 * Handles the drawing and cameralistening for all subseagrasses
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public class SeagrassHandler extends DrawableHandler implements CameraListener
{	
	// CONSTRUCTOR	-------------------------------------------------------
	
	/**
	 * Creates a new grasshandler. Handled grasses must be created later.
	 */
	public SeagrassHandler()
	{
		super(false);
	}
	
	
	// IMPLEMENTED METHODS	-----------------------------------------------

	@Override
	public void informCameraPosition(int posx, int posy, int posz)
	{
		// Informs all the seagrasses of changes
		for (int i = 0; i < getHandledNumber(); i++)
		{
			getGrass(i).informCameraPosition(posx, posy, posz);
		}
	}
	
	@Override
	public void addDrawable(Drawable d)
	{
		// Seagrasshandler only handles grasses
		if (d instanceof Seagrass)
			super.addDrawable(d);
	}
	
	@Override
	protected void addHandled(Handled h)
	{
		// Only handles seagrass
		if (h instanceof Seagrass)
			super.addHandled(h);
	}

	@Override
	public boolean isActive()
	{
		// Returns false only if all the handleds are inactive
		for (int i = 0; i < getHandledNumber(); i++)
		{
			if (getGrass(i).isActive())
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
			if (!getGrass(i).activate())
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
			if (!getGrass(i).inActivate())
				returnValue = false;
		}
		
		return returnValue;
	}
	
	
	// OTHER METHODS	----------------------------------------------------
	
	private Seagrass getGrass(int index)
	{
		Handled maybeGrass = getHandled(index);
		if (maybeGrass instanceof Seagrass)
			return (Seagrass) maybeGrass;
		else
			return null;
	}
}

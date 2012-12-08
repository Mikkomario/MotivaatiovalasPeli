package motivaatiovalaspeli;

/**
 * Handles the drawing and cameralistening for all subseagrasses
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public class SeagrassHandler extends DrawableHandler implements CameraListener
{	
	// TODO: Finish him!
	
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
			if (getHandled(i) instanceof Seagrass)
				((Seagrass) getHandled(i)).informCameraPosition(posx, posy, posz);
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
		// TODO Auto-generated method stub.
		return false;
	}

	@Override
	public boolean inActivate()
	{
		// TODO Auto-generated method stub.
		return false;
	}

	@Override
	public boolean activate()
	{
		// TODO Auto-generated method stub.
		return false;
	}
}

package motivaatiovalaspeli;

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
		super((Drawable) null, false);
	}
	
	
	// IMPLEMENTED METHODS	-----------------------------------------------

	@Override
	public void informCameraPosition(int posx, int posy, int posz)
	{
		// Informs all the seagrasses of changes
		for (int i = 0; i < getDrawableNumber(); i++)
		{
			if (getDrawable(i) instanceof Seagrass)
				((Seagrass) getDrawable(i)).informCameraPosition(posx, posy, posz);
		}
	}
	
	@Override
	public void addDrawable(Drawable d)
	{
		// Seagrasshandler only handles grasses
		if (d instanceof Seagrass)
			super.addDrawable(d);
	}
}

package motivaatiovalaspeli;

/**
 * This class informs all its sublisteners about changes in camera's position
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public class CameraListenerHandler extends LogicalHandler implements CameraListener
{	
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
		super(autodeath);
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public void informCameraPosition(int posx, int posy, int posz)
	{
		// Informs all sublisteners about the change
		for (int i = 0; i < getHandledNumber(); i++)
		{
			getListener(i).informCameraPosition(posx, posy, posz);
		}	
	}
	
	@Override
	protected void addHandled(Handled h)
	{
		// Only handles cameralisteners
		if (h instanceof CameraListener)
			super.addHandled(h);
	}
	
	
	// OTHER METHODS	---------------------------------------------------
	
	/**
	 * Adds a new cameralistener to the informed cameralisteners
	 * @param c The listener to be addded
	 */
	public void addListener(CameraListener c)
	{
		super.addHandled(c);
	}
	
	// Casts the handled to listener
	private CameraListener getListener(int index)
	{
		Handled maybeListener = getHandled(index);
		if (maybeListener instanceof CameraListener)
			return (CameraListener) maybeListener;
		else
			return null;
	}
}
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
	
	
	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * Creates a new listenerhandler that will inform all sublisteners about
	 * camera's changes. Listeners must be added manually later.
	 */
	public CameraListenerHandler()
	{
		// Initializes attributes
		this.listeners = new ArrayList<CameraListener>();
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
		// TODO Auto-generated method stub.
		return false;
	}


	@Override
	public boolean isDead()
	{
		// TODO Auto-generated method stub.
		return false;
	}


	@Override
	public boolean kill()
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

package motivaatiovalaspeli;

import processing.core.PConstants;

/**
 * Valas is the playable character of MotivaatiovalasPeli. Valas can be controlled 
 * using the keyboard (or mouse?) and is drawn as a 3D object.
 *
 * @author Gandalf.
 *         Created 2.12.2012.
 */
public class Valas extends PhysicObject3D implements KeyListener
{
	// ATTRIBUTES	------------------------------------------------------
	
	// TODO: Add speed
	
	
	// CONSTRUCTOR	------------------------------------------------------

	/**
	 * Creates a new valas to the given coordinates with the given name
	 *
	 * @param x Object's new position's x-coordinate in game world (Pxl)
	 * @param y Object's new position's y-coordinate in game world (Pxl)
	 * @param z Object's new position's z-coordinate in game world (Pxl)
	 * @param name Object's new nickname
	 */
	public Valas(int x, int y, int z, String name)
	{
		super(x, y, z, name);
		
		setRotationFriction(1);
	}
	
	
	// IMPLEMENTED METHODS	---------------------------------------------

	@Override
	public void drawSelf3D(MotivaatiovalasPeli applet)
	{
		// TODO Add cool valas models
		// Changes origin
		//applet.translate(-16, -16, 0);
		// Changes colour
		applet.fill(255, 0, 0);
		applet.stroke(0);
		// Draws
		applet.box(32, 32, 64);
		// Resets
		applet.noFill();
		applet.noStroke();
	}

	@Override
	public boolean pointCollides(int x, int y, int z)
	{
		// TODO Think of somehting here...
		return false;
	}

	@Override
	public boolean objectCollides(DrawnObject3D d)
	{
		// TODO Ush... Someday
		return false;
	}

	@Override
	public void onKeyDown(int key, int keyCode, boolean coded)
	{	
		// TODO Add controlls
		// Case up
		if (coded && keyCode == PConstants.UP)
			setRotation(-5, getYRotation(), getZRotation());
		// Case down
		else if (coded && keyCode == PConstants.DOWN)
			setRotation(5, getYRotation(), getZRotation());
		// Case left
		else if (coded && keyCode == PConstants.LEFT)
			setRotation(getXRotation(), 5, getZRotation());
		// Case Right
		else if (coded && keyCode == PConstants.RIGHT)
			setRotation(getXRotation(), -5, getZRotation());
	}

	@Override
	public void onKeyPressed(int key, int keyCode, boolean coded)
	{
		// Does nothing yet
	}

	@Override
	public void onKeyReleased(int key, int keyCode, boolean coded)
	{
		// Does nothing yet
	}

	@Override
	public boolean listens()
	{
		// Valas only listens to keys when its active
		return isActive();
	}

	@Override
	public int getOriginX()
	{
		return -16;
	}

	@Override
	public int getOriginY()
	{
		return -16;
	}

	@Override
	public int getOriginZ()
	{
		return 0;
	}
	
	// TODO: Override act and create movement
	
	
	// OTHER METHODS	--------------------------------------------------
	
	// Returns the x and y angles to a area
	private void checkAngle(int maxAngle)
	{
		if (getXAngle() > 180 && getXAngle() < 360 - maxAngle)
			setAngle(-50, getYAngle(), getZAngle());
		
		if (getXAngle() > maxAngle && getXAngle() < 180)
			setAngle(50, getYAngle(), getZAngle());
		
		if (getYAngle() > maxAngle && getYAngle() < 180)
			setAngle(getXAngle(), 50, getZAngle());
		
		if (getYAngle() < 310 && getYAngle() > 180)
			setAngle(getXAngle(), -50, getZAngle());
	}

}

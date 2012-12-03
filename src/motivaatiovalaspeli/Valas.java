package motivaatiovalaspeli;

import processing.core.PConstants;

/**
 * Valas is the playable character of MotivaatiovalasPeli. Valas can be controlled 
 * using the keyboard (or mouse?) and is drawn as a 3D object.
 *
 * @author Gandalf.
 *         Created 2.12.2012.
 */
public class Valas extends PhysicObject3D implements KeyListener, Scrollable
{
	// ATTRIBUTES	------------------------------------------------------
	
	// TODO: Add speed / motion
	private int tillMovement, movementInterval;
	private double movementForce;
	
	
	// CONSTRUCTOR	------------------------------------------------------

	/**
	 * Creates a new valas to the given coordinates with the given name
	 *
	 * @param x Object's new position's x-coordinate in game world (Pxl)
	 * @param y Object's new position's y-coordinate in game world (Pxl)
	 * @param z Object's new position's z-coordinate in game world (Pxl)
	 * @param movementInterval How often is the valas moved (steps)
	 * @param movementForce How much accelration is added to the object at each movement
	 * @param name Object's new nickname
	 */
	public Valas(int x, int y, int z, int movementInterval, double movementForce, String name)
	{
		super(x, y, z, name);
		
		setRotationFriction(1);
		setFriction(0.1);
		this.movementForce = movementForce;
		
		if (movementInterval > 0)
			this.movementInterval = movementInterval;
		else
			this.movementInterval = 1;
		this.tillMovement = this.movementInterval;
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
	
	@Override
	public void act()
	{
		// Adds valas' own movement if needed and checks the angles
		checkAngle(50);
		
		this.tillMovement --;
		if (this.tillMovement == 0)
		{
			this.tillMovement = this.movementInterval;
			addMotion3D((int) getXAngle(), -(int) getYAngle() -90, this.movementForce);
			
			System.out.println(getZDirection());
			
			/*
			if (getSpeed() > 10)
			{
				setSpeed3D(10);
			}
			*/
			//setMotion3D((int) getXAngle(), (int) -getYAngle() - 90, this.movementForce);
			
			//System.out.println(getVspeed());
			//System.out.println(getXAngle());
			//System.out.println(getYAngle());
			//System.out.println("Speed: " + getHspeed() + ", " + getVspeed() + ", " + getZspeed());
			//System.out.println("Direction " + getXAngle() + ", " + getYAngle() + ", " + getZAngle());
		}
		
		super.act();
	}
	
	@Override
	public void setZ(double z)
	{
		setPosition(getX(), getY(), z);
	}


	@Override
	public int getMaxZ()
	{
		return 5;
	}


	@Override
	public int getMinZ()
	{
		return -5;
	}


	@Override
	public boolean isReturned()
	{
		return false;
	}


	@Override
	public void onOutOfRange()
	{
		// Does nothing
	}
	
	
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

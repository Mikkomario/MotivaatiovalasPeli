package drawnobjects;

import listeners.CameraListener;
import motivaatiovalaspeli.HelpMath;

/**
 * These objects are simple 2 dimensional objects that are drawn to a 3 dimensional 
 * world by projecting them so that they always face the camera
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public abstract class DrawnObject2DProjected extends DrawnObject3D implements CameraListener
{
	// ATTRIBUTES	-------------------------------------------------------
	
	private int camx, camy, camz;
	private int camxangle, camyangle;
	private boolean active;
	
	
	// CONSTRUCTOR	-------------------------------------------------------
	
	/**
	 * Creates a new object to the given position, listening to the camera that 
	 * is on the other given position.
	 *
	 * @param x The object's position's x-coordinate
	 * @param y The object's position's y-coordinate
	 * @param z The object's position's z-coordinate
	 * @param camerax The camera's position's x-coordinate
	 * @param cameray The camera's position's y-coordinate
	 * @param cameraz The camera's position's z-coordinate
	 * @param cameraxangle The camera's current view-angle around the x-axis
	 * @param camerayangle The camera's current view-angle around the y-axis
	 */
	public DrawnObject2DProjected(int x, int y, int z, int camerax, 
			int cameray, int cameraz, int cameraxangle, int camerayangle)
	{
		super(x, y, z);
		informCameraPosition(camerax, cameray, cameraz, cameraxangle, camerayangle);
		this.active = true;
		this.camxangle = cameraxangle;
		this.camyangle = camerayangle;
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public void informCameraPosition(int posx, int posy, int posz, int xangle, int yangle)
	{
		this.camx = posx;
		this.camy = posy;
		this.camz = posz;
		this.camxangle = xangle;
		this.camyangle = yangle;
		changeAngle(posx, posy, posz);
	}
	
	@Override
	public void setPosition(double x, double y, double z)
	{
		// In addition to normal position change projectedobject also refreshes
		// its angle
		super.setPosition(x, y, z);
		refreshAngle();
	}
	
	@Override
	public boolean isActive()
	{
		return this.active;
	}
	
	@Override
	public boolean activate()
	{
		this.active = true;
		return true;
	}
	
	@Override
	public boolean inActivate()
	{
		this.active = false;
		return true;
	}
	
	@Override
	public double getOriginZ()
	{
		return 0;
	}
	
	
	// GETTERS & SETTERS	----------------------------------------------
	
	/**
	 * @return The camera's current position on the x-axis
	 */
	protected int getCamX()
	{
		return this.camx;
	}
	
	/**
	 * @return The camera's current position on the y-axis
	 */
	protected int getCamY()
	{
		return this.camy;
	}
	
	/**
	 * @return The camera's current position on the z-axis
	 */
	protected int getCamZ()
	{
		return this.camz;
	}
	
	/**
	 * @return The camera's current view angle around the x-axis
	 */
	protected int getCamXAngle()
	{
		return this.camxangle;
	}
	
	/**
	 * @return The camera's current view angle around the y-axis
	 */
	protected int getCamYAngle()
	{
		return this.camyangle;
	}
	
	
	// OTHER METHODS	--------------------------------------------------
	
	// Projects the object towards the camera
	private void changeAngle(int camx, int camy, int camz)
	{
		// Calculates the angles
		int yangle = HelpMath.PointYDirection((int) getX(), (int) getZ(), camx, camz) -90;
		int xangle = HelpMath.pointXDirection((int) getZ(), (int) getY(), camx, camy);
		
		// Changes the angles
		setAngle(-xangle, -yangle, getZAngle());
	}
	
	/**
	 * Changes the object's angle. Should be used when the object's position 
	 * changes but camera's position keeps the same
	 */
	private void refreshAngle()
	{
		changeAngle(this.camx, this.camy, this.camz);
	}
}

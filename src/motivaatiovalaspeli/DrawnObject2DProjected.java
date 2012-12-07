package motivaatiovalaspeli;

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
	 */
	public DrawnObject2DProjected(int x, int y, int z, int camerax, 
			int cameray, int cameraz)
	{
		super(x, y, z);
		informCameraPosition(camerax, cameray, cameraz);
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public void informCameraPosition(int posx, int posy, int posz)
	{
		this.camx = posx;
		this.camy = posy;
		this.camz = posz;
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

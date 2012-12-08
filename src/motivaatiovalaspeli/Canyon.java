package motivaatiovalaspeli;

/**
 * This object represents a piece of a canyon. Each piece has its own proportions 
 * and can draw a few simple walls
 *
 * @author Gandalf.
 *         Created 29.11.2012.
 */
public class Canyon implements Drawable, Scrollable
{
	// ATTRIBUTES	--------------------------------------------------------
	
	// TODO: Add texturing
	
	private boolean visible, alive;
	private int width, height, depth, maxz, minz;
	private double z;
	
	
	// CONSTRUCTOR	-------------------------------------------------------
	
	/**
	 * 
	 * Creates a new canyon with the gven sizes. The canyon is visible at default.
	 *
	 * @param width The width of the canyon (x-axis) (pxl)
	 * @param height The height of the canyon (y-axis) (pxl)
	 * @param depth The depth of the canyon (z-axis) (pxl)
	 * @param z How far the nearest point of the canyon is on the z-axis (pxl)
	 * @param minz How close the piece of canyon can come before it jumps back to maxz?
	 * @param maxz How far is the piece of canyon dropped when it goes too far?
	 */
	public Canyon(int width, int height, int depth, int z, int minz,
			int maxz)
	{
		// Initializes attributes
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.z = z;
		this.minz = minz;
		this.maxz = maxz;
		
		this.visible = true;
		this.alive = true;
	}
	
	
	// IMPLEMENTED METHODS	------------------------------------------------

	@Override
	public void drawSelf(MotivaatiovalasPeli applet)
	{
		applet.noStroke();
		
		// Draws bottom
		applet.pushMatrix();
		applet.translate(0, this.height, (float) this.z);
		applet.rotateX((float) Math.toRadians(-90));
		applet.fill(200, 200, 40);
		applet.rect(0, 0, this.width, this.depth);
		applet.popMatrix();
		
		// Draws left wall
		applet.pushMatrix();
		applet.translate(0, 0, (float) this.z);
		applet.rotateX((float) Math.toRadians(-90));
		applet.rotateY((float) Math.toRadians(-90));
		applet.fill(50, 20, 20);
		applet.rect(0, 0, this.height, this.depth);
		
		// Draws right wall
		applet.translate(0, 0, -this.width);
		applet.rect(0, 0, this.height, this.depth);
		
		applet.popMatrix();
		applet.noFill();
	}

	@Override
	public boolean isVisible()
	{
		return this.visible;
	}

	@Override
	public boolean isDead()
	{
		return !this.alive;
	}

	@Override
	public boolean kill()
	{
		this.alive = false;
		return true;
	}

	@Override
	public boolean setVisible()
	{
		this.visible = true;
		return true;
	}

	@Override
	public boolean setInvisible()
	{
		this.visible = false;
		return true;
	}
	
	@Override
	public double getZ()
	{
		return this.z;
	}


	@Override
	public void setZ(double z)
	{
		this.z = z;
	}


	@Override
	public int getMaxZ()
	{
		return this.maxz;
	}


	@Override
	public int getMinZ()
	{
		return this.minz;
	}


	@Override
	public boolean isReturned()
	{
		return true;
	}


	@Override
	public void onOutOfRange()
	{
		// Does nothing
		//System.out.println("Canyon moves");
	}
}

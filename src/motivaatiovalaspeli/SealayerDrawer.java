package motivaatiovalaspeli;

/**
 * This class draws transparent layers to the world, making vision fade at a distance
 *
 * @author Gandalf.
 *         Created 6.12.2012.
 */
public class SealayerDrawer implements Drawable
{
	// ATTRIBUTES	------------------------------------------------------
	
	private int layers;
	private int maxZ, minZ;
	private int r, g, b;
	private boolean visible;
	private boolean alive;
	
	
	// CONSTRUCTOR	-----------------------------------------------------
	
	/**
	 * 
	 * Creates a new SealayerDrawer with given attributes.
	 *
	 * @param minZ Where the vision is completely faded
	 * @param maxZ Where the vision starts to fade
	 * @param layers How many layers are drawn (more = slow but more gradient) (>0)
	 * @param r Layer's colour's R value [0, 255]
	 * @param g Layer's colour's G value [0, 255]
	 * @param b Layer's colour's B value [0, 255]
	 * 
	 */
	public SealayerDrawer(int minZ, int maxZ, int layers, int r, int g, int b)
	{
		// Initializes attributes
		this.layers = layers;
		this.minZ = minZ;
		this.maxZ = maxZ;
		this.r = r;
		this.g = g;
		this.b = b;
		
		this.visible = true;
		this.alive = true;
		
		if (this.layers <= 0)
			this.layers = 1;
	}
	

	@Override
	public void drawSelf(MotivaatiovalasPeli applet)
	{
		applet.pushMatrix();
		
		// Translates to maxZ (fades towards minZ)
		applet.translate(0, 0, this.maxZ);
		
		int ldist = (this.maxZ - this.minZ)/this.layers;
		int lalpha = 255 / this.layers;
		applet.fill(this.r, this.g, this.b, lalpha);
		// Draws the layers, moving them away
		for (int i = 0; i < this.layers; i++)
		{
			applet.rect(-1000, -1000, 2000, 2000);
			applet.translate(0, 0, -ldist);
		}
		// Draws the final layer that isn't transparent
		applet.fill(this.r, this.g, this.b);
		applet.rect(-1000, -1000, 2000, 2000);
		
		applet.popMatrix();
		applet.noFill();
	}

	@Override
	public boolean isVisible()
	{
		return this.visible;
	}

	@Override
	public boolean wontBeDrawn()
	{
		return !this.alive;
	}

	@Override
	public boolean endDrawing()
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

}

package motivaatiovalaspeli;

import java.util.Random;

/**
 * This class creates new seagrasses and adds them to a handler and a scroller.
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public class SeagrassCreator extends ObjectCreator implements CameraListener
{
	// ATTRIBUTES	------------------------------------------------------
	
	private static Random rand = new Random();
	
	private int camx, camy, camz;
	
	private SpriteBank sprtbank;
	
	
	// CONSTRUCTOR	------------------------------------------------------

	/**
	 * Creates a new seagrasscreator that starts to create seagrass right away
	 *
	 * @param minDelay How long is the smallest possible delay in creating a grass
	 * @param maxDelay How long is the longest possible delay in creating grass
	 * @param width How wide is the area where the grass is created
	 * @param height Where is the bottom of the area
	 * @param z What z will the new grass have at start
	 * @param maxZ How close the grass will come before it is destroyed
	 * @param grassHandler What drawablehandler will draw the grass.
	 * @param grassScroller What scroller will scroll the grass
	 * @param camerax The camera's current x-coordinate
	 * @param cameray The camera's current y-coordinate
	 * @param cameraz The camera's current z-coordinate
	 * @param spritebank From which spritebank the grassimage(s) can be found
	 */
	public SeagrassCreator(int minDelay, int maxDelay, int width, int height,
			int z, int maxZ, SeagrassHandler grassHandler,
			Scroller grassScroller, int camerax, int cameray, int cameraz, SpriteBank spritebank)
	{
		super(minDelay, maxDelay, width, height, z, maxZ, grassHandler, grassScroller);
		// TODO: Change handler class
		this.camx = camerax;
		this.camy = cameray;
		this.camz = cameraz;
		this.sprtbank = spritebank;
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public void createObject(int fieldWidth, int fieldHeight, int creationZ,
			int maxZ)
	{
		int newx;
		// Chooses the x [32, width-32]
		if (fieldWidth <= 32)
			newx = rand.nextInt(fieldWidth);
		else
			newx = 32 + rand.nextInt(fieldWidth - 64);
		
		// Creates the grass
		Seagrass newgrass = new Seagrass(newx, fieldHeight, creationZ, creationZ, 
				maxZ, this.camx, this.camy, this.camz, this.sprtbank);
		
		// Adds it to the handler + scroller
		getDrawableHandler().addDrawable(newgrass);
		getScroller().addScrollable(newgrass);
	}

	@Override
	public void informCameraPosition(int posx, int posy, int posz)
	{
		this.camx = posx;
		this.camy = posy;
		this.camz = posz;
	}

}

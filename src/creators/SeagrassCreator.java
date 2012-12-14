package creators;

import handlers.CameraListenerHandler;
import handlers.DrawableHandler;

import java.util.Random;

import scrolling.Scroller;
import sprites.SpriteBank;

import listeners.CameraListener;
import model.Seagrass;

/**
 * This class creates new seagrasses and adds them to a handler, a scroller and 
 * a cameralistenerhandler.
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public class SeagrassCreator extends ObjectCreator implements CameraListener
{
	// ATTRIBUTES	------------------------------------------------------
	
	private static Random rand = new Random();
	
	private int camx, camy, camz, camxangle, camyangle;
	
	private SpriteBank sprtbank;
	private CameraListenerHandler cameraHandler;
	
	
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
	 * @param grassDrawer What drawablehandler will draw the grass.
	 * @param grassScroller What scroller will scroll the grass
	 * @param camerahandler The cameralistenerHandler that informs the created 
	 * seagrasses of camera's changes
	 * @param camerax The camera's current x-coordinate
	 * @param cameray The camera's current y-coordinate
	 * @param cameraz The camera's current z-coordinate
	 * @param cameraxangle The camera's current view angle around the x-axis
	 * @param camerayangle The camera's current view angle around the y-axis
	 * @param spritebank From which spritebank the grassimage(s) can be found
	 */
	public SeagrassCreator(int minDelay, int maxDelay, int width, int height,
			int z, int maxZ, DrawableHandler grassDrawer,
			Scroller grassScroller, CameraListenerHandler camerahandler, 
			int camerax, int cameray, int cameraz, int cameraxangle, int camerayangle,
			SpriteBank spritebank)
	{
		super(minDelay, maxDelay, width, height, z, maxZ, grassDrawer, grassScroller);
		// Initializes attributes
		this.camx = camerax;
		this.camy = cameray;
		this.camz = cameraz;
		this.camxangle = cameraxangle;
		this.camyangle = camerayangle;
		this.sprtbank = spritebank;
		this.cameraHandler = camerahandler;
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
				maxZ, this.camx, this.camy, this.camz, this.camxangle, 
				this.camyangle, this.sprtbank);
		
		// Adds it to the handlers + scroller
		getDrawableHandler().addDrawable(newgrass);
		getScroller().addScrollable(newgrass);
		this.cameraHandler.addListener(newgrass);
	}

	@Override
	public void informCameraPosition(int posx, int posy, int posz, int xangle, 
			int yangle)
	{
		this.camx = posx;
		this.camy = posy;
		this.camz = posz;
		this.camxangle = xangle;
		this.camyangle = yangle;
	}
}

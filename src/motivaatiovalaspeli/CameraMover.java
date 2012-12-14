package motivaatiovalaspeli;

import processing.core.PApplet;
import handleds.Actor;
import handlers.CameraListenerHandler;

/**
 * Moves the camera around, informing cameralisteners
 *
 * @author Gandalf.
 *         Created 14.12.2012.
 */
public class CameraMover extends CameraListenerHandler implements Actor
{
	// ATTRIBUTES	------------------------------------------------------
	
	private int defcamx, defcamy, camz, maxmovementx, maxmovementy;
	private int objx, objy, objz;
	private double xmovephase, ymovephase;
	private boolean poschanged;
	
	
	// CONSTRUCTOR	-----------------------------------------------------

	/**
	 * Creates a new cameramover with the given information
	 * 
	 * @param defcamx Camera's default position on the x-axis
	 * @param defcamy Camera's default position on the y-axis
	 * @param camz Camera's default position on the z-axis
	 * @param maxmovementx Camera's maximal movement distance on the x-axis
	 * @param maxmovementy Camera's maximal movement distance on the y-axis
	 * @param objx The object's (towards which the camera looks) position on the x-axis
	 * @param objy The object's (towards which the camera looks) position on the y-axis
	 * @param objz The object's (towards which the camera looks) position on the z-axis
	 *
	 */
	public CameraMover(int defcamx, int defcamy, int camz, int maxmovementx, 
			int maxmovementy, int objx, int objy, int objz)
	{
		super(false);
		
		// Initializes attributes
		this.defcamx = defcamx;
		this.defcamy = defcamy;
		this.camz = camz;
		this.maxmovementx = maxmovementx;
		this.maxmovementy = maxmovementy;
		this.objx = objx;
		this.objy = objy;
		this.objz = objz;
		
		this.xmovephase = 0;
		this.ymovephase = 0;
		this.poschanged = true;
	}

	@Override
	public void act()
	{
		// Changes camera's phase
		this.xmovephase += 0.03;
		this.ymovephase += 0.02;
		
		this.xmovephase %= Math.PI*2;
		this.ymovephase %= Math.PI*2;
		
		this.poschanged = true;
	}
	
	
	// OTHER METHODS	---------------------------------------------------
	
	/**
	 *This method actually handles the camera and informs all the cameralisteners
	 *of changes. This should be called at each draw
	 * 
	 * @param applet The applet who's camera is changed
	 */
	public void changeCamera(PApplet applet)
	{
		// Calculates the position
		int x = (int) (this.defcamx + Math.sin(this.xmovephase)*this.maxmovementx*0.5);
		int y = (int) (this.defcamy + Math.sin(this.ymovephase)*this.maxmovementy*0.5);
		
		//System.out.println(this.xmovephase);
		
		// Changes camera's position and informs listeners
		applet.camera(x, y, this.camz, this.objx, this.objy, this.objz, 0, 1, 0 );
		
		// If the position was changed, informs the listeners
		if (this.poschanged)
		{
			if (isActive() && !isDead())
				informCameraPosition(x, y, this.camz);
				this.poschanged = false;
		}
	}
	
}

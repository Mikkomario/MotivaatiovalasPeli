package motivaatiovalaspeli;

import processing.core.PApplet;
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
	}
	
	
	// IMPLEMENTED METHODS	---------------------------------------------

	@Override
	public void drawSelf3D(MotivaatiovalasPeli applet)
	{
		// TODO Add cool valas models
		// Changes origin
		applet.translate(-16, -16, 0);
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
	public void onKeyPressed(int key, int keyCode, boolean coded)
	{
		// TODO Add controlls
		if (coded && keyCode == PConstants.UP)
			addPosition(0, -5, 0);
	}

	@Override
	public void onKeyReleased(int key, int keyCode)
	{
		// Doesn't do anyhting
	}

	@Override
	public void onKeyTyped(int key, int keyCode)
	{
		// Doesn't do anyhting
	}

}

package score;

import motivaatiovalaspeli.MotivaatiovalasPeli;
import drawnobjects.DrawnObject2DProjected;

/**
 * TODO Put here a description of what this class does.
 *
 * @author Gandalf.
 *         Created 14.12.2012.
 */
public class KuhaRemainderDrawer extends DrawnObject2DProjected
{
	// ATTRIBUTES	------------------------------------------------------
	
	private ScoreHandler scorehandler;
	
	
	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * Creates a new kuharemainderdrawer
	 * @param scorehandler The scorehandler which knows how many kuhas have been eaten
	 */
	public KuhaRemainderDrawer(ScoreHandler scorehandler)
	{
		super(150, 200, 100, 0, 0, 0, 0, 0);
		this.scorehandler = scorehandler;
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public void drawSelf3D(MotivaatiovalasPeli applet)
	{
		// Prints the kuhatext
		applet.stroke(0);
		applet.fill(0);
		applet.textSize(18);
		
		String text = "Kuhia: " + this.scorehandler.getKuhas() + " / 100";
		applet.text(text, 0, 0);
		
		applet.noStroke();
		applet.noFill();
	}

	@Override
	public double getOriginX()
	{
		return 32;
	}

	@Override
	public double getOriginY()
	{
		return 16;
	}

}

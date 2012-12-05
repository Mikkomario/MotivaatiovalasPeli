package motivaatiovalaspeli;

import processing.core.PApplet;

/**
 * This class calculates millisconds and calls all actors when a certain number 
 * of milliseconds has passed. All of the actors should be under the command of 
 * this object. This object doesn't stop functioning by itself if it runs out 
 * of actors. Also, the stephandler doesn't stop functioning even when there 
 * are no actors under its command.
 * 
 * The stephandler's act method must still be called at each frame
 *
 * @author Gandalf.
 *         Created 29.11.2012.
 */
public class StepHandler extends ActorHandler
{
	// ATTRIBUTES	-------------------------------------------------------
	
	private int stepduration;
	private int lastMillis;
	private PApplet applet;
	
	
	// CONSTRUCTOR	-------------------------------------------------------
	
	/**
	 * TThis creates a new stephandler. Actors are informed 
	 * when a certain number of milliseconds has passed. Actors can be 
	 * added using addActor method.
	 * 
	 * @param stepDuration How long does a single step last in milliseconds?
	 * In other words, how often are the actors updated.
	 * @param applet The applet which created the stepHandler
	 */
	public StepHandler(int stepDuration, PApplet applet)
	{
		super((Actor) null);
		this.stepduration = stepDuration;
		this.applet = applet;
		
		this.lastMillis = this.applet.millis();
	}
	
	
	// IMPLEMENTED METHODS	-----------------------------------------------
	
	@Override
	public boolean isDead()
	{
		// A stephandler is never dead
		super.isDead();
		return false;
	}
	
	@Override
	public void act()
	{	
		// Checks the current millis and performs a "step" if needed
		if (Math.abs(this.applet.millis() - this.lastMillis) > this.stepduration)
		{
			//System.out.println("STEP");
			
			// Also checks the liveliness of all subactors
			//isActive();
			isDead();
			
			super.act();
			this.lastMillis = this.applet.millis();
		}
	}
}

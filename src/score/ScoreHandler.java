package score;

import motivaatiovalaspeli.MotivaatiovalasPeli;
import handleds.Actor;
import handleds.Drawable;

/**
 * This class calculates points and listens to certain objects that want to 
 * increase / decrease the points. Points are also reduced at each step
 *
 * @author Gandalf.
 *         Created 13.12.2012.
 */
public class ScoreHandler implements Actor, Drawable
{
	// ATTRIBUTES	-------------------------------------------------------
	
	private double score;
	private boolean active;
	private boolean alive;
	private boolean visible;
	
	
	// CONSTRUCTOR	-------------------------------------------------------
	
	/**
	 *Creates a new scorehandler that starts to calculate points right away
	 */
	public ScoreHandler()
	{
		// Initializes attributes
		this.score = 0;
		this.active = true;
		this.alive = true;
		this.visible = true;
	}
	
	
	// IMPLEMENTED METHODS	------------------------------------------------

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
	public void act()
	{
		// Reduces the current points
		this.score -= 0.1;
	}


	@Override
	public void drawSelf(MotivaatiovalasPeli applet)
	{
		// Draws the score
		applet.stroke(255);
		applet.fill(255);
		applet.text((int) this.score + "", 100, 100);
		applet.noStroke();
		applet.noFill();
	}


	@Override
	public boolean isVisible()
	{
		return this.visible;
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

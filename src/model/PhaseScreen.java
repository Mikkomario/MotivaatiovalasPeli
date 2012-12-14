package model;

import handleds.Drawable;
import motivaatiovalaspeli.GamePhase;
import motivaatiovalaspeli.MotivaatiovalasPeli;
import sprites.Sprite;
import sprites.SpriteBank;

/**
 * Phasescreen draws a nice image to the screen
 *
 * @author Gandalf.
 *         Created 14.12.2012.
 */
public class PhaseScreen implements Drawable
{
	// ATTRIBUTES	-------------------------------------------------------
	private Sprite sprite;
	private boolean visible, alive;
	
	
	// CONSTRUCTOR	-------------------------------------------------------
	
	/**
	 * Creates a new phasescreen. The screen is invisible until otherwise noted
	 *
	 * @param phase The phase which the screen represents
	 * @param spritebank The spritebank which includes 
	 */
	public PhaseScreen(GamePhase phase, SpriteBank spritebank)
	{
		// Initializes attributes
		switch (phase)
		{
			case BEGIN: this.sprite = spritebank.getSprite("beginning"); break;
			case VICTORY: this.sprite = spritebank.getSprite("victory"); break;
			case OVER: this.sprite = spritebank.getSprite("loss"); break;
			case KSS: this.sprite = spritebank.getSprite("kss"); break;
			case PAUSED: this.sprite = spritebank.getSprite("pause"); break;
			default: System.err.println("No such phase applicable");break;
		}
		
		this.visible = false;
		this.alive = true;
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
		// Ends the drawing and also kills the object
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
	public void drawSelf(MotivaatiovalasPeli applet)
	{
		// Draws a simple image
		applet.image(this.sprite.getSubImage(0), 0, 0);
	}
}

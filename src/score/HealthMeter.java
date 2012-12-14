package score;

import sprites.Sprite;
import sprites.SpriteBank;
import motivaatiovalaspeli.MotivaatiovalasPeli;
import drawnobjects.DrawnObject2DProjected;

/**
 * Healthmeter draws the current health as a smile / frown
 *
 * @author Gandalf.
 *         Created 14.12.2012.
 */
public class HealthMeter extends DrawnObject2DProjected
{
	// ATTRIBUTES	-------------------------------------------------------
	
	private Sprite healthsprite;
	private ScoreHandler scorehandler;
	
	
	// CONSTRUCTOR	-------------------------------------------------------
	
	/**
	 * Creates a new healthmeter that starts drawing itself to the left corner
	 * @param scorehandler The Scorehandler that tells the current health
	 * @param spritebank The spritebank that contains the health sprite
	 */
	public HealthMeter(ScoreHandler scorehandler, SpriteBank spritebank)
	{
		super(100, 100, 100, 0, 0, 0, 0, 90);
		
		this.scorehandler = scorehandler;
		this.healthsprite = spritebank.getSprite("health");
	}
	
	
	// IMPLEMENTED METHODS	--------------------------------------------------

	@Override
	public double getOriginX()
	{
		return this.healthsprite.getOriginX();
	}


	@Override
	public double getOriginY()
	{
		return this.healthsprite.getOriginY();
	}
	
	@Override
	public void drawSelf3D(MotivaatiovalasPeli applet)
	{
		// Draws the healthsprite
		int imgIndex = (int) ((1 -  this.scorehandler.getHealth() / 100.0)
				*this.healthsprite.getImageNumber());
		
		if (imgIndex < 0)
			imgIndex = 0;
		else if (imgIndex >= this.healthsprite.getImageNumber())
			imgIndex = this.healthsprite.getImageNumber() - 1;
		
		applet.image(this.healthsprite.getSubImage(imgIndex), 0, 0);
	}
}

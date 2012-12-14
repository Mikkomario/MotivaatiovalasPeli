package score;

import drawnobjects.DrawnObject2DProjected;
import sprites.Sprite;
import sprites.SpriteBank;
import motivaatiovalaspeli.MotivaatiovalasPeli;
import handleds.Actor;

/**
 * This class calculates points and listens to certain objects that want to 
 * increase / decrease the points. Points are also reduced at each step
 *
 * @author Gandalf.
 *         Created 13.12.2012.
 */
public class ScoreHandler extends DrawnObject2DProjected implements Actor
{
	// ATTRIBUTES	-------------------------------------------------------
	
	private double score;
	
	private Sprite healthsprite;
	
	private double distance;
	
	
	// CONSTRUCTOR	-------------------------------------------------------
	
	/**
	 *Creates a new scorehandler that starts to calculate points right away
	 * 
	 * @param spritebank The spritebank that contains the healthbar sprite
	 */
	public ScoreHandler(SpriteBank spritebank)
	{
		super(100, 100, 100, 0, 0, 0, 0, 90);
		
		// Initializes attributes
		this.score = 50;
		
		this.healthsprite = spritebank.getSprite("health");
		
		this.distance = 0;
	}
	
	
	// IMPLEMENTED METHODS	------------------------------------------------

	@Override
	public void act()
	{
		// Reduces the current points
		increaseScore(-0.05);
		
	}
	
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
		int imgIndex = (int) ((1 -  this.score / 100)*this.healthsprite.getImageNumber());
		
		if (imgIndex < 0)
			imgIndex = 0;
		else if (imgIndex >= this.healthsprite.getImageNumber())
			imgIndex = this.healthsprite.getImageNumber() - 1;
		
		applet.image(this.healthsprite.getSubImage(imgIndex), 0, 0);
	}
	
	
	// OTHER METHODS	---------------------------------------------------
	
	/**
	 * Increases the current score by the given amount
	 * 
	 * @param increasement How much the score is increased
	 */
	public void increaseScore(double increasement)
	{
		this.score += increasement;
		
		if (this.score < 0){
			this.score = 0;
		//here the game is lost
		}
		else if (this.score > 100){
			this.score = 100;
		}
	
	}
	
	/**
	 * Increases the current distance by the given amount. If distance gets high
	 * enough, the method calls for a method that makes the game won.
	 * 
	 * @param increasement How much the distance is increased
	 */
	
	public void increaseDistance(double increasement){
		
		this.distance += increasement;
		
		if(this.distance > 20000){
			//here the game is won
		}
	}
}

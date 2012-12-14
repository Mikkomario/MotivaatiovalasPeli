package score;

import motivaatiovalaspeli.GameController;
import sprites.SpriteBank;
import handleds.Actor;
import handlers.CameraListenerHandler;
import handlers.DrawableHandler;

/**
 * This class calculates points and listens to certain objects that want to 
 * increase / decrease the points. Points are also reduced at each step.
 * 
 * Also draws the current health and remaining kuhas
 *
 * @author Gandalf.
 *         Created 13.12.2012.
 */
public class ScoreHandler extends DrawableHandler implements Actor
{
	// TODO: Draw how man kuha's are left
	
	// ATTRIBUTES	-------------------------------------------------------
	
	private double health;
	private double distance;
	private boolean active;
	private int kuhas;
	private int maxKuhas;
	
	private HealthMeter hpmeter;
	private KuhaRemainderDrawer kuhadraw;
	private GameController controller;
	
	
	// CONSTRUCTOR	-------------------------------------------------------
	
	/**
	 *Creates a new scorehandler that starts to calculate points right away
	 * 
	 * @param camerahandler The object which informs the healthmeter and 
	 * kuharemainderdrawer of camera's position
	 * @param controller The contoller that can end the game
	 * @param spritebank The spritebank that contains the healthbar sprite
	 */
	public ScoreHandler(CameraListenerHandler camerahandler, GameController 
			controller, SpriteBank spritebank)
	{
		super(false);
		
		// Initializes attributes
		this.health = 50;
		this.maxKuhas = 50;
		this.kuhas = 0;
		this.distance = 0;
		this.active = true;
		this.controller = controller;
		
		this.hpmeter = new HealthMeter(this, spritebank);
		addDrawable(this.hpmeter);
		camerahandler.addListener(this.hpmeter);
		
		this.kuhadraw = new KuhaRemainderDrawer(this);
		addDrawable(this.kuhadraw);
		camerahandler.addListener(this.kuhadraw);
	}
	
	
	// IMPLEMENTED METHODS	------------------------------------------------

	@Override
	public void act()
	{
		// Reduces the current points
		increaseHealth(-0.09);
		
	}

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
	
	
	// GETTERS & SETTERS	-----------------------------------------------
	
	/**
	 * @return Current health
	 */
	public int getHealth()
	{
		return (int) this.health;
	}
	
	/**
	 * @return How many kuhas have been eaten
	 */
	public int getKuhas()
	{
		return this.kuhas;
	}
	
	/**
	 * @return How many kuhas must be eaten
	 */
	public int getMaxKuhas()
	{
		return this.maxKuhas;
	}
	
	
	// OTHER METHODS	---------------------------------------------------
	
	/**
	 * Increases the current score by the given amount
	 * 
	 * @param increasement How much the score is increased
	 */
	public void increaseHealth(double increasement)
	{
		this.health += increasement;
		
		if (this.health < 0)
		{
			this.health = 0;
			System.out.println("Pelipaattyy");
			this.controller.loseTheGame();
		}
		else if (this.health > 100){
			this.health = 100;
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
			// NOTICE: The game is won by eating kuhas instead
		}
	}
	
	/**
	 * Eats a kuha, adding it ot the counter
	 */
	public void eatKuha()
	{
		this.kuhas ++;
		
		// If one has eaten enough kuhas, the game is beaten
		if (this.kuhas >= this.maxKuhas)
			this.controller.winTheGame();
	}
}

package motivaatiovalaspeli;

import model.Valas;
import creators.CanyonCreator;
import creators.KuhaCreator;
import creators.RockCreator;
import creators.SeagrassCreator;
import handlers.ActorHandler;
import handlers.CameraListenerHandler;
import handlers.CollisionHandler;
import handlers.DrawableHandler;
import handlers.KeyListenerHandler;
import handlers.MainKeyListenerHandler;
import handlers.StepHandler;
import processing.core.PApplet;
import score.ScoreHandler;
import scrolling.FollowingScroller;
import sounds.BackgroundMusicPlayer;
import sprites.SpriteBank;

/**
 * 
 * This class handles the game setup calls for drawablehandlers to draw objects
 *  and stephandler to handle actors and such. This class also listens to users 
 *  actions on the keyboard and informs keylisteners when necessary
 *
 * @author Gandalf.
 *         Created 29.11.2012.
 */
public class MotivaatiovalasPeli extends PApplet
{
    // ATTRIBUTES	-------------------------------------------------------
	
	private DrawableHandler mainDrawer;
	private StepHandler stepHandler;
	private MainKeyListenerHandler mainkeyhandler;
	private CameraMover camerahandler;
	private Valas player;
	private FollowingScroller playerscroller;
	private SpriteBank sprtbank;
	private CollisionHandler mainCollisionHandler;
	private ScoreHandler scorehandler;
	private ActorHandler gamelogic;
	private DrawableHandler gamedrawer;
	private GameController controller;
	private DrawableHandler primarydrawer;
	private DrawableHandler secondarydrawer;
	private DrawableHandler controllerDrawer;
	private KeyListenerHandler logicalListenerHandler;
	private CameraListenerHandler logicalCameraHandler;
    private BackgroundMusicPlayer backgroundMusicPlayer;
	
	private static final long serialVersionUID = 1L;
	
	
	// IMPLEMENTED METHODS	-----------------------------------------------
	
	@Override
	public void setup()
	{
		this.showStatus("Motivaatiovalas-Peli");
		size(640, 480, P3D);
		noFill();
		
		// Initializes the most basic attributes
		
		this.sprtbank = new SpriteBank(this);
		this.mainDrawer = new DrawableHandler(false);
		this.stepHandler = new StepHandler(60, this);
		this.primarydrawer = new DrawableHandler(false);
		this.secondarydrawer = new DrawableHandler(false);
		// This is needed for the rigth drawing order
		this.mainDrawer.addDrawable(this.secondarydrawer);
		this.mainDrawer.addDrawable(this.primarydrawer);
		// Creates the listenerhandler and adds it to stephandler
		this.mainkeyhandler = new MainKeyListenerHandler();
		this.stepHandler.addActor(this.mainkeyhandler);
		// Creates a cameralistenerhandler
		this.camerahandler = new CameraMover(this.width/2, this.height/2, 420, 
				this.width/3, this.height/3, this.width/2, this.height/2, 0);
		this.stepHandler.addActor(this.camerahandler);
		
		// And the attributes of the controller
		this.controllerDrawer = new DrawableHandler(true);
		this.controller = new GameController(this, this.controllerDrawer, this.sprtbank);
		this.primarydrawer.addDrawable(this.controllerDrawer);
		this.mainkeyhandler.addListener(this.controller);
		
		//this.beginGame();
	}
	
	@Override
	public void draw()
	{	
		//System.out.println(this.frameRate);
		//perspective((float) (PConstants.PI/3.0), (float) (640/480.0), 10, -1000);
		
		background(6, 158, 188);
		noStroke();
		noFill();
		
		// Changes the camera
		this.camerahandler.changeCamera(this);
		//camera(this.width/2, this.height/2, 420, this.width/2, this.height/2,
		//		0, 0, 1, 0 );
		/*
		camera(this.mouseX, this.mouseY, 420, this.width/2, this.height/2,
				0, 0, 1, 0 );
		*/
		
		// Updates all objects through the stephandler
		this.stepHandler.act();
		// Draws all the objects (Also checks if some are dead)
		//this.mainDrawer.isVisible();
		this.mainDrawer.isDead();
		this.mainDrawer.drawSelf(this);
	}
	
	@Override
	public void keyPressed()
	{
		this.mainkeyhandler.onKeyPressed(this.key, this.keyCode, (this.key == CODED));
	}
	
	@Override
	public void keyReleased()
	{
		this.mainkeyhandler.onKeyReleased(this.key, this.keyCode, (this.key == CODED));
	}
	
	
	// OTHER METHODS	----------------------------------------------------
	
	/*
	private void arrow()
	{
		line(0,0, 100,0);
		triangle(100,0, 80,10, 80,-10);
	}
	*/
	
	/**
	 *Starts the game
	 */
	public void beginGame(){
		
		// Initializes the most basic handlers concerning the game logic
		this.gamedrawer = new DrawableHandler(false);
		this.gamelogic = new ActorHandler(false);
		this.logicalListenerHandler = new KeyListenerHandler(false);
		this.secondarydrawer.addDrawable(this.gamedrawer);
		this.stepHandler.addActor(this.gamelogic);
		this.mainkeyhandler.addListener(this.logicalListenerHandler);
		this.logicalCameraHandler = new CameraListenerHandler(false);
		this.camerahandler.addListener(this.logicalCameraHandler);
		
		// Creates a collisionhandler and adds it to the stephandler
		this.mainCollisionHandler = new CollisionHandler(false);
		this.gamelogic.addActor(this.mainCollisionHandler);
		
		// Creates a scorehandler
		this.scorehandler = new ScoreHandler(this.logicalCameraHandler, this.sprtbank);
		
		// Creates the playable valas and adds it to drawer, stephandler,
		// collisionhandler and keyhandler
		this.player = new Valas(this.width/2, this.height/2, 0, this.width, 
				this.height, 15, 8, 15, this, this.scorehandler);
		this.gamedrawer.addDrawable(this.player);
		this.gamelogic.addActor(this.player);
		this.logicalListenerHandler.addKeyListener(this.player);
		this.mainCollisionHandler.addCollisionListener(this.player);
		
		// Creates the scroller and adds valas as its scrollable
		this.playerscroller = new FollowingScroller(this.player);
		this.gamelogic.addActor(this.playerscroller);
		
		// Creates the canyons
		DrawableHandler canyonDrawer = new DrawableHandler(true);
		this.gamedrawer.addDrawable(canyonDrawer);
		CanyonCreator.createCanyons(this.width, this.height, -1000, 500, 5, 
				this.playerscroller, canyonDrawer, this.sprtbank);
		
		// Creates a rockhandler for drawing the rocks
		DrawableHandler rhandler = new DrawableHandler(false);
		//rhandler.addCollisionListener(this.player);
		//this.stepHandler.addActor(rhandler);
		this.gamedrawer.addDrawable(rhandler);
		
		// Creates a rockcreator
		RockCreator rcreator = new RockCreator(1, 5, 250, 620, this.width,
				this.height, -1000, 300, rhandler, this.playerscroller, 
				this.mainCollisionHandler);
		this.gamelogic.addActor(rcreator);
		
		// Creates kuhahandler for drawing kuhas
		DrawableHandler khandler = new DrawableHandler(false);
		this.gamedrawer.addDrawable(khandler);
		
		// Creates a Kuhacreator
		KuhaCreator kcreator = new KuhaCreator(100, 250, this.width, this.height, 
				-1000, 300, khandler, this.playerscroller, 
				this.mainCollisionHandler, this);
		this.gamelogic.addActor(kcreator);
		
		// Creates a seagrasshandler
		DrawableHandler grasshandler = new DrawableHandler(false);
		this.gamedrawer.addDrawable(grasshandler);
		
		// Creates a seagrasscreator
		SeagrassCreator seagrasscreator = new SeagrassCreator(80, 400, this.width, 
				this.height, -1000, 300, grasshandler, this.playerscroller, 
				this.logicalCameraHandler, this.width/2, this.height/2, 420, 0, 
				90, this.sprtbank);
		this.logicalCameraHandler.addListener(seagrasscreator);
		this.gamelogic.addActor(seagrasscreator);
		
		// Adds the scorehandler to the drawn objects (must be done here for the drawing order)
		this.gamedrawer.addDrawable(this.scorehandler);
		this.gamelogic.addActor(this.scorehandler);
		
		this.backgroundMusicPlayer = new BackgroundMusicPlayer();
		this.gamelogic.addActor(this.backgroundMusicPlayer);
		
		// Creates a seaLayerDrawer
		/*
		SealayerDrawer sld = new SealayerDrawer(-900, 0, 10, 0, 10, 100);
		this.mainDrawer.addDrawable(sld);
		*/
		
		//testcanyon.setInvisible();
		
		/*
		ValasCollisionTestDrawer test = new ValasCollisionTestDrawer(this.player);
		this.mainDrawer.addDrawable(test);
		*/
	}
	
	/**
	 *Pauses the game
	 */
	public void pause()
	{
		// sets game inactive for a while
		this.gamelogic.inActivate();
		this.gamedrawer.setInvisible();
		this.logicalCameraHandler.inActivate();
		this.logicalListenerHandler.inActivate();
	}
	
	/**
	 * Unpauses the game
	 */
	public void unPause()
	{
		// Reactivates the game logic
		this.gamelogic.activate();
		this.gamedrawer.setVisible();
		this.logicalCameraHandler.activate();
		this.logicalListenerHandler.activate();
	}
	
	/**
	 * Stops running the main game
	 */
	public void endGame()
	{
		// Kills all elements that are connected to the game logic and game drawing
		this.gamelogic.kill();
		this.gamedrawer.kill();
		this.logicalCameraHandler.kill();
		this.logicalListenerHandler.kill();
	}
}

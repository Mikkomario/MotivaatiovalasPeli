package motivaatiovalaspeli;

import model.Valas;
import creators.CanyonCreator;
import creators.KuhaCreator;
import creators.RockCreator;
import creators.SeagrassCreator;
import handlers.CollisionHandler;
import handlers.DrawableHandler;
import handlers.KeyListenerHandler;
import handlers.StepHandler;
import processing.core.PApplet;
import score.ScoreHandler;
import scrolling.FollowingScroller;
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
	private KeyListenerHandler keyhandler;
	private CameraMover camerahandler;
	private Valas player;
	private FollowingScroller playerscroller;
	private SpriteBank sprtbank;
	private CollisionHandler mainCollisionHandler;
	private ScoreHandler scorehandler;
	
	
	public void beginGame(){
		size(640, 480, P3D);
		noFill();
		
		// Creates the spritebank
		this.sprtbank = new SpriteBank(this);
		
		// Creates the canyon and adds it to the drawables handled
		//Canyon testcanyon = new Canyon(this.width, this.height, 1000, 100, 
				//-900, 1100, this.sprtbank);
		/*
		Canyon testcanyon = new Canyon(this.width, this.height, 1000, 100, 
				-900, 1100, this.sprtbank);
		this.mainDrawer = new DrawableHandler(false);
		this.mainDrawer.addDrawable(testcanyon);
		//Canyon testcanyon2 = new Canyon(this.width, this.height, 1000, -900, -900, 1100, this.sprtbank);
		Canyon testcanyon2 = new Canyon(this.width, this.height, 1000, -900, 
				-900, 1100, this.sprtbank);
		this.mainDrawer.addDrawable(testcanyon2);
		*/
		this.mainDrawer = new DrawableHandler(false);
		
		// Also creates the stephandler
		this.stepHandler = new StepHandler(60, this);
		
		// Creates the listenerhandler and adds it to stephandler
		this.keyhandler = new KeyListenerHandler();
		this.stepHandler.addActor(this.keyhandler);
		
		// Creates a collisionhandler and adds it to the stephandler
		this.mainCollisionHandler = new CollisionHandler(false);
		this.stepHandler.addActor(this.mainCollisionHandler);
		
		// Creates a cameralistenerhandler
		this.camerahandler = new CameraMover(this.width/2, this.height/2, 420, 
				this.width/3, this.height/3, this.width/2, this.height/2, 0);
		this.stepHandler.addActor(this.camerahandler);
		
		// Creates a scorehandler
		this.scorehandler = new ScoreHandler(this.sprtbank);
		this.camerahandler.addListener(this.scorehandler);
		
		// Creates the playable valas and adds it to drawer, stephandler,
		// collisionhandler and keyhandler
		this.player = new Valas(this.width/2, this.height/2, 0, this.width, 
				this.height, 15, 8, 15, this, this.scorehandler);
		this.mainDrawer.addDrawable(this.player);
		this.stepHandler.addActor(this.player);
		this.keyhandler.addListener(this.player);
		this.mainCollisionHandler.addCollisionListener(this.player);
		
		// Creates the scroller and adds valas as its scrollable
		this.playerscroller = new FollowingScroller(this.player);
		this.stepHandler.addActor(this.playerscroller);
		//this.playerscroller.addScrollable(testcanyon);
		//this.playerscroller.addScrollable(testcanyon2);
		
		// Creates the canyons
		DrawableHandler canyonDrawer = new DrawableHandler(true);
		this.mainDrawer.addDrawable(canyonDrawer);
		CanyonCreator.createCanyons(this.width, this.height, -1000, 500, 5, 
				this.playerscroller, canyonDrawer, this.sprtbank);
		
		// Creates a rockhandler for drawing the rocks
		DrawableHandler rhandler = new DrawableHandler(false);
		//rhandler.addCollisionListener(this.player);
		//this.stepHandler.addActor(rhandler);
		this.mainDrawer.addDrawable(rhandler);
		
		// Creates a rockcreator
		RockCreator rcreator = new RockCreator(1, 5, 250, 620, this.width,
				this.height, -1000, 300, rhandler, this.playerscroller, 
				this.mainCollisionHandler);
		this.stepHandler.addActor(rcreator);
		
		// Creates kuhahandler for drawing kuhas
		DrawableHandler khandler = new DrawableHandler(false);
		this.mainDrawer.addDrawable(khandler);
		
		// Creates a Kuhacreator
		KuhaCreator kcreator = new KuhaCreator(100, 250, this.width, this.height, 
				-1000, 300, khandler, this.playerscroller, this.mainCollisionHandler);
		this.stepHandler.addActor(kcreator);
		
		// Creates a seagrass for testing
		/*
		Seagrass grass = new Seagrass(120, this.height, -800, -1000, 300, 
				this.width/2, this.height/2, 420, this.sprtbank);
		this.mainDrawer.addDrawable(grass);
		this.playerscroller.addScrollable(grass);
		*/
		
		// Creates a seagrasshandler
		DrawableHandler grasshandler = new DrawableHandler(false);
		this.mainDrawer.addDrawable(grasshandler);
		
		// Creates a seagrasscreator
		SeagrassCreator seagrasscreator = new SeagrassCreator(80, 400, this.width, 
				this.height, -1000, 300, grasshandler, this.playerscroller, 
				this.camerahandler, this.width/2, this.height/2, 420, 0, 90, this.sprtbank);
		this.camerahandler.addListener(seagrasscreator);
		this.stepHandler.addActor(seagrasscreator);
		
		// Adds the scorehandler to the drawn objects (must be done here for the drawing order)
		this.mainDrawer.addDrawable(this.scorehandler);
		this.stepHandler.addActor(this.scorehandler);
		
		
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
	
	// IMPLEMENTED METHODS	-----------------------------------------------
	
	@Override
	public void setup()
	{
		this.beginGame();
	}
	
	@Override
	public void draw()
	{	
		System.out.println(this.frameRate);
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
		
		// TEST DRAWING
		
		//text("asdasdasdadasdSAKJKLFJKASJSKLD", 100, 100);
		/*
		stroke(255);
		rect(this.width/2, this.height/2, 20, 30);
		
		// Draws the "room" boundaries
		rect(0, 0, this.width, this.height);
		
		stroke(255,0,0);
		
		pushMatrix();
		
		//rotateX((float) (this.mouseX/this.width * 2*Math.PI));
		
		translate(this.width/2 - 20, this.height/2 -20);
		
		//rotateZ((float) Math.toRadians(90));
		
		arrow(); // red x-axis
		rotateZ (radians(90));
		stroke(0,255,0);
		arrow(); // green y-axis
		
		// Yellow z-axis
		rotateY(radians(-90));
		stroke(255, 255, 0);
		arrow();
		
		popMatrix();
		*/
	}
	
	@Override
	public void keyPressed()
	{
		this.keyhandler.onKeyPressed(this.key, this.keyCode, (this.key == CODED));
	}
	
	@Override
	public void keyReleased()
	{
		this.keyhandler.onKeyReleased(this.key, this.keyCode, (this.key == CODED));
	}
	
	
	// OTHER METHODS	----------------------------------------------------
	
	/*
	private void arrow()
	{
		line(0,0, 100,0);
		triangle(100,0, 80,10, 80,-10);
	}
	*/
}

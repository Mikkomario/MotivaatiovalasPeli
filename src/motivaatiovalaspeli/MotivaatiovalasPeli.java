package motivaatiovalaspeli;

import processing.core.PApplet;

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
	// TODO: Add fading using layers with low alpha value
	// TODO: Add valas' controlling
	
	
	// ATTRIBUTES	-------------------------------------------------------
	
	private DrawableHandler mainDrawer;
	private StepHandler stepHandler;
	private KeyListenerHandler keyhandler;
	private Valas player;
	private FollowingScroller playerscroller;
	
	
	// IMPLEMENTED METHODS	-----------------------------------------------
	
	@Override
	public void setup()
	{
		// TODO: Add perspective
		
		size(640, 480, P3D);
		noFill();
		
		// Creates the canyon and adds it to the drawables handled
		Canyon testcanyon = new Canyon(this.width, this.height, 1000, 100, -900, 1100);
		this.mainDrawer = new DrawableHandler(testcanyon, false);
		Canyon testcanyon2 = new Canyon(this.width, this.height, 1000, -900, -900, 1100);
		this.mainDrawer.addDrawable(testcanyon2);
		
		// Also creates the stephandler
		this.stepHandler = new StepHandler(60, this);
		
		// Creates the listenerhandler and adds it to stephandler
		this.keyhandler = new KeyListenerHandler();
		this.stepHandler.addActor(this.keyhandler);
		
		// Creates the playable valas and adds it to drawer, stephandler and keyhandler
		this.player = new Valas(this.width/2, this.height/2, 0, this.width, 
				this.height, 15, 8, 15);
		this.mainDrawer.addDrawable(this.player);
		this.stepHandler.addActor(this.player);
		this.keyhandler.addListener(this.player);
		
		// Creates the scroller and adds canyon and valas as its scrollables
		this.playerscroller = new FollowingScroller(this.player);
		this.stepHandler.addActor(this.playerscroller);
		this.playerscroller.addScrollable(testcanyon);
		this.playerscroller.addScrollable(testcanyon2);
		
		// Creates a rockhandler for drawing the rocks + collision detection
		RockHandler rhandler = new RockHandler();
		rhandler.addCollisionListener(this.player);
		this.stepHandler.addActor(rhandler);
		this.mainDrawer.addDrawable(rhandler);
		
		// Creates a rockcreator
		RockCreator rcreator = new RockCreator(1, 8, 30, 100, this.width,
				this.height, -1000, 300, rhandler, this.playerscroller);
		this.stepHandler.addActor(rcreator);
		
		// Creates a seaLayerDrawer
		SealayerDrawer sld = new SealayerDrawer(-900, 0, 15, 0, 10, 100);
		this.mainDrawer.addDrawable(sld);
		
		//testcanyon.setInvisible();
	}
	
	@Override
	public void draw()
	{	
		//System.out.println(this.frameRate);
		
		background(0, 10, 100);
		noStroke();
		noFill();
		//camera(this.width/2, this.height/2, 420, this.width/2, this.height/2,
		//		0, 0, 1, 0 );
		camera(this.mouseX, this.mouseY, 420, this.width/2, this.height/2,
				0, 0, 1, 0 );
		
		// Updates all objects through the stephandler
		this.stepHandler.act();
		// Draws all the objects (Also checks if some are dead)
		//this.mainDrawer.isVisible();
		this.mainDrawer.wontBeDrawn();
		this.mainDrawer.drawSelf(this);
		
		// TEST DRAWING
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

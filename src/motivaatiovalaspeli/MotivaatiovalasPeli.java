package motivaatiovalaspeli;

import java.util.ArrayList;

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
	// ATTRIBUTES	-------------------------------------------------------
	
	private DrawableHandler mainDrawer;
	private StepHandler stepHandler;
	private KeyListenerHandler keyhandler;
	private Valas player;
	
	
	// IMPLEMENTED METHODS	-----------------------------------------------
	
	@Override
	public void setup()
	{
		size(640, 480, P3D);
		noFill();
		
		// Creates the canyon and adds it to the drawables handled
		Canyon testcanyon = new Canyon(this.width, this.height, 1000, 5, 0, 1100, 7);
		this.mainDrawer = new DrawableHandler(testcanyon);
		
		// Also creates the stephandler and ads canyon to its actors
		this.stepHandler = new StepHandler(60, this);
		this.stepHandler.addActor(testcanyon);
		
		// Creates the listenerhandler and adds it to stephandler
		this.keyhandler = new KeyListenerHandler();
		this.stepHandler.addActor(this.keyhandler);
		
		// Creates the playable valas and adds it to drawer, stephandler and keyhandler
		this.player = new Valas(this.width/2, this.height/2, 0, "Testivalas");
		this.mainDrawer.addDrawable(this.player);
		this.stepHandler.addActor(this.player);
		this.keyhandler.addListener(this.player);
	}
	
	@Override
	public void draw()
	{
		background(0, 10, 100);
		//camera(this.width/2, this.height/2, 420, this.width/2, this.height/2,
		//		0, 0, 1, 0 );
		camera(this.mouseX, this.mouseY, 420, this.width/2, this.height/2,
				0, 0, 1, 0 );
		
		// Updates all objects through the stephandler
		this.stepHandler.act();
		// Draws all the objects
		this.mainDrawer.drawSelf(this);
		
		// TEST DRAWING
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
	
	private void arrow()
	{
		line(0,0, 100,0);
		triangle(100,0, 80,10, 80,-10);
	}
}

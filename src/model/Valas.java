package model;

import handleds.Colliding;
import drawnobjects.PhysicObject3D;
import listeners.CollisionListener;
import listeners.KeyListener;
import sounds.SoundPlayer;
import motivaatiovalaspeli.HelpMath;
import motivaatiovalaspeli.MotivaatiovalasPeli;
import processing.core.PConstants;
import saito.objloader.OBJModel;
import score.ScoreHandler;
import scrolling.Scrollable;

/**
 * Valas is the playable character of MotivaatiovalasPeli. Valas can be controlled 
 * using the keyboard (or mouse?) and is drawn as a 3D object.
 *
 * @author Gandalf.
 *         Created 2.12.2012.
 */
public class Valas extends PhysicObject3D implements KeyListener, Scrollable, CollisionListener
{
    // ATTRIBUTES	------------------------------------------------------

    private int tillMovement, movementInterval, minX, maxX, minY, maxY, tillBoost;
    private double movementForce, maxSPeed, currentMaxSpeed;
    private double normalScale, currentScale, scalePhase;
    private OBJModel model;
    private ScoreHandler score;
    private SoundPlayer soundPlayer;


    // CONSTRUCTOR	------------------------------------------------------

    /**
     * Creates a new valas to the given coordinates with the given name. 
     * Valas also needs some information for its movement. Valas moves in 
     * bursts with the given interval and force. Valas also collides with borders 
     * of the 'screen' or area of play that is given with maxX and maxY. The border's 
     * left top is at (0, 0) at default.
     *
     * @param x Object's new position's x-coordinate in game world (Pxl)
     * @param y Object's new position's y-coordinate in game world (Pxl)
     * @param z Object's new position's z-coordinate in game world (Pxl)
     * @param maxX How large the position's x-coordinate can become before the valas "collides" 
     * with the wall and bounces back (Pxl)
     * @param maxY How large the position's x-coordinate can become before the valas "collides" 
     * with the wall and bounces back (Pxl)
     * @param movementInterval How often is the valas moved (steps)
     * @param movementForce How much accelration is added to the object at each movement
     * @param maxSpeed How fast can the object move
     * @param parent The applet that will draw the valas
     * @param scorehandler The scoreobject that handles valas' score
     */
    public Valas(int x, int y, int z, int maxX, int maxY, int movementInterval,
            double movementForce, double maxSpeed, MotivaatiovalasPeli parent, 
            ScoreHandler scorehandler)
    {
        super(x, y, z);

        this.model = new OBJModel(parent, "valas1.obj", "relative", 
                PConstants.POLYGON);

        setRotationFriction(1);
        setFriction(0.2);
        this.movementForce = movementForce;
        this.maxSPeed = maxSpeed;
        this.maxX = maxX;
        this.maxY = maxY;
        this.minX = 0;
        this.minY = 0;
        this.score = scorehandler;
        
        this.tillBoost = 0;
        this.currentMaxSpeed = this.maxSPeed;

        if (movementInterval > 0)
            this.movementInterval = movementInterval;
        else
            this.movementInterval = 1;
        this.tillMovement = this.movementInterval;

        // Valas's model is so small that we need to scale it a bit
        this.normalScale = 12;
        this.currentScale = 12;
        this.scalePhase = 0;
        setScale(this.normalScale, this.normalScale, this.normalScale);
        
        this.soundPlayer = new SoundPlayer();
    }


    // IMPLEMENTED METHODS	---------------------------------------------

    @Override
    public void drawSelf3D(MotivaatiovalasPeli applet)
    {
        this.model.draw();
        /*
	    applet.fill(0, 0, 255);
		applet.stroke(0);
		// Draws
		applet.rect(0, 0, 5, 100);
		applet.rect(0, 0, 100, 5);
		// Resets
		applet.noFill();
		applet.noStroke();
         */
        // Changes origin
        //applet.translate(-16, -16, 0);
        // Changes colour
        /*
		applet.fill(255, 0, 0);
		applet.stroke(0);
		// Draws
		applet.box(32, 32, 64);
		// Resets
		applet.noFill();
		applet.noStroke();
         */
    }

    @Override
    public void onKeyDown(int key, int keyCode, boolean coded)
    {	
        // Case up
        if (coded && keyCode == PConstants.UP)
            setRotation(-5, getYRotation(), getZRotation());
        // Case down
        else if (coded && keyCode == PConstants.DOWN)
            setRotation(5, getYRotation(), getZRotation());
        // Case left
        else if (coded && keyCode == PConstants.LEFT)
            setRotation(getXRotation(), 5, getZRotation());
        // Case Right
        else if (coded && keyCode == PConstants.RIGHT)
            setRotation(getXRotation(), -5, getZRotation());
        /*
        else if (!coded && key == "b".codePointAt(0))
        {
            //System.out.println("ASDASD");
            scale(2, 2, 2);
        }
        */
    }

    @Override
    public void onKeyPressed(int key, int keyCode, boolean coded)
    {
    	// When space is pressed, boosts forward
      	 if (!coded && key == ' ' && this.tillBoost <= 0)
      	 {
      		 	this.currentMaxSpeed = this.maxSPeed*1.5;
               boost(this.maxSPeed*2);
               this.tillBoost = this.movementInterval;
               //System.out.println(getSpeed());
      	 }
    }

    @Override
    public void onKeyReleased(int key, int keyCode, boolean coded)
    {
        // Does nothing yet
    }

    @Override
    public double getOriginX()
    {
        // 2, 16, 2
        return 2;
    }

    @Override
    public double getOriginY()
    {
        return -2;
    }

    @Override
    public double getOriginZ()
    {
        return 2;
    }

    
    
    @Override
    public void act()
    {
        //System.out.println(getY() - getCollisionY());

        //System.out.println(HelpMath.checkDirection(HelpMath.pointDirection((int) getX(), (int) getY(), 
        //		this.maxX/2, this.maxY/2)));
        //System.out.println(HelpMath.pointDistance((int) getX(), (int) getY(), this.maxX/2, this.maxY/2));

        // Adds valas' own movement if needed and checks the angles
        checkAngle(50);

        this.tillMovement --;
        this.tillBoost --;
        if (this.currentMaxSpeed > this.maxSPeed)
        	this.currentMaxSpeed -= getFriction();
        
        if (this.tillMovement == 0)
        {
            this.tillMovement = this.movementInterval;
            boost(this.movementForce);

            //setMotion3D((int) getXAngle(), (int) -getYAngle() - 90, this.movementForce);

            //System.out.println(getVspeed());
            //System.out.println(getXAngle());
            //System.out.println(getYAngle());
            //System.out.println("Speed: " + getHspeed() + ", " + getVspeed() + ", " + getZspeed());
            //System.out.println("Direction " + getXAngle() + ", " + getYAngle() + ", " + getZAngle());
        }
        // CHecks if the valas is colliding with the borders
        checkBorders();
        
        // Scales the valas a bit for a funny effect
        adjustScaling();
        
        //Adds the distance the valas has proceeded
        this.score.increaseDistance(-this.getZspeed());
        
        super.act();
    }

    @Override
    public void setZ(double z)
    {
        setPosition(getX(), getY(), z);
    }


    @Override
    public int getMaxZ()
    {
        return 5;
    }


    @Override
    public int getMinZ()
    {
        return -5;
    }


    @Override
    public boolean isReturned()
    {
        return false;
    }


    @Override
    public void onOutOfRange()
    {
        // Does nothing
    }

    @Override
    public int getCollisionX()
    {
        return (int) (getX() + getOriginX() 
                + HelpMath.lendirX(20, (int) getYAngle() + 90));
    }


    @Override
    public int getCollisionY()
    {
        return (int) (getY() +  getOriginY() 
                - HelpMath.lendirY(20, (int) -getXAngle()));
    }


    @Override
    public int getCollisionZ()
    {
        return (int) (getZ() + getOriginZ() 
                - HelpMath.lendirX(20, (int) getXAngle()));
    }


    @Override
    public void onCollision(Colliding collidedObject)
    {
        if (!isActive())
            return;

        //setSpeed3D(-5, false);
        //setSpeed3D(-getSpeed(), false);
        //System.out.println(getSpeed());

        if (collidedObject instanceof Rock){
            this.soundPlayer.playRandomKuhaSound();
            
            int ydir = HelpMath.PointYDirection((int) collidedObject.getX(), 
                    (int) collidedObject.getZ(), (int) getX(), (int) getZ());
            int zdir = HelpMath.PointZDirection((int) collidedObject.getX(), 
                    (int) collidedObject.getY(), (int) getX(), (int) getY());
            double hspeed = HelpMath.lendirX(4, ydir);
            double vspeed = HelpMath.lendirY(4, zdir);
            double zspeed = HelpMath.lendirY(4, ydir);
            setVelocity(hspeed, vspeed, zspeed);
        }
        else if (collidedObject instanceof Kuha)
        {
            this.soundPlayer.playRandomValasSound();
        	// Valas eats kuhas and gets points from it
        	collidedObject.kill();
        	this.score.increaseHealth(5);
        	this.score.eatKuha();
        }
    }


    // OTHER METHODS	--------------------------------------------------

    // Returns the x and y angles to a area
    private void checkAngle(int maxAngle)
    {
        if (getXAngle() > 180 && getXAngle() < 360 - maxAngle)
            setAngle(-50, getYAngle(), getZAngle());

        if (getXAngle() > maxAngle && getXAngle() < 180)
            setAngle(50, getYAngle(), getZAngle());

        if (getYAngle() > maxAngle && getYAngle() < 180)
            setAngle(getXAngle(), 50, getZAngle());

        if (getYAngle() < 310 && getYAngle() > 180)
            setAngle(getXAngle(), -50, getZAngle());
    }

    // Checks if the valas has gone too far on the x or y axis and returns it back
    private void checkBorders()
    {
        if (getX() - 35 < this.minX)
            setVelocity(Math.abs(getHspeed())*0.75, getVspeed(), getZspeed());
        else if (getX() + 32 > this.maxX)
            setVelocity(-Math.abs(getHspeed())*0.75, getVspeed(), getZspeed());

        if (getY() - 35 < this.minY)
            setVelocity(getHspeed(), Math.abs(getVspeed())*0.75, getZspeed());
        if (getY() + 35 > this.maxY)
            setVelocity(getHspeed(), -Math.abs(getVspeed())*0.75, getZspeed());
    }
    
    private void boost(double speed)
    {
    	addMotion3D((int) getXAngle(), -(int) getYAngle() -90, speed);

        //System.out.println(getZDirection());

        if (getSpeed() > this.currentMaxSpeed)
        {
            //System.out.println("Slows");
            setSpeed3D(this.currentMaxSpeed, false);
        }
    }
    
    private void adjustScaling()
    {
    	this.scalePhase += 0.1;
    	this.scalePhase %= Math.PI * 2;
    	this.currentScale = this.normalScale * (1.2 + Math.sin(this.scalePhase)*0.2);
    	setScale(this.currentScale, this.currentScale, this.currentScale);
    	//System.out.println(getXscale());
    }
}

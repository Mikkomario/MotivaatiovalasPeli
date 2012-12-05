package motivaatiovalaspeli;

/**
 * In addition to spriteobjects drawing capabilities many basic physics can be 
 * applied to the physicobject
 *
 * @author Gandalf.
 *         Created 28.11.2012.
 */
public abstract class PhysicObject3D extends DrawnObject3D implements Actor
{
	
	// TODO: Test this class
	
	// ATTRIBUTES	------------------------------------------------------
	
	private double hspeed, vspeed, zspeed, zrotation, yrotation, xrotation, 
		friction, rotFriction;
	private boolean active;
	
	
	// CONSTRUCTOR	------------------------------------------------------

	/**
	 * Creates a new physicobject with the given information. The object will
	 * be static until motion is applied. There's no friction or rotation friction 
	 * either until those are added. The object is active at default.
	 *
	 * @param x The ingame x-coordinate of the new object
	 * @param y The ingame y-coordinate of the new object
	 * @param z The ingame z-coordinate of the new object
	 */
	public PhysicObject3D(int x, int y, int z)
	{
		super(x, y, z);
		
		this.hspeed = 0;
		this.vspeed = 0;
		this.zspeed = 0;
		this.zrotation = 0;
		this.xrotation = 0;
		this.yrotation = 0;
		this.friction = 0;
		this.rotFriction = 0;
		this.active = true;
	}
	
	
	// IMPLEMENTED METHODS	-----------------------------------------------
	
	@Override
	public boolean isActive()
	{
		return this.active;
	}
	
	@Override
	public boolean isDead()
	{
		return wontBeDrawn();
	}
	
	@Override
	public void act()
	{
		// Handles the movement of the object
		move();
		rotate();
	}
	
	@Override
	public boolean kill()
	{
		// Kills the object and ends its drawing
		return endDrawing();
	}
	
	@Override
	public boolean inActivate()
	{
		this.active = false;
		return true;
	}
	
	@Override
	public boolean activate()
	{
		this.active = true;
		return true;
	}
	
	
	// GETTERS & SETTERS	-----------------------------------------------
	
	/**
	 * @return The speed with which the object is moving horizontally (pxl / step)
	 */
	public double getHspeed()
	{
		return this.hspeed;
	}
	
	/**
	 * @return The speed with which the object is moving vertically (pxl / step)
	 */
	public double getVspeed()
	{
		return this.vspeed;
	}
	
	/**
	 * @return The speed with which the object is moving z-axiswise (pxl / step)
	 */
	public double getZspeed()
	{
		return this.zspeed;
	}
	
	/**
	 * @return The speed with which the object is moving (pxl / step)
	 */
	public double getSpeed()
	{
		return Math.abs(this.hspeed) + Math.abs(this.vspeed) + Math.abs(this.zspeed);
	}
	
	/**
	 * 
	 * Changes the objects movement speed
	 *
	 * @param hspeed The new horizontal speed (pxl / step)
	 * @param vspeed The new vertical speed (pxl / step)
	 * @param zspeed The new z-axiswise speed (pxl / step)
	 */
	public void setVelocity(double hspeed, double vspeed, double zspeed)
	{
		this.hspeed = hspeed;
		this.vspeed = vspeed;
		this.zspeed = zspeed;
	}
	
	/**
	 * 
	 * Adds some horizontal and vertical motion to the object. The movement stacks 
	 * with the previous movement speed.
	 *
	 * @param haccelration How much speed is increased horizontally (pxl / step)
	 * @param vacceltarion How much speed is increased vertically (pxl / step)
	 * @param zaccelration How much speed is increased z-axiswise (pxl / step)
	 */
	public void addVelocity(double haccelration, double vacceltarion, double zaccelration)
	{
		this.hspeed += haccelration;
		this.vspeed += vacceltarion;
		this.zspeed += zaccelration;
	}
	
	/**
	 * @return How much the object is rotated around the z-axis at each step (degrees / step)
	 */
	public double getZRotation()
	{
		return this.zrotation;
	}
	
	/**
	 * @return How much the object is rotated around the x-axis at each step (degrees / step)
	 */
	public double getXRotation()
	{
		return this.xrotation;
	}
	
	/**
	 * @return How much the object is rotated around the y-axis at each step (degrees / step)
	 */
	public double getYRotation()
	{
		return this.yrotation;
	}
	
	/**
	 * 
	 * Changes how fast the object rotates around its origin
	 *
	 * @param xrotation The speed with which the object rotates around the x-axis (degrees / step)
	 * @param yrotation The speed with which the object rotates around the y-axis (degrees / step)
	 * @param zrotation The speed with which the object rotates around the z-axis (degrees / step)
	 */
	public void setRotation(double xrotation, double yrotation, double zrotation)
	{
		this.xrotation = xrotation;
		this.yrotation = yrotation;
		this.zrotation = zrotation;
	}
	
	/**
	 * @return How much the object's speed is reduced at each step (pxl / step)
	 */
	public double getFriction()
	{
		return this.friction;
	}
	
	/**
	 * 
	 * Changes how much the object's speed is reduced at each step
	 *
	 * @param friction the new friction of the object (pxl / step)
	 */
	public void setFriction(double friction)
	{
		this.friction = friction;
	}
	
	/**
	 * @return How much the rotation of the sprite is reduced at each step
	 */
	public double getRotationFriction()
	{
		return this.rotFriction;
	}
	
	/**
	 * Changes how much the rotation of the sprite is reduced at each step
	 * 
	 * @param rotationFriction How much the rotation is reduced at each step (degrees / step)
	 */
	public void setRotationFriction(double rotationFriction)
	{
		this.rotFriction = rotationFriction;
	}
	
	/**
	 * 
	 *Changes how much the object rotates at each step. The rotation accelration 
	 *stacks with the previous rotation speed.
	 *
	 * @param xraccelration How much faster will the object be rotated around the x-axis (degrees / step)
	 * @param yraccelration How much faster will the object be rotated around the y-axis (degrees / step)
	 * @param zraccelration How much faster will the object be rotated around the z-axis (degrees / step)
	 */
	public void addRotation(double xraccelration, double yraccelration, double zraccelration)
	{
		this.xrotation += xraccelration;
		this.yrotation += yraccelration;
		this.zrotation += zraccelration;
	}
	
	/**
	 * 
	 * Adds the object's movement towards the given direction
	 *
	 * @param direction Direction towards which the force is applied (degrees)
	 * @param force The amount of force applied to the object (pxl / step)
	 */
	public void addMotion2D(int direction, double force)
	{
		//double haccelration = Math.cos(Math.toRadians(direction))*force;
		double haccelration = HelpMath.lendirX(force, direction);
		//double vaccelration = Math.sin(Math.toRadians(direction))*force;
		double vaccelration = HelpMath.lendirY(force, direction);
		
		addVelocity(haccelration, vaccelration, 0);
	}
	
	/**
	 * 
	 * Adds the object's three dimensional movement towards the given directions
	 *
	 * @param xorzdirection Direction around the z or x-axis towards which the force is applied (degrees)
	 * @param ydirection Direction aroudn the y-axis towards which the force is applied (degrees)
	 * @param force The amount of force applied to the object (pxl / step)
	 */
	public void addMotion3D(int xorzdirection, int ydirection, double force)
	{
		int checkedydir = (int) HelpMath.checkDirection(ydirection);
		int checkedxzdir = (int) HelpMath.checkDirection(xorzdirection);
		
		double vaccelration = HelpMath.lendirY(force, checkedxzdir);
		double zxaccelration = HelpMath.lendirX(force, checkedxzdir);
		
		double haccelration = HelpMath.lendirX(zxaccelration, checkedydir);
		double zaccelration = HelpMath.lendirY(zxaccelration, checkedydir);
		
		addVelocity(haccelration, vaccelration, zaccelration);
		
		/*
		// = yaccelration
		double zaccelration = HelpMath.lendirY(force, checkedydir);
		// = zxaccelration
		double xyaccelration = HelpMath.lendirX(force, checkedydir);
		
		double haccelration = HelpMath.lendirX(xyaccelration, checkedxzdir);
		// = zaccelration
		double vaccelration = HelpMath.lendirY(xyaccelration, checkedxzdir);
		
		addVelocity(haccelration, vaccelration, zaccelration);
		*/
	}
	
	/**
	 * 
	 * Makes the object move towards given direction with given speed
	 *
	 * @param direction Towards what direction will the object move (degrees)
	 * @param speed How fast the objec will be moving (pxl / step)
	 */
	public void setMotion2D(int direction, double speed)
	{
		double newhspeed = HelpMath.lendirX(speed, direction);
		double newvspeed = HelpMath.lendirY(speed, direction);
		
		setVelocity(newhspeed, newvspeed, getZspeed());
	}
	
	/**
	 * 
	 * Makes the object move towards given directions with given speed
	 *
	 * @param xorzdirection The object's new moving direction around the z or x-axis (degrees)
	 * @param ydirection The object's new moving direction around the y-axis (degrees)
	 * @param speed How fast the objec will be moving (pxl / step)
	 */
	public void setMotion3D(int xorzdirection, int ydirection, double speed)
	{
		setVelocity(0, 0, 0);
		addMotion3D(xorzdirection, ydirection, speed);
		
		// TODO: Add checkdirection
	}
	
	/**
	 * 
	 * Changes the object's movement direction
	 *
	 * @param direction The object's new direction (degrees)
	 */
	public void setDirection2D(int direction)
	{
		setMotion2D(direction, getSpeed());
	}
	
	// GetDirections won't work!
	/**
	 * @return The direction around the z-axis towards which the object is currently moving
	 */
	public int getZDirection()
	{
		return (int) (Math.toDegrees(Math.atan2(getVspeed(), getHspeed())));
	}
	
	/**
	 * @return The direction around the x-axis towards which the object is currently moving
	 */
	public int getXDirection()
	{
		return (int) (Math.toDegrees(Math.atan2(getZspeed(), getVspeed())));
	}
	
	/**
	 * @return The direction around the y-axis towards which the object is currently moving
	 */
	public int getYDirection()
	{
		return (int) (Math.toDegrees(Math.atan2(getHspeed(), getZspeed())));
	}
	
	/**
	 * 
	 * Changes the objects moving speed, keeping the same direction
	 *
	 * @param speed The object's new speed (pxl / step)
	 */
	public void setSpeed2D(double speed)
	{
		setMotion2D(getZDirection(), speed);
	}
	
	/**
	 * Changes the objec't movement speed, keeping the same direction
	 * @param speed Object's new speed
	 * @param relative Is the speed added to the old speed (true) or absolute (false)
	 */
	public void setSpeed3D(double speed, boolean relative)
	{
		double oldspeed = getSpeed();
		
		// Can't divide by 0
		if (speed == 0)
			setVelocity(0, 0, 0);
		else
		{
			double multiplier = speed / oldspeed;
			
			if (relative)
				multiplier++;
			
			setVelocity(getHspeed()*multiplier, 
					getVspeed()*multiplier, getZspeed()*multiplier);
		}
		//setMotion3D(getZDirection(), getYDirection(), speed);
	}
	
	// OTHER METHODS	----------------------------------------------------
	
	// Moves the object and handles the friction
	private void move()
	{
		//this.x += getHspeed();
		//this.y += getVspeed();
		addPosition(getHspeed(), getVspeed(), getZspeed());
		
		// Checks the friction
		if (getFriction() == 0)
			return;
		
		implyFriction();
	}
	
	// Rotates teh object and handles the rotation friction
	private void rotate()
	{
		//this.angle += getRotation();
		addAngle(getXRotation(), getYRotation(), getZRotation());
		
		if (getRotationFriction() == 0)
			return;
		
		implyRotationFriction();
	}
	
	// Slows the speed the amount of given friction
	private void implyFriction()
	{	
		if (getSpeed() <= getFriction())
			setVelocity(0, 0, 0);
		else
			setSpeed3D(-getFriction(), true);
	}
	
	// Slows the rotation speed the amoutn of given friction
	private void implyRotationFriction()
	{	
		// MIKSI JAVASSA EI OLE FUNCTION POINTEREITA!?!
		if (Math.abs(getZRotation()) <= getRotationFriction())
			this.zrotation = 0;
		else if (getZRotation() > 0)
			this.zrotation -= getRotationFriction();
		else
			this.zrotation += getRotationFriction();
		
		if (Math.abs(getXRotation()) <= getRotationFriction())
			this.xrotation = 0;
		else if (getXRotation() > 0)
			this.xrotation -= getRotationFriction();
		else
			this.xrotation += getRotationFriction();
		
		if (Math.abs(getYRotation()) <= getRotationFriction())
			this.yrotation = 0;
		else if (getYRotation() > 0)
			this.yrotation -= getRotationFriction();
		else
			this.yrotation += getRotationFriction();
	}

}

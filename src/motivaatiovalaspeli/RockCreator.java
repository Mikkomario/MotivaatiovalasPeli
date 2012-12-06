package motivaatiovalaspeli;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class generates rocks to the canyon with certain patterns.
 *
 * @author Gandalf.
 *         Created 5.12.2012.
 */
public class RockCreator implements Actor
{
	// ATTRIBUTES	----------------------------------------------------
	
	private static Random rand = new Random();
	
	private int maxRocks, minRocks, delay, minDelay, maxDelay, width, height, 
		creationZ, maxZ;
	private boolean active, alive;
	
	private DrawableHandler rockDrawer;
	private Scroller rockScroller;
	
	
	// CONSTRUCTOR	----------------------------------------------------
	
	/**
	 * Creates a new rock creator with the given information. The creator starts 
	 * creating rocks right away.
	 *
	 * @param minRocks At least how many rocks are created after each delay
	 * @param maxRocks How many rocks are created after each delay at maximum
	 * @param minDelay How long is the smallest delay possible (steps)
	 * @param maxDelay How ling is the longest delay possible (steps)
	 * @param width To how wide an area are the rocks positioned (Pxl)
	 * @param height To how high an area are the rocks positioned (Pxl)
	 * @param z What z-coordinate will the new rocks have.
	 * @param maxZ How far can the rocks be scrolled before they disappear
	 * @param rockDrawer The drawablehandler who takes care of draing the rocks
	 * @param rockScroller The scrolled in charge of moving / scrolling the created rocks
	 */
	public RockCreator(int minRocks, int maxRocks, int minDelay, int maxDelay, 
			int width, int height, int z, int maxZ, DrawableHandler rockDrawer, 
			Scroller rockScroller)
	{
		// Initializes attributes
		this.minRocks = minRocks;
		this.maxRocks = maxRocks;
		this.minDelay = minDelay;
		this.maxDelay = maxDelay;
		this.width = width;
		this.height = height;
		this.creationZ = z;
		this.rockDrawer = rockDrawer;
		this.maxZ = maxZ;
		this.rockScroller = rockScroller;
		
		this.active = true;
		this.alive = true;
		this.delay = 1;
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public boolean isActive()
	{
		return this.active;
	}

	@Override
	public boolean isDead()
	{
		return !this.alive;
	}

	@Override
	public void act()
	{
		// Checks if a new wave of rocks should be created
		this.delay--;
		
		if (this.delay <= 0)
		{
			this.delay = this.minDelay + rand.nextInt(this.maxDelay + 1 - this.minDelay);
			generateRocks();
		}
	}

	@Override
	public boolean kill()
	{
		this.alive = false;
		return true;
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
	
	
	// OTHER METHODS	-------------------------------------------------
	
	// Creates a bunch of rocks to the screen
	private void generateRocks()
	{
		int minX, maxX, minY, maxY;
		ArrayList<int[]> usedpositions = new ArrayList<int[]>();
		int rocknumber = this.minRocks + rand.nextInt(this.maxRocks + 1 - this.minRocks);
		
		for (int i = 0; i < rocknumber; i++)
		{
			// Determines the wall to which the rock is created
			// Determining the maximum coordinates
			double randnumber = rand.nextDouble();
			
			// Case left wall
			if (randnumber < 0.33)
			{
				minX = 0;
				maxX = 32;
				minY = 0;
				maxY = this.height;
			}
			// Case right wall
			else if (randnumber < 0.66)
			{
				minX = this.width - 32;
				maxX = this.width;
				minY = 0;
				maxY = this.height;
			}
			// Case bottom
			else
			{
				minX = 0;
				maxX = this.width;
				minY = this.height -32;
				maxY = this.height;
			}
			
			// Gets the new position
			int newx = minX + rand.nextInt(maxX + 1 - minX);
			int newy = minY + rand.nextInt(maxY + 1 - minY);
			
			// Calculates the increasement direction (towards wich the rock is moved 
			// if it doesn't fit)
			int incdir = (int) HelpMath.checkDirection(HelpMath.pointDirection(
					newx, newy, this.width/2,  this.height/2)
					//+ rand.nextInt(45) - 22
					);
			//incdir = 0;
			
			// Tests if the position is free
			boolean fits = true;
			// Rock is tried to reposition 10 times
			int tries = 0;
			
			do
			{
				fits = true;
				
				for (int posi = 0; posi < usedpositions.size(); posi++)
				{
					int otherx = usedpositions.get(posi)[0];
					int othery = usedpositions.get(posi)[1];
					
					if (HelpMath.pointDistance(newx, newy, otherx, othery) < 150)
					{
						fits = false;
						break;
					}
				}
				
				// Gets new coordinates if the older ones were occupied
				if (!fits)
				{
					//int oldincdir = incdir;
					//int dist1 = HelpMath.pointDistance(newx, newy, this.width/2, this.height/2);
					newx += HelpMath.lendirX(50, incdir);
					newy += HelpMath.lendirY(50, incdir);
					//int dist2 = HelpMath.pointDistance(newx, newy, this.width/2, this.height/2);
					//System.out.println("Moving: " + dist1 + " -> " + dist2);
					//if (newx < this.width / 2)
					//	System.out.println(incdir);
					//System.out.println(incdir - oldincdir);
				}
					
				tries++;
			}
			while (!fits && tries < 10);
			
			//if (newx < this.width / 2)
			//	System.out.println(incdir);
			
			// Finally creates the rock and places it
			Rock newrock = new Rock(newx, newy, this.creationZ, this.creationZ, this.maxZ);
			int[] newpos = {newx, newy};
			usedpositions.add(newpos);
			this.rockDrawer.addDrawable(newrock);
			this.rockScroller.addScrollable(newrock);
			/*
			if (newx < 0)
			{
				double transx = HelpMath.lendirX(100, incdir); 
				double transy = HelpMath.lendirY(100, incdir);
				System.out.println(incdir + " -> " + transx + ", " + transy);
				//	System.out.println("ERROR: " + newx + ", " + newy);
			}
			*/
		}
	}
}

package creators;

import scrolling.Scroller;
import sprites.SpriteBank;
import handlers.DrawableHandler;
import model.Canyon;

/**
 * Creates a bunch of canyons at the start of the program
 *
 * @author Gandalf.
 *         Created 13.12.2012.
 */
public class CanyonCreator
{	
	/**
	 * 
	 * Fills the give area with canyons
	 *
	 * @param width How wide the canyons are
	 * @param height How high the canyons are
	 * @param minZ What is the smallest z the canyons will have
	 * @param maxZ What is the largest z the canyons will have
	 * @param canyonNumber How many canyons there will be
	 * @param scroller Which object scrolls the canyons
	 * @param drawer Which object draws the canyons
	 * @param spritebank Where can canyon graphics be found
	 */
	public static void createCanyons(int width, int height, int minZ,
			int maxZ, int canyonNumber, Scroller scroller, 
			DrawableHandler drawer, SpriteBank spritebank)
	{
		int canyonDepth = (maxZ - minZ)/canyonNumber;
		int z = maxZ;
		
		// Creates canyons and adds them to handlers
		while (z > minZ)
		{
			Canyon c = new Canyon(width, height, canyonDepth, z, minZ, maxZ, 
					spritebank);
			scroller.addScrollable(c);
			drawer.addDrawable(c);
			
			z -= canyonDepth;
		}
	}
	
	/*
	 * // Creates the canyon and adds it to the drawables handled
		//Canyon testcanyon = new Canyon(this.width, this.height, 1000, 100, 
				//-900, 1100, this.sprtbank);
		Canyon testcanyon = new Canyon(this.width, this.height, 1000, 100, 
				-900, 1100, this.sprtbank);
		this.mainDrawer = new DrawableHandler(false);
		this.mainDrawer.addDrawable(testcanyon);
		//Canyon testcanyon2 = new Canyon(this.width, this.height, 1000, -900, -900, 1100, this.sprtbank);
		Canyon testcanyon2 = new Canyon(this.width, this.height, 1000, -900, 
				-900, 1100, this.sprtbank);
		this.mainDrawer.addDrawable(testcanyon2);
		this.playerscroller.addScrollable(testcanyon);
		this.playerscroller.addScrollable(testcanyon2);
	 */
}

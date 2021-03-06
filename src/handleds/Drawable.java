package handleds;

import motivaatiovalaspeli.MotivaatiovalasPeli;

/**
 * All objects which implement this class are drawn at some point
 *
 * @author Gandalf.
 *         Created 26.11.2012.
 */
public interface Drawable extends Handled
{
	// TODO: Add an superinterface that can be used in any handler!
	
	/**
	 *	Draws the object to the applet
	 *
	 *@param applet The applet to which the object is drawn
	 */
	public void drawSelf(MotivaatiovalasPeli applet);
	
	/**
	 * @return Should the object be drawn at this time
	 */
	public boolean isVisible();
	
	/**
	 * 
	 * Tries to set the object visible
	 *
	 * @return Was the object made visible
	 */
	public boolean setVisible();
	
	/**
	 * 
	 * Tries to momentarily make the object invisible
	 *
	 * @return Was the object made invisible
	 */
	public boolean setInvisible();
}

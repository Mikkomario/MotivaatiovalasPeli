package scrolling;

/**
 * This scroller follows an object, returning it to the 0 z. Also scrolls other 
 * objects so that they appear on the same distance from that object as before.
 *
 * @author Gandalf.
 *         Created 3.12.2012.
 */
public class FollowingScroller extends Scroller
{
	// ATTRIBUTES	------------------------------------------------------
	
	private Scrollable followed;
	
	
	// CONSTRUCTOR	------------------------------------------------------

	/**
	 * Creates a new FollowingScroller that starts following the given object. 
	 * The followed object should never be null as it causes exceptions.
	 *
	 * @param followedObject The object which is always returned to 0 z
	 */
	public FollowingScroller(Scrollable followedObject)
	{
		super(0);
		
		this.followed = followedObject;
		addScrollable(this.followed);
	}
	
	
	// IMPLEMENTED METHODS	---------------------------------------------
	
	@Override
	public void act()
	{
		// Always resets the followed object to 0 z
		setSpeed(-getFollowed().getZ());
		super.act();
	}
	
	
	// GETTERS & SETTERS	---------------------------------------------
	
	/**
	 * @return The object which defines how much elements are scrolled
	 */
	public Scrollable getFollowed()
	{
		return this.followed;
	}
	
	/**
	 * Changes the followed object
	 * @param s The object which defines how much elements are scrolled
	 */
	public void setFollowed(Scrollable s)
	{
		if (s == null)
			return;
		
		this.followed = s;
		addScrollable(this.followed);
	}

}

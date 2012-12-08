package motivaatiovalaspeli;

/**
 * Each object implementing this interface will be considered an active creature 
 * that needs to perform its own actions during each step. This acting is 
 * done separately from the drawing. 
 * 
 * The actors often won't be acting indefinitely and each actor can tell whether 
 * it will be still acting or not. Actors can also stop acting momentarily.
 * 
 * Each actor should also be able to respond to a try to end its existence
 *
 * @author Gandalf.
 *         Created 27.11.2012.
 */
public interface Actor extends Handled
{
	/**
	 * @return Does the actor currently want to perform any actions 
	 */
	public boolean isActive();
	
	/**
	 * This is the actors action, which will be called frequently
	 */
	public void act();
	
	/**
	 * This method tries to make the actor inactive
	 *
	 * @return Was the actor made inactive
	 */
	public boolean inActivate();
	
	/**
	 * This method tries to reactivate the actor
	 *
	 * @return Was the actor reactivated
	 */
	public boolean activate();
}

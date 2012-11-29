package motivaatiovalaspeli;

/**
 * Keylisteners are interested in the user's activities on the keyboard and should 
 * be notified when a key is activated
 *
 * @author Gandalf.
 *         Created 28.11.2012.
 */
public interface KeyListener
{
	/**
	 * This method is called at each step when a key is down
	 *
	 * @param key The key that is currently pressed
	 * @param keyCode The key's keycode (used for some keys)
	 */
	public void onKeyPressed(int key, int keyCode);
	
	/**
	 * This method is called when a key is released
	 *
	 * @param key The key that is currently pressed
	 * @param keyCode The key's keycode (used for some keys)
	 */
	public void onKeyReleased(int key, int keyCode);
	
	/**
	 * This method is called when the key is typed
	 *
	 * @param key The key that is currently pressed
	 * @param keyCode The key's keycode (used for some keys)
	 */
	public void onKeyTyped(int key, int keyCode);
}

package motivaatiovalaspeli;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class unites the actor and keyListening interfaces so that keyevents are 
 * only called once in a step.
 *
 * @author Gandalf.
 *         Created 2.12.2012.
 */
public class KeyListenerHandler implements Actor
{
	// ATTRIBUTES	------------------------------------------------------
	
	private HashMap<Integer, Boolean> keysDown;
	private HashMap<Integer, Boolean> codesDown;
	private ArrayList<Integer> keysPressed;
	private ArrayList<Integer> codesPressed;
	private ArrayList<Integer> keysReleased;
	private ArrayList<Integer> codesReleased;
	private ArrayList<KeyListener> listeners;
	
	
	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * 
	 * Simply creates a new KeyListenerHandler.
	 *
	 */
	public KeyListenerHandler()
	{
		// Initializes the attributes
		this.keysDown = new HashMap<Integer, Boolean>();
		this.codesDown = new HashMap<Integer, Boolean>();
		this.keysPressed = new ArrayList<Integer>();
		this.keysReleased = new ArrayList<Integer>();
		this.codesPressed = new ArrayList<Integer>();
		this.codesReleased = new ArrayList<Integer>();
		this.listeners = new ArrayList<KeyListener>();
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------
	
	@Override
	public boolean isActive()
	{
		// KeyListenerHandler is always active
		return true;
	}

	@Override
	public boolean isDead()
	{
		// KeylistenerHandler never dies
		return false;
	}

	@Override
	public void act()
	{
		// Informs all listeners of the last changes
		for (int i = 0; i < this.listeners.size(); i++)
		{
			KeyListener listener = this.listeners.get(i);
			
			// Informs if a key was pressed
			for (int ik = 0; ik < this.keysPressed.size(); ik++)
			{
				listener.onKeyPressed(this.keysPressed.get(i), 0, false);
			}
			
			// Informs if a coded key was pressed
			for (int ik = 0; ik < this.codesPressed.size(); ik++)
			{
				listener.onKeyPressed(0, this.codesPressed.get(i), true);
			}
			
			// Informs if a key is down
			for (int key: this.keysDown.keySet())
			{
				if (this.keysDown.get(key))
					listener.onKeyDown(key, 0, false);
			}
			
			// Informs if a coded key is down
			for (int code: this.codesDown.keySet())
			{
				if (this.codesDown.get(code))
					listener.onKeyDown(0, code, true);
			}
		}
		
	}

	@Override
	public boolean kill()
	{
		// KeyListenerHandler can't be killed
		return false;
	}

	@Override
	public boolean inActivate()
	{
		// KeyListenerHandler can't be inactivated
		return false;
	}

	@Override
	public boolean activate()
	{
		// KeyListener is always active
		return true;
	}
	
	
	// OTHER METHODS	--------------------------------------------------
	
	/**
	 * 
	 * This method should be called at each keyPressed -event
	 *
	 * @param key The key that was pressed
	 * @param code The key's keycode
	 * @param coded Does the key use it's keycode
	 */
	public void onKeyPressed(int key, int code, boolean coded)
	{
		if (coded)
		{
			// Checks whether the key was just pressed instead of being already down
			if (!this.codesDown.containsKey(code) || !this.codesDown.get(code))
				// If so, marks the key as pressed
				if (!this.codesPressed.contains(code))
					this.codesPressed.add(code);
			
			// Sets the key down
			this.codesDown.put(code, true);
		}
		else
		{
			if (!this.keysDown.containsKey(key) || this.keysDown.get(key))
				if (!this.keysPressed.contains(key))
					this.keysPressed.add(key);
			this.keysDown.put(key, true);
		}
	}
	
	/**
	 * 
	 * This method should be called at each keyReleased -event
	 *
	 * @param key The key that was pressed
	 * @param code The key's keycode
	 * @param coded Does the key use it's keycode
	 */
	public void onKeyReleased(int key, int code, boolean coded)
	{
		if (coded)
		{
			// Marks the key as released
			if (!this.codesReleased.contains(code))
				this.codesReleased.add(code);
			
			// Sets the key up
			this.codesDown.put(code, false);
		}
		else
		{
			if (!this.keysReleased.contains(key))
				this.keysReleased.add(key);
			this.keysDown.put(key, false);
		}
	}
	
	/**
	 * 
	 * Adds a new keylistener to the informed keylistners
	 *
	 * @param k The KeyListener to be added
	 */
	public void addListener(KeyListener k)
	{
		if (!this.listeners.contains(k))
			this.listeners.add(k);
	}
	
	/**
	 * Removes a keyListener from the informed listeners 
	 *
	 * @param k The KeyListener to be removed
	 */
	public void removeListener(KeyListener k)
	{
		if (this.listeners.contains(k))
			this.listeners.remove(k);
	}

}
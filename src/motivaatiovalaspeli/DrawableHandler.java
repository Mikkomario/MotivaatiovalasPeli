package motivaatiovalaspeli;

import java.util.ArrayList;

/**
 * The object from this class will draw multiple drawables, calling their 
 * drawSelf-methods and removing them when necessary
 *
 * @author Gandalf.
 *         Created 27.11.2012.
 */
public class DrawableHandler implements Drawable
{
	// ATTRIBUTES	------------------------------------------------------
	
	private ArrayList<Drawable> drawables;
	private boolean autodeath;
	private boolean killed;
	
	
	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * This is the recommended version of the constructor.<br>
	 *This method creates a new DrawableHandler that will be able to take care of 
	 *new Drawables. An initialized DrawableHandler should contain at least one drawable 
	 *before its wontBeDrawn method is called. Other drawables can be added later
	 *
	 *@param initialDrawables The Drawables added to the handler
	 *@param autodeath Will the handler die when it runs out of living drawables
	 */
	@SuppressWarnings("unchecked")
	public DrawableHandler(ArrayList<Drawable> initialDrawables, boolean autodeath)
	{
		// Initializes the attributes
		if (initialDrawables != null)
			this.drawables = (ArrayList<Drawable>) initialDrawables.clone();
		this.autodeath = autodeath;
		this.killed = false;
		
		// Checks the parameeter(s)
		if (this.drawables == null)
			this.drawables = new ArrayList<Drawable>();
	}
	
	/**
	 * This is an alternate version of the constructor.<br>
	 *This method creates a new DrawableHandler that will be able to take care of 
	 *new Drawables. An initialized DrawableHandler should contain at least one drawable 
	 *before its WontBeDrawn method is called. Other drawables can be added later
	 *
	 *@param initialDrawables The Drawables added to the handler
	 *@param autodeath Will the handler die when it runs out of living drawables
	 */
	public DrawableHandler(Drawable[] initialDrawables, boolean autodeath)
	{
		this.drawables = new ArrayList<Drawable>();
		this.autodeath = autodeath;
		this.killed = false;
		
		// Initializes the attributes
		if (initialDrawables != null)
		{
			for (int i = 0; i < initialDrawables.length; i++)
			{
				this.drawables.add(initialDrawables[i]);
			}
		}
	}
	
	/**
	 * 
	 * An alternate version of the constructor (good for adding singular drawables). 
	 * This creates a new DrawableHandler that starts to handle the drawable. 
	 * The DrawableHandler dies when all it's subdrawables are dead.
	 *
	 * @param initialDrawable The Drawable the handler will be taking care of
	 * @param autodeath Will the handler die when it runs out of living drawables
	 */
	public DrawableHandler(Drawable initialDrawable, boolean autodeath)
	{
		this.autodeath = autodeath;
		this.killed = false;
		this.drawables = new ArrayList<Drawable>();
		if (initialDrawable != null)
			addDrawable(initialDrawable);
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------
	
	@Override
	public void drawSelf(MotivaatiovalasPeli applet)
	{
		// This calls for all active actor's act method
		for (int i = 0; i < this.drawables.size(); i++)
		{
			if (this.drawables.get(i).isVisible())
				this.drawables.get(i).drawSelf(applet);
		}
	}

	@Override
	public boolean isVisible()
	{
		// Returns false only if all the actors are inactive
		for (int i = 0; i < this.drawables.size(); i++)
		{
			if (this.drawables.get(i).isVisible())
				return true;
		}
		
		return false;
	}

	@Override
	public boolean wontBeDrawn()
	{
		// Removes the unnecessary dead actors and returns if alive actors still
		// exist
		removeNonDrawnDrawables();
		return this.killed || (this.drawables.isEmpty() && this.autodeath);
	}
	
	@Override
	public boolean endDrawing()
	{
		// tries to set all the drawables permanently invisible, returns false
		// if all the drawables couldn't be made invisible
		boolean returnValue = true;
		
		for (int i = 0; i < this.drawables.size(); i++)
		{
			if (!this.drawables.get(i).endDrawing())
				returnValue = false;
		}
		
		// Erases the memory and kills the handler if all the drawables were ended
		if (returnValue)
		{
			this.drawables.clear();
			this.killed = true;
		}
		
		return returnValue;
	}

	@Override
	public boolean setVisible()
	{
		// tries to set all the drawables visible, returns false if all the drawables
		// couldn't be made visible
		boolean returnValue = true;
		
		for (int i = 0; i < this.drawables.size(); i++)
		{
			if (!this.drawables.get(i).setVisible())
				returnValue = false;
		}
		
		return returnValue;
	}

	@Override
	public boolean setInvisible()
	{
		// tries to set all the drawables invisible, returns false if all the drawables
		// couldn't be made invisible
		boolean returnValue = true;
		
		for (int i = 0; i < this.drawables.size(); i++)
		{
			if (!this.drawables.get(i).setInvisible())
				returnValue = false;
		}
		
		return returnValue;
	}
	
	
	// OTHER METHODS	---------------------------------------------------

	// Removes all the dead actors from the list of actors to save processing
	// time
	private void removeNonDrawnDrawables()
	{
		for (int i = 0; i < this.drawables.size(); i++)
		{	
			if (this.drawables.get(i).wontBeDrawn())
				this.drawables.remove(i);
		}
	}
	
	/**
	 *Adds the given actor to the handled actors
	 *
	 * @param d The actor to be added
	 */
	public void addDrawable(Drawable d)
	{
		if (d != null && !this.drawables.contains(d))
			this.drawables.add(d);
	}
	
	/**
	 * @return How many drawables the handler is currently handling
	 */
	protected int getDrawableNumber()
	{
		return this.drawables.size();
	}
	
	/**
	 * 
	 * Returns a single drawable from handler's current drawables
	 * This should only be used in possible subclasses for further drawable handling
	 *
	 * @param index The index of the drawable in handler's current drawables
	 * @return A drawable from the given index or null if no such index exists
	 */
	protected Drawable getDrawable(int index)
	{
		if (index >= 0 && index < getDrawableNumber())
			return this.drawables.get(index);
		
		return null;
	}
}

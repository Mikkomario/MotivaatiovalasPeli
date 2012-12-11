package sprites;

import java.io.FileNotFoundException;
import java.util.HashMap;

import processing.core.PApplet;

/**
 * This class creates a group of sprites used in a project and gives them to the 
 * objects using them as needed.
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public class SpriteBank
{
	// ATTRIBUTES	---------------------------------------------------------
	
	private HashMap<String, Sprite> sprites;
	private PApplet applet;
	
	
	// CONSTRUCTOR	---------------------------------------------------------
	
	/**
	 * 
	 * Creates a new spritebank and load all the sprites needed in it
	 * 
	 * @param applet The applet with which the images are loaded
	 */
	public SpriteBank(PApplet applet)
	{
		this.applet = applet;
		this.sprites = new HashMap<String, Sprite>();
		createSprites();
	}
	
	
	// OTHER METHODS	-----------------------------------------------------
	
	private void createSprites()
	{
		try
		{
			// TODO: Add some other sprites to the bank
			Sprite seagrassSprite = new Sprite("testplant.png", 1, 60, 200, 
					"seagrass", this.applet);
			this.sprites.put(seagrassSprite.getName(), seagrassSprite);
		}
		catch(FileNotFoundException fnfe)
		{
			System.err.println("All of the sprites could not be loaded!");
		}
	}
	
	/**
	 * 
	 * Returns a sprite from the spritebank
	 *
	 * @param spriteName The name of the sprite looked
	 * @return The sprite with the given name
	 */
	public Sprite getSprite(String spriteName)
	{
		if (this.sprites.containsKey(spriteName))
			return this.sprites.get(spriteName);
		else
			return null;
	}
}

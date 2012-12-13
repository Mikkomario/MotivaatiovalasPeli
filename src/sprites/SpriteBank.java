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
            Sprite seagrassSprite = new Sprite("seaweed.png", 1, 25, 100, 
                    "seagrass", this.applet);
            this.sprites.put(seagrassSprite.getName(), seagrassSprite);

            Sprite bottomSprite = new Sprite("bottom.png", 1, 0, 0,
                    "bottom", this.applet);
            this.sprites.put(bottomSprite.getName(), bottomSprite);
            
            Sprite canyonSprite = new Sprite("canyonwall.png", 1, 0, 0,
                    "canyon", this.applet);
            this.sprites.put(canyonSprite.getName(), canyonSprite);
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

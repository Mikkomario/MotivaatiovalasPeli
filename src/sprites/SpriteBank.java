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
            
            Sprite healthSprite = new Sprite("health_strip6.png", 6, 25, 25,
                    "health", this.applet);
            this.sprites.put(healthSprite.getName(), healthSprite);
            
            Sprite winningSprite = new Sprite("voitto.png", 1, 320, 240, 
            		"victory", this.applet);
            this.sprites.put(winningSprite.getName(), winningSprite);
            
            Sprite losingSprite = new Sprite("havio.png", 1, 320, 240, 
            		"loss", this.applet);
            this.sprites.put(losingSprite.getName(), losingSprite);
            
            Sprite kssSprite = new Sprite("KSS.png", 1, 320, 240, 
            		"kss", this.applet);
            this.sprites.put(kssSprite.getName(), kssSprite);
            
            Sprite beginningSprite = new Sprite("aloitus.png", 1, 320, 240, 
            		"beginning", this.applet);
            this.sprites.put(beginningSprite.getName(), beginningSprite);
            
            Sprite pauseSprite = new Sprite("pause.png", 1, 320, 240,
            		"pause", this.applet);
            this.sprites.put(pauseSprite.getName(), pauseSprite);
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
        {
        	System.out.println("Tried to get a sprite that is not in the bank");
            return null;
        }
    }
}

package creators;

import java.awt.Point;
import java.util.Random;

import model.Kuha;
import motivaatiovalaspeli.HelpMath;

import handlers.DrawableHandler;
import scrolling.Scroller;

/**
 * Creates kuhas to the field creating lines of kuhas
 *
 * @author Gandalf.
 *         Created 9.12.2012.
 */
public class KuhaCreator extends ObjectCreator
{
	// ATTRIBUTES	------------------------------------------------------
	
	private static Random rand = new Random();
	
	private int streamlength;
	private Point lastPoint;
	
	
	// CONSTRCTOR
	
	/**
	 * Creates a new kuhacreator that starts creating kuhas
	 *
	 * @param minDelay How short is the smallest delay between creations
	 * @param maxDelay How long is the longest delay between creations
	 * @param width How wide is the area of creation
	 * @param height How high is the area of creation
	 * @param z What z will the created kuhas have
	 * @param maxZ How close can the created kuhas come before they are destroyed
	 * @param objectDrawer What handler will draw kuhas
	 * @param objectScroller What scroller will scroll kuhas
	 */
	public KuhaCreator(int minDelay, int maxDelay, int width, int height,
			int z, int maxZ, DrawableHandler objectDrawer,
			Scroller objectScroller)
	{
		super(minDelay, maxDelay, width, height, z, maxZ, objectDrawer, objectScroller);
		
		// Initializes attributes
		this.lastPoint = new Point(rand.nextInt(width), 
				rand.nextInt(height));
		this.streamlength = getRandomStreamlength();
	}

	@Override
	public void createObject(int fieldWidth, int fieldHeight, int creationZ,
			int maxZ)
	{
		// Creates a new kuha to the calculated position
		Kuha newKuha = new Kuha(this.lastPoint.x, this.lastPoint.y, creationZ, maxZ);
		getDrawableHandler().addDrawable(newKuha);
		getScroller().addScrollable(newKuha);
		
		this.streamlength--;
		
		// Changes the position and remaining delay depending on the streamlength
		if (this.streamlength > 0)
		{
			this.lastPoint = getNewPoint(this.lastPoint, fieldWidth, fieldHeight);
			setDelay(getRandomDelay(5, 10));
		}
		else
		{
			this.streamlength = getRandomStreamlength();
			this.lastPoint = new Point(rand.nextInt(fieldWidth), rand.nextInt(fieldHeight));
		}
	}
	
	
	// OTHER METHODS	-------------------------------------------------
	
	private int getRandomStreamlength()
	{
		double randnumber = rand.nextDouble();
		
		// 50% chance it's 1
		if (randnumber < 0.5)
			return 1;
		// 35% chance it's 3-6
		else if (randnumber < 0.85)
			return rand.nextInt(4) + 3;
		// 15% chance it's 9-12
		else
			return rand.nextInt(4) + 9;
	}
	
	private Point getNewPoint(Point lastPoint, int maxWidth, int maxHeight)
	{
		Point newPoint;
		
		do
		{
			newPoint = new Point(lastPoint.x + rand.nextInt(200) - 100,
					lastPoint.y + rand.nextInt(200) - 100);
		}
		while (!HelpMath.pointIsInRange(newPoint, 0, maxWidth, 0, maxHeight));
		
		return newPoint;
	}
}

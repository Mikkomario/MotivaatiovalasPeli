package motivaatiovalaspeli;

/**
 * This class only handles some mathematical problems
 *
 * @author Gandalf.
 *         Created 28.11.2012.
 */
public class HelpMath
{
	// TODO: Test this class
	
	/**
	 * 
	 * Calculates the direction from one point to another (in radians)
	 *
	 * @param x1 the first point's x coordinate
	 * @param y1 the first point's y coordinate
	 * @param x2 the second point's x coordinate
	 * @param y2 the second point's y coordinate
	 * @return the direction from point 1 to point 2 in degrees
	 */
	public static int pointDirection(int x1, int y1, int x2, int y2)
	{
		double xdist = x2 - x1;
		double ydist = y2 - y1;
		return (int) (Math.toDegrees(Math.atan2(ydist, xdist)));
	}
	
	/**
	 * 
	 * Calculates a distance between two points.
	 *
	 * @param x1 First point's x coordinate
	 * @param y1 First point's y coordinate
	 * @param x2 Second point's x coordinate
	 * @param y2 Second point's y coordinate
	 * @return Distance between points in pixels
	 */
	public static int pointDistance(int x1, int y1, int x2, int y2)
	{
		double a = x1 - x2;
		double b = y1 - y2;
		
		return (int) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}
	
	/**
	 * 
	 * Returns the x-coordinate of a point that is length pixels away to direction 
	 * angle from the origin
	 *
	 * @param length How far from the origin the point is (pxl)
	 * @param direction To what direction from the origin the point is (degrees)
	 * @return The point's x-coordinate
	 */
	public static double lendirX(double length, int direction)
	{
		return (int) (Math.cos(Math.toRadians(direction))*length);
	}
	
	/**
	 * 
	 * Returns the y-coordinate of a point that is length pixels away to direction 
	 * angle from the origin
	 *
	 * @param length How far from the origin the point is (pxl)
	 * @param direction To what direction from the origin the point is (degrees)
	 * @return The point's y-coordinate
	 */
	public static double lendirY(double length, int direction)
	{
		return (int) (Math.sin(Math.toRadians(direction))*length);
	}
}

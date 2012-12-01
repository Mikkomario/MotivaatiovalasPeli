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
	 * Calculates the direction from one point to another (in degrees)
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
	 * Calculates the direction from one point to another around the x-axis (in degrees)
	 *
	 * @param y1 the first point's y coordinate
	 * @param z1 the first point's z coordinate
	 * @param y2 the second point's y coordinate
	 * @param z2 the second point's z coordinate
	 * @return the direction from point 1 to point 2 in degrees around the x-axis
	 * **/
	public static int pointXDirection(int y1, int z1, int y2, int z2)
	{
		return HelpMath.pointDirection(y1, z1, y2, z2);
	}
	
	/**
	 * 
	 * Calculates the direction from one point to another around the Y-axis (in degrees)
	 *
	 * @param z1 the first point's z coordinate
	 * @param x1 the first point's x coordinate
	 * @param z2 the second point's z coordinate
	 * @param x2 the second point's x coordinate
	 * @return the direction from point 1 to point 2 in degrees around the y-axis
	**/
	public static int PointYDirection(int z1, int x1, int z2, int x2)
	{
		return HelpMath.pointDirection(z1, x1, z2, x2);
	}
	
	/**
	 * 
	 * Calculates the direction from one point to another (in degrees) around z-axis
	 *
	 * @param x1 the first point's x coordinate
	 * @param y1 the first point's y coordinate
	 * @param x2 the second point's x coordinate
	 * @param y2 the second point's y coordinate
	 * @return the direction from point 1 to point 2 in degrees around z-axis
	**/
	public static int PointZDirection(int x1, int y1, int x2, int y2)
	{
		return HelpMath.pointDirection(x1, y1, x2, y2);
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
	 * Calculates a distance between two points in three dimensions
	 *
	 * @param x1 First point's x coordinate
	 * @param y1 First point's y coordinate
	 * @param z1 First point's z coordinate
	 * @param x2 Second point's x coordinate
	 * @param y2 Second point's y coordinate
	 * @param z2 Second point's z coordinate
	 * @return Distance between points in pixels
	 */
	public static int pointDistance(int x1, int y1, int z1, int x2, int y2, int z2)
	{
		double deltax = x1 - x2;
		double deltay = y1 - y2;
		double deltaz = z1 - z2;
		
		double xydist = Math.sqrt(Math.pow(deltax, 2) + Math.pow(deltay, 2));
		double xyzdist = Math.sqrt(Math.pow(xydist, 2) + Math.pow(deltaz, 2));
		
		return (int) xyzdist;
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

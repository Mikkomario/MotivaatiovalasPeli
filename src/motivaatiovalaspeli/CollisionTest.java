package motivaatiovalaspeli;

/**
 * Tests DrawnObject3D's collision checking and point negating
 *
 * @author Gandalf.
 *         Created 6.12.2012.
 */
public class CollisionTest
{
	private static void test(boolean condition, String testname)
	{
		System.out.println("Testing " + testname + ":");
		
		if (condition)
			System.out.println("OK!");
		else
			System.out.println("Does not work!");
	}
	
	/**
	 * Testing is done in the main method
	 * 
	 * @param Args Nothing important
	 */
	public static void main(String[] Args)
	{
		Rock testedrock = new Rock(0, 0, 0, 0, 0);
		testedrock.setAngle(0, 0, 0);
		testedrock.setScale(1, 1, 1);
		
		test(testedrock.negateTransformations2D(32, 0).getX() == 64, 
				"Origin position untransformed");
		
		//System.out.println(testedrock.negateTransformations2D(0, 0));
		//System.out.println(testedrock.getZAngle());
		
		//System.out.println(testedrock.negateTransformations2D(-32, 0));
		testedrock.setScale(2, 2, 1);
		test(testedrock.negateTransformations2D(32, 32).getX() > 46 &&
				testedrock.negateTransformations2D(32, 32).getX() < 49 , "xscale");
		System.out.println(testedrock.negateTransformations2D(32, 32));
		
		testedrock.setScale(2, 2, 1);
		test(testedrock.negateTransformations2D(32, 32).getY() == 47, "yscale");
		//System.out.println(testedrock.negateTransformations2D(32, 32));
		
		test(HelpMath.lendirX(100, 90) < 0.01, "LendiX");
		//System.out.println(HelpMath.lendirX(100, 90));
		
		testedrock.setScale(1, 1, 1);
		testedrock.setAngle(0, 0, 45);
		int testposx = (int) HelpMath.lendirX(32, 45);
		int testposy = (int) HelpMath.lendirY(32, 45);
		test(testedrock.negateTransformations2D(testposx, testposy).getX() == 63, "rotate");
		//System.out.println(testposx);
		//System.out.println(testposy);
		//System.out.println(testedrock.negateTransformations2D(testposx, testposy));
		//System.out.println(testedrock.getXscale());
		//System.out.println(testedrock.getZAngle());
		//System.out.println(testedrock.negateTransformations2D(45, 45));
		//System.out.println(HelpMath.pointDistance(0, 0, 45, 45));
		//System.out.println(HelpMath.pointDistance(32, 32, 95, 32));
		
		testedrock.setAngle(0, 0, 0);
		test(testedrock.pointCollides(0, 0, 0), "Collision to origin");
		test(!testedrock.pointCollides(0, 0, 500), "Collision too far away on z-axis");
		test(testedrock.pointCollides(32, 32, 32), "A bit off but still inside");
		test(!testedrock.pointCollides(500, -500, 0), "Collision too far away on x/y -axis");
	}
}

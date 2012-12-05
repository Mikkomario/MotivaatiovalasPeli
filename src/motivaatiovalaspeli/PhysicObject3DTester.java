package motivaatiovalaspeli;

import java.util.Random;

/**
 * This class is supposed to test the physicObject3D class
 *
 * @author Gandalf.
 *         Created 3.12.2012.
 */
public class PhysicObject3DTester
{
	private static void printInfo(PhysicObject3D p)
	{
		//System.out.println("Position: " + p.getOriginX() + ", " + p.getY() + ", " + p.getZ());
		System.out.println("Velocity: X: " + p.getHspeed() + ", Y:" + p.getVspeed() + ", Z:" + p.getZspeed());
	}
	
	/**
	 * Tests the class by creating and modifying object Valas
	 * 
	 * @param Args Command line parameters. None needed.
	 */
	public static void main(String[] Args)
	{
		Random rand = new Random();
		Valas valas = new Valas(0, 0, 0, 20, 1, 1, "Test");
		
		printInfo(valas);
		
		/*
		int i = 0;
		while (i <= 18)
		{
			System.out.println("Xdirection: " + i*10);
			i++;
			valas.setMotion3D(i*10, 0, 1);
			printInfo(valas);
		}
		*/
		
		/*
		valas.setMotion3D(0, -90, 1);
		printInfo(valas);
		valas.setMotion3D(0, 270, 1);
		printInfo(valas);
		*/
		
		int i = 0;
		while(i < 90)
		{
			i+=3;
			valas.setMotion3D(i, rand.nextInt(360), 1);
			//System.out.println(i + ": " + i / valas.getVspeed());
			System.out.println(i + ": " + valas.getVspeed());
		}
	}
}

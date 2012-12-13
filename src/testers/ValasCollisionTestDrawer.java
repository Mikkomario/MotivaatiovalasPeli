package testers;

import model.Valas;
import motivaatiovalaspeli.MotivaatiovalasPeli;
import handleds.Drawable;

/**
 * TODO Put here a description of what this class does.
 *
 * @author Gandalf.
 *         Created 13.12.2012.
 */
public class ValasCollisionTestDrawer implements Drawable
{
	private Valas valas;
	
	@SuppressWarnings("javadoc")
	public ValasCollisionTestDrawer(Valas valas)
	{
		this.valas = valas;
	}

	@Override
	public boolean isDead()
	{
		return false;
	}

	@Override
	public boolean kill()
	{
		return false;
	}

	@SuppressWarnings("unqualified-field-access")
	@Override
	public void drawSelf(MotivaatiovalasPeli applet)
	{
		applet.translate(valas.getCollisionX(), valas.getCollisionY(), 
				valas.getCollisionZ());
		applet.stroke(0);
		applet.fill(255, 0, 0);
		applet.rect(-25, 0, 50, 5);
		applet.rect(0, -25, 5, 50);
		applet.noStroke();
		applet.noFill();
	}

	@Override
	public boolean isVisible()
	{
		return true;
	}

	@Override
	public boolean setVisible()
	{
		return true;
	}

	@Override
	public boolean setInvisible()
	{
		return false;
	}

}

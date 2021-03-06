package model;

import processing.core.PConstants;
import scrolling.Scrollable;
import sprites.Sprite;
import sprites.SpriteBank;
import handleds.Drawable;
import motivaatiovalaspeli.MotivaatiovalasPeli;

/**
 * This object represents a piece of a canyon. Each piece has its own proportions 
 * and can draw a few simple walls
 *
 * @author Gandalf.
 *         Created 29.11.2012.
 */
public class Canyon implements Drawable, Scrollable
{
	// ATTRIBUTES	--------------------------------------------------------
	
	// TODO: Add texturing
	
	private boolean visible, alive;
	private int width, height, depth, maxz, minz;
	private double z;
	
	private Sprite bottom;
	private Sprite canyon;

	
	
	// CONSTRUCTOR	-------------------------------------------------------
	
	/**
	 * 
	 * Creates a new canyon with the gven sizes. The canyon is visible at default.
	 *
	 * @param width The width of the canyon (x-axis) (pxl)
	 * @param height The height of the canyon (y-axis) (pxl)
	 * @param depth The depth of the canyon (z-axis) (pxl)
	 * @param z How far the nearest point of the canyon is on the z-axis (pxl)
	 * @param minz How close the piece of canyon can come before it jumps back to maxz?
	 * @param maxz How far is the piece of canyon dropped when it goes too far?
	 * @param spritebank The spritebank which includes canyon's textures
	 */
	public Canyon(int width, int height, int depth, int z, int minz,
			int maxz, SpriteBank spritebank)
	{
		// Initializes attributes
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.z = z;
		this.minz = minz;
		this.maxz = maxz;
		
		this.visible = true;
		this.alive = true;
		
		this.bottom = spritebank.getSprite("bottom");
		this.canyon = spritebank.getSprite("canyon");
	}
	
	
	// IMPLEMENTED METHODS	------------------------------------------------

	@Override
	public void drawSelf(MotivaatiovalasPeli applet)
	{	
		applet.textureMode(PConstants.NORMAL);
		
		/*
		applet.beginShape();
		applet.texture(this.bottom.getSubImage(0));
		*/
		// "laDefense.jpg" is 100x100 pixels in size so
		// the values 0 and 100 are used for the
		// parameters "u" and "v" to map it directly
		// to the vertex points
		/*
		applet.vertex(0, this.height, (int) getZ(), 0, 0);
		applet.vertex(this.width, this.height, (int) getZ(), 100, 0);
		applet.vertex(this.width, this.height, (int) getZ() -400, 100, 100);
		applet.vertex(0, this.height, (int) getZ() -400, 0, 100);
		applet.endShape();
		*/
		//applet.noStroke();
		
		// Draws bottom	
		/*
		applet.pushMatrix();
		applet.translate(0, this.height, (float) this.z);
		applet.rotateX((float) Math.toRadians(-90));
		*/
		applet.beginShape();
		//System.out.println(this.bottom);
		applet.texture(this.bottom.getSubImage(0));
		
		// x, y, z, tx, ty
		applet.vertex(0, this.height, (int) getZ() - this.depth, 0, 0);
		applet.vertex(0, this.height, (int) getZ(), 1, 
				0);
		applet.vertex(this.width, this.height, (int) getZ(), 
				1, 1);
		applet.vertex(this.width, this.height, (int) getZ() - this.depth, 0, 
				1);		
		
		applet.endShape();
	//	applet.fill(200, 200, 40);
	//	applet.rect(0, 0, this.width, this.depth);
		
		//applet.popMatrix();
	
		// Draws left wall
		/*
		applet.pushMatrix();
		applet.translate(0, 0, (float) this.z);
		applet.rotateX((float) Math.toRadians(-90));
		applet.rotateY((float) Math.toRadians(-90));
		*/
		applet.beginShape();
		applet.texture(this.canyon.getSubImage(0));
		applet.vertex(0, 0, (int) getZ(), 0, 0);
		applet.vertex(0, 0, (int) getZ() - this.depth, 1, 0);
		applet.vertex(0, this.height, (int) getZ() - this.depth, 
				1, 1);
		applet.vertex(0, this.height, (int) getZ(), 0, 1);
		applet.endShape();
		
	/*	applet.fill(50, 20, 20);
		applet.rect(0, 0, this.height, this.depth);*/
		
		// Draws right wall
		//applet.translate(0, 0, -this.width);
		//	applet.rect(0, 0, this.height, this.depth);
		applet.beginShape();
		applet.texture(this.canyon.getSubImage(0));
		applet.vertex(this.width, 0, (int) getZ(), 0, 0);
		applet.vertex(this.width, 0, (int) getZ() - this.depth, 
				1, 0);
		applet.vertex(this.width, this.height, (int) getZ() - this.depth, 
				1, 1);
		applet.vertex(this.width, this.height, (int) getZ(), 0, 
				1);
		applet.endShape();
		/*
		applet.popMatrix();
		applet.noFill();
		*/
	}

	@Override
	public boolean isVisible()
	{
		return this.visible;
	}

	@Override
	public boolean isDead()
	{
		return !this.alive;
	}

	@Override
	public boolean kill()
	{
		this.alive = false;
		return true;
	}

	@Override
	public boolean setVisible()
	{
		this.visible = true;
		return true;
	}

	@Override
	public boolean setInvisible()
	{
		this.visible = false;
		return true;
	}
	
	@Override
	public double getZ()
	{
		return this.z;
	}


	@Override
	public void setZ(double z)
	{
		this.z = z;
	}


	@Override
	public int getMaxZ()
	{
		return this.maxz;
	}


	@Override
	public int getMinZ()
	{
		return this.minz;
	}


	@Override
	public boolean isReturned()
	{
		return true;
	}


	@Override
	public void onOutOfRange()
	{
		// Does nothing
		//System.out.println("Canyon moves");
	}
}

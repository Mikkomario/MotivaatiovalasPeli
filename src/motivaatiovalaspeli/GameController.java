package motivaatiovalaspeli;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.PhaseScreen;

import handlers.DrawableHandler;

/**
 * Gamecontroller checks if the game has ended and other stuff
 *
 * @author Gandalf.
 *         Created 14.12.2012.
 */
public class GameController implements KeyListener
{
	// ATTRIBUTES	------------------------------------------------------
	
	private MotivaatiovalasPeli mvp;
	private GamePhase phase;
	private DrawableHandler drawer;
	private PhaseScreen beginscreen;
	private PhaseScreen pausescreen;
	private PhaseScreen victoryscreen;
	private PhaseScreen overscreen;
	private PhaseScreen kssscreen;
	
	
	// CONSTRUCTOR	------------------------------------------------------
	
	public GameController(MotivaatiovalasPeli peli, DrawableHandler phasedrawer)
	{}
	
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub.
		
	}
	
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub.
		
	}
	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub.
		
	}
}

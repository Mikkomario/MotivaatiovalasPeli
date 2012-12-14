package motivaatiovalaspeli;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sprites.SpriteBank;

import model.PhaseScreen;

import handlers.DrawableHandler;

/**
 * Gamecontroller checks if the game has ended and other stuff
 *
 * @author Gandalf.
 *         Created 14.12.2012.
 */
public class GameController implements listeners.KeyListener
{
	// ATTRIBUTES	------------------------------------------------------
	
	private MotivaatiovalasPeli mvp;
	private GamePhase phase;
	private DrawableHandler drawer;
	private PhaseScreen beginscreen;
	private PhaseScreen pausedscreen;
	private PhaseScreen victoryscreen;
	private PhaseScreen overscreen;
	private PhaseScreen kssscreen;
	
	
	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * Creates a new game controller. The game is at begin state when created.
	 *
	 * @param peli The applet creating this controller
	 * @param phasedrawer The drawablehandler drawing the phasescreens
	 * @param spritebank The spritebank that includes the phase sprites
	 */
	public GameController(MotivaatiovalasPeli peli, DrawableHandler phasedrawer, 
			SpriteBank spritebank)
	{
		// Initializes attributes
		this.mvp = peli;
		this.phase = GamePhase.BEGIN;
		this.drawer = phasedrawer;
		this.beginscreen = new PhaseScreen(GamePhase.BEGIN, spritebank);
		this.pausedscreen = new PhaseScreen(GamePhase.PAUSED, spritebank);
		this.victoryscreen = new PhaseScreen(GamePhase.VICTORY, spritebank);
		this.overscreen = new PhaseScreen(GamePhase.OVER, spritebank);
		this.kssscreen = new PhaseScreen(GamePhase.KSS, spritebank);
		
		// Sets the beginscreen visible
		this.beginscreen.setVisible();
	}

	
	// IMPLEMENTED METHODS	-----------------------------------------------

	@Override
	public boolean isActive()
	{
		// The gamecontroller is always active
		return true;
	}


	@Override
	public boolean activate()
	{
		// The gamecontroller is always active
		return true;
	}


	@Override
	public boolean inActivate()
	{
		// The gamecontroller is always active
		return false;
	}


	@Override
	public boolean isDead()
	{
		// The gamecontroller is always alive
		return false;
	}


	@Override
	public boolean kill()
	{
		// The gamecontroller is always alive
		return false;
	}


	@Override
	public void onKeyDown(int key, int keyCode, boolean coded)
	{
		// Nothing yet
	}


	@Override
	public void onKeyPressed(int key, int keyCode, boolean coded)
	{
		// When space is pressed, does different things
		if (!coded && key == ' ')
		{
			switch (this.phase)
			{
				// On begin, KSS and victoy, starts the game
				case KSS:
				case VICTORY:
				case BEGIN:
				{
					this.mvp.beginGame();
					this.phase = GamePhase.RUNNING;
					
					// Also sets screens invisible
					this.setScreensInvisible();
					
					break;
				}
				// On game-over goes to kss screen
				case OVER:
				{
					this.mvp.endGame();
					this.overscreen.setInvisible();
					this.phase = GamePhase.KSS;
					this.kssscreen.setVisible();
					
					break;
				}
				default: // Does nothing
					break;
			}
		}
		// If p was pressed, pauses / restarts the game
		else if (!coded && key == 'p')
		{
			if (this.phase == GamePhase.PAUSED)
			{
				this.phase = GamePhase.RUNNING;
				this.mvp.unPause();
				this.pausedscreen.setInvisible();
			}
			else
			{
				this.phase = GamePhase.PAUSED;
				this.mvp.pause();
				this.pausedscreen.setVisible();
			}
		}
	}


	@Override
	public void onKeyReleased(int key, int keyCode, boolean coded)
	{
		// Nothing yet
	}
	
	
	// GETTERS & SETTERS	------------------------------------------------
	
	/**
	 * @return The current phase of the game
	 */
	public GamePhase getPhase()
	{
		return this.phase;
	}
	
	/**
	 * Makes the game go to victory phase
	 */
	public void winTheGame()
	{
		setScreensInvisible();
		this.phase = GamePhase.VICTORY;
		this.victoryscreen.setVisible();
		this.mvp.endGame();
	}
	
	/**
	 * Makes the game go to game-over phase
	 */
	public void loseTheGame()
	{
		setScreensInvisible();
		this.phase = GamePhase.VICTORY;
		this.mvp.endGame();
		this.overscreen.setVisible();
	}
	
	private void setScreensInvisible()
	{
		this.beginscreen.setInvisible();
		this.pausedscreen.setInvisible();
		this.victoryscreen.setInvisible();
		this.overscreen.setInvisible();
		this.kssscreen.setInvisible();
	}
}

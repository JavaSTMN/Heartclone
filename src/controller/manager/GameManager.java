package controller.manager;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

import controller.Observable;
import model.hero.Hero;

/**
 * 
 */

/**
 * @author adrien
 *
 */
public class GameManager {

	private Hero opponents[];
	private int activeHero;
	private int turnCount;
	private Date turnStartDate;
	private long turnMaxSeconds;
	private Timer timer;

	private static GameManager instanceGameManager = null;

	public GameManager() throws Exception {
		
		opponents = new Hero[2];
		activeHero = 0;
		
		opponents[0] = new Hero();
		opponents[1] = new Hero();
		
		opponents[activeHero].setIsTurn(true);
		opponents[1].setIsTurn(false);
		
		turnCount = 0;
		turnMaxSeconds = 30;
		turnStartDate = Date.valueOf(LocalDate.now());
	}

	public static GameManager getInstance() throws Exception {
		if (instanceGameManager == null)
			synchronized (GameManager.class) {
				if (instanceGameManager == null) {
					instanceGameManager = new GameManager();
				}
			}

		return instanceGameManager;
	}

	/**
	 * Start a game
	 * @param _opponents[2]
	 */
	public void startGame() {
		
		for (int i = 0; i < 3; i++) {
			try {
				opponents[0].draw();
				opponents[1].draw();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		startTurn(opponents[activeHero]);
	}


	/**
	 * Finish a game
	 */
	public void finishGame() {
		System.out.println("partie terminée");
	}

	/**
	 * Start a turn
	 * 
	 */

	public void startTurn(Hero hero) {
		opponents[activeHero].setIsTurn(true);
		opponents[activeHero].regenerateCristals();
		opponents[activeHero].activateMinions();
		inTurn();
		
		// the hero tries to draw a card at the beginning of his turn
		try {
			hero.draw();
		} catch (Exception e) {
			// impossible to draw a card => suffer fatigue
			hero.sufferFatigue();
		}
	}

	/**
	 * Timer running while a hero is playing
	 * 
	 */
	public void inTurn()
	{
		this.timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println(System.currentTimeMillis() - scheduledExecutionTime());
				finishTurn();
			}
		}, turnMaxSeconds*1000);
		
		
	}
	
	public void cancelTimer() {
		this.timer.cancel();
		this.timer.purge();
	}

	/**
	 * Finish turn, pass to another player or finish the game
	 * 

	 * @param hero
	 */
	public void finishTurn(Hero hero) {
		System.out.println("le tour a été passée");
	}

	/**
	 * Time left for the turn in seconds
	 * 
	 * @return

	 */
	public void finishTurn() {
		if(!opponents[0].isAlive() || !opponents[1].isAlive())
		{
			finishGame();
		}
		
		else 
	 
		{
			opponents[activeHero].setIsTurn(false);
			
			switch (activeHero) {
			case 0:
					activeHero = 1;
				break;
			case 1:
					activeHero = 0;
				break;

			default:
				break;
			}
			
			startTurn(opponents[activeHero]);
		}
	}
	
	public Hero[] getHeros() {
		return this.opponents;
	}

	
	public Hero getOpponent(Hero hero) {
		if(this.opponents[0] == hero)
			return this.opponents[1];
		else
			return this.opponents[0];
	}
	
	public boolean isPlayerOne(Hero hero) {
		if(hero == this.opponents[1])
			return false;
		else
			return true;
	}

}

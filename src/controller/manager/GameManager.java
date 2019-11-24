package controller.manager;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.SwingWorker;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

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
	private int turnCount;
	private Date turnStartDate;
	private long turnMaxSeconds;

	private static GameManager instanceGameManager = null;

	public GameManager() throws Exception {
		
		opponents = new Hero[2];
		opponents[0] = new Hero();
		opponents[1] = new Hero();
		
		opponents[0].setIsTurn(true);
		
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
	 */
	public void startGame() {
		for (int i = 0; i < 4; i++) {
			try {
				opponents[0].draw();
				opponents[1].draw();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Finish a game
	 */
	public void finishGame() {

	}

	/**
	 * Start a turn
	 * 
	 * @param hero
	 */
	public void startTurn(Hero hero) {
		turnStartDate = Date.valueOf(LocalDate.now());
		inTurn(hero);
	}

	/**
	 * Timer running while a hero is playing
	 * 
	 * @param hero
	 */
	public void inTurn(Hero hero) {
		SwingWorker sw = new SwingWorker() {
			protected Object doInBackground() throws Exception {
				do {
					System.out.println(turnTimeLeft());
				} while (turnTimeLeft() > 0);
				return null;
			}

			public void done() {
				finishTurn(hero);
			}
		};

		sw.execute();
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
	private long turnTimeLeft() {
		long diff = (turnStartDate.getTime() / 1000) + turnMaxSeconds
				- (Date.valueOf(LocalDate.now()).getTime() / 1000);
		return diff;
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

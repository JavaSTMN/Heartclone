package controller.manager;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingWorker;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import controller.hero.Hero;



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
	
	private static GameManager instanceGameManager = null;
	
	public GameManager()
	{
		
		opponents = new Hero[2];
		activeHero = 0;
		turnCount = 0;		
		turnMaxSeconds = 30;
		turnStartDate = Date.valueOf(LocalDate.now());
	}
	
	
	/**
	 * singleton
	 * @return
	 */
	public static GameManager getInstance()
	{
		if(instanceGameManager == null)
			instanceGameManager = new GameManager();
		
		return instanceGameManager;
	}
	
	/**
	 * Start a game
	 * @param _opponents[2]
	 */
	public void startGame(Hero _opponents[])
	{
		try {
			opponents = _opponents;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		startTurn();
	}
	
	/**
	 * Finish a game
	 */
	public void finishGame()
	{
		
	}
	
	/**
	 * Start a turn
	 */
	public void startTurn()
	{
		inTurn();
	}
	
	/**
	 * Timer running while a hero is playing
	 */
	public void inTurn()
	{
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {	
			@Override
			public void run() {
				
				
			}
		}, turnMaxSeconds*1000);
		
		finishTurn();
		
		
		
//		SwingWorker sw = new SwingWorker()
//		{
//			protected Object doInBackground() throws Exception
//			{
//				do {System.out.println(turnTimeLeft());} while (turnTimeLeft() > 0);
//				return null;
//			}
//			
//			public void done()
//			{
//				finishTurn(hero);
//			}
//		};
//		sw.execute();
	}
	
	/**
	 * Finish turn, pass to another player or finish the game
	 */
	public void finishTurn()
	{
		if(!opponents[activeHero].isAlive())
		{
			finishGame();
		}
		
		else 
		{
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
			
			startTurn();
		}
	}
}

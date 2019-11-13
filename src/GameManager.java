import java.sql.Date;
import java.time.LocalDate;

import javax.swing.SwingWorker;

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
	
	
	public GameManager()
	{
		opponents = new Hero[2];
		turnCount = 0;		
		turnMaxSeconds = 30;
		turnStartDate = Date.valueOf(LocalDate.now());
	}
	
	/**
	 * Start a game
	 */
	public void startGame()
	{
		
	}
	
	/**
	 * Finish a game
	 */
	public void finishGame()
	{
		
	}
	
	/**
	 * Start a turn
	 * @param hero
	 */
	public void startTurn(Hero hero)
	{
		turnStartDate = Date.valueOf(LocalDate.now());
		inTurn(hero);
	}
	
	/**
	 * Timer running while a hero is playing
	 * @param hero
	 */
	public void inTurn(Hero hero)
	{
		SwingWorker sw = new SwingWorker()
		{
			protected Object doInBackground() throws Exception
			{
				do {System.out.println(turnTimeLeft());} while (turnTimeLeft() > 0);
				return null;
			}
			
			public void done()
			{
				finishTurn(hero);
			}
		};
		
		
		sw.execute();
	}
	
	/**
	 * Finish turn, pass to another player or finish the game
	 * @param hero
	 */
	public void finishTurn(Hero hero)
	{
		
	}
	
	/**
	 * Time left for the turn in seconds
	 * @return
	 */
	private long turnTimeLeft()
	{			
		long diff = (turnStartDate.getTime()/1000) + turnMaxSeconds - (Date.valueOf(LocalDate.now()).getTime()/1000);
		return diff;
	}

}

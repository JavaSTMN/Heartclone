import java.lang.reflect.Array;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
	
	public void startGame()
	{
		
	}
	
	public void finishGame()
	{
		
	}
	
	public void startTurn(Hero hero)
	{
		turnStartDate = Date.valueOf(LocalDate.now());
		inTurn(hero);
	}
	
	public void inTurn(Hero hero)
	{
		SwingWorker sw = new SwingWorker()
		{
			protected Object doInBackground() throws Exception
			{
				do {} while (turnTimeLeft() > 0);
				return null;
			}
			
			public void done()
			{
				finishTurn(hero);
			}
		};
		
		
		sw.execute();
	}
	
	public void finishTurn(Hero hero)
	{
		
	}
	
	private long turnTimeLeft()
	{			
		long diff = (turnStartDate.getTime()/1000) + turnMaxSeconds - (Date.valueOf(LocalDate.now()).getTime()/1000);
		return diff;
	}

}

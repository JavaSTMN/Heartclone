import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameManagerTest {

	@Test
	void test() {
		
	}
	
	@Test
	void startTurnTest()
	{
		GameManager gm = new GameManager();
		gm.startTurn(new Hero());
	}

}

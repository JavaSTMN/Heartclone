package hero;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.card.Card;
import controller.card.CardContainer;
import controller.card.MinionCard;
import controller.hero.Hero;

class HeroTest {

	@Test
	void playTest() throws Exception {
		Hero hero = new Hero();
		CardContainer hand = hero.getHand();
		CardContainer board = hero.getGameboard();
		
		hand.addCard(new MinionCard(3,5,false));
		
		Card cardToPlay = hand.getCard(0);
		
		hero.play(cardToPlay);
		
		assertEquals(0, hand.getCardNumber());
		assertEquals(1, board.getCardNumber());
	}
		

}

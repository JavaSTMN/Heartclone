/**
 * 
 */
package controller.card;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class DeckTest {

	/**
	 * Test method for {@link controller.card.Deck#drawCard()}.
	 */
	@Test
	void testDrawCard_normal() {
		MinionCard c1 = new MinionCard(1, 1, true);
		MinionCard c2 = new MinionCard(2, 2, true);
		MinionCard c3 = new MinionCard(3, 3, true);


		Deck deck = new Deck(new ArrayList<Card>(Arrays.asList(c1, c2, c3)));
		
		UUID c1id = deck.cards.get(0).getId();

		try {
			Card drawnCard = deck.drawCard();
			
			assertEquals(c1id, drawnCard.getId(), "Should be the same cards");
			assertEquals(2, deck.getCardNumber(), "There should be 2 cards left");
		} catch (Exception e) {
			fail("Shouldn't have thrown anything exception");
		}
	}
	
	/**
	 * Test method for {@link controller.card.Deck#drawCard()}.
	 */
	@Test
	void testDrawCard_noCard() {		
		assertThrows(Exception.class, () -> {
			Deck d = new Deck(new ArrayList<Card>());
			d.drawCard();
		}, "Should throw an Exception as there are no card to draw");
	}

}

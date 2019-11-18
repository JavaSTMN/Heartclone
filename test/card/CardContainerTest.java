package card;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import model.card.Card;
import model.card.CardContainer;
import model.card.MinionCard;

class CardContainerTest {

	@Test
	void constructorTest() {
		
		ArrayList<Card> cards = new ArrayList<Card>(Arrays.asList(
				new MinionCard(5,2,false),
				new MinionCard(3,3,false),
				new MinionCard(2,3,false)	
		));
				
		
		CardContainer cardContainer = new CardContainer(cards, 10);
		
		assertArrayEquals(cards.toArray(), cardContainer.getCards().toArray(), "The two arrays should be the same");
		assertEquals(10, cardContainer.getCardLimit());
		
		cardContainer = new CardContainer(cards);
		
		assertArrayEquals(cards.toArray(), cardContainer.getCards().toArray(), "The two arrays should be the same");
		assertEquals(Integer.MAX_VALUE, cardContainer.getCardLimit());
		
		cardContainer = new CardContainer();
		ArrayList<Card> emptyArray = new ArrayList<Card>();
		
		
		assertArrayEquals(emptyArray.toArray(), cardContainer.getCards().toArray(), "The two arrays should be the same");
		assertEquals(Integer.MAX_VALUE, cardContainer.getCardLimit(), "The limit should be the Maximum limit");
	}
	
	@Test
	void addCardTest() throws Exception {
		MinionCard card = new MinionCard(2,2,false);
		UUID id = card.getId();
		CardContainer container = new CardContainer();
		
		container.addCard(card);
		
		assertEquals(1, container.getCardNumber());
		
		if( container.getCard(0) instanceof MinionCard) {
			MinionCard cardInContainer = (MinionCard)container.getCard(0);
			assertEquals(id, cardInContainer.getId());
		}		
		
	}
	
}

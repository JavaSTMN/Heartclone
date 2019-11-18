package model.card;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import model.card.Card;
import model.card.CardContainer;
import model.card.MinionCard;


class CardContainerTest {

	@Test
	void testCardContainer_constructor_normal() {
		CardContainer defaultCardContainer = new CardContainer();
		CardContainer noCardLimitContainer = new CardContainer(new ArrayList<Card>());
		CardContainer limitedContainer = new CardContainer(new ArrayList<Card>(), 7);

		assertEquals(Integer.MAX_VALUE, defaultCardContainer.getCardLimit(),
				"defaultCardContainer's limit should be max int value");
		assertEquals(0, defaultCardContainer.getCardNumber(), "defaultCardContainer should be empty");

		assertEquals(Integer.MAX_VALUE, noCardLimitContainer.getCardLimit(),
				"noCardLimitContainer's limit should be max int value");
		assertEquals(0, noCardLimitContainer.getCardNumber(), "noCardLimitContainer should be empty");

		assertEquals(7, limitedContainer.getCardLimit(), "limitedContainer limit should be 7");
		assertEquals(0, limitedContainer.getCardNumber(), "limitedContainer should be empty");
	}

	@Test
	void testCardContainer_constructor_negativeCardLimit() {
		assertThrows(IllegalArgumentException.class, () -> {
			new CardContainer(new ArrayList<Card>(), -7);
		});
	}

	@Test
	void testCardContainer_constructor_exceedCardLimit() {
		assertThrows(Exception.class, () -> {
			new CardContainer(new ArrayList<Card>(Arrays.asList(new MinionCard(10, 10, true), new MinionCard(10, 10, true))), 1);
		});
	}

	@Test
	void testCardContainer_deleteCard_cardNormal() {
		Card c1 = new MinionCard(10, 10, true);
		Card c2 = new MinionCard(10, 10, true);

		CardContainer cardContainer = new CardContainer(new ArrayList<Card>(Arrays.asList(c1, c2)));

		try {
			cardContainer.deleteCard(c1);
		} catch (Exception e) {
			fail("Should be deleted : c1");
		}

		assertEquals(1, cardContainer.getCardNumber(), "cardContainer should have one card");
		assertEquals(0, cardContainer.cards.indexOf(c2), "c2 should be at index 0 in cardContainer");
	}

	@Test
	void testCardContainer_deleteCard_cardNotInContainer() {
		CardContainer cardContainer = new CardContainer();

		assertThrows(Exception.class, () -> {
			cardContainer.deleteCard(new MinionCard(10, 10, true));
		});
	}

	@Test
	void testCardContainer_deleteCard_indexNormal() {

    
		Card c1 = new MinionCard(10, 10, true);
		Card c2 = new MinionCard(10, 10, true);
  
    CardContainer cardContainer = new CardContainer(new ArrayList<Card>(Arrays.asList(c1, c2)));

		try {
			cardContainer.deleteCard(0);
		} catch (Exception e) {
			fail("Should be deleted : c1");
		}

		assertEquals(1, cardContainer.getCardNumber(), "cardContainer should have one card");
		assertEquals(0, cardContainer.cards.indexOf(c2), "c2 should be at index 0 in cardContainer");
	}

	@Test
	void testCardContainer_deleteCard_indexNegative() {
		CardContainer cardContainer = new CardContainer();

		assertThrows(Exception.class, () -> {
			cardContainer.deleteCard(-1);
		});
	}

	@Test
	void testCardContainer_deleteCard_indexOverLimit() {
		CardContainer cardContainer = new CardContainer(new ArrayList<Card>(), 7);

		assertThrows(Exception.class, () -> {
			cardContainer.deleteCard(42);
		});
	}

	@Test
	void testCardContainer_addCard_normal() {
		CardContainer cardContainer = new CardContainer();
		MinionCard c = new MinionCard(10, 10, true);
		try {
			cardContainer.addCard(c);
		} catch (Exception e) {
			fail("Should have added card");
		}

		assertEquals(1, cardContainer.getCardNumber(), "cardContainer should have one element");
		assertEquals(c, cardContainer.cards.get(0), "c should be in cardContainer");
	}

	@Test
	void testCardContainer_addCard_atTheLimit() {
		CardContainer cardContainer = new CardContainer(new ArrayList<Card>(), 0);

		assertThrows(Exception.class, () -> {
        cardContainer.addCard(new MinionCard(10, 10, true));
		});
	}

	@Test
	void testCardContainer_addCard_beforeLimit() {
		CardContainer cardContainer = new CardContainer(new ArrayList<Card>(), 1);

		try {
      cardContainer.addCard(new MinionCard(10, 10, true));
		} catch (Exception e) {
			fail("Should have added card");
		}

		assertEquals(1, cardContainer.getCardNumber());
	}
}

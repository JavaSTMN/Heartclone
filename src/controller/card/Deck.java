package controller.card;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A collection of cards in which the cards are drawn
 */
public class Deck extends CardContainer {
	public Deck (ArrayList<Card> cards) {
		super(cards);
		this.shuffle();
	}
 	
	/**
	 * Draws the first card of the deck
	 * @return the drawn card
	 */
	public Card drawCard() throws Exception {
		try {
			return this.fetchCard(0);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Shuffles the deck 
	 */
	private void shuffle() {
		Collections.shuffle(this.cards);
	} 
}

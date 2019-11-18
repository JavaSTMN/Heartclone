
package controller.card;
/**
 * 
 */

import java.util.ArrayList;


/**
 * Class which contains cards. 
 * - handles card limit
 * - remove/add card
 */
public class CardContainer {

	/* contains the cards */
	protected ArrayList<Card> cards;
	
	/* the maximum number of card int the list */
	protected int cardLimit;
	
	/**
	 * 
	 * @param cards
	 * @param cardLimit
	 * @throws IllegalArgumentException
	 */
	public CardContainer(ArrayList<Card> cards, int cardLimit) throws IllegalArgumentException {
		// cardLimit must be positive
		if (cardLimit < 0) 
			throw new IllegalArgumentException("cardLimit is negative");
		// the size of cards must not exceed the card limit
		if (cards.size() > cardLimit) 
			throw new IllegalArgumentException("The number of cards exceeds the limit. Maximum number of cards: " + cardLimit + ", got " + cards.size());
		
		this.cards = cards;
		this.cardLimit = cardLimit;
	}
	
	/**
	 * Constructor without card limit
	 * @param cards
	 */
	public CardContainer(ArrayList<Card> cards) {
		this.cards = cards;
		
		// unlimited number of cards
		this.cardLimit = Integer.MAX_VALUE;
	}
	
	/**
	 * Default constructor. Initialize cards with empty arraylist. Unlimited card number
	 */
	public CardContainer() {
		this.cards = new ArrayList<Card>();
		
		// unlimited number of cards
		this.cardLimit = Integer.MAX_VALUE;
	}
	
	
	
	public int getCardNumber() {
		return this.cards.size();
	}

	public int getCardLimit() {
		return this.cardLimit;
	}	
	
	
	/**
	 * Adds a card to the container if there is enough space
	 * @param card 
	 * @throws Exception if the limit have already been reached
	 */
	protected void addCard(Card card) throws Exception {
		// if the limit have been reached
		if (this.cards.size() >= this.cardLimit)
			throw new Exception("Can't add a new Card: the limit have been reached");
			
		this.cards.add(card);
	}
	

	/**
	 * Removes a card from the container
	 * @param card
	 * @throws Exception if the card isn't in the container
	 */
	protected void deleteCard(Card card) throws Exception {
		boolean success = this.cards.remove(card);
		
		if (!success) 
			throw new Exception("The card does not exist in the container");	
	}

	/**
	 * Removes a card from the container with an index
	 * @param index
	 * @throws Exception if the index does not exist
	 */
	protected void deleteCard(int index) throws Exception {
		if (index < 0 || index >= this.cards.size())
			throw new Exception("The index specified does not exist");
		
		this.cards.remove(index);
	}	
}



package model.card;
/**
 * 
 */

import java.io.IOException;
import java.util.ArrayList;

import controller.Observable;


/**
 * Class which contains cards. 
 * - handles card limit
 * - remove/add card
 */
public class CardContainer {
	
	private Observable observable;

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
		
		this.observable = new Observable();
	}
	
	/**
	 * Constructor without card limit
	 * @param cards
	 */
	public CardContainer(ArrayList<Card> cards) {
		this.cards = cards;
		
		// unlimited number of cards
		this.cardLimit = Integer.MAX_VALUE;
		this.observable = new Observable();
	}
	
	/**
	 * Constructor with a card limit
	 * @param limit
	 */
	public CardContainer(int cardLimit) {
		this.cards = new ArrayList<Card>();
		
		// unlimited number of cards
		this.cardLimit = cardLimit;
		this.observable = new Observable();
	}
	
	/**
	 * Default constructor. Initialize cards with empty arraylist. Unlimited card number
	 */
	public CardContainer() {
		this.cards = new ArrayList<Card>();
		
		// unlimited number of cards
		this.cardLimit = Integer.MAX_VALUE;
		this.observable = new Observable();
	}
	
	
	
	public Integer getCardNumber() {
		return this.cards.size();
	}

	public int getCardLimit() {
		return this.cardLimit;
	}
	
	public Card getCard(int index) {
		return this.cards.get(index);
	}
	
	public ArrayList<Card> getCards(){
		return this.cards;
	}
	
	public Observable getObservable() {
		return this.observable;
	}
	
	
	/**
	 * Adds a card to the container if there is enough space
	 * @param card 
	 * @throws Exception if the limit have already been reached
	 */
	public void addCard(Card card) throws Exception {
		// if the limit have been reached
		if (this.cards.size() >= this.cardLimit)
			throw new Exception("Can't add a new Card: the limit have been reached");
			
		this.cards.add(card);
		
		observable.notifyObservers();
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
		
		observable.notifyObservers();
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
		observable.notifyObservers();
	}
	
	public Card fetchCard(Card card) throws Exception {
		Card cardToReturn = card;
		this.deleteCard(card);
		observable.notifyObservers();
		return cardToReturn;
	}
	
	public Card fetchCard(int index) throws Exception {
		Card cardToReturn = this.cards.get(index);
		this.deleteCard(index);
		observable.notifyObservers();
		return cardToReturn;
	}
	
	
}


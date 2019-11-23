package model.hero;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observer;
import java.util.logging.Handler;

import controller.Attacker;
import controller.IObserver;
import controller.Observable;
import controller.Target;
import model.card.Card;
import model.card.CardContainer;
import model.card.Deck;
import model.card.MinionCard;
import service.StartDeck;


/**
 * 
 */

/**
 * @author adrien
 *
 */

public class Hero implements Attacker, Target {
	
	private Observable observable;
	
	private int cristals;
	private int cristalsRegeneration;
	private int lifePoints;
	private int maxLifePoints;
	private Deck deck;
	private CardContainer gameboard;
	private CardContainer hand;
	private CardContainer discard;
	boolean isActive;
	private Image image;
	
	public Hero() {
		
		cristals = 10;
		deck = new Deck(StartDeck.getDeck());		
		cristalsRegeneration = 1;
		hand = new CardContainer(10);
		gameboard = new CardContainer(7);
		discard = new CardContainer(); 
		lifePoints = 30;
		maxLifePoints = 30;
		isActive = true;
//		image
	}
	
	public CardContainer getHand() {
		return this.hand;
	}
	
	public CardContainer getGameboard() {
		return this.gameboard;
	}
	
	public Deck getDeck() {
		return this.deck;
	}
	
	public Observable getObservable() {
		return this.observable;
	}
	
	

	/**
	 * Hero put a card from his hand to the gameboard
	 * @param playableCard: the card to play
	 * @throws Exception if the player doesn't have enough cristals 
	 */
	public void play(Card playableCard) throws Exception
	{
		if(this.canPlay(playableCard)) {
			// We fetch the card to play from the hand
			playableCard = this.hand.fetchCard(playableCard);
			
			// We add it to the gameboard
			this.gameboard.addCard(playableCard);
		}else {
			throw new Exception("Not enough cristals to play this card");
		}
		
		try {
			this.observable.notifyObservers();
		} catch (Exception e) {
			e.getMessage();
		}
		
		
	}
	
	/**
	 * Hero draw a card from his deck to his hand
	 * @throws Exception 
	 */
	public void draw() throws Exception {
		try {
		hand.addCard(deck.fetchCard(0));
			hand.addCard(deck.drawCard());
		//this.observable.notifyObservers();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Hero use his special spell which cost him 2 cristals
	 */
	public void useSpell() 
	{
		useCristals(2);
	}
	
	/**
	 * Substract cristals from the hero
	 * @param nbCristalsUsed
	 */
	public void useCristals(int nbCristalsUsed) {
		if(nbCristalsUsed <= cristals)
		{
			cristals -= nbCristalsUsed;
		try {
			this.observable.notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

	@Override
	public void receiveDamage(int amount) throws IllegalArgumentException {
		lifePoints -= amount;
		try {
			this.observable.notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isAlive() {
		return(lifePoints>0);
	}

	@Override
	public void dealDamage(Target target, int amount) throws IllegalArgumentException {
		target.receiveDamage(amount);
		
	}

	@Override
	public void disable() {
		isActive = false;
	}

	@Override
	public void enable() {
		isActive = true;
		
	}

	@Override
	public boolean getState() {
		return isActive;
	}

	@Override
	public void receiveHealthPoints(int amount) throws IllegalArgumentException {
		if(lifePoints + amount > maxLifePoints)
			lifePoints = maxLifePoints;
		else 
			lifePoints += amount;
		try {
			this.observable.notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Check if the player can play a card
	 * @param card
	 * @return true if the player can play the card, false if not
	 */
	public boolean canPlay(Card card) {
		return (card.getCristalCost() <= this.cristals);
	}

	@Override
	public void dealDamage(Target target) throws IllegalArgumentException {
		this.dealDamage(target, 2);
		
	}
	
	/**
	 * Regenerate all cristals
	 */
	public void regenerateCristals()
	{
		cristalsRegeneration += 1;
		cristals = cristalsRegeneration;
	}
	
	/**
	 * Add cristals to the hero reserve
	 * @param amount
	 */
	public void addCristals(int amount)
	{
		cristals += amount;
	}
	
	

}



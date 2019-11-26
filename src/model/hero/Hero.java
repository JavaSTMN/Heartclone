package model.hero;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observer;
import java.util.logging.Handler;

import controller.Attacker;
import controller.Target;
import controller.IObserver;
import controller.Observable;
import controller.Target;
import controller.manager.GameManager;
import model.card.Card;
import model.card.CardContainer;
import model.card.Deck;
import model.card.MinionCard;
import model.card.SpellCard;
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
	private boolean isTurn = false;
	private boolean spellSelected = false;

	public Hero() {

		
		cristals = 1;
		deck = new Deck(StartDeck.getDeck());		
		cristalsRegeneration = 0;
		hand = new CardContainer(10);
		gameboard = new CardContainer(7);
		discard = new CardContainer();
		lifePoints = 30;
		maxLifePoints = 30;
		isActive = true;
		
		observable = new Observable();
//		image

		this.observable = new Observable();
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

	public boolean getIsTurn() {
		return this.isTurn;
	}

	public void setIsTurn(boolean value) {
		this.isTurn = value;
		try {
			this.getHand().getObservable().notifyObservers();
			this.getObservable().notifyObservers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer getLifePoints() {
		return this.lifePoints;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public Integer getCristals() {
		return this.cristals;
	}

	public void setCristals(int value) {
		this.cristals = value;
		try {
			this.observable.notifyObservers();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getCristalRegeneration() {
		return this.cristalsRegeneration;
	}
	
	public void setCristalsRegeneration(int value) {
		this.cristalsRegeneration = value;
	}

	public void setIsActive(boolean value) {
		this.isActive = value;
		try {
			this.observable.notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean getSpellSelected() {
		return this.spellSelected;
	}

	public void setSpellSelected(boolean value) {
		this.spellSelected = value;
		try {
			this.observable.notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Hero put a card from his hand to the gameboard
	 * 
	 * @param playableCard: the card to play
	 * @throws Exception if the player doesn't have enough cristals
	 */
	public void play(Card playableCard) throws Exception {
		if (this.canPlay(playableCard)) {
			// We fetch the card to play from the hand
			playableCard = this.hand.fetchCard(playableCard);
			// We add it to the gameboard
			this.gameboard.addCard(playableCard);
			// We spend cristals
			this.setCristals(this.getCristals() - playableCard.getCristalCost());
		} else {
			throw new Exception("Not enough cristals to play this card");
		}

		try {
			this.observable.notifyObservers();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void activateSpell(SpellCard card, Target target) throws Exception {
		if (this.canPlay(card)) {
			card.activateEffect(target);
			this.setCristals(this.getCristals() - card.getCristalCost());
		} else {
			throw new Exception("Not enough cristals to play this card");
		}
		
		try {
			this.observable.notifyObservers();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void activateSpell(SpellCard card, Hero target) throws Exception {
		if (this.canPlay(card)) {
			card.activateEffect(target);
			this.setCristals(this.getCristals() - card.getCristalCost());
		} else {
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
	 * 
	 * @throws Exception
	 */
	public void draw() throws Exception {
		try {
		hand.addCard(deck.fetchCard(0));

		observable.notifyObservers();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hero use his special spell which cost him 2 cristals
	 */
	public void useSpell(Target target) {
		useCristals(2);
		target.receiveDamage(2);
		this.setSpellSelected(false);
		this.setIsActive(false);
	}

	/**
	 * Substract cristals from the hero
	 * 
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

		if (!this.isAlive()) {
			try {
				GameManager.getInstance().finishGame();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * Makes the hero loose 2 healthpoints
	 */
	public void sufferFatigue() {
		this.receiveDamage(2);
	}
		
	
	@Override
	public boolean isAlive() {
		return (lifePoints > 0);
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
	 * 
	 * @param card
	 * @return true if the player can play the card, false if not
	 */
	public boolean canPlay(Card card) {
		return (this.cristals >= card.getCristalCost());
	}

	public boolean canUseSpell() {
		if (this.cristals < 2 || !this.getIsActive())
			return false;

		return true;
	}

	public void discard(Card card) {
		try {
			this.discard.addCard(this.gameboard.fetchCard(card));

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			this.discard.addCard(this.hand.fetchCard(card));
			this.hand.getObservable().notifyObservers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if( cristalsRegeneration < 10) {
			cristalsRegeneration += 1;
		}
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

	public void activateMinions() {
		for(Card card: this.gameboard.getCards()) {
			if(card instanceof MinionCard) {
				MinionCard mCard = (MinionCard)card;
				mCard.setActive(true);
			}
		}
		
		try {
			this.observable.notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deselectAll() {
		this.setSpellSelected(false);
		
		for(Card card : this.getHand().getCards()) {
			card.setSelected(false);
		}
		
		for(Card card : this.getGameboard().getCards()) {
			card.setSelected(false);
			card.setSelectedToAttack(false);
		}
	}

}

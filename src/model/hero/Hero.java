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

		cristals = 10;
		deck = new Deck(StartDeck.getDeck());
		hand = new CardContainer(10);
		gameboard = new CardContainer(7);
		discard = new CardContainer();
		lifePoints = 30;
		maxLifePoints = 30;
		isActive = true;
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
		hand.addCard(deck.fetchCard(0));

		// this.observable.notifyObservers();
	}

	/**
	 * Hero use his special spell which cost him 2 cristals
	 */
	public void useSpell(Target target) {
		useCristals(2);
		target.receiveDamage(2);
		this.setSpellSelected(false);
	}

	/**
	 * Substract cristals from the hero
	 * 
	 * @param nbCristalsUsed
	 */
	public void useCristals(int nbCristalsUsed) {
		cristals -= nbCristalsUsed;
		try {
			this.observable.notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void receiveDamage(int nb) throws IllegalArgumentException {
		lifePoints -= nb;

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

	@Override
	public boolean isAlive() {
		return (lifePoints > 0);
	}

	@Override
	public void dealDamage(Target target) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void receiveHealthPoints(int amount) throws IllegalArgumentException {
		// TODO Auto-generated method stub
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
		if (this.cristals < 2)
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

}

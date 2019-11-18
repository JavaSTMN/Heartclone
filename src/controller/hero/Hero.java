package controller.hero;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Handler;

import controller.Attacker;
import controller.Target;
import controller.card.Card;
import controller.card.CardContainer;
import controller.card.Deck;
import controller.card.MinionCard;


/**
 * 
 */

/**
 * @author adrien
 *
 */

public class Hero implements Attacker, Target {
	
	private int cristals;
	private int lifePoints;
	private int maxLifePoints;
	private Deck deck;
	private CardContainer gameboard;
	private CardContainer hand;
	private CardContainer discard;
	boolean isActive;
	private Image image;
	
	
	
	public Hero() {
		
		
//		ArrayList<Card> handCards = new ArrayList<Card>(Arrays.asList(
//				new MinionCard(5,2,false),
//				new MinionCard(3,3,false),
//				new MinionCard(2,3,false)	
//		));
	
		
		cristals = 1;
		deck = new Deck();		
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
	
	

	/**
	 * Hero put a card from his hand to the gameboard
	 * @param playableCard: the card to play
	 * @throws Exception if the player doesn't have enough cristals 
	 */
	public void play(Card playableCard) throws Exception
	{
		if(this.canPlay(playableCard)) {
			// We fetch the card to play from the hand
			playableCard = hand.fetchCard(playableCard);
			
			// We add it to the gameboard
			gameboard.addCard(playableCard);
		}else {
			throw new Exception("Not enough cristals to play this card");
		}
		
	}
	
	/**
	 * Hero draw a card from his deck to his hand
	 */
	public void draw() {
		
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
		cristals -= nbCristalsUsed;
	}

	@Override
	public void receiveDamage(int nb) throws IllegalArgumentException {
		lifePoints -= nb;
	}

	@Override
	public boolean isAlive() {
		return(lifePoints>0);
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
		
	}
	
	/**
	 * Check if the player can play a card
	 * @param card
	 * @return true if the player can play the card, false if not
	 */
	public boolean canPlay(Card card) {

		return (card.getCristalCost() < this.cristals);
	}

}



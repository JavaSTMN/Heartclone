package model.hero;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Handler;

import model.Attacker;
import model.Target;
import model.card.Card;
import model.card.CardContainer;
import model.card.Deck;
import model.card.MinionCard;


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
		deck = new Deck(new ArrayList<Card>());		
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
		try {
			hand.addCard(deck.drawCard());
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
	public Boolean useCristals(int nbCristalsUsed) {
		if(nbCristalsUsed <= cristals)
		{
			cristals -= nbCristalsUsed;
			return true;
		}
		else {return false;}
	}

	@Override
	public void receiveDamage(int amount) throws IllegalArgumentException {
		lifePoints -= amount;
		if(lifePoints - amount < 0)
			lifePoints = 0;
		else 
			lifePoints -= amount;
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
		
	}
	
	/**
	 * Check if the player can play a card
	 * @param card
	 * @return true if the player can play the card, false if not
	 */
	public boolean canPlay(Card card) {

		return (card.getCristalCost() < this.cristals);
	}

	@Override
	public void dealDamage(Target target) throws IllegalArgumentException {
		this.dealDamage(target, 2);
		
	}

}



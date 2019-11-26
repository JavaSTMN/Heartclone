package model.card;


import controller.Attacker;
import controller.Target;
import java.io.IOException;
import java.util.ArrayList;

import controller.Attacker;
import controller.Target;
import controller.manager.GameManager;


/**
 * 
 */

/**
 * @author adrien
 * @author MadKid
 *
 */
public class MinionCard extends Card implements Attacker, Target {

	private int healthPoints;
	private int maxHealtPoints;
	private int damagePoints;
	private boolean active;

	/**
	 * Constructor
	 * 
	 * @param healthPoints
	 * @param damagePoints
	 * @param active
	 */
	public MinionCard(int maxHealthPoints, int damagePoints, boolean active, String name, String description,
			int cristalCost) {
		super(name, description, cristalCost);
		this.maxHealtPoints = this.healthPoints = maxHealthPoints;
		this.damagePoints = damagePoints;
		this.active = active;
	}

	public MinionCard(int maxHealthPoints, int damagePoints, boolean active) {
		this.maxHealtPoints = this.healthPoints = maxHealthPoints;
		this.damagePoints = damagePoints;
		this.active = active;
	}

	public Integer getHealthPoints() {
		return this.healthPoints;
	}

	public void setHealthPoints(int value) {
		this.healthPoints = value;
	}

	public Integer getDamagePoints() {
		return this.damagePoints;
	}

	public void setDamagePoints(int value) {
		this.damagePoints = value;
	}
	
	public boolean getActive() {
		return this.active;
	}
	
	public void setActive(boolean value) {
		this.active = value;
	}

	/* METHODS REGION */

	// Attacker Interface //

	/**
	 * Allow the minion to attack a target
	 * 
	 * @param Target target: the card or Hero which will receive the damage
	 */
	@Override
	public void dealDamage(Target target) {
		target.receiveDamage(this.damagePoints);
		
		if(target instanceof MinionCard) {
			System.out.println("test");
			MinionCard mTarget = (MinionCard)target;
			this.receiveDamage(mTarget.damagePoints);
		}
		
		this.setActive(false); 
		this.setSelectedToAttack(false);
		this.setSelected(false);
	}
	
	@Override
	public void dealDamage(Target target, int amount) throws IllegalArgumentException {		
		
	}

	/**
	 * Disable the card. it's state becomes inactive
	 */
	@Override
	public void disable() {
		this.active = false;
	}

	/**
	 * Enable the card. it's state becomes active
	 */
	@Override
	public void enable() {
		this.active = true;
	}

	/**
	 * Return the state of the card
	 * 
	 * @return true if the card is active, false if not
	 */
	@Override
	public boolean getState() {
		return this.active;
	}

	// Target Interface //

	/**
	 * Allows the minion to receive damage.
	 * 
	 * @throws throw IllegalArgumentException if nb < 0
	 * @param int nb: amount of damage received
	 */
	@Override
	public void receiveDamage(int nb) throws IllegalArgumentException {

		if (nb <= 0)
			throw new IllegalArgumentException("Amount of damage is less or equal to 0");

		this.healthPoints -= nb;
		
		try {
			this.getObservable().notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!isAlive()) {
			discard(this);
		}
	}

	/**
	 * Allows the minion to receive health points
	 * 
	 * @throws throw IllegalArgumentException if amount < 0
	 * @param amount int: the amount of health to receive
	 */
	@Override
	public void receiveHealthPoints(int amount) throws IllegalArgumentException {

		if (amount < 0)
			throw new IllegalArgumentException("Amount of heal is less than 0");

		if (this.healthPoints + amount > this.maxHealtPoints)
			this.healthPoints = this.maxHealtPoints;
		else
			this.healthPoints += amount;
		
		try {
			this.getObservable().notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Inform if the minion is alive or not
	 * 
	 * @return true if the minion is alive, false if not
	 */
	public boolean isAlive() {
		if (this.healthPoints <= 0)
			return false;

		return true;
	}


	public void discard(Card card) {
		GameManager gameManager;
		try {
			gameManager = GameManager.getInstance();
			ArrayList<Card> cards = gameManager.getHeros()[0].getGameboard().getCards();
			if(cards.contains(card))
				gameManager.getHeros()[0].discard(card);
			else {
				gameManager.getHeros()[1].discard(card);
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

package model.card;

import model.Attacker;
import model.Target;

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
	public MinionCard(int maxHealthPoints, int damagePoints, boolean active) {
		this.maxHealtPoints = this.healthPoints = maxHealthPoints;
		this.damagePoints = damagePoints;
		this.active = active;
	}

	public int getHealthPoints() {
		return this.healthPoints;
	}

	public void setHealthPoints(int value) {
		this.healthPoints = value;
	}

	public int getDamagePoints() {
		return this.damagePoints;
	}

	public void setDamagePoints(int value) {
		this.damagePoints = value;
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
	}
	
	@Override
	public void dealDamage(Target target, int amount) throws IllegalArgumentException {
		this.dealDamage(target);
		
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

		if (nb < 0)
			throw new IllegalArgumentException("Amount of damage is less than 0");

		this.healthPoints -= nb;

		if (!isAlive()) {
			System.out.println("La carte a été détruite");
			//TODO: Send the card to the Discard
			// Solution: If the game manager contains the Discard instance and we have a singleton pattern
			// we can use the the addCard of Discard through the GameManager to send the card to the Discard
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
	}

	/**
	 * Inform if the minion is alive or not
	 * 
	 * @return true if the minion is alive, false if not
	 */
	public boolean isAlive() {
		if (this.healthPoints < 0)
			return false;

		return true;
	}

	

}

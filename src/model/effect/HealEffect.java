package model.effect;

import controller.Target;

/**
 * 
 */

/**
 * @author adrien
 *
 */
public class HealEffect extends Effect {
	
	private int amount;
	/**
	 * 
	 */
	public HealEffect(int amount) {
		this.amount = amount;
	}
	
	public void activateEffect(Target target, int amount) {
		target.receiveHealthPoints(amount);
	}
}

package model.effect;
/**
 * 
 */

import controller.Target;

/**
 * @author adrien
 *
 */
public class DealDamageEffect extends Effect {
	
	private int amount;
	/**
	 * 
	 */
	public DealDamageEffect(int amount) {
		this.amount = amount;
	}
	

	public void activateEffect(Target target, int amount){
		target.receiveDamage(amount);
	}

}

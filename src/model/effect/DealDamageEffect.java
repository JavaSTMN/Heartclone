package model.effect;
/**
 * 
 */

import controller.Target;
import model.hero.Hero;

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
	
	public void activateEffect(Target target){
		target.receiveDamage(this.amount);
	}
	
	public void activateEffect(Hero target){
		target.receiveDamage(this.amount);
	}

}

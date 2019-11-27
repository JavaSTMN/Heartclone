package model.effect;

import controller.Target;
import model.hero.Hero;

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
	
	public void activateEffect(Target target) {
		target.receiveHealthPoints(this.amount);
	}

	@Override
	public void activateEffect(Hero target) {
		target.receiveHealthPoints(this.amount);
		
	}
}

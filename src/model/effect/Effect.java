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
public abstract class Effect {

	/**
	 * 
	 */
	public Effect() {
		// TODO Auto-generated constructor stub
	}

	public abstract void activateEffect(Target target);
	
	public abstract void activateEffect(Hero target);
	

}

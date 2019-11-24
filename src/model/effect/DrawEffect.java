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
public class DrawEffect extends Effect {

	
	private int amount;
	/**
	 * 
	 */
	public DrawEffect(int amount) {
		this.amount = amount;
	}
	
	public void activateEffect(Hero target, int amount) {
		try {
			for(int i=0; i<amount ; i++) {
				target.draw();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

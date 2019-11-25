package model.card;

import java.util.ArrayList;

import controller.Target;
import controller.manager.GameManager;
import model.effect.Effect;
import model.hero.Hero;

/**
 * 
 */

/**
 * @author adrien
 *
 */
public class SpellCard extends Card {
	
	private int amount;
	
	private Effect effect;
	/**
	 * 
	 */
	public SpellCard(String name, String description, int cristalCost, Effect effect) {
		super(name, description, cristalCost);
		this.effect = effect;
	}
	
	public Effect getEffect() {
		return this.effect;
	}
	
	
	public void activateEffect(Target target) {
		effect.activateEffect(target);
		setSelected(false);
		discard(this);
	}
	
	public void activateEffect(Hero target) {
		effect.activateEffect(target);
		setSelected(false);
		discard(this);
		
	}

}

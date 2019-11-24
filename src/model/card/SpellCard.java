package model.card;

import java.util.ArrayList;

import model.effect.Effect;

/**
 * 
 */

/**
 * @author adrien
 *
 */
public class SpellCard extends Card {
	
	
	private ArrayList<Effect> effects;
	/**
	 * 
	 */
	public SpellCard(String name, String description, int cristalCost, Effect effect) {
		super(name, description, cristalCost);
		this.effects = new ArrayList<Effect>();
		this.effects.add(effect);
	}
	
	public void activateEffect() {
		for(Effect effect : effects) {
			effect.activateEffect();
		}
	}

}

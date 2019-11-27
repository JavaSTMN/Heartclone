package model.minioneffect;

import controller.Target;
import model.card.MinionCard;
import model.hero.Hero;

public abstract class MinionEffect {
	
	public MinionEffect() {

	}

	public abstract void activateEffect(MinionCard target);
}

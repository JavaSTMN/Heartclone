package model.minioneffect;

import model.card.MinionCard;

public class ChargeEffect extends MinionEffect {
	
	public ChargeEffect() {
		
	}
	
	@Override
	public void activateEffect(MinionCard target) {
		target.setActive(true);
	}


}

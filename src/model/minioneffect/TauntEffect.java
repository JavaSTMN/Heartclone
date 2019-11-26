package model.minioneffect;

import model.card.MinionCard;

public class TauntEffect extends MinionEffect{

	@Override
	public void activateEffect(MinionCard target) {
		target.setTaunt(true);
	}

}

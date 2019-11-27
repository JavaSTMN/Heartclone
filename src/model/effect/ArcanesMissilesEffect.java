package model.effect;

import java.util.ArrayList;
import java.util.Random;

import controller.Target;
import model.card.Card;
import model.card.MinionCard;
import model.hero.Hero;

public class ArcanesMissilesEffect extends Effect {

	private int amount = 1;

	/**
	 * 
	 */
	public ArcanesMissilesEffect() {
	}

	public void activateEffect(Hero target) {

		for (int i = 0; i < 3; i++) {
			int random = (int) (Math.random() * ((10)));

			if (random > 5 && target.getGameboard().getCardNumber() != 0) {
				int randomIndex = (int) (Math.random() * ((target.getGameboard().getCardNumber())));
				ArrayList<Card> cards = target.getGameboard().getCards();
				if (cards.get(randomIndex) instanceof MinionCard) {
					MinionCard card = (MinionCard) cards.get(randomIndex);
					card.receiveDamage(amount);
				}

			} else {
				target.receiveDamage(amount);
			}
		}

	}

	@Override
	public void activateEffect(Target target) {
		// TODO Auto-generated method stub
		
	}
}

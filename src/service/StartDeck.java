package service;

import java.util.ArrayList;
import java.util.Arrays;

import model.card.Card;
import model.card.MinionCard;
import model.card.SpellCard;
import model.effect.ArcanesMissilesEffect;
import model.effect.DealDamageEffect;
import model.effect.DrawEffect;
import model.effect.HealEffect;

public class StartDeck {
	private static ArrayList<Card> deck;
	
	public StartDeck() {
		
	}
	
	public static ArrayList<Card> getDeck() {
		
		DrawEffect drawEffect = new DrawEffect(2);
		HealEffect healEffect = new HealEffect(2);
		DealDamageEffect dealDamageEffect = new DealDamageEffect(2);
		
		
		deck = new ArrayList<Card>(Arrays.asList(
				new MinionCard(3, 2, false, "Clerc du soleil brisé", "blablabla", 3),
				new MinionCard(3, 2, false, "Clerc du soleil brisé", "blablabla", 3),
				new MinionCard(4, 5, false, "Yeti noroit", "", 4),
				new MinionCard(4, 5, false, "Yeti noroit", "", 4),
				new MinionCard(1, 1, false, "Loup des bois", "", 1),
				new MinionCard(1, 1, false, "Loup des bois", "", 1),
				new MinionCard(2, 3, false, "Crocillisque des rivières", "", 2),
				new MinionCard(2, 3, false, "Crocillisque des rivières", "", 2),
				new SpellCard("pioche", "permet de piocher 2 cartes", 3, drawEffect),
				new SpellCard("soin", "permet de soigner 2 dommages", 3, healEffect),
				new SpellCard("dmg", "inflige 2 dmg", 3, dealDamageEffect),
				new SpellCard("Projectiles des arcanes", "inflige 3 dmg aléatoires", 1, new ArcanesMissilesEffect()),
				new SpellCard("Projectiles des arcanes", "inflige 3 dmg aléatoires", 1, new ArcanesMissilesEffect())
		));
		
		return deck;
	}
}

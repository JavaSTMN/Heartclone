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
import model.minioneffect.ChargeEffect;

public class StartDeck {
	private static ArrayList<Card> deck;
	
	public StartDeck() {
		
	}
	
	public static ArrayList<Card> getDeck() {
		
		
		deck = new ArrayList<Card>(Arrays.asList(
				// Life, Dmg, Active, Name, Description, ManaCost
				new MinionCard(1, 1, false, "Docteur Vaudou", "Charge", 1, new ChargeEffect()),
				new MinionCard(2, 3, false, "Limon des marais acide", "", 2),
				new MinionCard(2, 3, false, "Limon des marais acide", "", 2),
				new MinionCard(3, 2, false, "Chasseuse de Tranchebauge", "", 3),
				new MinionCard(3, 2, false, "Chasseuse de Tranchebauge", "", 3),
				new MinionCard(2, 3, false, "Clerc du Soleil brisé", "", 3),
				new MinionCard(2, 3, false, "Clerc du Soleil brisé", "", 3),
				new MinionCard(4, 2, false, "Inventrice Gnome", "", 4),
				new MinionCard(4, 2, false, "Inventrice Gnome", "", 4),
				new MinionCard(5, 3, false, "Maitre bouclier de Sen'jin", "", 4),
				new MinionCard(5, 3, false, "Maitre bouclier de Sen'jin", "", 4),
				new MinionCard(5, 4, false, "Yéti noroît", "", 4),
				new MinionCard(7, 5, false, "Berserker Gurubashi", "", 5),
				new MinionCard(5, 5, false, "Chef de guerre loup de givre", "", 5),
				new MinionCard(5, 5, false, "Chef de guerre loup de givre", "", 5),
				new MinionCard(7, 4, false, "Archimage", "", 6),
				new MinionCard(3, 5, false, "Missilière téméraire", "", 6),
				new MinionCard(7, 6, false, "Ogre rochepoing", "", 6),
				new MinionCard(6, 6, false, "Champion de Hurlevent", "", 7),
				new MinionCard(6, 6, false, "Champion de Hurlevent", "", 7),
				// Name, Description, Cristal Cost, Effect
				new SpellCard("Eclair de givre", "Inflige 3 pts de dmg", 2, new DealDamageEffect(3)),
				new SpellCard("Eclair de givre", "Inflige 3 pts de dmg", 2, new DealDamageEffect(3)),
				new SpellCard("Intelligence des Arcanes", "permet de piocher 2 cartes", 3, new DrawEffect(2)),
				new SpellCard("Intelligence des Arcanes", "permet de piocher 2 cartes", 3, new DrawEffect(2)),
				new SpellCard("Mot de pouvoir: Soin", "permet de soigner 2 dommages", 1, new HealEffect(2)),
				new SpellCard("Mot de pouvoir: Soin", "permet de soigner 2 dommages", 1, new HealEffect(2)),
				new SpellCard("Boule de Feu", "inflige 6 pts de dmg", 4, new DealDamageEffect(6)),
				new SpellCard("Boule de Feu", "inflige 6 pts de dmg", 4, new DealDamageEffect(6)),
				new SpellCard("Projectiles des arcanes", "inflige 3 dmg aléatoires", 1, new ArcanesMissilesEffect()),
				new SpellCard("Projectiles des arcanes", "inflige 3 dmg aléatoires", 1, new ArcanesMissilesEffect())
		));
		
		return deck;
	}
}

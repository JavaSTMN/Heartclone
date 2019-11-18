package service;

import java.util.ArrayList;
import java.util.Arrays;

import model.card.Card;
import model.card.MinionCard;

public class StartDeck {
	private static ArrayList<Card> deck;
	
	public StartDeck() {
		
	}
	
	public static ArrayList<Card> getDeck() {
		deck = new ArrayList<Card>(Arrays.asList(
				new MinionCard(3, 2, false, "Clerc du soleil brisé", "", 3),
				new MinionCard(3, 2, false, "Clerc du soleil brisé", "", 3),
				new MinionCard(4, 5, false, "Yeti noroit", "", 4),
				new MinionCard(4, 5, false, "Yeti noroit", "", 4),
				new MinionCard(1, 1, false, "Loup des bois", "", 1),
				new MinionCard(1, 1, false, "Loup des bois", "", 1),
				new MinionCard(2, 3, false, "Crocillisque des rivières", "", 2),
				new MinionCard(2, 3, false, "Crocillisque des rivières", "", 2)
		));
		
		return deck;
	}
}

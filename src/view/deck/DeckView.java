package view.deck;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.card.CardContainer;

public class DeckView extends JPanel{
	
	public DeckView(CardContainer hand) {
		
		JPanel deckContainer = new JPanel();
		deckContainer.setLayout(new FlowLayout());
		
		
		JPanel deck = new JPanel();
		deck.setPreferredSize(new Dimension(100, 150));
		deck.setLayout(new BoxLayout(deck, BoxLayout.PAGE_AXIS));
		
		JLabel deckText = new JLabel();
		deckText.setText("DECK");
		deckText.setAlignmentY(CENTER_ALIGNMENT);
		deckText.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel cardNumber = new JLabel();
		cardNumber.setText(hand.getCardNumber().toString());
		cardNumber.setAlignmentY(CENTER_ALIGNMENT);
		cardNumber.setAlignmentX(CENTER_ALIGNMENT);
		
		
		deck.add(deckText);
		deck.add(cardNumber);
		deckContainer.add(deck);
		
		if(hand.getCardNumber() == 0) {
			Border border = BorderFactory.createLineBorder(Color.RED, 2);
			deck.setBorder(border);
		}
		
		this.setBackground(Color.DARK_GRAY);
		this.add(deckContainer);
		
	}
}

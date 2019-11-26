package view.deck;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.card.CardContainer;

public class DeckView extends JPanel implements MouseListener{
	
	public DeckView(CardContainer deck) {
		
		JPanel deckContainer = new JPanel();
		deckContainer.setLayout(new FlowLayout());
		
		
		JPanel deckView = new JPanel();
		deckView.setPreferredSize(new Dimension(100, 130));
		deckView.setLayout(new BoxLayout(deckView, BoxLayout.PAGE_AXIS));
		
		JLabel deckText = new JLabel();
		deckText.setText("DECK");
		deckText.setAlignmentY(CENTER_ALIGNMENT);
		deckText.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel cardNumber = new JLabel();
		cardNumber.setText(deck.getCardNumber().toString());
		cardNumber.setAlignmentY(CENTER_ALIGNMENT);
		cardNumber.setAlignmentX(CENTER_ALIGNMENT);
		
		
		deckView.add(deckText);
		deckView.add(cardNumber);
		deckContainer.add(deckView);
		
		if(deck.getCardNumber() == 0) {
			Border border = BorderFactory.createLineBorder(Color.RED, 2);
			deckView.setBorder(border);
		}
		
		this.setBackground(Color.DARK_GRAY);
		this.add(deckContainer);
		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

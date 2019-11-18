package view.hand;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.card.Card;
import service.StartDeck;
import view.hand.card.CardView;

public class HandView extends JPanel implements MouseListener {
	
	ArrayList<CardView> cardViews;
	
	public HandView() throws IOException {
		cardViews = new ArrayList<CardView>();
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBackground(Color.DARK_GRAY);
		
		for(Card card : StartDeck.getDeck()) {
			CardView cardView = new CardView(card);
			cardViews.add(cardView);
			cardView.addMouseListener(this);
			this.add(cardView);
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(CardView cardView : this.cardViews) {
			Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
			cardView.setSelected(false);
			cardView.setBorder(border);
		}
		
		if(e.getSource() instanceof CardView) {
			CardView cardView = (CardView)(e.getSource());
			Border border = BorderFactory.createLineBorder(Color.RED, 4);
			cardView.setSelected(true);
			cardView.setBorder(border);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Border border = BorderFactory.createLineBorder(Color.GRAY, 4);
		
		if(e.getSource() instanceof CardView) {
			CardView cardView = (CardView)(e.getSource());
			
			if(!cardView.getSelected())
				cardView.setBorder(border);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
		if(e.getSource() instanceof CardView) {
			CardView cardView = (CardView)(e.getSource());
			Border border;
			
			if(cardView.getSelected())
				border = BorderFactory.createLineBorder(Color.RED, 4);
			else
				border = BorderFactory.createLineBorder(Color.GRAY, 2);
			
			cardView.setBorder(border);
		}
		
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}

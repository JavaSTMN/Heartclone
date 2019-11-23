/**
 * @author MadKid
 */
package view.hand;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;


import controller.IObserver;
import controller.Observable;
import model.card.Card;
import model.card.CardContainer;
import model.card.MinionCard;
import model.hero.Hero;
import view.deck.DeckView;
import view.hand.card.CardView;


public class HandView extends JPanel implements MouseListener, IObserver {

	ArrayList<CardView> cardViews; // array of all the cards the player has in is hands

	Hero hero;
	
	JPanel cardContainer;

	public HandView(Hero hero) throws IOException {
		
		this.hero = hero;
		this.hero.getHand().getObservable().subscribe(this);
		
		// JPanel for the cards
		cardContainer = new JPanel();
		cardContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
		cardContainer.setBackground(Color.DARK_GRAY);
		
		
		// Widget setup
		this.setLayout(new FlowLayout());
		this.setBackground(Color.DARK_GRAY);

		cardViews = new ArrayList<CardView>();
		
		
		// We create the CardViews from the card models
		for (Card card : hero.getHand().getCards()) {
			cardViews.add(new CardView(card));
		}

		// Instantiation of the card views
		for (CardView cardView : cardViews) {
			cardView.addMouseListener(this);
			cardContainer.add(cardView);
		}
		
		JPanel filler = new JPanel();
		filler.setBackground(Color.DARK_GRAY);
		
		this.add(new DeckView(this.hero.getDeck()));
		this.add(cardContainer);
		this.add(filler);
	}

	/**
	 * If we click a card, it becomes selected and any other card that was selected
	 * before becomes deselected
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// We deselect all the cards
		for (CardView cardView : this.cardViews) {
			Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
			cardView.setSelected(false);
			cardView.setBorder(border);
		}
		
		// We select the card clicked by the player
		if (e.getSource() instanceof CardView) {
			CardView cardView = (CardView) (e.getSource());
			Border border = BorderFactory.createLineBorder(Color.ORANGE, 4);
			cardView.setSelected(true);
			cardView.setBorder(border);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// We create a larger border when the player has the mouse on top of a card
		Border border = BorderFactory.createLineBorder(Color.GRAY, 4);

		if (e.getSource() instanceof CardView) {
			CardView cardView = (CardView) (e.getSource());

			if (!cardView.getSelected())
				cardView.setBorder(border);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// We create a smaller border when the mouse leaves the card.
		// If the card is selected we create a larger red border
		if (e.getSource() instanceof CardView) {
			CardView cardView = (CardView) (e.getSource());
			Border border;

			if (cardView.getSelected())
				border = BorderFactory.createLineBorder(Color.ORANGE, 4);
			else
				border = BorderFactory.createLineBorder(Color.GRAY, 2);

			cardView.setBorder(border);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
////this.hero.draw();
	@Override
	public void update() {
		cardViews = new ArrayList<CardView>();

		// We remove all the cards
		this.removeAll();
		
		this.cardContainer = new JPanel();
		this.cardContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
		cardContainer.setBackground(Color.DARK_GRAY);
		
		this.setLayout(new FlowLayout());
		this.setBackground(Color.DARK_GRAY);
		
		// We add all the cards with the modifications
		for (Card card : this.hero.getHand().getCards()) {
			try {
				cardViews.add(new CardView(card));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Instantiation of the card views
		for (CardView cardView : cardViews) {
			cardView.addMouseListener(this);
			this.cardContainer.add(cardView);
		}
		
		JPanel filler = new JPanel();
		filler.setBackground(Color.DARK_GRAY);
		
		this.add(new DeckView(this.hero.getDeck()));
		this.add(cardContainer);
		this.add(filler);
		this.repaint();
		this.revalidate();
	}

	@Override
	public void setObservable(Observable obj) {
		// TODO Auto-generated method stub

	}

}

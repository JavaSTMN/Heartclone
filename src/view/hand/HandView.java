/**
 * @author MadKid
 */
package view.hand;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
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
import view.hand.card.CardView;

public class HandView extends JPanel implements MouseListener, IObserver{
	
	ArrayList<CardView> cardViews; // array of all the cards the player has in is hands
	
	Hero hero;
	
	public HandView(Hero hero) throws IOException {
		this.hero = hero;
		this.hero.getHand().getObservable().subscribe(this);
		
		CardContainer hand = hero.getHand();
		
		// Widget setup
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBackground(Color.DARK_GRAY);
				
		cardViews = new ArrayList<CardView>();
		
		// We create the CardViews from the card models
		for(Card card : hand.getCards()) {
			cardViews.add(new CardView(card));
		}
		
		//Instantiation of the card views
		for(CardView cardView : cardViews) {
			cardView.addMouseListener(this);
			this.add(cardView);
		}
	}
	
	/**
	 * If we click a card, it becomes selected and any other card that was selected before becomes deselected
	 */
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
			
			try {
				hero.play(cardView.getCard());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

	@Override
	public void update() {
		cardViews = new ArrayList<CardView>();
		
		// We remove all the cards
		for(Component card: this.getComponents()) {
			this.remove(card);
		}
		
		// We add all the cards with the modifications
		for(Card card : this.hero.getHand().getCards()) {
			try {
				cardViews.add(new CardView(card));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Instantiation of the card views
		for(CardView cardView : cardViews) {
			cardView.addMouseListener(this);
			this.add(cardView);
		}
		
		this.repaint();
	}

	@Override
	public void setObservable(Observable obj) {
		// TODO Auto-generated method stub
		
	}
	

}

package view.board;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;


import controller.IObserver;
import controller.Observable;
import model.card.Card;
import model.hero.Hero;
import view.hand.card.CardView;

public class BoardView extends JPanel implements IObserver, MouseListener {
	
	Hero hero;
	
	public BoardView(Hero hero) {
		this.hero = hero;
		this.hero.getGameboard().getObservable().subscribe(this);
		
		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		this.setBorder(border);
		this.setBackground(new Color(47, 59, 79));
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.addMouseListener(this);
		
		for(Card card: this.hero.getGameboard().getCards()) {
			try {
				this.add(new CardView(card));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void update() {
		// We remove all the card views
		this.removeAll();
		
		// We recreate the card Views with the updated data
		for(Card card: this.hero.getGameboard().getCards()) {
			try {
				this.add(new CardView(card));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// VERY IMPORTANT
		this.repaint();
		// VERY IMPORTANT
		this.revalidate(); 
	}

	@Override
	public void setObservable(Observable obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// If the player click his board with a card selected, he plays it
		for(Card card: this.hero.getHand().getCards()) {
			if(card.getSelected()) {
				try {
					this.hero.play(card);
				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// We set an hand cursor if the player can play a card
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		for(Card card: this.hero.getHand().getCards()) {
			if(card.getSelected()) {
				this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
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
	
}

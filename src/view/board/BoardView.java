package view.board;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.IObserver;
import controller.Observable;
import model.card.Card;
import model.hero.Hero;
import view.hand.card.CardView;

public class BoardView extends JPanel implements IObserver {
	
	Hero hero;
	
	public BoardView(Hero hero) {
		this.hero = hero;
		this.hero.getGameboard().getObservable().subscribe(this);
		
		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		this.setBorder(border);
		this.setBackground(new Color(47, 59, 79));
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
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
		this.removeAll();
		
		for(Card card: this.hero.getGameboard().getCards()) {
			try {
				this.add(new CardView(card));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.repaint();
		
	}

	@Override
	public void setObservable(Observable obj) {
		// TODO Auto-generated method stub
		
	}
	
}

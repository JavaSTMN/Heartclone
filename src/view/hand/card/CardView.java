package view.hand.card;

import service.ImageFetcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.IObserver;
import controller.Observable;
import controller.manager.GameManager;
import service.StretchIcon;
import model.card.Card;
import model.card.MinionCard;
import model.hero.Hero;

public class CardView extends JPanel implements IObserver, MouseListener {
	
	// Card
	private JLabel mana;
	private JLabel image;
	private JLabel description;
	
	// Minion Card Specific
	private JLabel attack;
	private JLabel health;
	
	// Config
	private int panelWidth = 100;
	private int panelHeight = 150;
	
	private boolean selected;
	private Card card;
	private Hero hero;

	
	public CardView(Card card, Hero hero) throws IOException {
		this.card = card;
		this.card.getObservable().subscribe(this);
		
		this.hero = hero;
		
		// JPanel configuration
		this.setOpaque(false);
		this.selected = false;
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setSize(panelWidth, panelHeight);
		this.setPreferredSize(new Dimension(panelWidth, panelHeight));
	
		// Default card border
		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		this.setBorder(border);
		
		// Mana label
		this.mana = new JLabel();
		this.mana.setText("Mana: "+card.getCristalCost().toString());
		this.mana.setFont(new Font(Font.DIALOG, Font.BOLD,  15));
		this.mana.setForeground(Color.WHITE);
		
		// Image label
		this.image = new JLabel();
		
		Image resizedImage = new ImageIcon("assets/card-images/ex-001.jpg").getImage(); // transform it 
		resizedImage = resizedImage.getScaledInstance(this.getWidth(), (int)(this.getHeight() * 0.3f), Image.SCALE_SMOOTH); // scale it the smooth way  
		ImageIcon img = new ImageIcon(resizedImage);  // transform it back
		this.image.setIcon(img);
		
		// Description Label
		this.description = new JLabel();
		this.description.setText(card.getDescription());
		
		
		this.add(mana);
		this.add(image);
		this.add(description);
		
		// Specific layout for a minion card
		if(card instanceof MinionCard) {
			MinionCard minionCard = (MinionCard)card;
			
			// Attack label
			attack = new JLabel();
			attack.setText("Atk: "+minionCard.getDamagePoints().toString());
			this.add(attack);
			attack.setFont(new Font(Font.DIALOG, Font.BOLD,  15));
			attack.setForeground(Color.WHITE);
			
			// Health label
			health = new JLabel();
			health.setText("Vie: "+minionCard.getHealthPoints().toString());
			this.add(health);
			health.setFont(new Font(Font.DIALOG, Font.BOLD,  15));
			health.setForeground(Color.WHITE);
			
		}

		this.setVisible(true);
	}

	
	public void setSelected(boolean value) {
		this.selected = value;
		this.card.setSelected(value);
	}
	
	public boolean getSelected() {
		return this.selected;
	}
	
	public Card getCard() {
		return this.card;
	}


	@Override
	public void update() {
		System.out.println("update on CardView called");
		if(this.card instanceof MinionCard) {
			MinionCard card = (MinionCard)this.card;
			this.health.setText("Vie: "+card.getHealthPoints().toString());
			this.attack.setText("Atk: "+card.getDamagePoints().toString());
		}
	}


	@Override
	public void setObservable(Observable obj) {
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// If the player clicks on his card he selects it. 
		// If he clicks on the opponents card with a card selected, he attacks it
		if(this.hero.getIsTurn()) {
			if(this.card.getSelectedToAttack()) {
				
			}else {
				for(Card card : hero.getGameboard().getCards()) {
					card.setSelectedToAttack(false);
					card.setSelected(false);
					
				}
				for(Card card : hero.getHand().getCards()) {
					card.setSelected(false);
					Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
					this.setBorder(border);
				}
				
				card.setSelectedToAttack(true);
				Border border = BorderFactory.createLineBorder(Color.ORANGE, 4);
				this.setBorder(border);
			}
			
		}else{
			try {
				
				Hero opponent = GameManager.getInstance().getOpponent(this.hero);
				
				if(this.card instanceof MinionCard) {
					MinionCard mCard = (MinionCard)this.card;
					for(Card attackerCard : opponent.getGameboard().getCards()) {
						if(attackerCard.getSelectedToAttack()) {
							if(attackerCard instanceof MinionCard) {
								MinionCard mAttackerCard = (MinionCard)attackerCard;
								mCard.receiveDamage(mAttackerCard.getDamagePoints());
							}
						}
					}
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
		}
//		if(e.getSource() instanceof CardView) {
//			CardView cardView = (CardView)e.getSource();
//			Card card = cardView.getCard();
//			if(card instanceof MinionCard) {
//				MinionCard mCard = (MinionCard)card;
//				mCard.receiveDamage(1);
//			}
//		}	
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


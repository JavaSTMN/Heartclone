package view.hero;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.IObserver;
import controller.Observable;
import controller.manager.GameManager;
import model.card.Card;
import model.card.MinionCard;
import model.hero.Hero;
import view.hand.card.CardView;

public class HeroView extends JPanel implements MouseListener, IObserver {

	private Hero hero;

	private JLabel heroHealth;
	private JLabel heroCristals;
	private JPanel spellButton;
	
	private JPanel containerButton;

	private boolean selected;

	public HeroView(Hero hero) {
		this.hero = hero;
		this.hero.getObservable().subscribe(this);
		
		if(this.hero.getIsTurn())
			this.addMouseListener(this);

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel containerLabel = new JPanel();
		containerLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
		containerLabel.setBackground(Color.DARK_GRAY);

		containerButton = new JPanel();
		containerButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		containerButton.setBackground(Color.DARK_GRAY);
		
		if(this.hero.getIsTurn())
			containerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		
		JPanel containerCristals = new JPanel();
		containerCristals.setLayout(new FlowLayout(FlowLayout.CENTER));
		containerCristals.setBackground(Color.DARK_GRAY);
		

		heroHealth = new JLabel("VIE: " + this.hero.getLifePoints().toString());
		heroHealth.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		heroHealth.setForeground(Color.WHITE);
		
		heroCristals = new JLabel("CRISTALS: "+ this.hero.getCristals()+"/10");
		heroCristals.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		heroCristals.setForeground(Color.WHITE);

		spellButton = new JPanel();
		spellButton.add(new JLabel("Utiliser Sort"));

		containerLabel.add(heroHealth);
		containerButton.add(spellButton);
		containerCristals.add(heroCristals);

		try {
			if (GameManager.getInstance().isPlayerOne(this.hero)) {
				this.add(containerLabel);
				this.add(containerButton);
				this.add(containerCristals);
			} else {
				this.add(containerCristals);
				this.add(containerButton);
				this.add(containerLabel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (this.hero.getIsTurn()) {
			if (this.hero.getSpellSelected()) {

			} else {
				for (Card card : this.hero.getHand().getCards()) {
					card.setSelected(false);
					card.setSelectedToAttack(false);
				}

				// We deselect all the cards on the gameboard
				for (Card card : this.hero.getGameboard().getCards()) {
					card.setSelectedToAttack(false);
				}

				// We select the spell
				if (this.hero.canUseSpell()) {
					this.selected = true;
					this.hero.setSpellSelected(true);
				}
			}

		} else {

			Hero opponent;
			try {
				opponent = GameManager.getInstance().getOpponent(this.hero);

				if (opponent.getSpellSelected()) {
					opponent.useSpell(this.hero);
					
				}
				
				for(Card card: opponent.getGameboard().getCards()) {
					if(card.getSelectedToAttack()) {
						if(card instanceof MinionCard) {
							MinionCard mCard = (MinionCard)card;
							this.hero.receiveDamage(mCard.getDamagePoints());
						}
					}
				}
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

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
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		
		this.heroHealth.setText("VIE: " + this.hero.getLifePoints().toString());
		this.heroCristals.setText("CRISTALS: "+this.hero.getCristals()+"/10");

		if (this.hero.getSpellSelected()) {
			spellButton.setBackground(Color.ORANGE);
		} else {
			spellButton.setBackground(Color.WHITE);
		}
		
		if(this.hero.getIsTurn()) {
			containerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.addMouseListener(this);
		} else {
			containerButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			this.addMouseListener(null);
		}
		

	}

	@Override
	public void setObservable(Observable obj) {
		// TODO Auto-generated method stub

	}

}

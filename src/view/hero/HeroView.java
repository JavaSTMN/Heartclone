package view.hero;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.IObserver;
import controller.Observable;
import controller.manager.GameManager;
import model.card.Card;
import model.card.MinionCard;
import model.card.SpellCard;
import model.effect.DealDamageEffect;
import model.effect.HealEffect;
import model.hero.Hero;
import view.hand.card.CardView;

public class HeroView extends JPanel implements MouseListener, IObserver {

	private Hero hero;

	private JLabel heroHealth;
	private JLabel heroCristals;
	private JPanel spellButton;
	private JLabel heroImage;

	private JPanel containerHero;
	private JPanel containerLabel;
	private JPanel containerCristals;
	
	private JPanel container;

	private boolean selected;

	public HeroView(Hero hero) {
		this.hero = hero;
		this.hero.getObservable().subscribe(this);

		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setOpaque(true);
		this.setBackground(Color.DARK_GRAY);

		this.container = new JPanel();
		this.container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		this.container.setOpaque(false);
		
		this.containerLabel = new JPanel();
		
		this.containerHero = new JPanel();
		this.containerHero.addMouseListener(this);
		this.containerHero.setLayout(new BoxLayout(containerHero, BoxLayout.PAGE_AXIS));

	
		if (this.hero.getIsTurn())
			containerHero.setCursor(new Cursor(Cursor.HAND_CURSOR));

		containerCristals = new JPanel();
		
		// HEALTH LABEL
		heroHealth = new JLabel("VIE: " + this.hero.getLifePoints().toString());
		heroHealth.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		heroHealth.setForeground(new Color(212, 53, 21));
		
		
		// CRISTALS LABEL
		heroCristals = new JLabel("CRISTALS: " + this.hero.getCristals() + "/10");
		heroCristals.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		heroCristals.setForeground(new Color(21, 167, 212));
		
		
		// SPELL BUTTON
		spellButton = new JPanel();
		spellButton.add(new JLabel("Utiliser Sort"));
		
		
		// HERO IMAGE
		Image heroImg = new ImageIcon("assets/card-images/hero.png").getImage();
		heroImg = heroImg.getScaledInstance(100, 60,
				Image.SCALE_SMOOTH); // scale it the smooth way
		ImageIcon img = new ImageIcon(heroImg); // transform it back
		
		this.heroImage = new JLabel();
		this.heroImage.setIcon(img);
		this.heroImage.setAlignmentX(CENTER_ALIGNMENT);

		//containerLabel.add(heroHealth);
		containerHero.add(heroImage);
		containerHero.add(spellButton);
		containerCristals.add(heroCristals);
		

		try {
			if (GameManager.getInstance().isPlayerOne(this.hero)) {
				heroHealth.setAlignmentX(RIGHT_ALIGNMENT);
				heroCristals.setAlignmentX(RIGHT_ALIGNMENT);
				this.add(containerHero);
				this.container.add(heroHealth);
				this.container.add(heroCristals);
				this.add(container);
			} else {
				heroHealth.setAlignmentX(LEFT_ALIGNMENT);
				heroCristals.setAlignmentX(LEFT_ALIGNMENT);
				this.container.add(heroCristals);
				this.container.add(heroHealth);
				this.add(container);
				this.add(containerHero);
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

				} else {

					for (Card card : opponent.getGameboard().getCards()) {
						if (card.getSelectedToAttack()) {
							if (card instanceof MinionCard) {
								MinionCard mCard = (MinionCard) card;
								mCard.dealDamage(this.hero);
							}
						}
					}

					for (Card attackerCard : opponent.getHand().getCards()) {
						if (attackerCard.getSelected()) {
							if (attackerCard instanceof SpellCard) {
								SpellCard sCard = (SpellCard) attackerCard;
								if (sCard.getEffect() instanceof DealDamageEffect
										|| sCard.getEffect() instanceof HealEffect) {
									sCard.activateEffect(this.hero);
								}
							}
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
		if(!this.hero.getIsTurn()) {
			
			try {
				Hero opponent = GameManager.getInstance().getOpponent(this.hero);
				if(opponent.getSpellSelected()) 
					this.containerHero.setBorder(BorderFactory.createLineBorder(Color.CYAN));
				
				for(Card card: opponent.getGameboard().getCards()) {
					if(card.getSelectedToAttack())
						this.containerHero.setBorder(BorderFactory.createLineBorder(Color.CYAN));
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.containerHero.setBorder(new EmptyBorder(0,0,0,0));

	}

	@Override
	public void update() {

		this.heroHealth.setText("VIE: " + this.hero.getLifePoints().toString());
		this.heroCristals.setText("CRISTALS: " + this.hero.getCristals() + "/10");

		if (this.hero.getSpellSelected()) {
			spellButton.setBackground(Color.ORANGE);
		} else {
			spellButton.setBackground(Color.WHITE);
		}

		if (this.hero.getIsTurn()) {
			containerHero.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else {
			containerHero.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

	}

	@Override
	public void setObservable(Observable obj) {
		// TODO Auto-generated method stub

	}

}

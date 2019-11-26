package view.hand.card;

import service.ImageFetcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
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
import javax.swing.border.EmptyBorder;

import controller.IObserver;
import controller.Observable;
import controller.Target;
import controller.manager.GameManager;
import service.StretchIcon;
import model.card.Card;
import model.card.MinionCard;
import model.card.SpellCard;
import model.effect.ArcanesMissilesEffect;
import model.effect.DealDamageEffect;
import model.effect.DrawEffect;
import model.effect.HealEffect;
import model.hero.Hero;

public class CardView extends JPanel implements IObserver, MouseListener {

	// Card
	private JLabel mana;
	private JLabel image;
	private JLabel name;
	private JLabel description;

	// Minion Card Specific
	private JLabel attack;
	private JLabel health;

	private JPanel body;
	private JPanel footer;

	// Config
	private int panelWidth = 110;
	private int panelHeight = 140;

	private boolean selected;
	private boolean selectedToAttack;
	private boolean isActive = false;

	private Card card;
	private Hero hero;

	public CardView(Card card, Hero hero) throws IOException {
		this.card = card;
		this.card.getObservable().subscribe(this);
		this.hero = hero;

		// JPanel configuration
		this.setOpaque(true);
		this.selected = false;
		this.selectedToAttack = false;
		this.setLayout(new BorderLayout());
		this.setSize(panelWidth, panelHeight);
		this.setPreferredSize(new Dimension(panelWidth, panelHeight));
		this.setBackground(Color.DARK_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		
		if (this.hero.getIsTurn())
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// BODY
		this.body = new JPanel();
		this.body.setLayout(new BoxLayout(this.body, BoxLayout.PAGE_AXIS));
		this.body.setBackground(Color.DARK_GRAY);
		
		if (this.card.getSelected()) 
			this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
		
		if (this.card.getSelectedToAttack()) 
			this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
	
		// CARD NAME
		this.name = new JLabel();
		this.name.setText("<html>" + card.getName() + "</html>");
		this.name.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
		this.name.setForeground(Color.WHITE);

		// CARD MANA
		this.mana = new JLabel();
		this.mana.setText("Mana: " + card.getCristalCost().toString());
		this.mana.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		this.mana.setForeground(Color.WHITE);
		
		// CARD IMAGE
		Image resizedImage = this.choseImage();

		resizedImage = resizedImage.getScaledInstance(this.getWidth(), (int) (this.getHeight() * 0.3f),
				Image.SCALE_SMOOTH); // scale it the smooth way
		ImageIcon img = new ImageIcon(resizedImage); // transform it back

		this.image = new JLabel();
		this.image.setIcon(img);

		// CARD DESCRIPTION
		this.description = new JLabel();
		this.description.setText("<html>" + card.getDescription() + "</html>");
		this.description.setForeground(Color.LIGHT_GRAY);
		
		// ADDING TO THE BODY CONTAINER
		this.body.add(mana);
		this.body.add(image);
		this.body.add(name);
		this.body.add(description);
		//ADDING TO THE CARD VIEW
		this.add(body, BorderLayout.CENTER);

		// Specific layout for a minion card
		if (card instanceof MinionCard) {
			MinionCard minionCard = (MinionCard) card;
			
			// FOOTER
			this.footer = new JPanel();	
			this.footer.setLayout(new GridLayout(0, 2));
			this.footer.setOpaque(false);

			// CARD ATTACK
			attack = new JLabel();
			attack.setText("Atk: " + minionCard.getDamagePoints().toString());
			attack.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
			attack.setForeground(Color.WHITE);
			attack.setAlignmentX(CENTER_ALIGNMENT);
			

			// CARD HEALTH
			health = new JLabel();
			health.setText("Vie: " + minionCard.getHealthPoints().toString());
			health.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
			health.setForeground(Color.WHITE);
			
			// ADDING TO FOOTER CONTAINER
			footer.add(attack);
			footer.add(health);
			// ADDING TO CARD VIEW
			this.add(footer, BorderLayout.PAGE_END);
		}
	}

	public void setSelected(boolean value) {
		this.selected = value;
		this.card.setSelected(value);
	}

	public void setSelectedToAttack(boolean value) {
		this.selectedToAttack = value;
		this.card.setSelectedToAttack(value);
	}

	public boolean getSelected() {
		return this.selected;
	}

	public Card getCard() {
		return this.card;
	}

	public Image choseImage() {

		if (this.card instanceof MinionCard) {
			return new ImageIcon("assets/card-images/minion.jpg").getImage(); // transform it
		}

		if (this.card instanceof SpellCard) {
			SpellCard sCard = (SpellCard) this.card;
			if (sCard.getEffect() instanceof HealEffect) {
				return new ImageIcon("assets/card-images/heal-spell.jpg").getImage();
			}

			if (sCard.getEffect() instanceof DrawEffect) {
				return new ImageIcon("assets/card-images/draw-spell.jpg").getImage();
			}

			if (sCard.getEffect() instanceof ArcanesMissilesEffect) {
				return new ImageIcon("assets/card-images/arcane-missiles-spell.png").getImage();
			}
		}

		return new ImageIcon("assets/card-images/damage-spell.jpg").getImage();
	}

	@Override
	public void update() {
		if (this.card instanceof MinionCard) {

			MinionCard card = (MinionCard) this.card;
			this.health.setText("Vie: " + card.getHealthPoints().toString());
			this.attack.setText("Atk: " + card.getDamagePoints().toString());
		}

		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		this.setBorder(border);

		if (this.card.getSelected() || this.card.getSelectedToAttack()) {
			border = BorderFactory.createLineBorder(Color.ORANGE, 4);
			this.setBorder(border);
		}
	}

	@Override
	public void setObservable(Observable obj) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		boolean spellPlayed = false;
		// If the player clicks on his card he selects it.
		// If he clicks on the opponents card with a card selected, he attacks it
		if (this.hero.getIsTurn()) {

			// TODO: check if the player wants to play a spell card
			// TODO: if its to play a spell card the player should not select a card

			// We play a DealDamageEffect or a HealEffect
			for (Card card : this.hero.getHand().getCards()) {
				if (card.getSelected()) {
					if (card instanceof SpellCard) {
						System.out.println("activateSpell");
						SpellCard sCard = (SpellCard) card;
						if (this.card instanceof MinionCard) {
							MinionCard mCard = (MinionCard) this.card;
							if (sCard.getEffect() instanceof DealDamageEffect
									|| sCard.getEffect() instanceof HealEffect) {
								sCard.activateEffect(mCard);
								spellPlayed = true;
							}
						}

					}
				}
			}

			if (!spellPlayed) {
				if (this.card.getSelectedToAttack()) {

				} else {
					for (Card card : hero.getGameboard().getCards()) {
						card.setSelectedToAttack(false);
						card.setSelected(false);
					}
					for (Card card : hero.getHand().getCards()) {
						card.setSelected(false);
						// Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
						// this.setBorder(border);
					}

					this.hero.setSpellSelected(false);

					card.setSelectedToAttack(true);
					// Border border = BorderFactory.createLineBorder(Color.ORANGE, 4);
					// this.setBorder(border);
				}
			}
		} else {
			try {

				Hero opponent = GameManager.getInstance().getOpponent(this.hero);

				if (this.card instanceof MinionCard) {
					MinionCard mCard = (MinionCard) this.card;

					if (opponent.getSpellSelected()) {
						opponent.useSpell(mCard);
					} else {
						for (Card attackerCard : opponent.getGameboard().getCards()) {
							if (attackerCard.getSelectedToAttack()) {
								if (attackerCard instanceof MinionCard) {
									MinionCard mAttackerCard = (MinionCard) attackerCard;
									mAttackerCard.dealDamage(mCard);
									mAttackerCard.setSelectedToAttack(false);
								}
							}
						}

						for (Card attackerCard : opponent.getHand().getCards()) {
							if (attackerCard.getSelected()) {
								if (attackerCard instanceof SpellCard) {
									SpellCard sCard = (SpellCard) attackerCard;
									if (sCard.getEffect() instanceof DealDamageEffect
											|| sCard.getEffect() instanceof HealEffect) {
										if (this.card instanceof MinionCard) {
											MinionCard target = (MinionCard) this.card;
											this.hero.activateSpell(sCard, target);
										}
									}
								}
							}
						}
					}
				}

				if (this.card instanceof SpellCard) {
					for (Card card : this.hero.getHand().getCards()) {
						if (card.getSelected()) {
							if (card instanceof SpellCard) {
								System.out.println("activateSpell");
								SpellCard sCard = (SpellCard) card;
								if (this.card instanceof MinionCard) {
									MinionCard mCard = (MinionCard) this.card;
									if (sCard.getEffect() instanceof DealDamageEffect
											|| sCard.getEffect() instanceof HealEffect)
										this.hero.activateSpell(sCard, mCard);
									spellPlayed = true;
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

		if (this.card instanceof MinionCard && this.hero.getIsTurn()) {
			MinionCard mCard = (MinionCard) this.card;
			if (!mCard.getActive()) {
				Border border = BorderFactory.createLineBorder(Color.RED, 4);
				this.setBorder(border);
			} else {
				Border border = BorderFactory.createLineBorder(Color.GRAY, 4);
				this.setBorder(border);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {

		if (this.card.getSelectedToAttack()) {
			Border border = BorderFactory.createLineBorder(Color.ORANGE, 4);
			this.setBorder(border);
		} else {
			Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
			this.setBorder(border);
		}
	}
}

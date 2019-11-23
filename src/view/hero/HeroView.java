package view.hero;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.IObserver;
import controller.Observable;
import controller.manager.GameManager;
import model.card.Card;
import model.hero.Hero;
import view.hand.card.CardView;

public class HeroView extends JPanel implements MouseListener, IObserver{
	
	private Hero hero;
	
	private JLabel heroHealth;
	private JPanel spellButton;
	
	private boolean selected;
	
	public HeroView(Hero hero) {
		this.hero = hero;
		this.hero.getObservable().subscribe(this);
		this.addMouseListener(this);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		

		JPanel containerLabel = new JPanel();
		containerLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
		containerLabel.setBackground(Color.DARK_GRAY);
		
		JPanel containerButton = new JPanel();
		containerButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		containerButton.setBackground(Color.DARK_GRAY);
		
		heroHealth = new JLabel("VIE: "+this.hero.getLifePoints().toString());
		heroHealth.setFont(new Font(Font.DIALOG, Font.BOLD,  15));
		heroHealth.setForeground(Color.WHITE);
		
		spellButton = new JPanel();
		spellButton.add(new JLabel("Utiliser Sort"));

		
		
		containerLabel.add(heroHealth);
		containerButton.add(spellButton);
		
		try {
			if(GameManager.getInstance().isPlayerOne(this.hero)) {
				this.add(containerLabel);
				this.add(containerButton);
			} else {
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
		for (Card card : this.hero.getHand().getCards()) {
			card.setSelected(false);
			card.setSelectedToAttack(false);
		}
		
		//We deselect all the cards on the gameboard
		for (Card card: this.hero.getGameboard().getCards()) {
			card.setSelectedToAttack(false);
		}
		
		
		if(this.hero.canUseSpell()) {
			this.selected = true;
			this.hero.setSpellSelected(true);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		this.heroHealth.setText("VIE: "+this.hero.getLifePoints().toString());
		
		if(this.hero.getSpellSelected()) {
			spellButton.setBackground(Color.ORANGE);
		}else {
			spellButton.setBackground(Color.WHITE);
		}
		
	}

	@Override
	public void setObservable(Observable obj) {
		// TODO Auto-generated method stub
		
	}

}

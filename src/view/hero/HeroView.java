package view.hero;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.manager.GameManager;
import model.hero.Hero;

public class HeroView extends JPanel{
	
	private Hero hero;
	
	private int heroHealth;
	
	public HeroView(Hero hero) {
		this.hero = hero;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel containerLabel = new JPanel();
		containerLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
		containerLabel.setBackground(Color.DARK_GRAY);
		
		JPanel containerButton = new JPanel();
		containerButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		containerButton.setBackground(Color.DARK_GRAY);
		
		
		JLabel heroHealth = new JLabel("VIE: "+this.hero.getLifePoints().toString());
		heroHealth.setFont(new Font(Font.DIALOG, Font.BOLD,  15));
		heroHealth.setForeground(Color.WHITE);
		
		JPanel spellButton = new JPanel();
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

}

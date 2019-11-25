package view.hand;

import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import controller.manager.GameManager;
import model.hero.Hero;
import view.hero.HeroView;

public class HandContentView extends JPanel {
	
	private Hero hero;
	
	public HandContentView(Hero hero) throws IOException {
		this.hero = hero;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		try {
			if(GameManager.getInstance().isPlayerOne(this.hero)) {
				this.add(new HeroView(this.hero));
				this.add(new HandView(this.hero));
				
			}else {
				this.add(new HandView(this.hero));
				this.add(new HeroView(this.hero));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

package view.card;

import service.ImageFetcher;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardView extends JPanel {
	
	private BufferedImage cardBackground;
	private BufferedImage cristalImage;
	private BufferedImage lifeImage;
	private BufferedImage attackImage;
	
	private int cardHeight;
	private int cardWidth;
	
	private int panelWidth = 120;
	private int panelHeight = 200;

	
	public CardView() throws IOException {

		
		cardBackground = ImageIO.read(new File("assets/base-minion-premium.png"));
		cristalImage = ImageIO.read(new File("assets/cost-mana.png"));
		lifeImage = ImageIO.read(new File("assets/health-premium.png"));
		attackImage = ImageIO.read(new File("assets/attack-minion-premium.png"));
		
		
		// JPanel card = new JPanel();
		// card.setSize(this.panelWidth, this.panelHeight);
		// card.setLayout(new BorderLayout());
		// card.setVisible(true);
		
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Draw the background image.
	    g.drawImage(cardBackground, 25, 0, 120, 200, this);
//	    g.drawImage(cristalImage, 0, 20, this);
//	    g.drawImage(attackImage, 0, this.cardBackground.getHeight() - 150, this);
//	    g.drawImage(lifeImage, this.cardBackground.getWidth() - 70, this.cardBackground.getHeight() - 150, this);
	  }

}

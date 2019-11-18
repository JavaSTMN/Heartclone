package view.card;

import service.ImageFetcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CardView extends JPanel {
	
	private BufferedImage cardBackground;
	private BufferedImage cristalImage;
	private BufferedImage lifeImage;
	private BufferedImage attackImage;
	
	private int cardHeight;
	private int cardWidth;
	
	private int panelWidth = 120;
	private int panelHeight = 200;
	
	private Integer cristalCost = 25;
	
	CardContent card;

	
	public CardView() throws IOException {

		this.setOpaque(false);
		
		cardBackground = ImageIO.read(new File("assets/base-minion-premium.png"));
		cristalImage = ImageIO.read(new File("assets/cost-mana.png"));
		lifeImage = ImageIO.read(new File("assets/health-premium.png"));
		attackImage = ImageIO.read(new File("assets/attack-minion-premium.png"));
		
		// Creation of the card background
		card = new CardContent();
		
		ImageIcon cardImage = new ImageIcon("assets/base-minion-premium.png");
		
		// Resizing the card background
		Image resizedImage = cardImage.getImage(); // transform it 
		resizedImage = resizedImage.getScaledInstance(120, 180, Image.SCALE_SMOOTH); // scale it the smooth way  
		cardImage = new ImageIcon(resizedImage);  // transform it back
		//Border border = BorderFactory.createLineBorder(Color.RED, 5);
		//card.setBorder(border);
		card.setPreferredSize(new Dimension(140,200));
		card.setIcon(cardImage);
		
		
		this.add(card);
		this.setSize(140, 250);
		this.setVisible(true);
	}	

}


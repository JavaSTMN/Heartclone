package view.hand.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import model.card.Card;
import model.card.MinionCard;

public class CardContent extends JLabel {
	
	Card card;
	
	private BufferedImage cardBackground;
	private BufferedImage cristalImage;
	private BufferedImage lifeImage;
	private BufferedImage attackImage;
	
	private JLabel descriptionBlock;
	
	private Integer cristalCost = 25;
	private Integer life = 5;
	private Integer attack = 2;
	private String description = "Test description for the card";
		
	
	public CardContent(Card card) throws IOException {
		this.card = card;
		cardBackground = ImageIO.read(new File("assets/base-minion-premium.png"));
		cristalImage = ImageIO.read(new File("assets/cost-mana.png"));
		lifeImage = ImageIO.read(new File("assets/health-premium.png"));
		attackImage = ImageIO.read(new File("assets/attack-minion-premium.png"));
		
		this.descriptionBlock = new JLabel(description);
		
		
		
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
		
		this.add(descriptionBlock);
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	   

	    // Draw the background image.
	    g.drawImage(cristalImage, 5, 15, 30, 30, this);
	    g.drawImage(attackImage, 0, 160, 35, 35, this);
	    g.drawImage(lifeImage, 110, 160, 25, 35, this);

	    if(g instanceof Graphics2D)
	    {
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.setColor(Color.WHITE);
	        //g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	        g2d.drawString(card.getCristalCost().toString(), 12, 32);
	        
	        if(card instanceof MinionCard) {
	        	g2d.drawString(((MinionCard) card).getDamagePoints().toString(), 16, 185);
	        	
	        }
	        
	    }
	  }

}

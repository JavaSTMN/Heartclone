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

import service.StretchIcon;
import model.card.Card;
import model.card.MinionCard;

public class CardView extends JPanel {
	
	private BufferedImage cardBackground;
	private BufferedImage cristalImage;
	private BufferedImage lifeImage;
	private BufferedImage attackImage;
	
	private int panelWidth = 100;
	private int panelHeight = 150;
	
	private boolean selected;

	
	public CardView(Card card) throws IOException {

		cardBackground = ImageIO.read(new File("assets/base-minion-premium.png"));
		cristalImage = ImageIO.read(new File("assets/cost-mana.png"));
		lifeImage = ImageIO.read(new File("assets/health-premium.png"));
		attackImage = ImageIO.read(new File("assets/attack-minion-premium.png"));
		
		// JPanel configuration
		this.setOpaque(false);
		this.selected = false;
		
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setSize(panelWidth, panelHeight);
		this.setPreferredSize(new Dimension(panelWidth, panelHeight));
		

		// Creation of the card background
//		cardContent = new CardContent(card);
		
//		ImageIcon cardImage = new ImageIcon("assets/base-minion-premium.png");
		
//		// Resizing the card background
//		Image resizedImage = cardImage.getImage(); // transform it 
//		resizedImage = resizedImage.getScaledInstance(120, 180, Image.SCALE_SMOOTH); // scale it the smooth way  
//		cardImage = new ImageIcon(resizedImage);  // transform it back
//		Border border = BorderFactory.createLineBorder(Color.RED, 5);
//		this.setBorder(border);
//		cardContent.setPreferredSize(new Dimension(140,200));
//		//cardContent.setIcon(cardImage);
		
		
		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		this.setBorder(border);
		
		JLabel mana = new JLabel();
		mana.setText("Mana: "+card.getCristalCost().toString());
		mana.setFont(new Font(Font.DIALOG, Font.BOLD,  15));
		mana.setForeground(Color.WHITE);
		
		
		JLabel image = new JLabel();
		
		Image resizedImage = new ImageIcon("assets/card-images/ex-001.jpg").getImage(); // transform it 
		resizedImage = resizedImage.getScaledInstance(this.getWidth(), (int)(this.getHeight() * 0.3f), Image.SCALE_SMOOTH); // scale it the smooth way  
		ImageIcon img = new ImageIcon(resizedImage);  // transform it back
		
		image.setIcon(img);
		
		JLabel description = new JLabel();
		description.setText(card.getDescription());
		
		
		this.add(mana);
		this.add(image);
		this.add(description);
		
		
		if(card instanceof MinionCard) {
			MinionCard minionCard = (MinionCard)card;
			
			JLabel attack = new JLabel();
			attack.setText("Atk: "+minionCard.getDamagePoints().toString());
			this.add(attack);
			attack.setFont(new Font(Font.DIALOG, Font.BOLD,  15));
			attack.setForeground(Color.WHITE);
			
			
			JLabel health = new JLabel();
			health.setText("Vie: "+minionCard.getHealthPoints().toString());
			this.add(health);
			health.setFont(new Font(Font.DIALOG, Font.BOLD,  15));
			health.setForeground(Color.WHITE);
			
		}
		
		
		this.setVisible(true);
	}

	
	public void setSelected(boolean value) {
		this.selected = value;
	}
	
	public boolean getSelected() {
		return this.selected;
	}

}


package view.card;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class CardContent extends JLabel {
	
	private BufferedImage cardBackground;
	private BufferedImage cristalImage;
	private BufferedImage lifeImage;
	private BufferedImage attackImage;
	
	private Integer cristalCost = 25;
	
	
	public CardContent() throws IOException {
		
		cardBackground = ImageIO.read(new File("assets/base-minion-premium.png"));
		cristalImage = ImageIO.read(new File("assets/cost-mana.png"));
		lifeImage = ImageIO.read(new File("assets/health-premium.png"));
		attackImage = ImageIO.read(new File("assets/attack-minion-premium.png"));
		
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);

	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Draw the background image.
	    g.drawImage(cristalImage, 5, 15, 25, 25, this);
	    g.drawImage(attackImage, 0, 160, 35, 35, this);
	    g.drawImage(lifeImage, 110, 160, 25, 35, this);

	    if(g instanceof Graphics2D)
	    {
	        Graphics2D g2d = (Graphics2D)g;
	        //g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	        //g2d.drawString(cristalCost.toString(), 10, 25);
	    }
	  }

}

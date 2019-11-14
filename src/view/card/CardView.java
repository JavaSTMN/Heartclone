package view.card;

import service.ImageFetcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CardView extends JPanel {
	
	String backgroundLocation;
	Image backgroundImage;
	
	public CardView() throws IOException {
		this.backgroundLocation = "assets/base-minion-premium.png";
		
		backgroundImage = ImageIO.read(new File(backgroundLocation));
		
		JPanel card = new JPanel();
		card.setSize(40, 80);
		card.setBackground(Color.cyan);
		card.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Draw the background image.
	    g.drawImage(backgroundImage, 20, 20, this);
	  }

}

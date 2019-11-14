package view.gameview;

import service.ImageFetcher;
import view.card.CardView;

import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JFrame {
	
	private JFrame gameView;
	private JPanel viewContent;
	private Toolkit toolkit;
	private MediaTracker tracker;
	
	public GameView() {
		super();
		
		Image image = ImageFetcher.findImage("assets/icon.png", this);
		
		this.gameView = new JFrame();
		this.viewContent = new JPanel();
		
		this.viewContent.setBackground(Color.DARK_GRAY);
		try {
			this.viewContent.add(new CardView());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.gameView.setTitle("HeartClone");
		this.gameView.setIconImage(image);
		this.gameView.setSize(getMaximumSize());
		this.gameView.setContentPane(this.viewContent);
		
		
		this.gameView.setVisible(true);
		
	}
}

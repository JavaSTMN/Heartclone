package view.game;

import service.ImageFetcher;
import view.card.CardView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameView extends JFrame {
	
	private JFrame frame;
	private JPanel gameView;
	private Toolkit toolkit;
	private MediaTracker tracker;
	private JLabel testLabel;
	
	private JPanel handPlayerOne;
	private JPanel handPlayerTwo;
	
	public GameView(){
		super();
		
		Image image = ImageFetcher.findImage("assets/icon.png", this);
		
		
		this.frame = new JFrame();
		this.frame.setLayout(new BorderLayout());
		this.gameView = new JPanel();
		this.gameView.setBackground(Color.DARK_GRAY);
		this.gameView.setLayout(new BorderLayout());
		
		this.handPlayerOne = new JPanel();
		this.handPlayerOne.setBackground(Color.RED);
		this.handPlayerOne.setPreferredSize(new Dimension(0, 200));
		
		this.handPlayerTwo = new JPanel();
		this.handPlayerTwo.setBackground(Color.BLUE);
		this.handPlayerTwo.setPreferredSize(new Dimension(0, 200));
		
		
		this.gameView.add(handPlayerOne, BorderLayout.PAGE_END);
		this.gameView.add(handPlayerTwo, BorderLayout.PAGE_START);
		
//		try {
//			 this.gameView.add(new CardView());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		this.frame.setTitle("HeartClone");
		this.frame.setIconImage(image);
		this.frame.setSize(getMaximumSize());
		this.frame.setContentPane(this.gameView);
		
		
		this.frame.setVisible(true);
		
	}
}

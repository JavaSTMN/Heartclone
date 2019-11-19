package view.game;

import service.ImageFetcher;
import service.StartDeck;
import view.board.BoardView;
import view.hand.HandView;
import view.hand.card.CardView;

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
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.manager.GameManager;
import model.card.Card;
import model.hero.Hero;

public class GameView extends JFrame {
	
	
	private Hero player1;
	private Hero player2;
	
	private JFrame window;
	private JPanel gameView;
	private Toolkit toolkit;
	private MediaTracker tracker;
	private JLabel testLabel;
	
	private JPanel handPlayerOne;
	private JPanel handPlayerTwo;
	
	private JPanel boardPlayerOne;
	private JPanel boardPlayerTwo;
	
	
	
	public GameView() throws Exception{
		super();
		player1 = GameManager.getInstance().getHeros()[0];
		player2 = GameManager.getInstance().getHeros()[1];
		GameManager.getInstance().startGame();

		Image image = ImageFetcher.findImage("assets/icon.png", this);
		// Main window
		this.window = new JFrame();
		
		// Panel inside the window
		this.gameView = new JPanel();
		this.gameView.setBackground(Color.DARK_GRAY);
		this.gameView.setLayout(new BorderLayout());
		

		// Hands
		this.handPlayerOne = new HandView(player1);
		this.handPlayerTwo = new HandView(player2);
		
		
		this.gameView.add(handPlayerOne, BorderLayout.PAGE_END);
		this.gameView.add(handPlayerTwo, BorderLayout.PAGE_START);
		
		// Boards
		JPanel centerPart = new JPanel();
		this.gameView.add(centerPart, BorderLayout.CENTER);
		centerPart.setLayout(new BoxLayout(centerPart, BoxLayout.PAGE_AXIS));
		
		this.boardPlayerTwo = new BoardView();
		
		centerPart.add(this.boardPlayerTwo);
		
		
		this.boardPlayerOne = new BoardView();
		centerPart.add(this.boardPlayerOne);
			
		// Main window setup
		this.window.setTitle("HeartClone");
		this.window.setIconImage(image);
		this.window.setSize(getMaximumSize());
		this.window.setContentPane(this.gameView);
		
		this.window.setVisible(true);
	}
}

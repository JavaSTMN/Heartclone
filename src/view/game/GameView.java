package view.game;

import service.ImageFetcher;
import service.StartDeck;
import view.board.BoardView;
import view.hand.HandContentView;
import view.hand.HandView;
import view.hand.card.CardView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.IObserver;
import controller.Observable;
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

	private static GameView instanceGameView = null;

	public GameView() throws Exception {

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
		this.handPlayerOne = new HandContentView(player1);
		this.handPlayerTwo = new HandContentView(player2);

		this.gameView.add(handPlayerOne, BorderLayout.PAGE_END);
		this.gameView.add(handPlayerTwo, BorderLayout.PAGE_START);

		// Boards
		JPanel centerPart = new JPanel();
		this.gameView.add(centerPart, BorderLayout.CENTER);
		centerPart.setLayout(new BoxLayout(centerPart, BoxLayout.PAGE_AXIS));

		this.boardPlayerTwo = new BoardView(player2);
		this.boardPlayerTwo.setPreferredSize(new Dimension(0, 180));
		centerPart.add(this.boardPlayerTwo);

		this.boardPlayerOne = new BoardView(player1);
		this.boardPlayerOne.setPreferredSize(new Dimension(0, 180));

		centerPart.add(this.boardPlayerOne);

		// Main window setup
		this.window.setTitle("HeartClone");
		this.window.setIconImage(image);
		this.window.setSize(getMaximumSize());
		this.window.setContentPane(this.gameView);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.window.setVisible(true);
	}

	public void showEndView(boolean isPlayer1Alive) {

		JPanel outerPanel = new JPanel();

		outerPanel.setLayout(new GridBagLayout());
		outerPanel.setVisible(true);
		outerPanel.setBackground(Color.DARK_GRAY);

		// Panel inside the window
		JPanel endPanel = new JPanel();
		endPanel.setBackground(Color.LIGHT_GRAY);
		endPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		endPanel.setPreferredSize(new Dimension(400, 200));
		endPanel.add(new JLabel(isPlayer1Alive ? "Player 1 wins !" : "Player 2 wins !"), c);
		
		JButton b3 = new JButton("Exit");

		b3.setBounds(50, 375, 250, 50);

		b3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		        System.exit(0);
		    }
		});
		
		c.gridy = 1;
		endPanel.add(b3, c);

		outerPanel.add(endPanel, c);

		window.getContentPane().removeAll();
		window.getContentPane().add(outerPanel);
		
		window.repaint();
		window.revalidate();
	}

	public static GameView getInstance() throws Exception {
		if (instanceGameView == null)
			synchronized (GameManager.class) {
				if (instanceGameView == null) {
					instanceGameView = new GameView();
				}
			}

		return instanceGameView;
	}
}
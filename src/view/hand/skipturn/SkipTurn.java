package view.hand.skipturn;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.manager.GameManager;
import model.hero.Hero;

public class SkipTurn extends JPanel implements MouseListener {
	
	private Hero hero;
	
	public SkipTurn(Hero hero) {
		
		this.hero = hero;
		
		this.add(new JLabel("Passer le tour"));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.addMouseListener(this);
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		try {
			GameManager.getInstance().finishTurn(this.hero);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

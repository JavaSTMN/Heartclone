package view.board;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.hero.Hero;

public class BoardView extends JPanel {
	
	Hero hero;
	
	public BoardView() {
		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		this.setBorder(border);
		this.setBackground(new Color(47, 59, 79));
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
	}
	
}

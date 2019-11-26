package view;

import view.game.*;

import java.io.IOException;
import java.util.Timer;

import javax.swing.JFrame;

import controller.manager.GameManager;

public class Main extends JFrame {

	public static void main(String[] args) throws Exception {
		GameView.getInstance();	
	}

}

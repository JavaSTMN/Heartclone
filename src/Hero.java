import java.awt.Image;

/**
 * 
 */

/**
 * @author adrien
 *
 */
public class Hero implements Attacker, Target {
	
	private int cristals;
	private int lifePoints;
	private Deck deck;
	private CardContainer gameboard;
	private Image image;
	
	
	
	public Hero() {
		cristals = 1;
		deck = new Deck();
		gameboard = new CardContainer();
		lifePoints = 30;
//		image
	}
	
	public void play(Card playableCard) {
		
	}
	
	public void draw() {
		
	}
	
	public void useSpell() {
		
	}
	
	public void useCristal(int nbCristalsUsed) {
		cristals -= nbCristalsUsed;
	}

	@Override
	public void receiveDamage(int nb) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dealDamage(Target target) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return false;
	}

}

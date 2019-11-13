import java.awt.Image;
import java.util.logging.Handler;

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
	private int maxLifePoints;
	private Deck deck;
	private CardContainer gameboard;
	private CardContainer hand;
	boolean isActive;
	private Image image;
	
	
	
	public Hero() {
		cristals = 1;
		deck = new Deck();
		hand = new CardContainer();
		gameboard = new CardContainer();
		lifePoints = 30;
		maxLifePoints = 30;
		isActive = true;
//		image
	}
	
	/**
	 * Hero put a card from his hand to the gameboard
	 * @param playableCard
	 */
	public void play(Card playableCard)
	{
		
	}
	
	/**
	 * Hero draw a card from his deck to his hand
	 */
	public void draw() {
		
	}
	
	/**
	 * Hero use his special spell which cost him 2 cristals
	 */
	public void useSpell() 
	{
		useCristals(2);
	}
	
	/**
	 * Substract cristals from the hero
	 * @param nbCristalsUsed
	 */
	public void useCristals(int nbCristalsUsed) {
		cristals -= nbCristalsUsed;
	}

	@Override
	public void receiveDamage(int nb) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		lifePoints -= nb;
	}

	@Override
	public boolean isAlive() {
		return(lifePoints>0);
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

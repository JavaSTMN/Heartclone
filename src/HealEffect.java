

/**
 * @author adrien
 *
 */
public class HealEffect extends Effect {
	
	int healAmount;
	
	/**
	 * 
	 */
	public HealEffect(int healAmount) {
		super();
		this.healAmount = healAmount;
	}
	
	/**
	 * Activate the healEffect
	 * @param target Target: the target of the heal
	 */
	public void activateEffect(Target target) {
		target.receiveHealthPoints(this.healAmount);
		this.used = true;
	}
}

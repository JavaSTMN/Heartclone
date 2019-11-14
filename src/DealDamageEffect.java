

/**
 * @author adrien
 *
 */
public class DealDamageEffect extends Effect {
	
	private int damageAmount;
	
	/**
	 * 
	 */
	public DealDamageEffect(int damageAmount) {
		super();
		this.damageAmount = damageAmount;
		
	}
	
	public void activateEffect(Target target) {
		target.receiveDamage(damageAmount);
		this.used = true;
	}

}

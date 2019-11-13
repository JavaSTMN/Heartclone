/**
 * 
 */

/**
 * @author adrien
 *
 */
public interface Attacker {
	
	/**
	 * Deals damage to the target
	 * @param target 
	 */
	public void dealDamage(Target target) throws IllegalArgumentException;
	
	/**
	 * Sets the Attacker to disabled state (it can't attack until it's activated again)
	 */
	public void disable();
	
	/**
	 * Sets the Attacker to enabled state (it can't attack until it's activated again)
	 */
	public void enable();
	
	/**
	 * getter on the Attacker's state
	 * @return boolean
	 */
	public boolean getState();
}

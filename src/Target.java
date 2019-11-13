/**
 * 
 */

/**
 * @author adrien
 *
 */
public interface Target {
	/**
	 * Substract healthpoints by the specified number.
	 * @throws IllegalArgumentException if nb < 0
	 * @param nb int the number of damage to be taken
	 */
	public void receiveDamage (int nb) throws IllegalArgumentException;
	
	/**
	 * Add the specified amount to the healtpoints
	 * @throws IllegalArgumentException if amount < 0
	 * @param amount int: the amount of health points to add
	 */
	public void receiveHealthPoints(int amount) throws IllegalArgumentException;
	
	/**
	 * Checks if the target has more than 0 healthpoints
	 * @return boolean true if healthpoints > 0 | false otherwise
	 */
	public boolean isAlive();
}

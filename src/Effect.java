


/**
 * 
 */

/**
 * @author adrien
 *
 */
public abstract class Effect {
	
	boolean used;

	/**
	 * 
	 */
	public Effect() {
		this.used = false;
	}

	public void activateEffect(Target target) {
		this.used = true;
	}

}



/**
 * @author adrien
 *
 */
public class DrawEffect extends Effect {
	
	int drawAmount;
	
	/**
	 * 
	 */
	public DrawEffect() {
		// TODO Auto-generated constructor stub
	}
	
	public void activateEffect(Hero hero) {
		// for(int i = 0; i < drawAmount; i++){
		// 		hero.draw();
		// }
		
		this.used = true;
	}

}

package controller.card;
import java.util.UUID;

/**
 * 
 */

/**
 * @author adrien
 *
 */
public abstract class Card{

	private UUID id;					// Unique Id
	private String name;				// Card name
	private String description;			// Card description
	private int cristalCost;			// Amount of cristal needed to play the card


	/**
	 * Default constructor
	 */
	public Card() {
		this.id = UUID.randomUUID();
	}
	
	public String getName(){
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getDescription(){
		return this.description;
	}

	public void setDescription(String value){
		this.description = value;
	}
	
	/**
	 * check if two cards are the same
	 * @param other Object: the object to compare with this one
	 */
	@Override
	public boolean equals(Object other) {
		if(other == null) 
			return false;
		
		if(other instanceof Card) {
			return ((Card) other).id == this.id;
		}
		
		return false;
	}
}

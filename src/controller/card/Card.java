package controller.card;
import java.util.UUID;

import controller.manager.GameManager;

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
	
	private GameManager gameManager;


	/**
	 * Default constructor
	 */
	public Card() {
		this.id = UUID.randomUUID();
		this.gameManager = GameManager.getInstance();
	}
	
	public UUID getId() {
		return this.id;
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
	
	public int getCristalCost() {
		return this.cristalCost;
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
			return ((Card) other).getId() == this.getId();
		}
		
		return false;
	}

	public UUID getId() {
		return id;
	}
}

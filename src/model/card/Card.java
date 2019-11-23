package model.card;
import java.util.UUID;

import controller.Observable;
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
	
	private boolean selected = false;
	private boolean selectedToAttack = false;
	
	private Observable observable;

	/**
	 * Default constructor
	 */
	public Card() {
		this.id = UUID.randomUUID();
		this.observable = new Observable();
	}
	
	public Card(String name, String description, int cristalCost) {
		this.id = UUID.randomUUID();
		this.observable = new Observable();
		
		this.name = name;
		this.description = description;
		this.cristalCost = cristalCost;
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
	
	public Integer getCristalCost() {
		return this.cristalCost;
	}
	
	public Observable getObservable() {
		return this.observable;
	}
	
	public boolean getSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean value) {
		this.selected = value;
	}
	
	public boolean getSelectedToAttack() {
		return this.selectedToAttack;
	}
	
	public void setSelectedToAttack(boolean value) {
		this.selectedToAttack = value;
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

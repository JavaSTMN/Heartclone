package model.card;


import static org.junit.jupiter.api.Assertions.*;
import junit.framework.*;
import model.card.MinionCard;

import org.junit.jupiter.api.Test;

class MinionCardTest {


	@Test
	void constructorTest() {
		MinionCard card = new MinionCard(5,3,false);
		
		assertEquals(5, card.getHealthPoints());
		assertEquals(3, card.getDamagePoints());
		assertEquals(false, card.getState());
	}
	
	@Test
	void disableEnableTest() {
		MinionCard card = new MinionCard(5, 3, false);
		card.enable();
		
		assertEquals(true, card.getState());
		
		card.disable();
		assertEquals(false, card.getState());
	}
	
	@Test
	void dealDamageTest() {
		MinionCard card = new MinionCard(5,3, false);
		MinionCard enemy = new MinionCard(6,1, false);
		
		
		card.dealDamage(enemy);
		
		assertEquals(3, enemy.getHealthPoints());
	}
	
	@Test
	void receiveDamageTest() throws IllegalArgumentException {
		MinionCard card = new MinionCard(5,3,false);
		
		try {
			card.receiveDamage(4);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		assertEquals(1, card.getHealthPoints());	
	}
	
	@Test
	void receiveDamageExceptionTest(){
		MinionCard card = new MinionCard(5,3,false);
		
		assertThrows( IllegalArgumentException.class, () -> {
			card.receiveDamage(-2);
		});
	}
	
	@Test
	void receiveHealthPointsTest() {
		MinionCard card = new MinionCard(5,3,false);
		
		card.receiveDamage(3);
		
		assertEquals(2, card.getHealthPoints());
		
		card.receiveHealthPoints(2);
		
		assertEquals(4, card.getHealthPoints());
		
		card.receiveHealthPoints(10);
		
		assertEquals(5, card.getHealthPoints());
	}
	
	@Test
	void receiveHealthPointsExceptionTest() {
		MinionCard card = new MinionCard(5,3,false);
		
		assertThrows( IllegalArgumentException.class, () -> {
			card.receiveHealthPoints(-2);
		});
		
	}
	

}

package de.htwg.se.dog.models;


public class Card {
	
	private final int value;

	protected Card(int value){
		if (value > 0 && value <= 14) {
			this.value = value;
		} else {
			throw new IllegalArgumentException("Failed to create card with value:" + value);
		}
	}
	public int getValue() {
		return this.value;
	}
	
	public String getCardName(int value) {
		switch (value) {
		case 1:
			return "Ass";
		case 11:
			return "Bube"; 
		case 12:
			return "Dame";
		case 13: 
			return "K�nig";
		case 14:
			return "JOKER";
		default:
			return ""+value; 
		}
		
	}
}


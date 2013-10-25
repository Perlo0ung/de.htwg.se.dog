package de.htwg.se.dog.models;


public class Card {
	
	private final int value;
	private final int ZERO = 0;
	private final int FOURTEEN = 0;
	
	protected Card(int value){
		if (value > ZERO && value <= FOURTEEN) {
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


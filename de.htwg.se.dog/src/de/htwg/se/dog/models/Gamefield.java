package de.htwg.se.dog.models;


public class Gamefield {

	Field[] field;
	private final int fieldsTilHouse;
	private final int playerCount;
	private final int houseCount;
	
	public Gamefield(int fieldsTilHouse, int playerCount, int houseCount){
		this.fieldsTilHouse = fieldsTilHouse;
		this.houseCount = houseCount;
		this.playerCount = playerCount;
		
		field = new Field[(fieldsTilHouse+houseCount)*playerCount];
		generateGamefield();
	}

	private void generateGamefield(){
		
	}
	
}

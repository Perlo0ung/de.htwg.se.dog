package de.htwg.se.dog.models;

public class Figure {
	private static final int ZERO = 0;
	private static final int FOUR = 4;
	private final int fignr;
	private final Player owner;
	
	public Figure(Player owner, int fignr){
		if(owner.getPlayerID() > ZERO){
			this.owner = owner;
		} else {
			throw new IllegalArgumentException("Failed to create Figure with Owner:" + owner);
		}
		if ( fignr > ZERO && fignr <= FOUR){
			this.fignr = fignr;	
		} else  {
			throw new IllegalArgumentException("Failed to create Figrue with Fignr:" + fignr);
		}
		
	}
	
	public Player getOwner(){
		return this.owner;
	}
	public int getFignr(){
		return this.fignr;
	}
	
}

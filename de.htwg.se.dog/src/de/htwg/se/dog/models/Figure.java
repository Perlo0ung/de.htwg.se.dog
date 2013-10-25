package de.htwg.se.dog.models;

public class Figure {
	
	private final int owner;
	private final int fignr;
	
	public Figure(int owner, int fignr){
		
		this.owner = owner;
		this.fignr = fignr;
	}
	
	public int getOwner(){
		return this.owner;
	}
	public int getFignr(){
		return this.fignr;
	}
	
}

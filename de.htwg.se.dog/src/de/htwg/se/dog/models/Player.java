package de.htwg.se.dog.models;

import java.util.LinkedList;

public class Player {

	public int[] figure;
	public LinkedList<Card> cards;
	
	public Player(){
		figure = new int[4];
		figure[0] = -1;
		figure[1] = -1;
		figure[2] = -1;
		figure[3] = -1;
		
		cards = new LinkedList<Card>();
		
	}
	
	public LinkedList<Card> getCardList(){
		return this.cards;
	}
	
	public void addCard(Card c) {
		this.cards.add(c);
	}
}

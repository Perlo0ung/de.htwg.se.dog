package de.htwg.se.dog.models;

import java.util.LinkedList;

public class Player {

	public LinkedList<Figure> figure;
	private LinkedList<Card> cards;
	
	public Player(int playerNr, int figcount){
		figure = new LinkedList<Figure>();
		for ( int i = 0; i < figcount; i++){
			figure.add(new Figure(playerNr, i+1));
		}
		cards = new LinkedList<Card>();
		
	}
	
	public LinkedList<Card> getCardList(){
		return this.cards;
	}
	
	public void addCard(Card c) {
		this.cards.add(c);
	}
	
	public boolean removeCard(Card c){
		return cards.remove(c);
	}
}

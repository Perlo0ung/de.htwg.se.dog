package de.htwg.se.dog.models;

import java.util.LinkedList;

public class Player {

	public LinkedList<Figure> figure;
	private LinkedList<Card> cards;
	private final int playernum;
	
	public Player(int playerNr, int figcount){
		playernum = playerNr;
		figure = new LinkedList<Figure>();
		for ( int i = 0; i < figcount; i++){
			figure.add(new Figure(this, i+1));
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
	public Figure removeFigure() {
		return figure.removeFirst();
	}
	public void addFigure(Figure f) {
		figure.add(f);
	}
	public int getPlayerID() {
		return playernum;
	}
}

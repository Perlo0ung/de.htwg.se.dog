package de.htwg.se.dog.controller;

import de.htwg.se.dog.models.*;

public class CardDealer {
	public CardStack stack; 
	private final static int MAXCARDS = 6;
	private final static int MAXROUND = 5;
	private final int players;
	
	private final int CARDS = 12;
	private int round = 0;
	
	public CardDealer(int players) {
		this.players = players;
		stack = new CardStack(CARDS,players);
	}
	
	public void dealCards(Player p) {
		if(expectedNumOfCards() > stack.getSize()) {
			stack = new CardStack(CARDS, players);
		}
		for (int i = 0; i < (MAXCARDS-round); i++) {
			p.addCard(stack.dealCard(0, stack.getSize()));
		}
	}
	
	public void newRound() {
		round = (round+1)%MAXROUND;
	}
	public int getRound() {
		return this.round;
	}
	public int expectedNumOfCards() {
		return (MAXCARDS-round)*players;
	}
}

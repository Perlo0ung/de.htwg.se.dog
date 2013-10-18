package de.htwg.se.dog.models;

import java.util.Random;
import java.util.Stack;

public class CardStack {

	private Stack<Card> cardstack = null;
	private final int CARDS = 4;
	private final int JOKER = 14;
	private Random gen;
	
	public CardStack(int size) {
		cardstack = new Stack<Card>();
		generateStack(size);
	}

	public void generateStack(int size) {
		for (int i = 0; i <= (CARDS-1); i++) {
			for (int j = 0; j <= size; j++) {
				cardstack.push(new Card(j + 1));
			}
		}
		cardstack.push(new Card(JOKER));
		cardstack.push(new Card(JOKER));
		cardstack.push(new Card(JOKER));
		/*
		 * for (Iterator<Card> iterator = cardstack.iterator();
		 * iterator.hasNext();) { Card type = (Card) iterator.next();
		 * System.out.println(type.getValue() + " " +
		 * type.getCardName(type.getValue())); }
		 */
	}

	public Stack<Card> getStack() {
		return this.cardstack;
	}

	public Card dealCard(int range) {
		gen = new Random();
		int index = gen.nextInt(range);
		System.out.println(index);
		return cardstack.remove(index);
	}
}

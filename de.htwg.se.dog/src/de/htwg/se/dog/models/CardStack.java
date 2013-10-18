package de.htwg.se.dog.models;

import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

public class CardStack {

	public static Stack<Card> cardstack;
	
	public CardStack(int size){
		cardstack = new Stack<Card>();
		generateStack(size);
		shuffleStack();
	}
	
	public static void generateStack(int size){
		for(int i = 0; i <= 3 ; i++){
			for(int j = 0; j <= size; j++){
				cardstack.push(new Card(j+1));
			}
		}
		cardstack.push(new Card(14));
		cardstack.push(new Card(14));
		cardstack.push(new Card(14));
		for (Iterator iterator = cardstack.iterator(); iterator.hasNext();) {
			Card type = (Card) iterator.next();
			System.out.println(type.getValue()+" "+type.getCardName(type.getValue()));
			
		}
		
	}
	public void shuffleStack(){
		Collections.shuffle(cardstack);
	}

}
	

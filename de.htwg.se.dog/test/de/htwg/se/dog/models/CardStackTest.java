package de.htwg.se.dog.models;

import static org.junit.Assert.*;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class CardStackTest {

	Card wrong;
	CardStack cardStack, cardStackSorted;
	Stack<Card> stack;
	final int CARDS = 12;
	final int PLAYERS = 12;
	
	//for pseudo random generation
	final int RANDOMCARD = 5;
	int size;

	@Before
	public void setUp() throws Exception {
		cardStack = new CardStack(CARDS,PLAYERS);
		stack = cardStack.getStack();
		size = stack.size();
	}

	@Test
	public void testgenerateStack() {
		for (int i = 0; i <= (4 * (CARDS + 1) - 1); i++) {
			int tmp = stack.elementAt(i).getValue();
			//System.out.println("I: " + i + ", Karte: " + tmp);
			assertEquals(i % (CARDS + 1) + 1, tmp);
		}
		assertEquals(14, stack.pop().getValue());
		assertEquals(14, stack.pop().getValue());
	}

	@Test
	public void testgetStackSize() {
		assertEquals(cardStack.getSize(),stack.size());
	}
	@Test
	public void testdealCard() {
		assertEquals(cardStack.dealCard(RANDOMCARD,RANDOMCARD+1).getValue(),RANDOMCARD+1);
	}
	@Test
	public void testgetStack() {
		assertNotNull(cardStack.getStack());
	}
}

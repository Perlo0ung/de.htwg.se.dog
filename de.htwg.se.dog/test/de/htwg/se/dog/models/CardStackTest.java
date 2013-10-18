package de.htwg.se.dog.models;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class CardStackTest {
	
	Card wrong;
	CardStack cardStack;
	Stack<Card> stack;
	int size;
	@Before
	public void setUp() throws Exception{
		new CardStack(12);
		stack = CardStack.cardstack;
		size = stack.size();
	}
	
	@Test
	public void testgenerateStack() {
		//System.out.println(cardStack.size());
		for(int i = 0; i <=51; i++){
		int tmp = stack.elementAt(i).getValue();
		System.out.println("I: " + i + ", Karte: " + tmp);	
		assertEquals(i % 13 + 1, tmp);
		}
		
		assertEquals(14, stack.pop().getValue());
		assertEquals(14, stack.pop().getValue());
		assertEquals(14, stack.pop().getValue());
	}

}

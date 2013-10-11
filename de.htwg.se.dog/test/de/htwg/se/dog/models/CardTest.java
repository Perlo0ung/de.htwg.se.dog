package de.htwg.se.dog.models;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.models.Card;

public class CardTest {
	
	Card card;
	Card wrong;
	
	@Before
	public void setUp() throws Exception{
		card = new Card(12);	
	}
	
	@Test
	public void testGetValue() {
		assertEquals(12, card.getValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExpectedException() {
		wrong = new Card(18);
	}
}

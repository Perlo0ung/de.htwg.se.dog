package de.htwg.se.dog.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.htwg.se.dog.models.Card;

public class CardTest {
	
	Card card;
	Card wrong;
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Before
	public void setUp() {
		card = new Card(12);
	}
	
	@Test
	public void testGetValue() {
		assertEquals(12, card.getValue());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExpectedException() {
		wrong = new Card(200);
		assertNotNull(wrong);
	}
	
	@Test
	public void testGetCardName() {
		assertEquals(card.getCardName(11),"Bube");
		assertEquals(card.getCardName(12),"Dame");
		assertEquals(card.getCardName(13),"König");
		assertEquals(card.getCardName(14),"JOKER");
		assertEquals(card.getCardName(1),"Ass");
		assertEquals(card.getCardName(2),"2");
	}
}

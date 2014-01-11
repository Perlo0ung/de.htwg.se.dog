package de.htwg.se.dog.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.models.impl.Card;

public class CardTest {

    Card card;
    Card wrong;

    @Before
    public void setUp() {
        card = new Card(12);
    }

    @Test
    public void testGetValue() {
        assertEquals(12, card.getValue());
    }


    @Test
    public void testGetCardName() {
        card = new Card(11);
        assertEquals(card.getCardName(), "Bube");
        card = new Card(12);
        assertEquals(card.getCardName(), "Dame");
        card = new Card(13);
        assertEquals(card.getCardName(), "Koenig");
        card = new Card(14);
        assertEquals(card.getCardName(), "Joker");
        card = new Card(1);
        assertEquals(card.getCardName(), "Ass");
        card = new Card(2);
        assertEquals(card.getCardName(), "Zwei");
        assertEquals(card.toString(), new Card(2).toString());
    }

    @Test
    public void testCardEquals() {
    	CardInterface c = new Card(5);
        assertEquals(c, new Card(5));
    	assertEquals(c, c);
        assertNotEquals(new Card(5), new Card(4));
        assertFalse(c.equals(null));
        assertFalse(c.equals(new String("a")));
    }
    @Test
    public void testHashCode() {
    	CardInterface c = new Card(5);
        assertEquals(c.hashCode(), c.hashCode());
    }
    @Test
    public void testCompareTo() {
        CardInterface c1 = new Card(5);
        CardInterface c2 = new Card(4);
        assertEquals(c1.compareTo(c1), 0);
        assertEquals(c1.compareTo(c2), 1);
        assertEquals(c2.compareTo(c1), -1);
    }
}

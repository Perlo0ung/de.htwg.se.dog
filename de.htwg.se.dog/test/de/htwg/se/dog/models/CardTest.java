package de.htwg.se.dog.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.htwg.se.dog.models.impl.Card;

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

    @Test(expected = IllegalArgumentException.class)
    public void testExpectedException() {
        wrong = new Card(200);
        assertNotNull(wrong);
        wrong = new Card(-5);
        assertNotNull(wrong);
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
}

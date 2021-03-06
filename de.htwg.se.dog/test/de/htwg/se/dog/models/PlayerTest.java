package de.htwg.se.dog.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.htwg.se.dog.models.impl.Card;
import de.htwg.se.dog.models.impl.Player;

public class PlayerTest {

    PlayerInterface p1;
    CardInterface card2;
    int playerNr = 1;
    int figCount = 4;
    FigureInterface temp;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Before
    public void setUp() {
        card2 = new Card(2);
        p1 = new Player(playerNr, figCount, 0);
    }

    @Test
    public void testAddCard() {
        assertEquals(false, p1.getCardList().contains(card2));
        p1.addCard(card2);
        assertEquals(true, p1.getCardList().contains(card2));
    }

    @Test
    public void testRemoveCard() {
        p1.addCard(card2);
        assertEquals(true, p1.getCardList().contains(card2));
        assertEquals(true, p1.removeCard(card2));
    }

    @Test
    public void testRemoveFigure() {
        temp = p1.removeFigure();
        assertEquals(p1.getFigureList().size(), 3);
        p1.getFigureList().clear();
        assertNull(p1.removeFigure());
    }

    @Test
    public void testAddFigure() {
        p1.addFigure(temp);
        assertEquals(p1.getFigureList().size(), 5);
    }

    @Test
    public void testGetPlayerId() {
        assertEquals(playerNr, p1.getPlayerID());
    }

    @Test
    public void testUpdateFigurePos() {
        p1.updateFigurePos(1, 2);
        p1.updateFigurePos(15, 12);
        p1.updateFigurePos(1, -1);
        assertEquals((Integer) 12, p1.getFigureRegister().get(0));
    }

    @Test
    public void testtoString() {
        assertEquals("PlayerId: 1", p1.toString());
    }
    @Test
    public void testgetStartFieldNr() {
        assertEquals(0, p1.getStartFieldNr());
    }
    @Test
    public void testgetCardfromCardNr() {
    	assertNull(p1.getCardfromCardNr(2));
    	p1.addCard(card2);
    	assertEquals(card2, p1.getCardfromCardNr(2));
    	assertNull(p1.getCardfromCardNr(12));
    }
    @Test
    public void testclearCardList() {
    	p1.clearCardList();
    	assertTrue(p1.getCardList().isEmpty());
    }
}

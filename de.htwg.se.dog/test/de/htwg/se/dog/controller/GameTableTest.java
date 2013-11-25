package de.htwg.se.dog.controller;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.models.Field;
import de.htwg.se.dog.models.Figure;
import de.htwg.se.dog.models.Player;
import static org.junit.Assert.*;

public class GameTableTest {
    GameTable table; 
    Player first;
    Field[] array;
    @Before
    public void setUp() {
        table = new GameTable(2, 1);
        table.newRound();
        array = table.getGameField().getGamefield();
        first = table.getCurrentPlayer();
    }
    
    @Test
    public void testCurrentPlayer() {
        Player second = table.getCurrentPlayer();
        table.newRound();
        assertEquals(first, table.getCurrentPlayer());
        assertEquals(second, table.getCurrentPlayer());
    }
    @Test
    public void testNewRound() {
        table.newRound();
    }
    
    @Test
    public void testPlayerQueueIsEmpty() {
    	assertFalse(table.playerQueueIsEmpty());
    }
    
    @Test
    public void testcanPlay() {
        assertFalse(table.canPlay(first));
        table.dealCards();
        first = table.getCurrentPlayer();
        Figure fig = first.removeFigure();
        array[20].putFigure(fig);
        first.updateFigurePos(fig.getFignr(), 20);
        assertTrue(table.canPlay(first));
    }
    
}

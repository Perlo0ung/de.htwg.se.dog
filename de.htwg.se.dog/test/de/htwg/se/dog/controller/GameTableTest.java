package de.htwg.se.dog.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.models.impl.Figure;
import de.htwg.se.dog.models.impl.Player;

public class GameTableTest {
    GameTable table;
    de.htwg.se.dog.models.impl.Player first;
    de.htwg.se.dog.models.impl.Field[] array;

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

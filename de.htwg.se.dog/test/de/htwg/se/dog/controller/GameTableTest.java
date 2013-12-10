package de.htwg.se.dog.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.FigureInterface;
import de.htwg.se.dog.models.PlayerInterface;
import de.htwg.se.dog.models.impl.Player;

public class GameTableTest {
    GameTable table;
    PlayerInterface first;
    FieldInterface[] array;

    @Before
    public void setUp() {
        table = new GameTable(2);
        table.newRound();
        array = table.getGameField().getGamefield();
        first = table.getNextPlayer();
    }

    @Test
    public void testCurrentPlayer() {
        PlayerInterface second = table.getNextPlayer();
        table.newRound();
        assertEquals(second, table.getNextPlayer());
        assertEquals(first, table.getNextPlayer());
    }

    @Test
    public void testAddPlayerToQueue() {
        table.addPlayerToQueue(new Player(5, 1));
        table.getNextPlayer();
        assertEquals(5, table.getNextPlayer().getPlayerID());
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
        first = table.getNextPlayer();
        FigureInterface fig = first.removeFigure();
        array[20].putFigure(fig);
        first.updateFigurePos(fig.getFignr(), 20);
        assertTrue(table.canPlay(first));
    }

    @Test
    public void testSetStartingPlayer() {
        GameTable here = new GameTable(10);
        here.setStartingPlayer(8);
        here.newRound();
        assertEquals(8, here.getNextPlayer().getPlayerID());
    }

}

/*
package de.htwg.se.dog.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.controller.impl.GameTable;
import de.htwg.se.dog.controller.impl.Movement;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.FigureInterface;
import de.htwg.se.dog.models.GameFieldInterface;
import de.htwg.se.dog.models.PlayerInterface;

public class GameTableTest {
    private final int TWO = 2;
    Movement movement;
    GameTableInterface table;
    GameFieldInterface gamefield;
    PlayerInterface first;
    FieldInterface[] array;
    FigureInterface fig;

    @Before
    public void setUp() {
        table = new GameTable(2);
        table.newRound();
        table.nextPlayer();
        array = table.getGameField().getField();
        first = table.getCurrentPlayer();
        gamefield = table.getGameField();
    }

    @Test
    public void testCurrentPlayer() {
        table.newRound();
        table.nextPlayer();
        table.nextPlayer();
        assertEquals(first, table.getCurrentPlayer());
    }

    @Test
    public void testAddPlayerToQueue() {
        table.getCurrentPlayer();
        assertEquals(1, table.getCurrentPlayer().getPlayerID());
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
        here.nextPlayer();
        assertEquals(8, here.getCurrentPlayer().getPlayerID());
    }

    @Test
    public void testWin() {
        movement = new Movement();
        movement.setMoveStrategie(TWO);
        int firsthouse = gamefield.getFieldsTillHouse();

        assertTrue(array[firsthouse].isHouse());
        assertTrue(array[firsthouse + 1].isHouse());
        assertTrue(array[firsthouse + 2].isHouse());
        assertTrue(array[firsthouse + 3].isHouse());

        array[firsthouse - 1].putFigure(first.removeFigure(), firsthouse - 1);
        array[firsthouse + 1].putFigure(first.removeFigure(), firsthouse + 1);
        array[firsthouse + 2].putFigure(first.removeFigure(), firsthouse + 2);
        array[firsthouse + 3].putFigure(first.removeFigure(), firsthouse + 3);
        // not every figure is in house
        assertFalse(table.playerHaswon(gamefield, first));
        movement.move(1, firsthouse - 1);
        // every figure is in house
        assertTrue(table.playerHaswon(gamefield, first));

        //branch coverage
        GameTable branch = new GameTable(5);
        branch.newRound();
        gamefield = branch.getGameField();
        branch.nextPlayer();
        first = branch.getCurrentPlayer();
        assertFalse(branch.playerHaswon(gamefield, first));
    }

    @Test
    public void testToStrings() {
        array[5].putFigure(first.removeFigure(), 5);
        assertNotNull(table.getGameFieldString());
        assertNotNull(table.getPlayerHandString());
        assertNotNull(table.getPlayerString());
    }
}
*/
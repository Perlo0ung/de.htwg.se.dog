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
import de.htwg.se.dog.models.impl.Card;

public class GameTableTest {
    private static final int TEN = 10;
    private static final int SIX = 6;
    private static final int FIVE = 5;
    private static final int JOKER = 14;
    private static final int LOW = -1;
    private static final int NONE = 50;
    private static final int ELEVEN = 11;
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
        for (PlayerInterface p : table.getPlayerList()) {
            p.addCard(new Card(JOKER));
        }
        table.newRound();
        table.nextPlayer();
        array = table.getGameField().getGameArray();
        first = table.getCurrentPlayer();
        gamefield = table.getGameField();
    }

    @Test
    public void testNextPlayer() {
        for (PlayerInterface p : table.getPlayerList()) {
            p.clearCardList();
        }
        table.nextPlayer();
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
    public void testPossibleCards() {
        PlayerInterface current = table.getCurrentPlayer();
        PlayerInterface next = table.getPlayerList().get(0);
        current.addCard(new Card(JOKER));
        assertTrue(!table.possibleCards(table.getCurrentPlayer()).isEmpty());
        current.clearCardList();
        assertTrue(table.possibleCards(table.getCurrentPlayer()).isEmpty());
        current.updateFigurePos(TWO, FIVE);
        array[FIVE].putFigure(current.removeFigure());
        array[SIX].putFigure(next.removeFigure());
        current.addCard(new Card(JOKER));
        current.addCard(new Card(TWO));
        current.addCard(new Card(ELEVEN));
        assertTrue(!table.possibleCards(table.getCurrentPlayer()).isEmpty());
    }

    @Test
    public void testFieldIsEmpty() {
        assertTrue(table.fieldIsEmpty(NONE));
        assertTrue(table.fieldIsEmpty(LOW));
        assertTrue(table.fieldIsEmpty(FIVE));
    }

    @Test
    public void testIsPlayerStartfieldBlocked() {
        assertFalse(table.isPlayerStartfieldBlocked());
    }

    @Test
    public void testMoveFigureToStart() {
        assertTrue(table.moveFigureToStart(ELEVEN));
        assertFalse(table.moveFigureToStart(JOKER));
    }

    @Test
    public void testGetCurrentPlayerID() {
        assertEquals(1, table.getCurrentPlayerID());
    }

    @Test
    public void testGetFigureOwnerID() {
        array[FIVE].putFigure(table.getCurrentPlayer().removeFigure());
        assertEquals(1, table.getFigureOwnerID(FIVE));
    }

    @Test
    public void testGetTagetField() {
        array[FIVE].putFigure(table.getCurrentPlayer().removeFigure());
        assertEquals(table.getTargetField(FIVE, FIVE), TEN);
    }

    @Test
    public void testPlayJoker() {
        array[0].putFigure(table.getCurrentPlayer().removeFigure());
        array[1].putFigure(table.getCurrentPlayer().removeFigure());
        assertEquals(table.playJoker(JOKER), new Card(JOKER));
        table.getCurrentPlayer().clearCardList();
        table.getCurrentPlayer().addCard(new Card(JOKER));
        table.playJoker(TWO);
        assertEquals(table.playJoker(TWO), new Card(JOKER));
    }

    @Test
    public void testGetRound() {
        assertEquals(1, table.getRound());
    }

    @Test
    public void testPlayerQueueIsEmpty() {
        assertFalse(table.playerQueueIsEmpty());
    }

    @Test
    public void testPlayerHasCard() {
        table.getCurrentPlayer().addCard(new Card(JOKER));
        assertFalse(table.playerHasCard(NONE));
        assertTrue(table.playerHasCard(JOKER));
    }

    @Test
    public void testcanPlay() {
        assertTrue(table.canPlay(first));
        table.getCurrentPlayer().clearCardList();
        assertFalse(table.canPlay(first));

    }

    @Test
    public void testSetStartingPlayer() {
        GameTable here = new GameTable(2);
        for (PlayerInterface p : here.getPlayerList()) {
            p.addCard(new Card(JOKER));
        }
        here.newRound();
        here.setStartingPlayer(1);
        here.newRound();
        here.nextPlayer();
        assertEquals(2, here.getCurrentPlayer().getPlayerID());
    }

    @Test
    public void testWin() {

        movement = new Movement(gamefield);
        movement.setMoveStrategie(TWO);
        int firsthouse = gamefield.getFieldSize() - 4;
        assertTrue(array[firsthouse].isHouse());
        assertTrue(array[firsthouse + 1].isHouse());
        assertTrue(array[firsthouse + 2].isHouse());
        assertTrue(array[firsthouse + 3].isHouse());

        array[firsthouse - 1].putFigure(first.removeFigure(), firsthouse - 1);
        array[firsthouse + 1].putFigure(first.removeFigure(), firsthouse + 1);
        array[firsthouse + 2].putFigure(first.removeFigure(), firsthouse + 2);
        array[firsthouse + 3].putFigure(first.removeFigure(), firsthouse + 3);
        // not every figure is in house
        assertFalse(table.currentPlayerHaswon());

        movement.move(1, firsthouse - 1);
        System.out.println(gamefield);
        // every figure is in house;
        assertTrue(table.currentPlayerHaswon());

        //branch coverage
        GameTable branch = new GameTable(FIVE);
        branch.newRound();
        gamefield = branch.getGameField();
        branch.nextPlayer();
        first = branch.getCurrentPlayer();
        assertFalse(branch.currentPlayerHaswon());
    }

    @Test
    public void testToStrings() {
        array[FIVE].putFigure(first.removeFigure(), FIVE);
        assertNotNull(table.getGameFieldString());
        assertNotNull(table.getPlayerHandString());
        assertNotNull(table.getPlayerString());
    }

    @Test
    public void testPlayCard() {
        int startfield = 0;
        array[startfield].putFigure(first.removeFigure(), startfield);
        assertTrue(table.playCard(2, 2, startfield));
        assertFalse(table.playCard(2, 2, startfield));
    }

    @Test
    public void testIsValidMove() {
        int startfield = 0;
        array[startfield].putFigure(first.removeFigure(), startfield);
        assertTrue(table.isValidMove(2, 2, startfield));
        assertTrue(table.isValidMove(2, 2, startfield));
        //if-Branch Coverage
        assertFalse(table.isValidMove(2, 2, -1));
        assertFalse(table.isValidMove(2, 2, 250000));
    }

}

package de.htwg.se.dog.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.models.impl.Card;
import de.htwg.se.dog.models.impl.GameField;
import de.htwg.se.dog.models.impl.Player;

public class MoveSwitchTest {
    Movement movement;
    GameField gamefield;
    Player tp1, tp2;
    Field[] array;
    private final int JACK = 11;
    private final int PLAYERCOUNT = 2;
    private final int FIELDSTILLHOUSE = 2;
    private final int HOUSECOUNT = 2;
    private final int FIGCOUNT = 2;
    private final int PLAYERID1 = 1;
    private final int PLAYERID2 = 2;
    private final int ZERO = 0;
    private final int ONE = 1;
    private final int THREE = 3;
    private final int FOUR = 4;
    private final int FIVE = 5;
    private final int SIX = 6;
    private final int TP1HOUSEFIELD = 3;
    private final int TP2HOUSEFIELD = 6;

    /* Gamefield from setUp
     * 0 1 2 3 4 5 6 7
     * - - 1 1 - - 2 2
     */
    /* Generate empty gamefield with 2 players */
    @Before
    public void setUp() throws Exception {
        movement = new Movement();
        movement.setMoveStrategie(new Card(JACK));
        gamefield = new GameField(FIELDSTILLHOUSE, PLAYERCOUNT, HOUSECOUNT);
        tp1 = new Player(PLAYERID1, FIGCOUNT);
        tp2 = new Player(PLAYERID2, FIGCOUNT);
        array = gamefield.getGamefield();

    } // Voll Geil

    @Test
    public void testMove() {
        array[ZERO].putFigure(tp1.removeFigure());
        array[FIVE].putFigure(tp2.removeFigure());
        array[TP1HOUSEFIELD].putFigure(tp1.removeFigure());
        array[TP2HOUSEFIELD].putFigure(tp2.removeFigure());
        assertTrue(movement.move(gamefield, FIVE, ZERO));
        assertFalse(movement.move(gamefield, ONE, ZERO));
        assertFalse(movement.move(gamefield, ZERO, ONE));
        assertFalse(movement.move(gamefield, TP1HOUSEFIELD, ONE));
        assertFalse(movement.move(gamefield, ONE, TP1HOUSEFIELD));
        assertFalse(movement.move(gamefield, THREE, SIX));
    }

    @Test
    public void testvalidMove() {
        array[ZERO].putFigure(tp1.removeFigure());
        array[FIVE].putFigure(tp2.removeFigure());
        array[TP1HOUSEFIELD].putFigure(tp1.removeFigure());
        array[TP2HOUSEFIELD].putFigure(tp2.removeFigure());
        assertTrue(movement.validMove(gamefield, FIVE, ZERO));
        assertFalse(movement.validMove(gamefield, ONE, ZERO));
    }

}

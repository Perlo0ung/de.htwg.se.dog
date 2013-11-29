package de.htwg.se.dog.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.models.Field;
import de.htwg.se.dog.models.Figure;
import de.htwg.se.dog.models.GameField;
import de.htwg.se.dog.models.Player;

public class MovementTest {
    Movement movement;
    GameField gamefield;
    Player tp1, tp2;
    Field[] array;
    private final int PLAYERCOUNT = 2;
    private final int FIELDSTILLHOUSE = 2;
    private final int HOUSECOUNT = 2;
    private final int FIGCOUNT = 2;
    private final int PLAYERID1 = 1;
    private final int PLAYERID2 = 2;
    private final int ONE = 1;
    private final int ZERO = 0;

    /* Generate empty gamefield with 2 players */
    @Before
    public void setUp() throws Exception {
        movement = new Movement();
        gamefield = new GameField(FIELDSTILLHOUSE, PLAYERCOUNT, HOUSECOUNT);
        tp1 = new Player(PLAYERID1, FIGCOUNT);
        tp2 = new Player(PLAYERID2, FIGCOUNT);
        array = gamefield.getGamefield();

    }

    @Test
    public void testMoveFigure() {
        //Move Figure from empty Field
        assertFalse(Movement.moveFigure(gamefield, ONE, ZERO));
        array[ZERO].putFigure(tp1.removeFigure());
        Figure tmpZERO = array[ZERO].getFigure();
        array[5].putFigure(tp2.removeFigure());
        // Deprecated Field
        //Move Figure to occupied field
        assertFalse(Movement.moveFigure(gamefield, 5, 0));
        //Is startfield empty
        assertNull(array[0].getFigure());
        //Is figure moved correctly
        assertEquals(tmpZERO.getFignr(), array[5].getFigure().getFignr());
    }

    @Test
    public void testMoveFigurOverBlocked() {
        array[ZERO].putFigure(tp1.removeFigure());
        array[ONE].putFigure(tp1.removeFigure());
        array[ONE].setBlocked(true);
        //Move Figure over blocked field
        assertFalse(Movement.moveFigure(gamefield, 2, ZERO));
    }

    @Test
    public void testMoveOverOwnHouse() {
        array[ZERO].putFigure(tp1.removeFigure());
        assertFalse(Movement.moveFigure(gamefield, 5, 0));
    }

    @Test
    public void testvalidMove() {
        array[ONE].putFigure(tp1.removeFigure());
        array[ONE].setBlocked(true);
        array[ZERO].putFigure(tp1.removeFigure());
        assertFalse(Movement.validMove(gamefield, 4, ZERO));
    }

}

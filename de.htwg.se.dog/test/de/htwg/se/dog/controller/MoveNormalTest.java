package de.htwg.se.dog.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.FigureInterface;
import de.htwg.se.dog.models.impl.Card;
import de.htwg.se.dog.models.impl.GameField;
import de.htwg.se.dog.models.impl.Player;

public class MoveNormalTest {
    Movement movement;
    GameField gamefield;
    Player tp1, tp2;
    FieldInterface[] array;
    private final int PLAYERCOUNT = 2;
    private final int FIELDSTILLHOUSE = 2;
    private final int HOUSECOUNT = 2;
    private final int FIGCOUNT = 2;
    private final int PLAYERID1 = 1;
    private final int PLAYERID2 = 2;
    private final int ONE = 1;
    private final int ZERO = 0;
    private final int TWO = 2;
    private final int FOUR = 4;

    /* Gamefield from setUp
     * 0 1 2 3 4 5 6 7
     * - - 1 1 - - 2 2
     */
    /* Generate empty gamefield with 2 players */
    @Before
    public void setUp() throws Exception {
        movement = new Movement();
        movement.setMoveStrategie(new Card(TWO));
        gamefield = new GameField(FIELDSTILLHOUSE, PLAYERCOUNT, HOUSECOUNT);
        tp1 = new Player(PLAYERID1, FIGCOUNT);
        tp2 = new Player(PLAYERID2, FIGCOUNT);
        array = gamefield.getGamefield();

    }

    @Test
    public void testMoveFigure() {
        //Move Figure from empty Field
        assertFalse(movement.move(gamefield, ONE, ZERO));
        array[ZERO].putFigure(tp1.removeFigure());
        FigureInterface tmpZERO = array[ZERO].getFigure();
        array[4].putFigure(tp2.removeFigure());
        array[4].setBlocked(true);
        //Move Figure to occupied field
        assertFalse(movement.move(gamefield, 4, 0));
        assertFalse(movement.move(gamefield, 5, 0));
        array[4].setBlocked(false);
        movement.move(gamefield, 1, 0);
        //Is startfield empty
        assertNull(array[0].getFigure());

        //Is figure moved correctly
        assertEquals(tmpZERO, array[1].getFigure());
        //move figure over house
        assertTrue(movement.move(gamefield, 3, 1));
        assertEquals(tmpZERO, array[0].getFigure());
    }

    @Test
    public void testKickfigure() {
        array[4].putFigure(tp1.removeFigure());
        FigureInterface tmpZERO = array[4].getFigure();
        array[5].putFigure(tp2.removeFigure());
        assertTrue(movement.move(gamefield, 1, 4));
        assertEquals(tmpZERO, array[5].getFigure());
    }

    @Test
    public void testMoveFigurOverBlocked() {
        array[ZERO].putFigure(tp1.removeFigure());
        array[ONE].putFigure(tp1.removeFigure());
        array[ONE].setBlocked(true);
        //Move Figure over blocked field
        assertFalse(movement.move(gamefield, 2, ZERO));
    }

    @Test
    public void testMoveOverOwnHouse() {
        array[ZERO].putFigure(tp1.removeFigure());
        assertTrue(movement.move(gamefield, 5, 0));
    }

    @Test
    public void testvalidMove() {
        array[ONE].putFigure(tp1.removeFigure());
        array[ONE].setBlocked(true);
        array[ZERO].putFigure(tp1.removeFigure());
        assertFalse(movement.validMove(gamefield, 4, ZERO));
    }

    @Test
    public void testMoveFour() {
        movement.setMoveStrategie(new Card(FOUR));
        array[FOUR].putFigure(tp1.removeFigure());
        FigureInterface tempFig = array[FOUR].getFigure();
        assertTrue(movement.move(gamefield, -4, FOUR));
        assertEquals(tempFig, array[4].getFigure());
    }

}

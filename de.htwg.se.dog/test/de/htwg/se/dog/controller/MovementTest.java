package de.htwg.se.dog.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.controller.impl.Movement;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.impl.GameField;
import de.htwg.se.dog.models.impl.Player;

public class MovementTest {

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
    private final int TWO = 2;

    /* Gamefield from setUp
     * 0 1 2 3 4 5 6 7
     * - - 1 1 - - 2 2
     */
    /* Generate empty gamefield with 2 players */
    @Before
    public void setUp() throws Exception {
        movement = new Movement();
        movement.setMoveStrategie(TWO);
        gamefield = new GameField(FIELDSTILLHOUSE, PLAYERCOUNT, HOUSECOUNT);
        tp1 = new Player(PLAYERID1, FIGCOUNT);
        tp2 = new Player(PLAYERID2, FIGCOUNT);
        array = gamefield.getField();

    }

    @Test
    public void testMoveStart() {
        //MoveStart was successful
        assertTrue(movement.moveStart(tp1));
        //Startfield is blocked
        assertFalse(movement.moveStart(tp1));
        //Startfield is blocked and no figures left
        array[4].putFigure(tp1.removeFigure());
        assertFalse(movement.moveStart(tp1));
        //Player has no figures left
        movement.move(1, 0);
        assertFalse(movement.moveStart(tp1));
    }

}

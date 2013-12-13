package de.htwg.se.dog.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.controller.impl.Movement;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.FigureInterface;
import de.htwg.se.dog.models.impl.GameField;
import de.htwg.se.dog.models.impl.Player;

public class MoveSevenTest {
    Movement movement;
    GameField gamefield;
    Player tp1, tp2;
    FieldInterface[] array;
    private final int PLAYERCOUNT = 2;
    private final int FIELDSTILLHOUSE = 4;
    private final int HOUSECOUNT = 2;
    private final int FIGCOUNT = 2;
    private final int PLAYERID1 = 1;
    private final int PLAYERID2 = 2;
    private final int SEVEN = 7;

    /* Gamefield from setUp
     * 0 1 2 3 4 5 6 7 8 9 10 11
     * - - - - 1 1 - - - - 2  2
     */
    /* Generate empty gamefield with 2 players */
    @Before
    public void setUp() throws Exception {

        gamefield = new GameField(FIELDSTILLHOUSE, PLAYERCOUNT, HOUSECOUNT);
        movement = new Movement(gamefield);
        movement.setMoveStrategie(SEVEN);
        tp1 = new Player(PLAYERID1, FIGCOUNT);
        tp2 = new Player(PLAYERID2, FIGCOUNT);
        array = gamefield.getField();

    }

    @Test
    public void testMoveFigure() {
        //Move Figure from empty Field
        array[2].putFigure(tp1.removeFigure());
        FigureInterface tempFig = array[2].getFigure();
        Map<Integer, Integer> moves = new HashMap<Integer, Integer>();
        moves.put(2, 2);
        movement.move(moves);
        //Startfield is empty
        assertNull(array[2].getFigure());
        //Figure on targetfield == Figure which moved
        assertEquals(tempFig, array[4].getFigure());

        //Move Figure over blocked field-------------------------
        array[3].putFigure(tp1.removeFigure());
        moves = new HashMap<Integer, Integer>();
        moves.put(1, 2);
        //Move over Blocked field is not possible
        assertFalse(movement.move(moves));
    }

    //TODO test überdenken
    @Test
    public void testAnyValidMove() throws CloneNotSupportedException {
        //Search AnyValidMove of Player without Figures on Field        
        assertFalse(movement.AnyValidMove(tp2));
        //One Figure moves 7
        array[0].putFigure(tp1.removeFigure(), 0);
        assertTrue(movement.AnyValidMove(tp1));
        //two figures move
        array[7].putFigure(tp1.removeFigure(), 7);
        array[6].putFigure(tp2.removeFigure(), 6);
        array[8].putFigure(tp2.removeFigure(),8);
        array[6].setBlocked(true);
        assertTrue(movement.AnyValidMove(tp1));
        //No Move possible    
        array[8].setBlocked(true);      
        System.out.println(gamefield.toString());
        System.err.println(tp1.getFigureRegister());
        System.err.println(tp2.getFigureRegister());
        assertFalse(movement.AnyValidMove(tp1));
        System.out.println(gamefield.toString());
        System.err.println(tp1.getFigureRegister());
        System.err.println(tp2.getFigureRegister());
    }
}
/* Gamefield from setUp
 * 0 1 2 3 4 5 6 7 8 9 10 11
 * - - - - 1 1 - - - - 2  2
 * 1          !2 1  
 */
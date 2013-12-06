package de.htwg.se.dog.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.models.impl.Field;
import de.htwg.se.dog.models.impl.Figure;
import de.htwg.se.dog.models.impl.Player;

public class FieldTest {
    private final int NOOWNER = 0;
    private final int OWNER = 1;
    private final int FIGNUM = 1;
    public Field feld1;
    public Field feld2;
    Figure f = new Figure(new Player(OWNER, 0), FIGNUM);

    @Before
    public void setUp() {
        feld1 = new Field(NOOWNER);
        feld2 = new Field(OWNER);
        feld2.putFigure(f);
        feld2.setBlocked(true);
        feld2.getBlocked();
    }

    @Test
    public void testSetFigure() {
        assertEquals(f, feld2.getFigure());
    }

    @Test
    public void testGetFigure() {
        assertEquals(f, feld2.getFigure());
    }

    @Test
    public void testGetOwner() {
        assertEquals(OWNER, feld2.getOwner());
    }

    @Test
    public void testGetFigureOwnerNr() {
        assertEquals(1, feld2.getFigureOwnerNr());
    }

    @Test
    public void setBlocked() {
        assertEquals(feld2.getBlocked(), true);
    }

    @Test
    public void testIsHouse() {
        assertEquals(true, feld2.isHouse());
    }

    @Test
    public void testremoveFigure() {
        Figure tmp = feld2.removeFigure();
        assertEquals(f, tmp);
        assertNull(feld2.getFigure());
    }

}

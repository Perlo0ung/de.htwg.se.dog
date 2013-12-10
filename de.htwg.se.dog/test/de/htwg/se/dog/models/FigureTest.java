package de.htwg.se.dog.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.htwg.se.dog.models.impl.Figure;
import de.htwg.se.dog.models.impl.Player;

public class FigureTest {
    Figure f;
    Figure fwrong;
    int playernr = 2;
    int fignr = 4;
    int wrongPlayernr = -1;
    int wrongFignr = 34;
    Player player = new Player(playernr, 0);
    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Before
    public void setUp() {
        f = new Figure(player, fignr);
    }

    @Test
    public void testGetOwner() {
        assertEquals(player, f.getOwner());
    }

    @Test
    public void testGetFignr() {
        assertEquals(fignr, f.getFignr());
    }

}

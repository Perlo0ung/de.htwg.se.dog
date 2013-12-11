package de.htwg.se.dog.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.htwg.se.dog.models.impl.GameField;

public class GamefiledTest {
    GameField f;
    GameField temp;
    int playerCount = 3;
    int fieldsTillHouse = 2;
    int houseCount = 3;
    int owner = 1;
    int noowner = 0;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Before
    public void setUp() {
        f = new GameField(fieldsTillHouse, playerCount, houseCount);
        temp = new GameField(fieldsTillHouse, playerCount, houseCount);
    }

    @Test
    public void testGenerateGameField() {
        assertEquals(noowner, f.getOwner(fieldsTillHouse + houseCount));
        assertEquals(owner, f.getOwner(fieldsTillHouse));
    }

    @Test
    public void testGetFieldSize() {
        assertEquals(((fieldsTillHouse + houseCount) * playerCount), f.getFieldSize());
    }

    @Test
    public void testGetGameField() {
        assertNotNull(f.getGamefield());
    }

    @Test
    public void testGetHouseCount() {
        assertEquals(houseCount, f.getHouseCount());
    }

    @Test
    public void testGetFieldsTillHouse() {
        assertEquals(fieldsTillHouse, f.getFieldsTillHouse());
    }
}

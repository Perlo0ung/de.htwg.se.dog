package de.htwg.se.dog.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.models.impl.GameField;

public class GamefieldTest {
    GameField f;
    GameField temp;
    int playerCount = 3;
    int fieldsTillHouse = 2;
    int houseCount = 3;
    int owner = 2;
    int noowner = 0;
    int field = 50;

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
        assertNotNull(f.getGameArray());
    }

    @Test
    public void testGetHouseCount() {
        assertEquals(houseCount, f.getHouseCount());
    }

    @Test
    public void testGetFieldsTillHouse() {
        assertEquals(fieldsTillHouse, f.getFieldsTillHouse());
    }

    @Test
    public void testGetPlayerCount() {
        assertEquals(3, f.getPlayerCount());
    }

    @Test
    public void testGetField() {
        assertNotNull(f.getFieldForNum(3));
    }
    @Test
    public void getFieldforNum() {
    	assertNull(f.getFieldForNum(field));
    }
}

package de.htwg.se.dog.controller;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.models.Player;
import static org.junit.Assert.*;

public class GameTableTest {
    GameTable table; 
    Player first;
    @Before
    public void setUp() {
        table = new GameTable(2, 1);
        table.newRound();
    }
    
    @Test
    public void testCurrentPlayer() {
        first = table.getCurrentPlayer();
        table.newRound();
        assertNotEquals(first, table.getCurrentPlayer());
    }
    @Test
    public void testNewRound() {
        table.newRound();
    }
    
    @Test
    public void testcanPlay() {
        assertTrue(table.canPlay(first));
    }
    
}
